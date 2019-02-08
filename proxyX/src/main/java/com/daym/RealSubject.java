package com.daym;

public class RealSubject implements Subject {
    public void rent() {
        System.out.println("rent a house");
    }

    public void hello(String str) {
        System.out.println("hello" +
                str);
    }
}
