package com.nowcoder.community.controller;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/alpha")
public class AlphaController {
    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "hello";
    }

    //GET请求
    //传参方式1、/student?cur=1&lim=20
    @RequestMapping(path = "/student",method = RequestMethod.GET)
    @ResponseBody//加了这个注解 返回的东西就不是modelandview 而是直接当作网页的body显示出来
    public String getStudents(
            @RequestParam(name = "cur",required = false,defaultValue = "10") int current,
            @RequestParam(name = "lim",required = false,defaultValue = "100") int limit){
        System.out.println(current);
        System.out.println(limit);
        return "some student";
    }
    //传参方式2. /student/123
    @RequestMapping(path = "/student/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id){
        System.out.println(id);
        return "a student";
    }
    //POST请求
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, String age){
        System.out.println(name);
        System.out.println(age);
        return "success";

    }


    //响应 前面的写的都是用ResponseBody响应，下面介绍如何响应HTML数据
    @RequestMapping(path = "/teacher",method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("name","张三");
        mav.addObject("age",30);
        mav.setViewName("/demo/view");//模板自动跳到templates目录下，其下级路径要补充
        return mav;
    }

    @RequestMapping(path="/school",method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name","北京大学");
        model.addAttribute("age",80);
        return "/demo/view";
    }

    //响应异步请求 Json数据
    //java对象——》json字符串——》js对象
    @RequestMapping(path = "/emp",method = RequestMethod.GET)
    @ResponseBody//写这个才返回json字符串

    public Map<String,Object> getEmp(){
        Map<String,Object> emp = new HashMap<>();
        emp.put("name","张三");
        emp.put("age",23);
        emp.put("salary",18000);
        return emp;
    }

    @RequestMapping(path = "/emps",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> getEmps(){
        Map<String,Object> map1 = new HashMap<>();
        map1.put("name","张三");
        map1.put("age",23);
        map1.put("salary",18000);

        Map<String,Object> map2 = new HashMap<>();
        map2.put("name","李四");
        map2.put("age",24);
        map2.put("salary",28000);

        List<Map<String,Object>> list = new ArrayList<>();
        list.add(map1);
        list.add(map2);
        return list;
    }

}
