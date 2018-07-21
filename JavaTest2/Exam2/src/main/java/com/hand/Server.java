package com.hand;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

    public static void main(String[] args)
    {
        Server server = new Server();
        server.start();
    }


    private final String FILENAME = "Exam1/tmp/SampleChapter1.pdf";

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("服务器端口打开");

                Socket socket = serverSocket.accept();
                InputStream inputStream = new FileInputStream(FILENAME);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                OutputStream outputStream = socket.getOutputStream();
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
                byte[] bytes = new byte[50];
                int length;
                while ((length = bufferedInputStream.read(bytes))!= -1)
                {
                    bufferedOutputStream.write(bytes,0,length);
                }
                bufferedInputStream.close();
                inputStream.close();
                bufferedOutputStream.close();
                outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
