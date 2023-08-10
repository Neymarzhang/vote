package com;

import jdbcstudy.JDBCTemplate;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet("/QueryPerson.s")
public class QueryPersonServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("---------QueryPersonServlet 初始化----------");
    }

    @Override
    public void destroy() {
        System.out.println("-------------QueryPersonServlet 关闭-----------------");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("-------------QueryPersonServlet service-----------------");
        super.service(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final List<Map<String, Object>>list= JDBCTemplate.select("select * from person");
        response.setContentType("text/html;charset=utf-8");
        final PrintWriter out = response.getWriter();
        out.append("<table><tr><td>id</td><td>姓名</td><td>年龄</td></tr>");
        for (Map<String, Object> person : list) {
            out.append("<tr><td>" +
                    person.get("id")+"</td><td>" +
                    person.get("name")+"</td><td>" +
                    person.get("age")+"</td></tr>");
        }
        out.append("</table>");
    }

}
