package com.xiaojiuwo.services;

import java.util.List;

import com.facebook.swift.service.ThriftMethod;
import com.facebook.swift.service.ThriftService;

@ThriftService("scribe")
public interface InMemoryScribe
{
    
    public List<LogEntry> getMessages();

    @ThriftMethod("log")
    public String log(List<LogEntry> messages);
}
