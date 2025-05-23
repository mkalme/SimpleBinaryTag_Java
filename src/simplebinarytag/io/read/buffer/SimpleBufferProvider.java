package simplebinarytag.io.read.buffer;

public class SimpleBufferProvider extends BufferProvider {
    private byte[] simpleBuffer;
    public byte[] getSimpleBuffer() {
        return simpleBuffer;
    }
    public void setSimpleBuffer(byte[] simpleBuffer) {
        this.simpleBuffer = simpleBuffer;
    }

    private int position;
    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }

    public SimpleBufferProvider(byte[] simpleBuffer, int position){
        this.simpleBuffer = simpleBuffer;
        this.position = position;
    }

    @Override
    public void setBuffer(int length) {
        setBuffer(simpleBuffer);
        setIndex(position);

        position += length;
    }
}
