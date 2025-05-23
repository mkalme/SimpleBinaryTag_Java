package simplebinarytag.deserializer;

import simplebinarytag.io.IReader;
import simplebinarytag.TagID;
import simplebinarytag.ValueTag;

public class ValueTagDeserializer implements IIdTagDeserializer<ValueTag> {
    @Override
    public ValueTag deserialize(IReader reader, TagID id) {
        switch (id){
            case BYTE: return new ValueTag(reader.readByte());
            case INT16: return new ValueTag(reader.readInt16());
            case INT32: return new ValueTag(reader.readInt32());
            case INT64: return new ValueTag(reader.readInt64());
            case SINGLE: return new ValueTag(reader.readSingle());
            case DOUBLE: return new ValueTag(reader.readDouble());
            case BOOLEAN: return new ValueTag(reader.readBoolean());
            case CHAR: return new ValueTag(reader.readChar());
            case STRING: return new ValueTag(reader.readString());
            case DATETIME: return new ValueTag(reader.readDateTime());
            default: return null;
        }
    }
}
