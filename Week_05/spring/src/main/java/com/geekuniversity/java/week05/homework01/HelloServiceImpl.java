package com.geekuniversity.java.week05.homework01;

/**
 * 使 Java 里的动态代理，实现一个简单的 AOP
 * 实现类
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public void sayHello() {
        System.out.println("Hello World!");
    }
}