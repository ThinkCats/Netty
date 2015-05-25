package com.wang.server;

import com.wang.server.handler.ChildChannelHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/*
 * Created by wang on 2015/5/25.
 */
public class TimeServer {
        public void bind(int port){
                EventLoopGroup bossGroup = new NioEventLoopGroup();
                EventLoopGroup workGroup = new NioEventLoopGroup();
                ServerBootstrap b =new ServerBootstrap();
                b.group(bossGroup,workGroup).channel(NioServerSocketChannel.class)
                        .option(ChannelOption.SO_BACKLOG,1024)
                        .childHandler(new ChildChannelHandler());

                try {
                        //bind complete ,waiting for success
                        ChannelFuture f = b.bind(port).sync();
                        f.channel().closeFuture().sync();
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }
                finally {
                        //exit
                        bossGroup.shutdownGracefully();
                        workGroup.shutdownGracefully();
                }
        }

        public static void main(String[] args) {
                int port = 8090;
                if (args != null && args.length >0){
                        port = Integer.valueOf(args[0]);
                }
                new TimeServer().bind(port);
        }
}
