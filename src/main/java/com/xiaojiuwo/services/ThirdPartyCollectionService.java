package com.xiaojiuwo.services;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.facebook.swift.codec.ThriftField;
import com.facebook.swift.service.ThriftMethod;
import com.facebook.swift.service.ThriftService;
import com.xiaojiuwo.models.ThirdPartyCollection;

@ThriftService("ThirdPartyCollectionService")
public interface ThirdPartyCollectionService {
    @ThriftMethod
    public ThirdPartyCollection save(@ThriftField(name = "collection") ThirdPartyCollection collection) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException;
    @ThriftMethod
    public ThirdPartyCollection update(@ThriftField(name = "collection") ThirdPartyCollection collection);
    @ThriftMethod
    public List<ThirdPartyCollection> getAll();
}