package com.observer;

import com.jdk.proxy.JdkProxyExample;
import jdk.internal.dynalink.linker.LinkerServices;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
/*
观察者/订阅发布模式 基于Observable 和 Observer 的实现
 */
public class ProductList extends java.util.Observable {
    private List<String> productList = null;
    private static ProductList instance;
    private  ProductList(){}
    /*全局唯一使用*/
    public static ProductList getInstance(){
        if (instance == null){
            instance = new ProductList();
            instance.productList = new ArrayList<String>();
        }
        return instance;
    }
    /*
    * 增加观察者
    * */
    public void addProductListObserver(Observer observer){
        this.addObserver(observer); //该方法继承自Observable,设置了一个观察者
    }

    public void addProduct(String newProduct){
        productList.add(newProduct);
        System.out.println("产品列表新增了产品: "+newProduct);
        this.setChanged();//继承自Observable,设置observable为更改状态
        this.notifyObservers(newProduct);//见名知意,通知观察者.传递参数给 观察者的update用
    }
    public static void  main(String[] args){
        ProductList observable = ProductList.getInstance();
        TaoBaoObserver taoBaoObserver = new TaoBaoObserver();
        JingDongObserver jingDongObserver = new JingDongObserver();
        observable.addObserver(jingDongObserver);
        observable.addObserver(taoBaoObserver);
        observable.addProduct("黑人牙刷SCLON");
    }
}
