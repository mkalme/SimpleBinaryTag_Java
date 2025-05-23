package simplebinarytag.deserializer;

import simplebinarytag.io.IReader;
import simplebinarytag.ArrayTag;
import simplebinarytag.ObjectTag;
import simplebinarytag.TagID;

public class ArrayTagDeserializer implements IIdTagDeserializer<ArrayTag> {
    private ITagDeserializer<ObjectTag> objectTagDeserializer;
    public ITagDeserializer<ObjectTag> getObjectTagDeserializer(){
        return this.objectTagDeserializer;
    }
    public void setObjectTagDeserializer(ITagDeserializer<ObjectTag> objectTagDeserializer) {
        this.objectTagDeserializer = objectTagDeserializer;
    }

    public ArrayTagDeserializer(ITagDeserializer<ObjectTag> objectTagDeserializer){
        this.objectTagDeserializer = objectTagDeserializer;
    }

    @Override
    public ArrayTag deserialize(IReader reader, TagID id) {
        int length = reader.readInt32();

        switch (id){
            case BYTE_ARRAY: return new ArrayTag(reader.readByteArray(length));
            case INT16_ARRAY: return new ArrayTag(reader.readInt16Array(length));
            case INT32_ARRAY: return new ArrayTag(reader.readInt32Array(length));
            case INT64_ARRAY: return new ArrayTag(reader.readInt64Array(length));
            case SINGLE_ARRAY: return new ArrayTag(reader.readSingleArray(length));
            case DOUBLE_ARRAY: return new ArrayTag(reader.readDoubleArray(length));
            case BOOLEAN_ARRAY: return new ArrayTag(reader.readBooleanArray(length));
            case CHAR_ARRAY: return new ArrayTag(reader.readCharArray(length));
            case STRING_ARRAY: return new ArrayTag(reader.readStringArray(length));
            case DATETIME_ARRAY: return new ArrayTag(reader.readDateTimeArray(length));
            case OBJECT_ARRAY:
                ObjectTag[] objectArray = new ObjectTag[length];

                for(int i = 0; i < length; i++){
                    objectArray[i] = objectTagDeserializer.deserialize(reader);
                }

                return new ArrayTag(objectArray);
            default: return null;
        }
    }
}
