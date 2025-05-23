package simplebinarytag;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import simplebinarytag.deserializer.TagDeserializer;
import simplebinarytag.io.IReader;
import simplebinarytag.io.read.BinaryReader;
import simplebinarytag.io.read.buffer.SimpleBufferProvider;
import simplebinarytag.io.read.buffer.StreamBufferProvider;
import simplebinarytag.io.write.BinaryWriter;
import simplebinarytag.io.write.buffer.StreamBufferWriter;
import simplebinarytag.serializer.TagSerializer;

import java.io.InputStream;
import java.io.OutputStream;

public abstract class Tag {
    private TagID ID;
    public TagID getID(){
        return this.ID;
    }

    protected Tag(TagID tagID){
        this.ID = tagID;
    }

    public static Tag fromBytes(byte[] bytes){
        return fromBytes(bytes, 0);
    }
    public static Tag fromBytes(byte[] bytes, int index){
        return fromReader(new BinaryReader(new SimpleBufferProvider(bytes, index)));
    }
    public static Tag fromStream(InputStream inputStream){
        return fromReader(new BinaryReader(new StreamBufferProvider(inputStream)));
    }
    public static Tag fromReader(IReader reader){
        return new TagDeserializer().deserialize(reader);
    }

    public byte[] toBytes(){
        ByteOutputStream outputStream = new ByteOutputStream();
        writeToStream(outputStream);

        return outputStream.toByteArray();
    }
    public void writeToStream(OutputStream outputStream){
        BinaryWriter writer = new BinaryWriter(new StreamBufferWriter(outputStream));
        new TagSerializer().serialize(this, writer);
    }

    public abstract Tag clone();

    @Override
    public abstract boolean equals(Object obj);
}
