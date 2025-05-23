package simplebinarytag.serializer;

import simplebinarytag.io.IWriter;
import simplebinarytag.Tag;

public interface ITagSerializer<TTag extends Tag> {
    void serialize(TTag tag, IWriter writer);
}
