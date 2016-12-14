package com.example.DynamicProxy;

/**
 * Created by Administrator on 2016/12/14.
 */

public class RealSubject implements Subject {
    @Override
    public void rent() {
        System.out.println("I want to rent my house");
    }

    @Override
    public void hello(String str) {
        System.out.println("hello: "+ str);
    }
}
