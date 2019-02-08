package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Servlet extends javax.servlet.http.HttpServlet {
     static List<String>datas=new ArrayList<String>();
    static {

        datas.add("ajax");
        datas.add("ajax pos");
        datas.add("bk");
        datas.add("jer");
        datas.add("jim");
        datas.add("tom");
    }
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("123");
        System.out.println(request.getCookies());
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        System.out.println(request.getRequestURL());
        System.out.println(request);
        response.getWriter().write(request.getRequestURL().toString());
    }
    public List<String> getData(String keyword){
        List<String>list=new ArrayList<String>();
        for (String data:datas){
            if(data.contains(keyword)){
                list.add(data);
            }
        }
        return  list;
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        System.out.println(request.getRequestURL());
        System.out.println("123");
        String keyword=request.getParameter("keyword");
        List<String> listData=getData(keyword);
        System.out.println(listData);

        response.getWriter().write(listData.toString());
    /*    String re=JSONArray.fromObject(listData).toString();
        System.out.println(re);*/

    }
}
