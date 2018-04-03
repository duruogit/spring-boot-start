package com.yunnex.boot.framework.nio;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

public class EchoClientHandler extends SimpleChannelInboundHandler<String> {
	
	public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
		System.out.println(s);
	}
	
	 /** 
     *此方法会在连接到服务器后被调用  
     * */  
    public void channelActive(ChannelHandlerContext ctx) {  
        ctx.write(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));  
    }  
    /** 
     *此方法会在接收到服务器数据后调用  
     * */  
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {  
        System.out.println("Client received: " + ByteBufUtil.hexDump(in.readBytes(in.readableBytes())));  
    }  
    /** 
     *捕捉到异常 ，出现异常关闭连接
     * */  
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {  
        System.out.println("SimpleChatClient:"+ctx.channel().remoteAddress()+"异常");
        cause.printStackTrace();  
        ctx.close();  
    }  

}
