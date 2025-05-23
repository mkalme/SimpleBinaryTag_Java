package simplebinarytag.serializer;

import simplebinarytag.io.IWriter;
import simplebinarytag.ObjectTag;
import simplebinarytag.Tag;

public class ObjectTagSerializer implements ITagSerializer<ObjectTag> {
    private ITagSerializer<Tag> tagSerializer;
    public ITagSerializer<Tag> getTagSerializer(){
        return this.tagSerializer;
    }
    public void setTagSerializer(ITagSerializer<Tag> tagSerializer) {
        this.tagSerializer = tagSerializer;
    }

    public ObjectTagSerializer(ITagSerializer<Tag> tagSerializer){
        this.tagSerializer = tagSerializer;
    }

    @Override
    public void serialize(ObjectTag tag, IWriter writer) {
        writer.writeInt32(tag.size());

        tag.forEach((name, childTag) -> {
            writer.writeString(name);
            tagSerializer.serialize(childTag, writer);
        });
    }
}
