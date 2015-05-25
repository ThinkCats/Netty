package com.wang.client;

import com.wang.client.handler.ClientChannelHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/*
 * Created by wang on 2015/5/25.
 */
public class TimeClient {
        public void connect(int port,String host){
                //client nio group
                EventLoopGroup group = new NioEventLoopGroup();
                Bootstrap b = new Bootstrap();
                b.group(group).channel(NioSocketChannel.class)
                        .option(ChannelOption.TCP_NODELAY,true)
                        .handler(new ClientChannelHandler());

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
                int port = 8090;
                if (args!=null && args.length>0){
                        port = Integer.valueOf(args[0]);
                }
                new TimeClient().connect(port,"127.0.0.1");
        }
}
