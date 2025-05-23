package simplebinarytag.io.read.buffer;

public abstract class BufferProvider {
    private byte[] buffer;
    public byte[] getBuffer() {
        return buffer;
    }
    public void setBuffer(byte[] buffer) {
        this.buffer = buffer;
    }

    private int index;
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }

    public abstract void setBuffer(int length);
}
