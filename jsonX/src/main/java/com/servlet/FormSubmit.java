package com.servlet;

import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "FormSubmit ")
public class FormSubmit extends HttpServlet{

    /**
     *
     */
    private static final long serialVersionUID = -8615452472287083708L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        System.out.println(req.getParameter("name")+","+req.getParameter("age")+req.getParameter("email")+","+req.getParameter("mytext"));
        String age=req.getParameter("age");
        String email=req.getParameter("email");
        String mytext=req.getParameter("mytext");
        String name=req.getParameter("name");
        mytext="mytext";
        age="24";
        name="hahhhd";
        email="15545555@qq.com";

        JSONObject jObject=new JSONObject();
        jObject.put("mytext", mytext);
        jObject.put("age", age);
        jObject.put("name", name);
        jObject.put("email", email);
        System.out.println(jObject);

        PrintWriter out=resp.getWriter();
        out.print(jObject);
        out.close();
    }


}
