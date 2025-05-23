package simplebinarytag;

import java.time.LocalDateTime;
import java.util.Arrays;

public final class ArrayTag extends Tag {
    private Object internalArrayObj;
    public Object getInternalArrayObject(){
        return this.internalArrayObj;
    }

    private int length;
    public int getLength(){
        return this.length;
    }

    private ArrayTag(TagID tagID, Object arrayObj, int length) {
        super(tagID);

        this.internalArrayObj = arrayObj;
        this.length = length;
    }

    public ArrayTag(byte... array){
        this(TagID.BYTE_ARRAY, array, array.length);
    }
    public ArrayTag(short... array){
        this(TagID.INT16_ARRAY, array, array.length);
    }
    public ArrayTag(int... array){
        this(TagID.INT32_ARRAY, array, array.length);
    }
    public ArrayTag(long... array){
        this(TagID.INT64_ARRAY, array, array.length);
    }
    public ArrayTag(float... array){
        this(TagID.SINGLE_ARRAY, array, array.length);
    }
    public ArrayTag(double... array){
        this(TagID.DOUBLE_ARRAY, array, array.length);
    }
    public ArrayTag(boolean... array){
        this(TagID.BOOLEAN_ARRAY, array, array.length);
    }
    public ArrayTag(char... array){
        this(TagID.CHAR_ARRAY, array, array.length);
    }
    public ArrayTag(String... array){
        this(TagID.STRING_ARRAY, array, array.length);
    }
    public ArrayTag(LocalDateTime... array){
        this(TagID.DATETIME_ARRAY, array, array.length);
    }
    public ArrayTag(ObjectTag... array){
        this(TagID.OBJECT_ARRAY, array, array.length);
    }

    public byte[] getByteArray(){
        return (byte[])internalArrayObj;
    }
    public short[] getInt16Array(){
        return (short[])internalArrayObj;
    }
    public int[] getInt32Array(){
        return (int[])internalArrayObj;
    }
    public long[] getInt64Array(){
        return (long[])internalArrayObj;
    }
    public float[] getSingleArray(){
        return (float[])internalArrayObj;
    }
    public double[] getDoubleArray(){
        return (double[])internalArrayObj;
    }
    public boolean[] getBooleanArray(){
        return (boolean[])internalArrayObj;
    }
    public char[] getCharArray(){
        return (char[])internalArrayObj;
    }
    public String[] getStringArray(){
        return (String[])internalArrayObj;
    }
    public LocalDateTime[] getDateTimeArray(){
        return (LocalDateTime[])internalArrayObj;
    }
    public ObjectTag[] getObjectArray(){
        return (ObjectTag[])internalArrayObj;
    }

    @Override
    public Tag clone() {
        switch (getID()){
            case BYTE_ARRAY: return new ArrayTag(getByteArray().clone());
            case INT16_ARRAY: return new ArrayTag(getInt16Array().clone());
            case INT32_ARRAY: return new ArrayTag(getInt32Array().clone());
            case INT64_ARRAY: return new ArrayTag(getInt64Array().clone());
            case SINGLE_ARRAY: return new ArrayTag(getSingleArray().clone());
            case DOUBLE_ARRAY: return new ArrayTag(getDoubleArray().clone());
            case BOOLEAN_ARRAY: return new ArrayTag(getBooleanArray().clone());
            case CHAR_ARRAY: return new ArrayTag(getCharArray().clone());
            case STRING_ARRAY: return new ArrayTag(getStringArray().clone());
            case DATETIME_ARRAY: return new ArrayTag(getDateTimeArray().clone());
            case OBJECT_ARRAY:
                ObjectTag[] array = getObjectArray();
                ObjectTag[] objArray = new ObjectTag[length];
                for(int i = 0; i < length; i++){
                    objArray[i] = (ObjectTag) array[i].clone();
                }

                return new ArrayTag(objArray);
            default: return null;
        }
    }

    @Override
    public boolean equals(Object other) {
        if(other.getClass() != getClass()) return false;
        if(getID() != ((Tag)other).getID()) return false;

        ArrayTag otherArray = (ArrayTag) other;
        if(length != otherArray.length) return false;

        switch (getID()){
            case BYTE_ARRAY: return Arrays.equals((byte[])internalArrayObj, (byte[])otherArray.internalArrayObj);
            case INT16_ARRAY: return Arrays.equals((short[])internalArrayObj, (short[])otherArray.internalArrayObj);
            case INT32_ARRAY: return Arrays.equals((int[])internalArrayObj, (int[])otherArray.internalArrayObj);
            case INT64_ARRAY: return Arrays.equals((long[])internalArrayObj, (long[])otherArray.internalArrayObj);
            case SINGLE_ARRAY: return Arrays.equals((float[])internalArrayObj, (float[])otherArray.internalArrayObj);
            case DOUBLE_ARRAY: return Arrays.equals((double[])internalArrayObj, (double[])otherArray.internalArrayObj);
            case BOOLEAN_ARRAY: return Arrays.equals((boolean[])internalArrayObj, (boolean[])otherArray.internalArrayObj);
            case CHAR_ARRAY: return Arrays.equals((char[])internalArrayObj, (char[])otherArray.internalArrayObj);
            case STRING_ARRAY:
                String[] leftString = (String[]) internalArrayObj, rightString = (String[]) otherArray.internalArrayObj;
                for(int i = 0; i < leftString.length; i++){
                    if(!leftString[i].equals(rightString[i])) return false;
                }
                break;
            case DATETIME_ARRAY:
                LocalDateTime[] leftDateTime = (LocalDateTime[]) internalArrayObj, rightDateTime = (LocalDateTime[]) otherArray.internalArrayObj;
                for(int i = 0; i < leftDateTime.length; i++){
                    if(!leftDateTime[i].isEqual(rightDateTime[i])) return false;
                }
                break;
            case OBJECT_ARRAY:
                ObjectTag[] leftObject = (ObjectTag[]) internalArrayObj, rightObject = (ObjectTag[]) otherArray.internalArrayObj;
                for(int i = 0; i < rightObject.length; i++){
                    if(!leftObject[i].equals(rightObject[i])) return false;
                }
                break;
        }

        return true;
    }
}
