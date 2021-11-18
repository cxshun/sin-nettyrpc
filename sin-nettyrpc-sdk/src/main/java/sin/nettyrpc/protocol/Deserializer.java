package sin.nettyrpc.protocol;

import io.netty.buffer.ByteBuf;

/**
 * user: cxshun@gmail.com
 * date: 2021/11/18
 **/
public interface Deserializer {

    /**
     * deserialize byteBuf content
     * @param byteBuf byteBuf
     * @return content
     */
    <T> T deserialize(ByteBuf byteBuf, Class<T> clazz);

}
