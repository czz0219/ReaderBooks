package com.interceptor;

import java.lang.reflect.Method;

public class MyInterceptor implements Interceptor {
    public boolean before(Object proxy, Object target, Method method, Object[] args) {
        System.err.println("before");
        return false;//不反射被代理对象原有方法,原方法被around代替
    }

    public void around(Object proxy, Object target, Method method, Object[] args) {
        System.err.println("around 取代了被代理对象的原方法");
    }

    public void after(Object proxy, Object target, Method method, Object[] args) {
        System.err.println("after");
    }
}
