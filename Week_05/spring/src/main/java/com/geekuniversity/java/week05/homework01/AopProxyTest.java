package com.geekuniversity.java.week05.homework01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class AopProxyTest {

    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        InvocationHandler handler = new HelloServiceProxy(helloService);
        ClassLoader loader = helloService.getClass().getClassLoader();
        Class[] interfaces = helloService.getClass().getInterfaces();
        /**
         * 该方法用于为指定类装载器、一组接口及调用处理器生成动态代理类实例
         */
        HelloService proxyInstance = (HelloService) Proxy.newProxyInstance(loader, interfaces, handler);
        System.out.println("动态代理对象的类型：" + proxyInstance.getClass().getName());
        proxyInstance.sayHello();
    }
}