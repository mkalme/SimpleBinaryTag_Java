package simplebinarytag;

public enum TagID {
    BYTE((byte)1),
    BYTE_ARRAY((byte)2),
    INT16((byte)3),
    INT16_ARRAY((byte)4),
    INT32((byte)5),
    INT32_ARRAY((byte)6),
    INT64((byte)7),
    INT64_ARRAY((byte)8),
    SINGLE((byte)9),
    SINGLE_ARRAY((byte)10),
    DOUBLE((byte)11),
    DOUBLE_ARRAY((byte)12),
    BOOLEAN((byte)13),
    BOOLEAN_ARRAY((byte)14),
    CHAR((byte)15),
    CHAR_ARRAY((byte)16),
    STRING((byte)17),
    STRING_ARRAY((byte)18),
    DATETIME((byte)19),
    DATETIME_ARRAY((byte)20),
    OBJECT((byte)21),
    OBJECT_ARRAY((byte)22);

    private byte id;
    public byte getByte(){
        return this.id;
    }

    TagID(byte id){
        this.id = id;
    }

    public static TagID fromByte(byte b){
        switch (b){
            case 1: return BYTE;
            case 2: return BYTE_ARRAY;
            case 3: return INT16;
            case 4: return INT16_ARRAY;
            case 5: return INT32;
            case 6: return INT32_ARRAY;
            case 7: return INT64;
            case 8: return INT64_ARRAY;
            case 9: return SINGLE;
            case 10: return SINGLE_ARRAY;
            case 11: return DOUBLE;
            case 12: return DOUBLE_ARRAY;
            case 13: return BOOLEAN;
            case 14: return BOOLEAN_ARRAY;
            case 15: return CHAR;
            case 16: return CHAR_ARRAY;
            case 17: return STRING;
            case 18: return STRING_ARRAY;
            case 19: return DATETIME;
            case 20: return DATETIME_ARRAY;
            case 21: return OBJECT;
            case 22: return OBJECT_ARRAY;
            default: return null;
        }
    }
}
