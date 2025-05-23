package simplebinarytag.io;

import java.time.LocalDateTime;

public interface IReader {
    byte readByte();
    byte[] readByteArray(int length);

    short readInt16();
    short[] readInt16Array(int length);

    int readInt32();
    int[] readInt32Array(int length);

    long readInt64();
    long[] readInt64Array(int length);

    float readSingle();
    float[] readSingleArray(int length);

    double readDouble();
    double[] readDoubleArray(int length);

    boolean readBoolean();
    boolean[] readBooleanArray(int length);

    char readChar();
    char[] readCharArray(int length);

    String readString();
    String[] readStringArray(int length);

    LocalDateTime readDateTime();
    LocalDateTime[] readDateTimeArray(int length);
}
