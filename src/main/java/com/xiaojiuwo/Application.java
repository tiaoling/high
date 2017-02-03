package com.xiaojiuwo;

import java.util.Arrays;

import javax.servlet.Servlet;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.facebook.nifty.processor.NiftyProcessorAdapters;
import com.facebook.swift.codec.ThriftCodecManager;
import com.facebook.swift.service.ThriftEventHandler;
import com.facebook.swift.service.ThriftServiceProcessor;
import com.xiaojiuwo.services.TCalculatorService;

//@SpringBootApplication

@Configuration
@EnableAutoConfiguration(exclude = {DispatcherServletAutoConfiguration.class ,DataSourceAutoConfiguration.class,DataSourceTransactionManagerAutoConfiguration.class})
@ComponentScan
@ImportResource("classpath:config/applicationContext.xml")
public class Application {

    public static void main(String[] args) {
    	SpringApplication.run(Application.class, args);

    }
    
    @Bean
    TProtocolFactory tProtocolFactory() {
        return new TBinaryProtocol.Factory();
    }
    
    @Bean
    ThriftCodecManager thriftCodecManager() {
        return new ThriftCodecManager();
    }

    @Bean
    Servlet thrift(ThriftCodecManager thriftCodecManager, TProtocolFactory protocolFactory, TCalculatorService calculatorService) {
        ThriftServiceProcessor processor = new ThriftServiceProcessor(thriftCodecManager, Arrays.<ThriftEventHandler>asList(), calculatorService);

        return new TServlet(
                NiftyProcessorAdapters.processorToTProcessor(processor),
                protocolFactory,
                protocolFactory
        );
    }

}

