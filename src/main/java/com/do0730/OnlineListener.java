package com.do0730;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.LinkedHashMap;
import java.util.Map;
@WebListener
public class OnlineListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("=================+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ =================");
        Map<String,Long> onlineMap=new LinkedHashMap<>();
        final ServletContext servletContext= servletContextEvent.getServletContext();
        servletContext.setAttribute("onlineMap",onlineMap);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("================= attributeAdded =================");
        if("loginedUser".equals(httpSessionBindingEvent.getName())){
            final ServletContext servletContext=httpSessionBindingEvent.getSession().getServletContext();
            Map<String,Long> onlineMap=(Map<String,Long>) servletContext.getAttribute("onlineMap");
            onlineMap.put((String) httpSessionBindingEvent.getValue(),System.currentTimeMillis());
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("================= attributeRemoved =================");
        if ("loginedUser".equals(httpSessionBindingEvent.getName())){
            final ServletContext servletContext=httpSessionBindingEvent.getSession().getServletContext();
            Map<String,Long> onlineMap=(Map<String, Long>) servletContext.getAttribute("onlineMap");
            onlineMap.remove((String) httpSessionBindingEvent.getValue());
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("================= attributeReplaced =================");
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }
}
