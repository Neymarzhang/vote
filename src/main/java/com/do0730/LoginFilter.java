package com.do0730;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter(urlPatterns = {"/page/cart.html","/page/myinfo.html","/page/myorder.html"})
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        HttpServletResponse resp=(HttpServletResponse) servletResponse;
        final HttpSession session=req.getSession();//获取会话对象
        if(session.getAttribute("loginedUser")==null){
            final String requestURL=req.getRequestURI();//获取请求访问页面的绝对地址
            session.setAttribute("requestURL",requestURL);//将请求的访问地址存入到会话对象中
            final String contextPath=req.getContextPath();//获取请求的上下文地址
            resp.sendRedirect(contextPath+"/login.html?code=1");//

            return;
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
    }
}
