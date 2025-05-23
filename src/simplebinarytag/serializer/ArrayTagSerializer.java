package simplebinarytag.serializer;

import simplebinarytag.io.IWriter;
import simplebinarytag.ArrayTag;
import simplebinarytag.ObjectTag;

public class ArrayTagSerializer implements ITagSerializer<ArrayTag> {
    private ITagSerializer<ObjectTag> objectTagTagSerializer;
    public ITagSerializer<ObjectTag> getObjectTagTagSerializer(){
        return this.objectTagTagSerializer;
    }
    public void setObjectTagTagSerializer(ITagSerializer<ObjectTag> tagSerializer) {
        this.objectTagTagSerializer = tagSerializer;
    }

    public ArrayTagSerializer(ITagSerializer<ObjectTag> tagSerializer){
        this.objectTagTagSerializer = tagSerializer;
    }

    @Override
    public void serialize(ArrayTag tag, IWriter writer) {
        writer.writeInt32(tag.getLength());

        switch (tag.getID()){
            case BYTE_ARRAY: writer.writeByteArray(tag.getByteArray()); break;
            case INT16_ARRAY: writer.writeInt16Array(tag.getInt16Array()); break;
            case INT32_ARRAY: writer.writeInt32Array(tag.getInt32Array()); break;
            case INT64_ARRAY: writer.writeInt64Array(tag.getInt64Array()); break;
            case SINGLE_ARRAY: writer.writeSingleArray(tag.getSingleArray()); break;
            case DOUBLE_ARRAY: writer.writeDoubleArray(tag.getDoubleArray()); break;
            case BOOLEAN_ARRAY: writer.writeBooleanArray(tag.getBooleanArray()); break;
            case CHAR_ARRAY: writer.writeCharArray(tag.getCharArray()); break;
            case STRING_ARRAY: writer.writeStringArray(tag.getStringArray()); break;
            case DATETIME_ARRAY: writer.writeDateTimeArray(tag.getDateTimeArray()); break;
            case OBJECT_ARRAY:
                ObjectTag[] objectArray = tag.getObjectArray();

                for(int i = 0; i < objectArray.length; i++){
                    objectTagTagSerializer.serialize(objectArray[i], writer);
                }
                break;
        }
    }
}
