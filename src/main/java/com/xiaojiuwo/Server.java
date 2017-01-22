/**
 * 
 */
package com.xiaojiuwo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.thrift.TProcessor;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.jboss.netty.util.HashedWheelTimer;

import com.facebook.nifty.core.NettyServerConfigBuilder;
import com.facebook.nifty.core.NettyServerTransport;
import com.facebook.nifty.core.ThriftServerDef;
import com.facebook.nifty.core.ThriftServerDefBuilder;
import com.xiaojiuwo.example.ThriftTestService;

/**
 * @author liuhaibao
 *
 */
public class Server {
	
	public static void main(String args[]){
		startServer();
	}
	
	public static void startServer() {
	    // Create the handler
	    ThriftTestService.Iface serviceInterface = new MyServiceHandler();

	    // Create the processor
	    TProcessor processor = new ThriftTestService.Processor<>(serviceInterface);

	    // Build the server definition
	    ThriftServerDef serverDef = new ThriftServerDefBuilder().withProcessor(processor)
	                                                            .build();

	    // Create the server transport
	    final NettyServerTransport server = new NettyServerTransport(serverDef	);

	    // Create netty boss and executor thread pools
	    ExecutorService bossExecutor = Executors.newCachedThreadPool();
	    ExecutorService workerExecutor = Executors.newCachedThreadPool();

	    // Start the server
	    //server.start(bossExecutor, workerExecutor);
	    server.start();
	    // Arrange to stop the server at shutdown
	    Runtime.getRuntime().addShutdownHook(new Thread() {
	        @Override
	        public void run() {
	            try {
	                server.stop();
	            } catch (InterruptedException e) {
	                Thread.currentThread().interrupt();
	            }
	        }
	    });
	}
}
