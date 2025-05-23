package simplebinarytag.io.write;

import simplebinarytag.io.IWriter;
import simplebinarytag.io.write.buffer.BufferWriter;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.time.*;
import java.util.concurrent.TimeUnit;

public class BinaryWriter implements IWriter {
    private BufferWriter bufferWriter;
    public BufferWriter getBufferWriter() {
        return bufferWriter;
    }
    public void setBufferWriter(BufferWriter bufferWriter) {
        this.bufferWriter = bufferWriter;
    }

    private ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
    public ByteOrder getByteOrder() {
        return byteOrder;
    }
    public void setByteOrder(ByteOrder byteOrder) {
        this.byteOrder = byteOrder;
    }

    private static ZoneId zoneId = ZoneId.systemDefault();

    public BinaryWriter(BufferWriter bufferWriter) {
        this.bufferWriter = bufferWriter;
    }

    @Override
    public void writeByte(byte value) {
        writeBuffer(new byte[]{value});
    }

    @Override
    public void writeByteArray(byte[] array) {
        writeBuffer(array);
    }

    @Override
    public void writeInt16(short value) {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.order(byteOrder);
        buffer.putShort(value);

        writeBuffer(buffer.array());
    }

    @Override
    public void writeInt16Array(short[] array) {
        ByteBuffer buffer = ByteBuffer.allocate(array.length * 2);
        buffer.order(byteOrder);
        buffer.asShortBuffer().put(array);

        writeBuffer(buffer.array());
    }

    @Override
    public void writeInt32(int value) {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.order(byteOrder);
        buffer.putInt(value);

        writeBuffer(buffer.array());
    }

    @Override
    public void writeInt32Array(int[] array) {
        ByteBuffer buffer = ByteBuffer.allocate(array.length * 4);
        buffer.order(byteOrder);
        buffer.asIntBuffer().put(array);

        writeBuffer(buffer.array());
    }

    @Override
    public void writeInt64(long value) {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.order(byteOrder);
        buffer.putLong(value);

        writeBuffer(buffer.array());
    }

    @Override
    public void writeInt64Array(long[] array) {
        ByteBuffer buffer = ByteBuffer.allocate(array.length * 8);
        buffer.order(byteOrder);
        buffer.asLongBuffer().put(array);

        writeBuffer(buffer.array());
    }

    @Override
    public void writeSingle(float value) {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.order(byteOrder);
        buffer.putFloat(value);

        writeBuffer(buffer.array());
    }

    @Override
    public void writeSingleArray(float[] array) {
        ByteBuffer buffer = ByteBuffer.allocate(array.length * 4);
        buffer.order(byteOrder);
        buffer.asFloatBuffer().put(array);

        writeBuffer(buffer.array());
    }

    @Override
    public void writeDouble(double value) {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.order(byteOrder);
        buffer.putDouble(value);

        writeBuffer(buffer.array());
    }

    @Override
    public void writeDoubleArray(double[] array) {
        ByteBuffer buffer = ByteBuffer.allocate(array.length * 8);
        buffer.order(byteOrder);
        buffer.asDoubleBuffer().put(array);

        writeBuffer(buffer.array());
    }

    @Override
    public void writeBoolean(boolean value) {
        writeBuffer(new byte[]{ value ? (byte)1 : (byte)0 });
    }

    @Override
    public void writeBooleanArray(boolean[] array) {
        byte[] output = new byte[array.length];

        for(int i = 0; i < output.length; i++){
            output[i] = array[i] ? (byte)1 : (byte)0;
        }

        writeBuffer(output);
    }

    @Override
    public void writeChar(char value) {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.order(byteOrder);
        buffer.putChar(value);

        writeBuffer(buffer.array());
    }

    @Override
    public void writeCharArray(char[] array) {
        ByteBuffer buffer = ByteBuffer.allocate(array.length * 2);
        buffer.order(byteOrder);
        buffer.asCharBuffer().put(array);

        writeBuffer(buffer.array());
    }

    @Override
    public void writeString(String value) {
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);

        writeInt32(bytes.length);
        writeBuffer(bytes);
    }

    @Override
    public void writeStringArray(String[] array) {
        for (int i = 0; i < array.length; i++){
            writeString(array[i]);
        }
    }

    @Override
    public void writeDateTime(LocalDateTime value) {
        Instant utc = Instant.from(value.atZone(zoneId).withZoneSameInstant(ZoneOffset.UTC));
        long micros = TimeUnit.SECONDS.toMicros(utc.getEpochSecond()) + TimeUnit.NANOSECONDS.toMicros(utc.getNano());

        writeInt64(micros);
    }

    @Override
    public void writeDateTimeArray(LocalDateTime[] array) {
        long[] longArray = new long[array.length];

        for (int i = 0; i < array.length; i++){
            Instant utc = Instant.from(array[i].atZone(zoneId).withZoneSameInstant(ZoneOffset.UTC));
            long micros = TimeUnit.SECONDS.toMicros(utc.getEpochSecond()) + TimeUnit.NANOSECONDS.toMicros(utc.getNano());

            longArray[i] = micros;
        }

        writeInt64Array(longArray);
    }

    private void writeBuffer(byte[] buffer){
        bufferWriter.writeBuffer(buffer);
    }
}
