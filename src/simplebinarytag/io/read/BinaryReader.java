package simplebinarytag.io.read;

import simplebinarytag.io.IReader;
import simplebinarytag.io.read.buffer.BufferProvider;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;

public class BinaryReader implements IReader {
    private BufferProvider bufferProvider;
    public BufferProvider getBufferProvider() {
        return bufferProvider;
    }
    public void setBufferProvider(BufferProvider bufferProvider) {
        this.bufferProvider = bufferProvider;
    }

    private ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
    public ByteOrder getByteOrder() {
        return byteOrder;
    }
    public void setByteOrder(ByteOrder byteOrder) {
        this.byteOrder = byteOrder;
    }

    public byte[] buffer;
    public int index;

    private static ZoneId zoneId = TimeZone.getDefault().toZoneId();

    public BinaryReader(BufferProvider bufferProvider) {
        this.bufferProvider = bufferProvider;
    }

    @Override
    public byte readByte() {
        setBuffer(1);
        return buffer[index];
    }

    @Override
    public byte[] readByteArray(int length) {
        setBuffer(length);
        byte[] output = new byte[length];

        System.arraycopy(buffer, index, output, 0, length);

        return output;
    }

    @Override
    public short readInt16() {
        setBuffer(2);
        return ByteBuffer.wrap(buffer, index, 2).order(byteOrder).getShort();
    }

    @Override
    public short[] readInt16Array(int length) {
        setBuffer(length * 2);

        short[] output = new short[length];
        ByteBuffer.wrap(buffer, index, length * 2).order(byteOrder).asShortBuffer().get(output);

        return output;
    }

    @Override
    public int readInt32() {
        setBuffer(4);
        return ByteBuffer.wrap(buffer, index, 4).order(byteOrder).getInt();
    }

    @Override
    public int[] readInt32Array(int length) {
        setBuffer(length * 4);

        int[] output = new int[length];
        ByteBuffer.wrap(buffer, index, length * 4).order(byteOrder).asIntBuffer().get(output);

        return output;
    }

    @Override
    public long readInt64() {
        setBuffer(8);
        return ByteBuffer.wrap(buffer, index, 8).order(byteOrder).getLong();
    }

    @Override
    public long[] readInt64Array(int length) {
        setBuffer(length * 8);

        long[] output = new long[length];
        ByteBuffer.wrap(buffer, index, length * 8).order(byteOrder).asLongBuffer().get(output);

        return output;
    }

    @Override
    public float readSingle() {
        setBuffer(4);
        return ByteBuffer.wrap(buffer, index, 4).order(byteOrder).getFloat();
    }

    @Override
    public float[] readSingleArray(int length) {
        setBuffer(length * 4);

        float[] output = new float[length];
        ByteBuffer.wrap(buffer, index, length * 4).order(byteOrder).asFloatBuffer().get(output);

        return output;
    }

    @Override
    public double readDouble() {
        setBuffer(8);
        return ByteBuffer.wrap(buffer, index, 8).order(byteOrder).getDouble();
    }

    @Override
    public double[] readDoubleArray(int length) {
        setBuffer(length * 8);

        double[] output = new double[length];
        ByteBuffer.wrap(buffer, index, length * 8).order(byteOrder).asDoubleBuffer().get(output);

        return output;
    }

    @Override
    public boolean readBoolean() {
        setBuffer(1);
        return buffer[index] == 1;
    }

    @Override
    public boolean[] readBooleanArray(int length) {
        setBuffer(length);
        boolean[] output = new boolean[length];

        for(int i = 0; i < length; i++){
            output[i] = buffer[index + i] == 1;
        }

        return output;
    }

    @Override
    public char readChar() {
        setBuffer(2);
        return ByteBuffer.wrap(buffer, index, 2).order(byteOrder).getChar();
    }

    @Override
    public char[] readCharArray(int length) {
        setBuffer(length * 2);

        char[] output = new char[length];
        ByteBuffer.wrap(buffer, index, length * 2).order(byteOrder).asCharBuffer().get(output);

        return output;
    }

    @Override
    public String readString() {
        int size = readInt32();
        setBuffer(size);

        return new String(buffer, index, size, StandardCharsets.UTF_8);
    }

    @Override
    public String[] readStringArray(int length) {
        String[] output = new String[length];

        for(int i = 0; i < length; i++){
            output[i] = readString();
        }

        return output;
    }

    @Override
    public LocalDateTime readDateTime() {
        return LocalDateTime.ofInstant(Instant.EPOCH.plusNanos(readInt64() * 1000), zoneId);
    }

    @Override
    public LocalDateTime[] readDateTimeArray(int length) {
        long[] array = readInt64Array(length);

        LocalDateTime[] output = new LocalDateTime[length];
        for (int i = 0; i < length; i++){
            output[i] = LocalDateTime.ofInstant(Instant.EPOCH.plusNanos(array[i] * 1000), zoneId);
        }

        return output;
    }

    private void setBuffer(int length){
        bufferProvider.setBuffer(length);

        buffer = bufferProvider.getBuffer();
        index = bufferProvider.getIndex();
    }
}
