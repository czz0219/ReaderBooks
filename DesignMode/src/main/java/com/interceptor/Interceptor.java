package com.interceptor;

import java.lang.reflect.Method;

public interface Interceptor {
    /*  p1 代理对象
        p2 真实对象
        p3 方法实例，通过类加载器获得
        p4 方法的参数列表
    */
    public boolean before(Object proxy, Object target, Method method,Object[] args);
    public void around(Object proxy, Object target, Method method,Object[] args);
    public void after(Object proxy, Object target, Method method,Object[] args);
}
