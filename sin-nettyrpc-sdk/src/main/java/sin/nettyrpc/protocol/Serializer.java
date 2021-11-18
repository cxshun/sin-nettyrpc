package sin.nettyrpc.protocol;

import io.netty.buffer.ByteBuf;

/**
 * user: cxshun@gmail.com
 * date: 2021/11/18
 **/
public interface Serializer {

    /**
     * serialize obj to bytes
     * @param obj       obj
     * @param byteBuf   byteBuf
     */
    <T> void serialize(T obj, ByteBuf byteBuf);

}
