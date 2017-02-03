/**
 * 
 */
package com.xiaojiuwo;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.facebook.nifty.client.HttpClientConnector;
import com.facebook.swift.codec.ThriftCodecManager;
import com.facebook.swift.service.ThriftClientManager;
import com.xiaojiuwo.services.InMemoryScribe;
import com.xiaojiuwo.services.LogEntry;  
  
public class Client {  
  
    /** 
     * @param args 
     */  
    public static void main(String[] args) throws Exception {  
    	/**
        TTransport transport = new TSocket("localhost", 8080);  
        transport.open();  
        TProtocol protocol = new TBinaryProtocol(transport);  
        ThriftTestService.Client client = new ThriftTestService.Client(protocol);  
        System.out.println(client.test("name"));  
        transport.close();  **/
    	InMemoryScribe client;
    	ThriftCodecManager thriftCodecManager = new ThriftCodecManager();
    	HttpClientConnector connector = new HttpClientConnector(URI.create("http://localhost:" + 8080 +"/scribe"));

        ThriftClientManager clientManager = new ThriftClientManager(thriftCodecManager);
        client = clientManager.createClient(connector, InMemoryScribe.class).get();
        List<LogEntry> msgs = new ArrayList<LogEntry>();
        String name = client.log(msgs);
        System.out.println("name: "+ name);
    }  
  
}  