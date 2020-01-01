package com.myedu.project.dongtai.controller;

import com.alibaba.fastjson.JSONObject;
import com.myedu.project.dongtai.domain.CityImg;
import com.myedu.project.dongtai.service.UrdiService;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.myedu.project.dongtai.domain.CityImg;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.io.File;

@RestController
public class dongtaiContoller {

    @Autowired
    private UrdiService urdi;

    /**
     * 功能分析：完成对前端文件上传的请求，接收图像，修改图像，存储图片的功能
     *
     * @param request //接收请求体
     * @param file //上传的文件
     * @return
     */
   //多图片上传控制器
    @RequestMapping("/uploadFile")
    @ResponseBody
    public JSONObject uploadFile(HttpServletRequest request, @RequestParam("file") MultipartFile file){
        JSONObject json = new JSONObject();
        //设置同步代码块
        synchronized (this) {
            Map<String, Object> map = new HashMap<>();
            Boolean flag=false;
            //判断是否有图片上
            if(file.isEmpty()){
                json.put("ret", false);
                json.put("code", 2);
                return json;
            }

            try{
                //获取上传文件的名称
                String fileName = file.getOriginalFilename();
                //获取文件上传的时间戳
                DateFormat format1 = new SimpleDateFormat("yyyyMMddhhmmss");
                String dateStr = format1.format(new Date()) + Math.floor(Math.random()*10000);
                //设置上传文件在服务器端的存储路径
                String path = request.getServletContext().getRealPath("/static/")+dateStr;
                //存储路径和上传文件名生成一个文件
                File dest = new File(path, fileName);
                //判断文件父目录是否存在
                if(!dest.getParentFile().exists()){
                    //不存在就创建一个文件夹出来存储上传文件
                    dest.getParentFile().mkdir();
                    //将上传文件保存到一个目标文件中
//                file.transferTo(new File(path + File.separator + fileName));
                    //通过图片的IO流读取上传的图片资源
                    BufferedImage image = ImageIO.read(file.getInputStream());
                    //获取一个操作图像资源的镀锡
                    BufferedImage   images = null;
                    //定义上传图片的高、宽值的变量
                    int img_width = 610,img_height = 400;
                    if (image != null) {//如果image=null 表示上传的不是图片格式
                        //创建一个存放图片区域的对象
                        images = new BufferedImage(img_width, img_height, BufferedImage.TYPE_INT_BGR);
                        //创建一个 Graphics2D，可以将它绘制到此 BufferedImage 中。
                        Graphics garphics = images.createGraphics();
                        /**
                         * 绘制当前可用的指定图像的指定区域,动态地缩放图像使其符合目标绘制表面的指定区域。
                         * 简单点说就是对上传的图像进行宽度和高度的修改从而达到符合我们前端的展示的需要
                         */
                        garphics.drawImage(image, 0, 0, img_width, img_height, null);
                        //创建一个文件输出流
                        OutputStream outputStream = new FileOutputStream(dest);
                        //把修改好的图片按指定输出流的路径存储
                        JPEGImageEncoder j = JPEGCodec.createJPEGEncoder(outputStream);
                        j.encode(images);
                        outputStream.close();
                    }
                }

                if(dest.exists()){
                    map.put("name", fileName);
                   // map.put("url", dataUrl);
                    json.put("ret",true);
                    json.put("data", map);
                    json.put("code", 0);
                } else {
                    json.put("ret", false);
                    json.put("code", 2);
                }
                //System.out.println("数据库路径："+dataUrl);
            }catch(Exception e){
                e.printStackTrace();
                json.put("ret", false);
                json.put("code", 2);
            }
            return json;
        }
    }




    /**
     * 功能分析：可以完成对一些失去价值的图像资源进行回收内存操作
     *
     * @param request
     * @return
     */
    //清除用户取消的图片
    @RequestMapping("/deleteUpdate")
    @ResponseBody
    public JSONObject deleteImg(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String url = request.getParameter("fileUrl");
        if (url == null) {
            return json;
        }
        //删除数据库中的记录
        CityImg cityImg = urdi.findCityImgByUrl(url);
        if (cityImg != null) {
            int number = urdi.DeleteCityImg(cityImg.getId());
        }
        String[] fileArr=url.split("/");
        String fileName = fileArr[fileArr.length-1];
        String path = request.getServletContext().getRealPath("/static/")+fileArr[fileArr.length-2];
        File dest = new File(path, fileName);
        //删除磁盘目录下的记录
        if (dest.exists()){
            //如果该目录的文件存在，执行删除操作
            dest.delete();
        }
        return json;
    }

}
