package simplebinarytag.io.read.buffer;

import java.io.IOException;
import java.io.InputStream;

public class StreamBufferProvider extends BufferProvider {
    private InputStream inputStream;
    public InputStream getInputStream() {
        return inputStream;
    }
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public StreamBufferProvider(InputStream inputStream){
        this.inputStream = inputStream;
    }

    @Override
    public void setBuffer(int length) {
        setBuffer(new byte[length]);
        setIndex(0);

        try {
            inputStream.read(getBuffer(), 0, length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
