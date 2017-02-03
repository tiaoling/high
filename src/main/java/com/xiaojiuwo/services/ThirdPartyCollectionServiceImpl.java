package com.xiaojiuwo.services;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.facebook.swift.codec.ThriftField;
import com.xiaojiuwo.models.ThirdPartyCollection;


public class ThirdPartyCollectionServiceImpl implements ThirdPartyCollectionService{
    
    public ThirdPartyCollection save(@ThriftField(name = "collection") ThirdPartyCollection collection) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException{
    	//ThirdPartyCollection th = new ThirdPartyCollection(2, "2017-03-04");
    	return collection;
    }
    
    public ThirdPartyCollection update(@ThriftField(name = "collection") ThirdPartyCollection collection){
    	//ThirdPartyCollection th = new ThirdPartyCollection(2, "2017-03-05");
    	return collection;
    }
    
    public List<ThirdPartyCollection> getAll(){
    	return null;
    }
}