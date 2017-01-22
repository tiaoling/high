package com.xiaojiuwo;

import com.xiaojiuwo.example.ThriftTestService.Iface;

public class MyServiceHandler implements Iface{
	
	public String test(String name) throws org.apache.thrift.TException {
		System.out.println("name");
		return "welcome "  + name +"!";
	}
}
