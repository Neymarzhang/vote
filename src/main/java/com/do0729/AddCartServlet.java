package com.do0729;

import com.Result;
import com.google.gson.Gson;
import com.util.Utils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/addCart.s")
public class AddCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session=request.getSession();
        List<Map<String,String>> list=(ArrayList)session.getAttribute("carts");
        session.removeAttribute("carts");
        final String src=request.getParameter("src");
        final String goodsName=request.getParameter("goodsName");
        final String price=request.getParameter("price");
        final String num=request.getParameter("nums");
        Map<String,String> map=new HashMap<>();
        if(src!=null && src.isEmpty()==false && goodsName!=null && goodsName.isEmpty()==false &&price!=null && price.isEmpty()==false &&num!=null && num.isEmpty()==false){
            if(list==null){
                map.put("src",src);
                map.put("goodsName",goodsName);
                map.put("price",price);
                map.put("num",num);
                list=new ArrayList<>();
                list.add(map);
            }else {
                boolean b=false;
                int i =0;
                for(;i<list.size();i++){
                    if(list.get(i).get("src").equals(src)){
                        b=true;
                        break;
                    }
                }
                if(b){
                    map=list.get(i);
                    int count =Integer.parseInt(num)+Integer.parseInt(map.get("num"));
                    map.put("num",""+count);
                    list.remove(i);
                    list.add(i,map);
                }else {
                    map.put("src",src);
                    map.put("goodsName",goodsName);
                    map.put("price",price);
                    map.put("num",num);
                    list.add(map);
                }
            }
        }else if(src!=null && src.isEmpty()==false && num!=null &&num.isEmpty()==false){
            int i =0;
            for(;i<list.size();i++){
                if(list.get(i).get("src").equals(src)){
                    break;
                }
            }
            map=list.get(i);
            int count =Integer.parseInt(num)+Integer.parseInt(map.get("num"));
            list.remove(i);
            if(count>0){
                map.put("num",""+count);
                list.add(i,map);
            }
        }

        //addCart.s?scr=1&goodsName=1&price=1&nums=1
        session.setAttribute("carts",list);
        Utils.result(response,list);
    }
}
