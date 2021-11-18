package sin.nettyrpc.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.yaml.snakeyaml.Yaml;
import sin.nettyrpc.decoder.ServerMsgDecoder;
import sin.nettyrpc.dto.RpcCallReq;
import sin.nettyrpc.encoder.ClientMsgEncoder;
import sin.nettyrpc.protocol.json.JsonDeserializer;
import sin.nettyrpc.protocol.json.JsonSerializer;
import sin.nettyrpc.util.ConfigUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * user: cxshun@gmail.com
 * date: 2021/11/17
 **/
public class RpcServiceFactory implements InvocationHandler {

    private String className;
    private Map<String, Object> configMap = new HashMap<>();
    @SuppressWarnings("unchecked")
    public <T> T getClass(Class<?> clazz) {
        this.configMap = new Yaml().load(this.getClass().getClassLoader().getResourceAsStream("config.yaml"));

        this.className = clazz.getName();

        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{clazz}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        RpcCallReq rpcCallReq = new RpcCallReq(className, method.getName(), args);

        RpcClientHandler clientHandler = new RpcClientHandler();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline().addLast(
                                    new ClientMsgEncoder(new JsonSerializer()),
                                    new ServerMsgDecoder(new JsonDeserializer()),
                                    clientHandler,
                                    new LoggingHandler(LogLevel.DEBUG)
                            );
                        }
                    });

            ChannelFuture f = bootstrap.connect(
                            ConfigUtils.getOrDefault(this.configMap, "server.host", "").toString(),
                            Integer.parseInt(ConfigUtils.getOrDefault(this.configMap, "server.port", "8080").toString())
                    )
                    .sync();
            f.channel().writeAndFlush(rpcCallReq);
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
        return clientHandler.getRpcCallResp().getObj();
    }
}
