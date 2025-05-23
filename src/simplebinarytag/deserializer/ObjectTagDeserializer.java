package simplebinarytag.deserializer;

import simplebinarytag.io.IReader;
import simplebinarytag.ObjectTag;
import simplebinarytag.Tag;

public class ObjectTagDeserializer implements ITagDeserializer<ObjectTag> {
    private ITagDeserializer<Tag> tagDeserializer;
    public ITagDeserializer<Tag> getTagDeserializer(){
        return this.tagDeserializer;
    }
    public void setTagDeserializer(ITagDeserializer<Tag> tagDeserializer) {
        this.tagDeserializer = tagDeserializer;
    }

    public ObjectTagDeserializer(ITagDeserializer<Tag> tagDeserializer){
        this.tagDeserializer = tagDeserializer;
    }

    @Override
    public ObjectTag deserialize(IReader reader) {
        int length = reader.readInt32();

        ObjectTag output = new ObjectTag(length);
        for (int i = 0; i < length; i++){
            String name = reader.readString();
            Tag tag = tagDeserializer.deserialize(reader);

            output.put(name, tag);
        }

        return output;
    }
}
