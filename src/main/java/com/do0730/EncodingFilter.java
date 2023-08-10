package com.do0730;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
@WebFilter({"*.s","*.do"})//设置过滤器拦截对象
public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req=(HttpServletRequest) servletRequest;//向下转型成HttpServletRequest
        if(req.getHeader("Accept").startsWith("text/html")){
            servletResponse.setContentType("text/html;charset=utf-8");//如果请求头与text/html匹配设置相应的字符集对象
        }else if (req.getHeader("Accept").startsWith("application/json")
        ||req.getHeader("Accept").startsWith("*/*")){
            servletResponse.setContentType("application/json;charset=utf-8");
        }
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setCharacterEncoding("utf-8");
        //请求到达访问页面之前
        filterChain.doFilter(servletRequest,servletResponse);
        //请求到达访问页面之后
    }

    @Override
    public void destroy() {
    }
}
