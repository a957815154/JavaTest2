package com.hand;

import java.io.*;
import java.net.Socket;

public class Client extends Thread {

    public static void main(String[] args)
    {
        try {
            Socket socket = new Socket("127.0.0.1",12345);
            Client client = new Client(socket);
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Socket socket;

    public Client(Socket socket)
    {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("connected");
        try {
            InputStream inputStream = socket.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);



            File pdf = new File("Exam2/tmp/SampleChapter1.pdf");
            if (!pdf.exists())
            {
                pdf.createNewFile();
                OutputStream outputStream = new FileOutputStream(pdf);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
                byte[] data = new byte[100];
                int length;
                while ((length = bufferedInputStream.read(data))!= -1)
                {
                    bufferedOutputStream.write(data,0,length);
                }
                System.out.println("finish");
                bufferedOutputStream.close();
                outputStream.close();
            }
            else {
                System.err.println("exists");
            }
            System.out.println(this.getName()+"断开连接");
            bufferedInputStream.close();
            inputStream.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
