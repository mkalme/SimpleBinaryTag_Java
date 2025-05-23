package simplebinarytag.deserializer;

import simplebinarytag.io.IReader;
import simplebinarytag.Tag;

public interface ITagDeserializer<TTag extends Tag> {
    TTag deserialize(IReader reader);
}
