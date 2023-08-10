package com.do0729;

import com.Result;
import com.google.gson.Gson;
import com.util.Utils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/logined.s")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String username= req.getParameter("username");
        final String password =req.getParameter("password");
        final  String vcode1=req.getParameter("vcode");
        final String vcode2=(String)req.getSession().getAttribute("vcode");
        Result result;
        if(vcode1==null || vcode1.isEmpty()){
            result=new Result(0,"请输入验证码!",username);
        }else if (vcode1.equalsIgnoreCase(vcode2)==false){
            result=new Result(0,"验证码错误!",username);
        }else if(("yc".equals(username) || "zs".equals(username) ||"ls".equals(username)) && "123".equals(password)){
            final HttpSession session=req.getSession();
            Map<String,Object> data=new HashMap<>();
            data.put("username",username);
            data.put("requestURL",session.getAttribute("requestURL"));
            session.setAttribute("requestURL",null);
            result=new Result(1,"登录成功",data);
            session.setAttribute("loginedUser",username);
            session.removeAttribute("vcode");
        }else {
            result=new Result(0,"登录失败",null);
        }
        Utils.result(resp,result);

    }
    //logined.s?username=yc&password=123
}
