package com.xiaojiuwo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Generator {
	public static void main(String args[]){
		AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("com.xiaojiuwo.Appliction");
			//DemoService demoService = context.getBean("thrift");
	}
}
