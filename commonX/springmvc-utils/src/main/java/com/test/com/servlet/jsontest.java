package com.test.com.servlet;

import net.sf.json.JSONObject;

public class jsontest {
    public static void main(String args[]){
        String mytext,age,email,name;
        mytext="mytext";
        age="24";
        name="hahhhd";
        email="15545555@qq.com";

        JSONObject jObject=new JSONObject();
        jObject.put("mytext", mytext);
        jObject.put("age", age);
        jObject.put("name", email);
        jObject.put("email", email);
        System.out.println(jObject);
    }

}
