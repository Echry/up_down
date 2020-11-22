import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

  /*      //输入流，获取客户端传来数据流
        InputStream inputStream = req.getInputStream();
        Reader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        //输出流，将数据流输出到本地硬盘
        //获取文件夹的绝对路径
        String path = req.getServletContext().getRealPath("file/copy.txt");
        OutputStream outputStream = new FileOutputStream(path);
        Writer writer = new OutputStreamWriter(outputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        String str = "";
        while ((str = bufferedReader.readLine()) != null){
            bufferedWriter.write(str);
        }

        bufferedWriter.close();
        writer.close();
        outputStream.close();
        bufferedReader.close();
        reader.close();
        inputStream.close();*/

        try {
            DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            List<FileItem> list = servletFileUpload.parseRequest(req);
            for (FileItem fileItem :list){
                if(fileItem.isFormField()){
                    String name = fileItem.getFieldName();
                    String value = fileItem.getString("UTF-8");
                    System.out.println(name + ":" + value);
                }else {
                    String fileName = fileItem.getName();
                    long size = fileItem.getSize();
                    System.out.println(fileName + ":" + size + "Byte");
                    InputStream inputStream = fileItem.getInputStream();
                    String path = req.getServletContext().getRealPath("file/" + fileName);
                    OutputStream outputStream = new FileOutputStream(path);
                    int temp = 0;
                    while ((temp = inputStream.read()) != -1){
                        outputStream.write(temp);
                    }
                    outputStream.close();
                    inputStream.close();
                    System.out.println("上传成功");
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }
}
