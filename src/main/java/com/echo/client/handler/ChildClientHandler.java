package com.echo.client.handler;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/*
 * Created by wang on 2015/5/27.
 */
public class ChildClientHandler extends ChannelInitializer<NioSocketChannel> {

        @Override
        protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
                nioSocketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,delimiter));
                nioSocketChannel.pipeline().addLast(new StringDecoder());
                nioSocketChannel.pipeline().addLast(new EchoClientHandler());
        }
}
