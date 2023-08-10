package com.do0801;

import com.Result;
import com.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/upload.s")
@MultipartConfig(maxFileSize = 1024*1024)
public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Part image=req.getPart("image");
        image.getSubmittedFileName();
        image.getSize();
        image.getContentType();

        String fileName=image.getSubmittedFileName();
        String suffix=fileName.replaceAll(".+(\\.\\w)","$1");
        String prefix= UUID.randomUUID().toString();
        fileName=prefix+suffix;
        image.write("D:\\QQ\\temp\\"+fileName);
        String  webPath="upload/"+fileName;
        Result result=new Result(1,"文件上传成功!",webPath);
        System.out.println(webPath);
        Utils.result(resp,result);
    }
}
