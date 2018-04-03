package com.yunnex.boot.framework.nio;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer {
	
//	private static final String ip = "127.0.0.1";
	private static final int port = 7070;
	
	public void start() throws Exception {
		//创建 ServerBootstrap实例
		ServerBootstrap server = new ServerBootstrap();
		
		//通过NIO方式来接受和处理连接请求，NioEventLoopGroup处理I/O操作的多线程事件循环器
		EventLoopGroup bossGroup = new NioEventLoopGroup(2);//boss group用来接收进来的链接
		EventLoopGroup workGroup = new NioEventLoopGroup(4);//work group用来处理被boss group接进来的链接
		
		try {
			server.group(bossGroup, workGroup);
			//设置NIO类型的channel  
			server.channel(NioServerSocketChannel.class);
			//绑定服务器监听端口
			
			//创建childHandler来处理每一个链接请求   
			server.option(ChannelOption.SO_BACKLOG, 128);
			server.childHandler(new SimpleServerInitializer());
			server.childOption(ChannelOption.SO_KEEPALIVE, true); 
			
			//配置完成，开始绑定server，通过调用sync同步方法阻塞直到绑定成功  
			ChannelFuture f = server.bind(port).sync();
			System.out.println("SimpleChatServer started and listen on " + f.channel().localAddress());  
			//应用程序会一直等待，直到channel关闭 
			f.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {  
			bossGroup.shutdownGracefully().sync();//关闭EventLoopGroup，释放掉所有资源包括创建的线程  
			workGroup.shutdownGracefully().sync();
        } 
		
	}
	
	public static void main(String[] args) {  
        try {  
            new EchoServer().start();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}
