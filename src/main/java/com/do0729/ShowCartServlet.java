package com.do0729;

import com.Result;
import com.google.gson.Gson;
import com.util.Utils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/showCart.s")
public class ShowCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session=request.getSession();
        List<Map<String,String>> list=(ArrayList)session.getAttribute("carts");
        Utils.result(response,list);
    }
}
