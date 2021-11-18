package sin.nettyrpc.encoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import sin.nettyrpc.dto.RpcCallReq;
import sin.nettyrpc.protocol.Serializer;

/**
 * msg encoder when request out, here we just delegate our encode logic to serialization implementation
 * {@link sin.nettyrpc.protocol}
 * user: cxshun@gmail.com
 * date: 2021/11/17
 **/
public class ClientMsgEncoder extends MessageToByteEncoder<RpcCallReq> {

    private final Serializer serializer;
    public ClientMsgEncoder(Serializer serializer) {
        this.serializer = serializer;
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, RpcCallReq obj, ByteBuf byteBuf) throws Exception {
        serializer.serialize(obj, byteBuf);
    }
}
