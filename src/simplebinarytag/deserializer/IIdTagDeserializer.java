package simplebinarytag.deserializer;

import simplebinarytag.Tag;
import simplebinarytag.TagID;
import simplebinarytag.io.IReader;

public interface IIdTagDeserializer<TTag extends Tag> {
    TTag deserialize(IReader reader, TagID id);
}