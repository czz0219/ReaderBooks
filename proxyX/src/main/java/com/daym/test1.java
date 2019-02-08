package com.daym;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
/*
    *  摘自JDK 1.8
    *  public static Object newProxyInstance(ClassLoader loader,
                                      Class<?>[] interfaces,
                                      InvocationHandler h)throws IllegalArgumentException
{
    Objects.requireNonNull(h);

    final Class<?>[] intfs = interfaces.clone();//克隆被代理接口实例的class对象列表
    final SecurityManager sm = System.getSecurityManager();
    if (sm != null) {
        checkProxyAccess(Reflection.getCallerClass(), loader, intfs);
    }


    Class<?> cl = getProxyClass0(loader, intfs);//通过类加载器 和这些class对象获得代理类的class对象


    try {
        if (sm != null) {
            checkNewProxyPermission(Reflection.getCallerClass(), cl);
        }

        final Constructor<?> cons = cl.getConstructor(constructorParams);//通过反射技术获得 代理类的构造
        final InvocationHandler ih = h;
        if (!Modifier.isPublic(cl.getModifiers())) {
            AccessController.doPrivileged(new PrivilegedAction<Void>() {
                public Void run() {
                    cons.setAccessible(true);
                    return null;
                }
            });
        }
        return cons.newInstance(new Object[]{h});  //通过构造创建代理类的对象，
    } catch (IllegalAccessException|InstantiationException e) {
        throw new InternalError(e.toString(), e);
    } catch (InvocationTargetException e) {
        Throwable t = e.getCause();
        if (t instanceof RuntimeException) {
            throw (RuntimeException) t;
        } else {
            throw new InternalError(t.toString(), t);
        }
    } catch (NoSuchMethodException e) {
        throw new InternalError(e.toString(), e);
    }
}     * */
public class test1
{
    public static void main(String[] args)
    {
        //    我们要代理的真实对象
        Subject realSubject = new RealSubject();

        //    创建中间工具
        InvocationHandler handler = new DynamicProxy<Subject>(realSubject); //InvocationHandler的实现类作用：将target 与 invoke关联。生成一个handler
        //通过该工具生成代理对象 subject，这里看不到代理类（它是由JDK动态生成再内存文件中的，可以通过反编译查看生成的代理类)，

        Subject subject = (Subject)Proxy.newProxyInstance(handler.getClass().getClassLoader(),//类加载器
                realSubject.getClass().getInterfaces(), //被代理的target类的接口列表
                handler);//handler中包含被代理对象realSubject，通过反编译中静态代码，通过反射（realSubject->方法、属性等地的)获得被代理对象的调用方法，
        //代理对象，再通过实现接口的方式，间接调用中间类 InvocationHandler的invoke方法实现对被代理类的方法调用，
        // 因此 所有被代理的实例的所有方法均有invoke调用;并且Java的jdk动态代理模式的被代理类都要满足接口模式，即定义一个共有接口interface

        byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0", RealSubject.class.getInterfaces());
        String path = "E:/SubjectProxy.class";
        try{
            FileOutputStream fos = new FileOutputStream(path);
            fos.write(classFile);
            fos.flush();
            System.out.println("代理类class文件写入成功");
        } catch (Exception e) {
            System.out.println("写文件错误");
        }






        System.out.println("main.subject"+subject);
        System.out.println(subject.getClass().getName());
        subject.rent();
        subject.hello("world");
    }
}