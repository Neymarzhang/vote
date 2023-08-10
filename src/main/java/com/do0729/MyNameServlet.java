package com.do0729;

import com.Result;
import com.util.Utils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/myName.s")
public class MyNameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        final Object loginedUser=session.getAttribute("loginedUser");
        Result result;
        if(loginedUser==null){
            result=new Result(0,"未登录",null);
        }else {
            result=new Result(1,"已登录",loginedUser);
        }
        Utils.result(response,result);
    }
}
