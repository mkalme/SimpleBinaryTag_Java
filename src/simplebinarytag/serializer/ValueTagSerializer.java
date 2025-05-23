package simplebinarytag.serializer;

import simplebinarytag.io.IWriter;
import simplebinarytag.ValueTag;

public class ValueTagSerializer implements ITagSerializer<ValueTag> {

    @Override
    public void serialize(ValueTag tag, IWriter writer) {
        switch (tag.getID()){
            case BYTE: writer.writeByte(tag.getByte()); break;
            case INT16: writer.writeInt16(tag.getInt16()); break;
            case INT32: writer.writeInt32(tag.getInt32()); break;
            case INT64: writer.writeInt64(tag.getInt64()); break;
            case SINGLE: writer.writeSingle(tag.getSingle()); break;
            case DOUBLE: writer.writeDouble(tag.getDouble()); break;
            case BOOLEAN: writer.writeBoolean(tag.getBoolean()); break;
            case CHAR: writer.writeChar(tag.getChar()); break;
            case STRING: writer.writeString(tag.getString()); break;
            case DATETIME: writer.writeDateTime(tag.getDateTime()); break;
        }
    }
}
