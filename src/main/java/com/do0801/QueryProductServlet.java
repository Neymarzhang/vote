package com.do0801;

import com.util.Utils;
import jdbcstudy.JDBCTemplate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/queryProduct.s")
public class QueryProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String page=req.getParameter("page");
        final String size=req.getParameter("size");
        String sql="select * from product limit ?,?";
        int iPage=Integer.parseInt(page);
        int iSize=Integer.parseInt(size);
        int begin=(iPage-1)+iSize;
        final List<Map<String,Object>> list= JDBCTemplate.select(sql,begin,iSize);

        sql="select count(*) cnt from product";
        final List<Map<String,Object>> res=JDBCTemplate.select(sql);
        final Object total=res.get(0).get("cnt");
        Map<String,Object> result=new HashMap<>();
        result.put("rows",list);
        result.put("total",total);
        Utils.result(resp,result);
    }
}
