package com.time.client.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/*
 * Created by wang on 2015/5/25.
 */
public class ClientChannelHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
                //support TCP Packet , using LineBasedFrameDecoder and  StringDecoder
                socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                socketChannel.pipeline().addLast(new StringDecoder());
                socketChannel.pipeline().addLast(new TimeClientHandler());
        }
}
