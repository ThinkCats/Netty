package com.time.server.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;


/*
 * Created by wang on 2015/5/25.
 */
public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
                //add tcp decoder
                socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                socketChannel.pipeline().addLast(new StringDecoder());
                //add Server handler
                socketChannel.pipeline().addLast(new TimeServerHandler());
        }
}
