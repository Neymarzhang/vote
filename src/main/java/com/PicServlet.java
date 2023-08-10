package com;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet({"/img.s","/pic.s"})
public class PicServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/jpg");
        final ServletOutputStream out = resp.getOutputStream();
        try (FileInputStream fis=new FileInputStream("D:/avatar22.png")){
            byte[] bytes=new byte[1024];
            int count;
            while ((count=fis.read(bytes))>0){
                out.write(bytes,0,count);
            }

        }
    }
}