package com;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/login.s")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final  String username=req.getParameter("username");
        final String password=req.getParameter("password");
        if("yc".equals(username) && "123".equals(password)){
            resp.sendRedirect("b.html");
        }
        else
            resp.sendRedirect("a.html");
    }
}
