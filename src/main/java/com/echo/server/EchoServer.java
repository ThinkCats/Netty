package com.echo.server;

import com.echo.server.handler.ServerChildHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/*
 * Created by wang on 2015/5/26.
 */
public class EchoServer {
        public void bind(int port){
                //Config NIO thread group
                EventLoopGroup bossGroup = new NioEventLoopGroup();
                EventLoopGroup workerGroup = new NioEventLoopGroup();
                ServerBootstrap b = new ServerBootstrap();
                b.group(bossGroup,workerGroup)
                        .channel(NioServerSocketChannel.class)
                        .option(ChannelOption.SO_BACKLOG,100)
                        .handler(new LoggingHandler(LogLevel.INFO))
                        .childHandler(new ServerChildHandler());

                try {
                        ChannelFuture f = b.bind(port).sync();
                        f.channel().closeFuture().sync();
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }finally {
                        bossGroup.shutdownGracefully();
                        workerGroup.shutdownGracefully();
                }
        }

        public static void main(String[] args) {
                int port = 8080;
                if (args != null && args.length >0 ){
                        port = Integer.valueOf(args[0]);
                }
                new EchoServer().bind(port);
        }
}
