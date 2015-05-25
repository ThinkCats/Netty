package com.wang.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

/*
 * Created by wang on 2015/5/25.
 */
public class TimeServerHandler extends ChannelHandlerAdapter {

        private int counter;

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                //read message from msg , then write this msg into req

                // in default, we need get message from msg ,and then ,read bytes from ByteBuf ,write to byte array "req"
                //ByteBuf  buf = (ByteBuf) msg;
                //byte[] req = new byte[buf.readableBytes()];
                //buf.readBytes(req);
                //String body = new String(req,"UTF-8").substring(0,req.length-System.getProperty("line.separator").length());

                //in Tcp transport , packet process ,we use LineBasedFrameDecoder and StringDecoder
                //so ,get String from msg directly
                String body = (String) msg;

                System.out.println("Server Received Order:"+body+", the count is:"+(++counter));
                String currentTime = "Query Time Order".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).toString():"Bad Order";
                currentTime = currentTime+System.getProperty("line.separator");
                ByteBuf response = Unpooled.copiedBuffer(currentTime.getBytes());
                ctx.writeAndFlush(response);
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                ctx.flush();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
               ctx.close();
        }
}
