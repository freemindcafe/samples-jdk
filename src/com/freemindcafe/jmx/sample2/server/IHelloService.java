package com.freemindcafe.jmx.sample2.server;

public interface IHelloService {
    public void sayHello(); 
    public int add(int x, int y); 
    
    public String getName(); 
     
    public int getCacheSize(); 
    public void setCacheSize(int size); 
}
