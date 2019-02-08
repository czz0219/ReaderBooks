package com.service;

import com.beans.Person;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.*;

@Service
public class PersonService {
    private static Map<String ,Person> personMap = new HashMap<String,Person>();
    private static Integer id = 0;
    static{
        for (int i = 0;i<10;i++){
            Person p = new Person();
            p.setId("9527-"+(id++).toString());
            p.setName("tony"+i);
            p.setAge(20+i);
            p.setJoinDate(new Date());
            personMap.put(p.getId(),p);
        }
    }

    //获取所有记录
    public List<Person> listAll(){
        return new ArrayList<Person>(personMap.values());//将personMap里面的所有person放进去
    }

    //查询一条
    public Person get(String id){
        return personMap.get(id);
    }
    //新增
    public void insert(Person p){
        personMap.put(p.getId(),p);
    }
    //修改
    public void update(Person p ){
        personMap.put(p.getId(),p);
    }

    //删除
    public void deleteById(String id){
        if(id!=null){
            personMap.remove(id);}
    }
    //批量删除
    public void delete(String [] ids){
        //ctrl+alt+t
        try {
            for(String id:ids){
                personMap.remove(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
