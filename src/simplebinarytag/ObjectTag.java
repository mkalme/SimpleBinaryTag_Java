package simplebinarytag;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class ObjectTag extends Tag implements Map<String, Tag> {
    private Map<String, Tag> map;

    public ObjectTag() {
        super(TagID.OBJECT);
        this.map = new HashMap<>();
    }
    public ObjectTag(int length) {
        super(TagID.OBJECT);
        this.map = new HashMap<>(length);
    }
    public ObjectTag(Map<String, Tag> map, boolean useThisReference) {
        super(TagID.OBJECT);

        if(useThisReference) this.map = map;
        else this.map = new HashMap<>(map);
    }

    public byte getByte(String name){
        return (byte)((ValueTag)get(name)).getInternalValue();
    }
    public byte[] getByteArray(String name){
        return (byte[])((ArrayTag)get(name)).getInternalArrayObject();
    }
    public short getInt16(String name){
        return (short)((ValueTag)get(name)).getInternalValue();
    }
    public short[] getInt16Array(String name){
        return (short[])((ArrayTag)get(name)).getInternalArrayObject();
    }
    public int getInt32(String name){
        return (int)((ValueTag)get(name)).getInternalValue();
    }
    public int[] getInt32Array(String name){
        return (int[])((ArrayTag)get(name)).getInternalArrayObject();
    }
    public long getInt64(String name){
        return (long)((ValueTag)get(name)).getInternalValue();
    }
    public long[] getInt64Array(String name){
        return (long[])((ArrayTag)get(name)).getInternalArrayObject();
    }
    public float getSingle(String name){
        return (float)((ValueTag)get(name)).getInternalValue();
    }
    public float[] getSingleArray(String name){
        return (float[])((ArrayTag)get(name)).getInternalArrayObject();
    }
    public double getDouble(String name){
        return (double)((ValueTag)get(name)).getInternalValue();
    }
    public double[] getDoubleArray(String name){
        return (double[])((ArrayTag)get(name)).getInternalArrayObject();
    }
    public boolean getBoolean(String name){
        return (boolean)((ValueTag)get(name)).getInternalValue();
    }
    public boolean[] getBooleanArray(String name){
        return (boolean[])((ArrayTag)get(name)).getInternalArrayObject();
    }
    public char getChar(String name){
        return (char)((ValueTag)get(name)).getInternalValue();
    }
    public char[] getCharArray(String name){
        return (char[])((ArrayTag)get(name)).getInternalArrayObject();
    }
    public String getString(String name){
        return (String)((ValueTag)get(name)).getInternalValue();
    }
    public String[] getStringArray(String name){
        return (String[])((ArrayTag)get(name)).getInternalArrayObject();
    }
    public LocalDateTime getDateTime(String name){
        return (LocalDateTime)((ValueTag)get(name)).getInternalValue();
    }
    public LocalDateTime[] getDateTimeArray(String name){ return (LocalDateTime[])((ArrayTag)get(name)).getInternalArrayObject();}
    public ObjectTag getObject(String name){
        return (ObjectTag)((ValueTag)get(name)).getInternalValue();
    }
    public ObjectTag[] getObjectArray(String name){
        return (ObjectTag[])((ArrayTag)get(name)).getInternalArrayObject();
    }

    public void putByte(String name, byte value){
        map.put(name, new ValueTag(value));
    }
    public void putByteArray(String name, byte... value){
        map.put(name, new ArrayTag(value));
    }
    public void putInt16(String name, short value){
        map.put(name, new ValueTag(value));
    }
    public void putInt16Array(String name, short... value){
        map.put(name, new ArrayTag(value));
    }
    public void putInt32(String name, int value){
        map.put(name, new ValueTag(value));
    }
    public void putInt32Array(String name, int... value){
        map.put(name, new ArrayTag(value));
    }
    public void putInt64(String name, long value){
        map.put(name, new ValueTag(value));
    }
    public void putInt64Array(String name, long... value){
        map.put(name, new ArrayTag(value));
    }
    public void putSingle(String name, float value){
        map.put(name, new ValueTag(value));
    }
    public void putSingleArray(String name, float... value){
        map.put(name, new ArrayTag(value));
    }
    public void putDouble(String name, double value){
        map.put(name, new ValueTag(value));
    }
    public void putDoubleArray(String name, double... value){
        map.put(name, new ArrayTag(value));
    }
    public void putBoolean(String name, boolean value){
        map.put(name, new ValueTag(value));
    }
    public void putBooleanArray(String name, boolean... value){
        map.put(name, new ArrayTag(value));
    }
    public void putChar(String name, char value){
        map.put(name, new ValueTag(value));
    }
    public void putCharArray(String name, char... value){
        map.put(name, new ArrayTag(value));
    }
    public void putString(String name, String value){
        map.put(name, new ValueTag(value));
    }
    public void putStringArray(String name, String... value){
        map.put(name, new ArrayTag(value));
    }
    public void putDateTime(String name, LocalDateTime value){
        map.put(name, new ValueTag(value));
    }
    public void putDateTimeArray(String name, LocalDateTime... value){
        map.put(name, new ArrayTag(value));
    }
    public void putObject(String name, ObjectTag value){
        map.put(name, value);
    }
    public void putObjectArray(String name, ObjectTag... value){
        map.put(name, new ArrayTag(value));
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public Tag get(Object key) {
        return map.get(key);
    }

    @Override
    public Tag put(String key, Tag value) {
        return map.put(key, value);
    }

    @Override
    public Tag remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends Tag> m) {
        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<Tag> values() {
        return map.values();
    }

    @Override
    public Set<Entry<String, Tag>> entrySet() {
        return map.entrySet();
    }

    @Override
    public Tag clone() {
        ObjectTag output = new ObjectTag(size());

        map.forEach((name, childTag) -> output.put(name, childTag.clone()));

        return output;
    }

    @Override
    public boolean equals(Object other) {
        return map.equals(other);
    }
}
