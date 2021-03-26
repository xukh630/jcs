package com.jcs.socket.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SocketServer {

    static List<SocketChannel> channelList = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9000));
        serverSocketChannel.configureBlocking(false);
        System.out.println("channel 配置完成");

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (null != socketChannel){
                System.out.println("连接成功");
                socketChannel.configureBlocking(false);
                channelList.add(socketChannel);
            }

            Iterator<SocketChannel> iterator = channelList.iterator();
            if (iterator.hasNext()){
                SocketChannel sc = iterator.next();
                //初始化byteBuffer
                ByteBuffer byteBuffer = ByteBuffer.allocate(128);
                int len = sc.read(byteBuffer);
                if (len > 0){
                    System.out.println("接受到消息: " + new String(byteBuffer.array()));
                }else if (len == -1){
                    iterator.remove();
                    System.out.println("客户端断开连接");
                }
            }
        }
    }
}
