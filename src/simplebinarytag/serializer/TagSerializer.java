package simplebinarytag.serializer;

import simplebinarytag.io.IWriter;
import simplebinarytag.ArrayTag;
import simplebinarytag.ObjectTag;
import simplebinarytag.Tag;
import simplebinarytag.ValueTag;

public class TagSerializer implements ITagSerializer<Tag> {
    private ITagSerializer<ValueTag> valueTagSerializer;
    public ITagSerializer<ValueTag> getValueTagSerializer(){
        return this.valueTagSerializer;
    }
    public void setValueTagSerializer(ITagSerializer<ValueTag> valueTagSerializer){
        this.valueTagSerializer = valueTagSerializer;
    }

    private ITagSerializer<ArrayTag> arrayTagTagSerializer;
    public ITagSerializer<ArrayTag> getArrayTagTagSerializer(){
        return this.arrayTagTagSerializer;
    }
    public void setArrayTagTagSerializer(ITagSerializer<ArrayTag> arrayTagTagSerializer){
        this.arrayTagTagSerializer = arrayTagTagSerializer;
    }

    private ITagSerializer<ObjectTag> objectTagTagSerializer;
    public ITagSerializer<ObjectTag> getObjectTagTagSerializer(){
        return this.objectTagTagSerializer;
    }
    public void setObjectTagTagSerializer(ITagSerializer<ObjectTag> objectTagTagSerializer){
        this.objectTagTagSerializer = objectTagTagSerializer;
    }

    public TagSerializer(){
        this.valueTagSerializer = new ValueTagSerializer();
        this.objectTagTagSerializer = new ObjectTagSerializer(this);
        this.arrayTagTagSerializer = new ArrayTagSerializer(this.objectTagTagSerializer);
    }

    @Override
    public void serialize(Tag tag, IWriter writer) {
        writer.writeByte(tag.getID().getByte());

        switch (tag.getID()){
            case BYTE:
            case INT16:
            case INT32:
            case INT64:
            case SINGLE:
            case DOUBLE:
            case BOOLEAN:
            case CHAR:
            case STRING:
            case DATETIME:
                valueTagSerializer.serialize((ValueTag) tag, writer);
                break;
            case BYTE_ARRAY:
            case INT16_ARRAY:
            case INT32_ARRAY:
            case INT64_ARRAY:
            case SINGLE_ARRAY:
            case DOUBLE_ARRAY:
            case BOOLEAN_ARRAY:
            case CHAR_ARRAY:
            case STRING_ARRAY:
            case DATETIME_ARRAY:
            case OBJECT_ARRAY:
                arrayTagTagSerializer.serialize((ArrayTag) tag, writer);
                break;
            case OBJECT:
                objectTagTagSerializer.serialize((ObjectTag) tag, writer);
                break;
        }
    }
}
