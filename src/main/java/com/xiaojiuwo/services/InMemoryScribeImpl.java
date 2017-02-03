package com.xiaojiuwo.services;

import java.util.ArrayList;
import java.util.List;

public class InMemoryScribeImpl implements InMemoryScribe {
    private final List<LogEntry> messages = new ArrayList<>();

    public List<LogEntry> getMessages()
    {
        return messages;
    }

    public String log(List<LogEntry> messages)
    {
        this.messages.addAll(messages);
        return "ok";
    }
}
