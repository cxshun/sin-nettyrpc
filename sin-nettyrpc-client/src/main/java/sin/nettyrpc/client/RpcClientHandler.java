package sin.nettyrpc.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.Getter;
import sin.nettyrpc.dto.RpcCallResp;

/**
 * user: cxshun@gmail.com
 * date: 2021/11/17
 **/
@Getter
public class RpcClientHandler extends SimpleChannelInboundHandler<RpcCallResp> {
    private RpcCallResp rpcCallResp;
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcCallResp rpcCallResp) throws Exception {
        this.rpcCallResp = rpcCallResp;
    }
}
