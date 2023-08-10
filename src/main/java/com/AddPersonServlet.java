package com;

import com.google.gson.Gson;
import jdbcstudy.JDBCTemplate;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AddPersonServlet", value = "/AddPerson.s")
public class AddPersonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String id = request.getParameter("id");
        final String name = request.getParameter("name");
        final String age = request.getParameter("age");

        response.setContentType("application/json;charset=utf-8");
        final PrintWriter out= response.getWriter();
        Gson gson=new Gson();

        Result result = null;
        if(id==null ||id.trim().isEmpty()){
            result=new Result(0,"请输入id",null);
            out.println(gson.toJson(result));
            return;
        }
        if(name==null ||name.trim().isEmpty()){
            result=new Result(0,"请输入姓名",null);
            out.println(gson.toJson(result));
            return;
        }
        final List<Map<String,Object>> list= JDBCTemplate.select("select count(*) cnt from person where name=?",name);
        final int cnt=Integer.parseInt(list.get(0).get("cnt")+"");
        if(cnt>0){
            result=new Result(0,"该姓名已被注册",null);
            out.println(gson.toJson(result));
            return;
        }

        JDBCTemplate.update("insert into person (id,name,age) values(?,?,?)",id,name,age);
        result=new Result(1,"用户注册成功",null);
        out.println(gson.toJson(result));
        return;
    }

}
