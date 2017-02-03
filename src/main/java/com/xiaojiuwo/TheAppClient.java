package com.xiaojiuwo;

import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutionException;

import com.facebook.nifty.client.FramedClientConnector;
import com.facebook.swift.service.ThriftClientManager;
import com.xiaojiuwo.models.ThirdPartyCollection;
import com.xiaojiuwo.services.ThirdPartyCollectionService;

public class TheAppClient {

	public static void main(String[] args) throws ExecutionException, InterruptedException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ThriftClientManager clientManager = new ThriftClientManager();
        FramedClientConnector connector = new FramedClientConnector(new InetSocketAddress("localhost",8899));
        ThirdPartyCollectionService scribe = clientManager.createClient(connector, ThirdPartyCollectionService.class).get();
        //scribe.getAll();
        ThirdPartyCollection collection =
                new ThirdPartyCollection(1001, "2014-08-29");
        ThirdPartyCollection co2 = scribe.save(collection);
        System.out.println("c:"+ co2.getId());
        System.out.println("c:"+ co2.getDate());
	}


}
