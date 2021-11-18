package sin.nettyrpc.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sin.nettyrpc.decoder.ClientMsgDecoder;
import sin.nettyrpc.encoder.ServerMsgEncoder;
import sin.nettyrpc.protocol.json.JsonDeserializer;
import sin.nettyrpc.protocol.json.JsonSerializer;

/**
 * user: cxshun@gmail.com
 * date: 2021/11/17
 **/
public class RpcServer {

    public void start() throws InterruptedException {
        ApplicationContext context = new AnnotationConfigApplicationContext("sin.nettyrpc");

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(
                                    new ClientMsgDecoder(new JsonDeserializer()),
                                    new RpcServerHandler(),
                                    new ServerMsgEncoder(new JsonSerializer()),
                                    new LoggingHandler(LogLevel.DEBUG)
                            );
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture f = bootstrap.bind(8080).sync();

            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

}
