package com.xiaojiuwo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.eclipse.jetty.util.thread.ThreadPool;

import com.facebook.nifty.core.NettyServerConfig;
import com.facebook.nifty.core.NettyServerTransport;
import com.facebook.nifty.core.ThriftServerDef;
import com.facebook.nifty.core.ThriftServerDefBuilder;
import com.facebook.swift.codec.ThriftCodecManager;
import com.facebook.swift.service.ThriftEventHandler;
import com.facebook.swift.service.ThriftServer;
import com.facebook.swift.service.ThriftServiceProcessor;
import com.google.common.collect.ImmutableList;
import com.xiaojiuwo.services.ThirdPartyCollectionServiceImpl;

public class ThApp {

	public static void main(String[] args) {
		ThriftServiceProcessor processor = new ThriftServiceProcessor(
                new ThriftCodecManager(),
                ImmutableList.<ThriftEventHandler>of(),
                new ThirdPartyCollectionServiceImpl()
        );

		
		
		// Build the server definition
	    ThriftServerDef serverDef = new ThriftServerDefBuilder()
	    		.listen(8899)
	    		.withProcessor(processor)
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
		
		/**ThreadPool taskWorkerExecutor = newFixedThreadPool(1);

        ThriftServerDef serverDef = ThriftServerDef.newBuilder()
                .listen(8899)
                .withProcessor(processor)
                .using(taskWorkerExecutor)
                .build();

        bossExecutor = newCachedThreadPool();
        ioWorkerExecutor = newCachedThreadPool();

        NettyServerConfig serverConfig = NettyServerConfig.newBuilder()
                .setBossThreadExecutor(bossExecutor)
                .setWorkerThreadExecutor(ioWorkerExecutor)
                .build();

        server = new ThriftServer(serverConfig, serverDef);
        server.start();**/

	}

}
