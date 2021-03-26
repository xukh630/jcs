package com.jcs.socket.socket.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取客户端发送的数据
     * @param ctx 上下文对象. 含有通道channel, 管道pipeline
     * @param msg 客户端发送的数据
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务端读取线程 "+ Thread.currentThread().getName());

        ByteBuf byteBuf = (ByteBuf) msg;

        byte[] bytes = new byte[byteBuf.readableBytes()];
        ByteBuf result = byteBuf.readBytes(bytes);
        System.out.println("客户端发送消息: " + new String(bytes));

        result.release();
    }

    /**
     * 数据读取完毕处理方法
     * @param ctx
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx){
        ByteBuf buf = Unpooled.copiedBuffer("我是服务端", CharsetUtil.UTF_8);
        ctx.writeAndFlush(buf);
    }

    /**
     * 异常处理,关闭通道
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }

}
