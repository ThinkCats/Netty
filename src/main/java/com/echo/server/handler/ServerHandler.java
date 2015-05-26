package com.echo.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/*
 * Created by wang on 2015/5/26.
 */
public class ServerHandler extends ChannelHandlerAdapter {

        int counter = 0;

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                String body = (String) msg;
                System.out.println("This is "+(++counter)+" times received client :[ "+body+"]");
                body+="$_";
                ByteBuf buf = Unpooled.copiedBuffer(body.getBytes());
                ctx.writeAndFlush(buf);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                System.out.printf("Exception :"+cause.getMessage());
        }
}
