/**
 * 
 */
package com.xiaojiuwo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;

import com.facebook.nifty.core.NettyServerTransport;
import com.facebook.nifty.core.ThriftServerDef;
import com.facebook.nifty.core.ThriftServerDefBuilder;
import com.facebook.swift.codec.ThriftCodecManager;
import com.facebook.swift.service.ThriftEventHandler;
import com.facebook.swift.service.ThriftServiceProcessor;
import com.xiaojiuwo.services.InMemoryScribe;
import com.xiaojiuwo.services.InMemoryScribeImpl;

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
	    //ThriftTestService.Iface serviceInterface = 
	   //	MyService.Iface serviceInterface = new MyServiceHandler();

	    // Create the processor
	    //TProcessor processor = new MyService.Processor<>(serviceInterface);

	    // Create the processor
	    //TProcessor processor = new ThriftTestService.Processor<>(new InMemoryScribe());
		
		InMemoryScribe inMemoryScribe = new InMemoryScribeImpl();
		TProtocolFactory protocolFactory  = new TBinaryProtocol.Factory();
		ThriftCodecManager thriftCodecManager = new ThriftCodecManager();
		 List list  = new ArrayList<>();
		 list.add(inMemoryScribe);
		 
	    ThriftServiceProcessor processor = new ThriftServiceProcessor(thriftCodecManager, Arrays.<ThriftEventHandler>asList(), inMemoryScribe);

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
