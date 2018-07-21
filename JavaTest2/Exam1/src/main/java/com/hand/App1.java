package com.hand;


import java.io.*;
import java.net.*;

public class App1 {

    /**
     * 从网络Url中下载文件
     * @param urlStr
     * @param fileName
     * @param savePath
     * @throws IOException
     */
    public static void  downLoadByUrl(String urlStr,String fileName,String savePath) throws IOException{
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(5*1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        //得到输入流
        InputStream inputStream = conn.getInputStream();
    BufferedInputStream bufferedInputStream =new BufferedInputStream(inputStream);
        //获取自己数组
        byte[] getData = readInputStream(bufferedInputStream);
        //文件保存位置
        File saveDir = new File(savePath);
        if(!saveDir.exists()){
            saveDir.mkdir();
        }
        File file = new File(saveDir+File.separator+fileName);
        FileOutputStream fos = new FileOutputStream(file);
        BufferedOutputStream bufferedOutputStream =new BufferedOutputStream(fos);
        fos.write(getData);
        fos.flush();
        if(bufferedOutputStream!=null){
            bufferedOutputStream.close();
        }
        if(inputStream!=null){
            inputStream.close();
        }
        System.out.println("info:"+url+" download success");

    }


    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static  byte[] readInputStream(BufferedInputStream bufferedInputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = bufferedInputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    public static void main(String[] args) {
        try{
            downLoadByUrl("http://192.168.11.205:18080/trainning/SampleChapter1.pdf",
                    "SampleChapter1.pdf","Exam1/tmp");
        }catch (Exception e) {
            // TODO: handle exception
        }
    }



}