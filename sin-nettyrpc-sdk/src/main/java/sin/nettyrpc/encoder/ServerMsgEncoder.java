package sin.nettyrpc.encoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import sin.nettyrpc.dto.RpcCallResp;
import sin.nettyrpc.protocol.Serializer;

/**
 * msg encoder when request out, here we just delegate our encode logic to serialization implementation
 * {@link sin.nettyrpc.protocol}
 * user: cxshun@gmail.com
 * date: 2021/11/17
 **/
public class ServerMsgEncoder extends MessageToByteEncoder<RpcCallResp> {

    private final Serializer serializer;
    public ServerMsgEncoder(Serializer serializer) {
        this.serializer = serializer;
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, RpcCallResp obj, ByteBuf byteBuf) throws Exception {
        serializer.serialize(obj, byteBuf);
    }
}
