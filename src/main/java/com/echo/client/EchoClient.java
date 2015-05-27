package com.echo.client;

import com.echo.client.handler.ChildClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/*
 * Created by wang on 2015/5/26.
 */
public class EchoClient {
        public void connect(String host,int port){
                EventLoopGroup group = new NioEventLoopGroup();
                Bootstrap b = new Bootstrap();
                b.group(group).channel(NioSocketChannel.class)
                        .option(ChannelOption.TCP_NODELAY,true)
                        .handler(new ChildClientHandler());

                try {
                        ChannelFuture f = b.connect(host,port).sync();
                        f.channel().closeFuture().sync();
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }finally {
                        group.shutdownGracefully();
                }
        }

        public static void main(String[] args) {
                int port = 8080 ;
                if (args != null && args.length>0){
                        port = Integer.valueOf(args[0]);
                }
                new EchoClient().connect("127.0.0.1",port);
        }
}
