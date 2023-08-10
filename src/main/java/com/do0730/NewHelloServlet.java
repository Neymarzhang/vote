package com.do0730;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/hello.do")
public class NewHelloServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final  String name=request.getParameter("name");
        response.getWriter().append("你好: "+name);
    }
}
