package simplebinarytag.io;

import java.time.LocalDateTime;

public interface IWriter {
    void writeByte(byte value);
    void writeByteArray(byte[] array);

    void writeInt16(short value);
    void writeInt16Array(short[] array);

    void writeInt32(int value);
    void writeInt32Array(int[] array);

    void writeInt64(long value);
    void writeInt64Array(long[] array);

    void writeSingle(float value);
    void writeSingleArray(float[] array);

    void writeDouble(double value);
    void writeDoubleArray(double[] array);

    void writeBoolean(boolean value);
    void writeBooleanArray(boolean[] array);

    void writeChar(char value);
    void writeCharArray(char[] array);

    void writeString(String value);
    void writeStringArray(String[] array);

    void writeDateTime(LocalDateTime value);
    void writeDateTimeArray(LocalDateTime[] array);
}
