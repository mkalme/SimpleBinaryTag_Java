package simplebinarytag.deserializer;

import simplebinarytag.*;
import simplebinarytag.io.IReader;

public class TagDeserializer implements ITagDeserializer<Tag> {
    private IIdTagDeserializer<ValueTag> valueTagDeserializer;
    public IIdTagDeserializer<ValueTag> getValueTagDeserializer(){
        return this.valueTagDeserializer;
    }
    public void setValueTagDeserializer(IIdTagDeserializer<ValueTag> valueTagDeserializer) {
        this.valueTagDeserializer = valueTagDeserializer;
    }

    private IIdTagDeserializer<ArrayTag> arrayTagDeserializer;
    public IIdTagDeserializer<ArrayTag> getArrayTagDeserializer(){
        return this.arrayTagDeserializer;
    }
    public void setArrayTagDeserializer(IIdTagDeserializer<ArrayTag> arrayTagDeserializer) {
        this.arrayTagDeserializer = arrayTagDeserializer;
    }

    private ITagDeserializer<ObjectTag> objectTagDeserializer;
    public ITagDeserializer<ObjectTag> getObjectTagDeserializer(){
        return this.objectTagDeserializer;
    }
    public void setObjectTagDeserializer(ITagDeserializer<ObjectTag> objectTagDeserializer) {
        this.objectTagDeserializer = objectTagDeserializer;
    }

    public TagDeserializer(){
        this.valueTagDeserializer = new ValueTagDeserializer();
        this.objectTagDeserializer = new ObjectTagDeserializer(this);
        this.arrayTagDeserializer = new ArrayTagDeserializer(this.objectTagDeserializer);
    }

    @Override
    public Tag deserialize(IReader reader) {
        TagID id = TagID.fromByte(reader.readByte());

        switch (id){
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
                return valueTagDeserializer.deserialize(reader, id);
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
                return arrayTagDeserializer.deserialize(reader, id);
            case OBJECT:
                return objectTagDeserializer.deserialize(reader);
            default: return null;
        }
    }
}
