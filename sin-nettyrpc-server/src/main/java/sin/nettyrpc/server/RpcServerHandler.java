package sin.nettyrpc.server;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.Getter;
import sin.nettyrpc.dto.MessageCode;
import sin.nettyrpc.dto.RpcCallReq;
import sin.nettyrpc.dto.RpcCallResp;
import sin.nettyrpc.util.ClassUtils;

/**
 * user: cxshun@gmail.com
 * date: 2021/11/17
 **/
@Getter
public class RpcServerHandler extends SimpleChannelInboundHandler<RpcCallReq> {

    private RpcCallResp rpcCallResp;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcCallReq rpcCallReq) throws Exception {
        Object obj = ClassUtils.invoke(rpcCallReq.getClassName(), rpcCallReq.getMethodName(), rpcCallReq.getParams());
        this.rpcCallResp = new RpcCallResp(MessageCode.SUCCESS.getCode(), MessageCode.SUCCESS.getMessage(), obj);
        channelHandlerContext.channel().writeAndFlush(this.rpcCallResp).sync();
        channelHandlerContext.channel().close().sync();
    }
}
