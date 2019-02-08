package com.controller;

import com.beans.Person;
import com.service.PersonService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.AbstractMultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class PersonController {

    @Resource
    PersonService personService;

    @InitBinder
    //此方法用于日期的转换，如果未加，当页面日期格式转换错误，将报400错误，实际是因为此方法
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping("/person/listAll.action")
    public String listAll(Map<String,Object> model){
        List<Person> personList = personService.listAll();
        //
        model.put("person_list",personList);//springmvc 会调用 HttpServletRequest.setAttribute方法,设置personList属性到request中
        return "person/JPersonList";
    }
    //新增 1,增加页面;2.新增保存
    @RequestMapping(value = "/person/toCreate.action")
    public String tocreate(){
        return "person/jPersonCreate";
    }
    @RequestMapping(value = "/person/insert.action")
    /*页面提交时回附带 request对象，springmvc利用 request.getPrameter()方法获得页面参数，根据参数名找到他的getter and seeter方法
    * 通过动态代理AOP凡是将这些值设置进去*/
    public String insert(String id,String name, Integer age, Date joinDate){
        Person p = new Person();
        p.setId(id);
        p.setName(name);
        p.setAge(age);
        p.setJoinDate(joinDate);
        personService.insert(p);
        return "redirect:/person/listAll.action";
    }
    //转向修改
    @RequestMapping(value = "/person/toupdate.action")
    public String toupdate(String id,Model model){
        Person p =personService.get(id);
        //将页面现有的数据p传递到页面
        model.addAttribute("person_para",p);
        return "person/jPersonUpdate";
    }
    //保存修改
    @RequestMapping(value = "/person/update.action")
    public String update(Person p){
        personService.update(p);
        return "redirect:/person/listAll.action";
    }

    //删除一条
    @RequestMapping(value = "/person/deleteById.action")
    public String deleteById(String id){
        personService.deleteById(id);
        return "redirect:/person/listAll.action";
    }

    //删除多条
    @RequestMapping(value = "/person/delete.action")
    public String delete(String[] id){
        personService.delete(id);
        return "redirect:/person/listAll.action";
    }
    @RequestMapping(value = "/testJSON.action")  //接受$.ajax:data数据并返回
    @ResponseBody
    public String returnParam(HttpServletRequest request,HttpServletResponse response)throws IOException {
        ServletInputStream is;
        try{
            is=request.getInputStream();
            int read=0;
            int totalRead=0;
            byte[] bytes = new byte[10240];
            do {
                read=is.read(bytes,totalRead,bytes.length-totalRead);
                if(read>0)
                    totalRead = totalRead+read;
            }while(read>0);
            String str= new String(bytes,0,totalRead,"UTF-8");
            System.out.println(str);
            return str;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }


    //接收最原始的js，用servlet方式获取 xmlhttpservletrequest 发的Ajax数据
    static List<String>datas=new ArrayList<String>();
    static {

        datas.add("ajax");
        datas.add("ajax pos");
        datas.add("bk");
        datas.add("jer");
        datas.add("jim");
        datas.add("tom");
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
    @RequestMapping(value = "/serch")
    @ResponseBody
    public String requestPost(HttpServletRequest request,HttpServletResponse response)throws IOException
    {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        System.out.println(request.getContextPath()+"|"+request.getContentLength()+"|"+request.getContentType()+"|"+request.getServletContext());
        System.out.println("参数集:"+request.getParameterNames());
        Enumeration<String> attrs =request.getAttributeNames();
        System.out.println("属性集:"+attrs);
        String keyword=request.getParameter("keyword");
        List<String> listData=getData(keyword);
        System.out.println(listData);
        JSONArray re=JSONArray.fromObject(listData);
        return re.toString();
    }
    @RequestMapping(value = "/form")
    @ResponseBody
    public String receiveFormAjax(HttpServletRequest req ,HttpServletResponse resp )throws IOException{
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        System.out.println(req.getParameter("name")+","+req.getParameter("age")+req.getParameter("email")+","+req.getParameter("mytext"));
        String age=req.getParameter("age");
        String name=req.getParameter("name");
        String email=req.getParameter("email");
        String mytext=req.getParameter("mytext");
        JSONObject jObject=new JSONObject();
        jObject.put("name", name);
        jObject.put("mytext", mytext);
        jObject.put("age", age);
        jObject.put("email", email);
        jObject.put("mark","server return data");
        return  jObject.toString();
    }
}
