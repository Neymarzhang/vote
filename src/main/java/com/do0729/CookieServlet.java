package com.do0729;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/cookie.s")
public class CookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //发送网络请求时，Cookie会在请求头里一起发送给服务器端
        resp.setContentType("text/html;charset=utf-8");
        final PrintWriter out = resp.getWriter();
        //获取请求里的Cookie数据
        final Cookie[] cookies=req.getCookies();
        if(cookies!=null){
            out.append("<ol>");
            for (Cookie cookie:cookies){
                out.append("<li>")
                        .append(cookie.getName())
                        .append("=")
                        .append(cookie.getValue())
                        .append("</li>");
            }
            out.append("</ol>");
        }
        final  String name=req.getParameter("name");
        final  String value=req.getParameter("value");
        final String maxAge=req.getParameter("maxAge");
        if(name!=null){
            //服务器端操作Cookie
            Cookie cookie=new Cookie(name,value);
            //设置有效期如果有效期为0，表示删除Cookie
            //如果不设置有效期，表示Cookie在浏览器关闭时，自动消失
            if(maxAge!=null){
                cookie.setMaxAge(Integer.parseInt(maxAge));
            }
            //将Cookie保存在响应对象里，发送给浏览器
            resp.addCookie(cookie);
        }
    }
    //?name=gender&value=1            添加Cookie
    //?name=gender&value=1&maxAge=0   删除Cookie
}
