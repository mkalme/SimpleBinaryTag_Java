import simplebinarytag.ObjectTag;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

public class MainClass {
    public static void main(String[] args) {
        ObjectTag obj = createTestTag();

        try {
            FileOutputStream outputStream = new FileOutputStream("D://java_testsimplebinarytag");

            obj.writeToStream(outputStream);

            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ObjectTag createTestTag(){
        ObjectTag obj = new ObjectTag();

        obj.putByte("Byte", (byte)0);
        obj.putByteArray("ByteArray", new byte[10]);
        obj.putInt16("Int16", Short.MIN_VALUE);
        obj.putInt16Array("Int16Array", new short[10]);
        obj.putInt32("Int32", Integer.MIN_VALUE);
        obj.putInt32Array("Int32Array", new int[10]);
        obj.putInt64("Int64", Long.MIN_VALUE);
        obj.putInt64Array("Int64Array", new long[10]);
        obj.putSingle("Single", 0.00005F);
        obj.putSingleArray("SingleArray", new float[10]);
        obj.putDouble("Double", 5.55555555D);
        obj.putDoubleArray("DoubleArray", new double[10]);
        obj.putBoolean("Boolean", true);
        obj.putBooleanArray("BooleanArray", new boolean[10]);
        obj.putChar("Char", 'a');
        obj.putCharArray("CharArray", new char[10]);
        obj.putString("String", "test");
        obj.putStringArray("StringArray", "test1", "test2");
        obj.putDateTime("DateTime", LocalDateTime.of(1999, 5, 12, 1, 1, 1));
        obj.putDateTimeArray("DateTimeArray",
                LocalDateTime.of(2002, 1, 4, 6, 15, 59),
                LocalDateTime.of(2005, 2, 27, 2, 44, 55));
        obj.put("Object", obj.clone());
        obj.putObjectArray("ObjectArray", (ObjectTag) obj.clone(), (ObjectTag) obj.clone());

        return obj;
    }
}
