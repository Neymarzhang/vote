package com.do0801;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/upload/*")
public class ResMappingServlet extends HttpServlet {
    public static final String UPLOAD_DIR="D:\\QQ\\temp\\";//上传文件夹的路径
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURL= req.getRequestURI();;//获取文件的web路径
        System.out.println("文件的Web路径:"+requestURL);
        final ServletContext servletContext=req.getServletContext();//获取上下文对象
        final String contextPath=servletContext.getContextPath();

        System.out.println("文件的上下文路径"+contextPath);

        requestURL=requestURL.substring(contextPath.length());

        System.out.println(requestURL);

        String resPath=requestURL.substring("/upload".length());//获取文件名
        String diskPath=UPLOAD_DIR+resPath;//获取文件的绝对路径
        String contentType=servletContext.getMimeType(resPath);//获取文件的输出类型
        System.out.println(contentType);
        resp.setContentType(contentType);//设置文件输出类型
        final ServletOutputStream out=resp.getOutputStream();//获取文件输出流
        try(FileInputStream fis=new FileInputStream(diskPath)){
            byte[] bytes=new byte[1024];
            int count;
            while ((count=fis.read(bytes))>0){
                out.write(bytes,0,count);
            }
        }
    }
}
