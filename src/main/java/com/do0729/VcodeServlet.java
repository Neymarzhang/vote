package com.do0729;

import com.util.VerifyCodeUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "VcodeServlet", value = "/vcode.s")
public class VcodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session=request.getSession();

        final  String vcode= VerifyCodeUtils.outputImage(response);

        session.setAttribute("vcode",vcode);
    }
}
