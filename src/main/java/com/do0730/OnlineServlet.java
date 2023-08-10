package com.do0730;

import com.util.Utils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

@WebServlet("/online.s")
public class OnlineServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.getServletContext();
//        request.getSession().getServletContext();
        final ServletContext servletContext=request.getSession().getServletContext();
//        final Object onlineMap=servletContext.getAttribute("onlineMap");
        final Map<String,Long> onlineMap=(Map<String,Long>)servletContext.getAttribute("onlineMap");
        Utils.result(response,onlineMap);
    }
}
