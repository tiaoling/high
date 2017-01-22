/**
 * 
 */
package com.xiaojiuwo;

/**
 * @author liuhaibao
 *
 */
import org.apache.thrift.protocol.TBinaryProtocol;  
import org.apache.thrift.protocol.TProtocol;  
import org.apache.thrift.transport.TSocket;  
import org.apache.thrift.transport.TTransport;  
  
import com.xiaojiuwo.example.ThriftTestService;  
  
public class Client {  
  
    /** 
     * @param args 
     */  
    public static void main(String[] args) throws Exception {  
        TTransport transport = new TSocket("localhost", 8080);  
        transport.open();  
        TProtocol protocol = new TBinaryProtocol(transport);  
        ThriftTestService.Client client = new ThriftTestService.Client(protocol);  
        System.out.println(client.test("name"));  
        transport.close();  
    }  
  
}  