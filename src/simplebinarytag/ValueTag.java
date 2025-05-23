package simplebinarytag;

import java.time.LocalDateTime;

public final class ValueTag extends Tag {
    private Object internalValue;
    public Object getInternalValue(){
        return this.internalValue;
    }

    private ValueTag(TagID tagID, Object internalValue){
        super(tagID);
        this.internalValue = internalValue;
    }

    public ValueTag(byte value){
        this(TagID.BYTE, value);
    }
    public ValueTag(short value){
        this(TagID.INT16, value);
    }
    public ValueTag(int value){
        this(TagID.INT32, value);
    }
    public ValueTag(long value){
        this(TagID.INT64, value);
    }
    public ValueTag(float value){
        this(TagID.SINGLE, value);
    }
    public ValueTag(double value){
        this(TagID.DOUBLE, value);
    }
    public ValueTag(boolean value){
        this(TagID.BOOLEAN, value);
    }
    public ValueTag(char value){
        this(TagID.CHAR, value);
    }
    public ValueTag(String value){
        this(TagID.STRING, value);
    }
    public ValueTag(LocalDateTime value){
        this(TagID.DATETIME, value);
    }

    public byte getByte(){
        return (byte)internalValue;
    }
    public short getInt16(){
        return (short)internalValue;
    }
    public int getInt32(){
        return (int)internalValue;
    }
    public long getInt64(){
        return (long)internalValue;
    }
    public float getSingle(){
        return (float)internalValue;
    }
    public double getDouble(){
        return (double)internalValue;
    }
    public boolean getBoolean(){
        return (boolean)internalValue;
    }
    public char getChar(){
        return (char)internalValue;
    }
    public String getString(){
        return (String)internalValue;
    }
    public LocalDateTime getDateTime(){
        return (LocalDateTime)internalValue;
    }

    @Override
    public Tag clone() {
        switch (getID()) {
            case BYTE: return new ValueTag(getByte());
            case INT16: return new ValueTag(getInt16());
            case INT32: return new ValueTag(getInt32());
            case INT64: return new ValueTag(getInt64());
            case SINGLE: return new ValueTag(getSingle());
            case DOUBLE: return new ValueTag(getDouble());
            case BOOLEAN: return new ValueTag(getBoolean());
            case CHAR: return new ValueTag(getChar());
            case STRING: return new ValueTag(getString());
            case DATETIME:  return new ValueTag(getDateTime());
            default: return null;
        }
    }

    @Override
    public boolean equals(Object other) {
        if(other.getClass() != getClass()) return false;
        if(getID() != ((Tag)other).getID()) return false;

        switch (getID()){
            case STRING:
                return getString().equals(((ValueTag)other).internalValue);
            case DATETIME:
                return getDateTime().isEqual((LocalDateTime)((ValueTag)other).internalValue);
            default:
                return internalValue.equals(((ValueTag)other).internalValue);
        }
    }
}
