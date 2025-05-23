package simplebinarytag.io.write.buffer;

import java.io.IOException;
import java.io.OutputStream;

public class StreamBufferWriter extends BufferWriter {
    private OutputStream outputStream;
    public OutputStream getOutputStream() {
        return outputStream;
    }
    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public StreamBufferWriter(OutputStream outputStream){
        this.outputStream = outputStream;
    }

    @Override
    public void writeBuffer(byte[] buffer) {
        try {
            outputStream.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
