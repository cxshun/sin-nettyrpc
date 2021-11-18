package sin.nettyrpc.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import sin.nettyrpc.dto.RpcCallReq;
import sin.nettyrpc.protocol.Deserializer;

import java.util.List;

/**
 * msg decoder when request incoming, here we just delegate our decode logic to serialization implementation
 * {@link sin.nettyrpc.protocol}
 * user: cxshun@gmail.com
 * date: 2021/11/16
 **/
public class ClientMsgDecoder extends ByteToMessageDecoder {
    private final Deserializer deserializer;
    public ClientMsgDecoder(Deserializer deserializer) {
        this.deserializer = deserializer;
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        list.add(deserializer.deserialize(byteBuf, RpcCallReq.class));
    }
}
