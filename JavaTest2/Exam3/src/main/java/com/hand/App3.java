package com.hand;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Hello world!
 *
 */
public class App3
{
    public static void  downLoadByUrl(String urlStr,String fileName,String savePath) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(5*1000);

        //得到输入流
        InputStream inputStream = conn.getInputStream();

        BufferedInputStream bufferedInputStream =new BufferedInputStream(inputStream);
        BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(bufferedInputStream,"GBK"));

        //获取自己数组
        String str = readInputStream(bufferedReader);
        //文件保存位置
        File saveDir = new File(savePath);
        if(!saveDir.exists()){
            saveDir.mkdir();
        }
        File file = new File(saveDir+File.separator+fileName);
        FileOutputStream fos = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter =new OutputStreamWriter(fos,"utf-8");
        outputStreamWriter.write(str);
        outputStreamWriter.flush();
        if(outputStreamWriter!=null){
            outputStreamWriter.close();
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
    public static  String readInputStream(BufferedReader bufferedReader) throws IOException {
        String line;
       String len ="";
        while((line = bufferedReader.readLine()) != null) {
            len=len+line+"";
        }

        return len;
    }

    public static void main(String[] args) {
        try{

            System.out.println("[INFO] 股票编码："+args[0]);
            System.out.println("[INFO] 开始获取数据。。。。。。");
            downLoadByUrl("http://hq.sinajs.cn/list="+args[0],
                    "123.txt","Exam3/tmp");
            System.out.println("[INFO] 获取数据成功！");
            Xmlexec xmlpaser=new Xmlexec();
            xmlpaser.start();
            System.out.println("[INFO] 解析为xml成功！");
            Jsonexec jspnexec=new Jsonexec();
            jspnexec.start();
            System.out.println("[INFO] 解析为json成功！");
        }catch (Exception e) {
            // TODO: handle exception
        }
    }
}
