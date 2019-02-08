/*
 * 三种方法获得class对象
 */
package com.getclassobj;

class Demo{  
    //other codes...  
}  
   
class GetClassObj{  
    public static void main(String[] args) {  
        Class<?> demo1=null;  
        Class<?> demo2=null;  
        Class<?> demo3=null;  
        try{  
            //一般尽量采用这种形式  
            demo1=Class.forName("com.getclassobj.GetClassObj");  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
        demo2=new Demo().getClass();  
        demo3=Demo.class;  
           
        System.out.println("类名称   "+demo1.getName());  
        System.out.println("类名称   "+demo2.getName());  
        System.out.println("类名称   "+demo3.getName());  
           
    }  
}  