package com.echo.client.handler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/*
 * Created by wang on 2015/5/27.
 */
public class EchoClientHandler extends ChannelHandlerAdapter {

        private int counter;
        static final String REQ = "hello ,how are you $_";

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                System.out.println("Exception Cause:"+cause.getMessage());
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                for (int i=0;i<20;i++){
                        ctx.writeAndFlush(Unpooled.copiedBuffer(REQ.getBytes()));
                }
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                System.out.println("This is "+(++counter)+" Times Received From Server :["+msg+"]");
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                ctx.flush();
        }
}
