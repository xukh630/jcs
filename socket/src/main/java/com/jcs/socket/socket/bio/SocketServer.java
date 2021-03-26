package com.jcs.socket.socket.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {

    //bio 问题   1.主线程空转   2.并发数有限
    public static void main(String[] args) throws IOException {
        server1();
    }

    public static void server1() throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);

        while (true) {
            Socket accept = serverSocket.accept();
            System.out.println("有客户端连接");

            try {
                byte[] bytes = new byte[1024];
                System.out.println("准备读取");
                InputStream inputStream = accept.getInputStream();
                while (true) {
                    int read = inputStream.read(bytes);
                    if (read != -1) {
                        System.out.println("接受客户端数据:" + new String(bytes, 0, read));
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
            }finally {
                serverSocket.close();
            }
        }
    }

    public static void server2() throws IOException {
        ExecutorService pool = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(9000);
        System.out.println("====== ServerSocket 服务启动 ======");
        while (true) {
            System.out.println("====== 连接请求进入 ======");
            Socket socket = serverSocket.accept();
            System.out.println("====== 客户端连接成功 ======");

            pool.execute(() -> {
                try {
                    byte[] bytes = new byte[1024];
                    try {
                        InputStream inputStream = socket.getInputStream();
                        while (true) {
                            int read = inputStream.read(bytes);
                            if (read == -1) {
                                break;
                            }
                            System.out.println(new String(bytes, 0, read));
                        }
                    } catch (IOException e) {
                        // ignore
                    }
                } catch (Exception ex) {
                    // ignore
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        // ignore
                    }
                }
            });
        }
    }

    public static void server3() throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);
        System.out.println("====== ServerSocket 服务启动 ======");
        while (true) {
            System.out.println("====== 连接请求进入 ======");
            Socket socket = serverSocket.accept();
            System.out.println("====== 客户端连接成功 ======");


                try {
                    byte[] bytes = new byte[1024];

                        InputStream inputStream = socket.getInputStream();
                        while (true) {
                            int read = inputStream.read(bytes);
                            if (read != -1) {
                                System.out.println(new String(bytes, 0, read));
                            }
                        }

                } catch (Exception ex) {
                    // ignore
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        // ignore
                    }
                }
        }
    }
}
