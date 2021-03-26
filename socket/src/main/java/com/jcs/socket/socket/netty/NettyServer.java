package com.jcs.socket.socket.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

    public static void main(String[] args) {

        NioEventLoopGroup masterGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup slaveGroup = new NioEventLoopGroup();

        try {
            //创建服务启动对象
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(masterGroup,slaveGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new NettyServerHandler());
                        }
                    });

            System.out.println(" netty server start");

            ChannelFuture channelFuture = bootstrap.bind(9000).sync();
            //给channelFuture注册监听器
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (channelFuture.isSuccess()){
                        System.out.println("监听端口9000成功");
                    }else {
                        System.out.println("监听端口9000失败");
                    }
                }
            });

            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            masterGroup.shutdownGracefully();
            slaveGroup.shutdownGracefully();
        }
    }
}
