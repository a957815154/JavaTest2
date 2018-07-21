
package com.hand;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

public class Xmlexec extends Thread{

    @SuppressWarnings("resource")
    public static void txtToXML(String txtPath) throws IOException
    {
        File file = new File(txtPath);

        Document document = DocumentHelper.createDocument();


        Element list = document.addElement("xml");


        BufferedReader br = new BufferedReader(new FileReader(file));

        String line = br.readLine();

        while(line!=null)
        {
            String temp[]  = line.split(",");
            Element stock = list.addElement("stock");
            Element name = stock.addElement("name");
            Element open = stock.addElement("open");
            Element close = stock.addElement("close");
            Element current = stock.addElement("current");
            Element high = stock.addElement("high");
            Element low = stock.addElement("low");

            for(int i = 0;i<temp.length;i++)
            {
                name.setText(temp[0].split("=")[1].substring(1));
                open.setText(temp[1]);
                close.setText(temp[2]);
                current.setText(temp[3]);
                high.setText(temp[4]);
                low.setText(temp[5]);

            }
            line = br.readLine();
        }

        Writer filewriter = new FileWriter("Exam3/tmp/股票编码.xml");
        XMLWriter xmlWriter = new XMLWriter(filewriter);
        xmlWriter.write(document);
        xmlWriter.close();


    }

    @Override
    public void run() {
        try {
            Xmlexec.txtToXML("Exam3/tmp/123.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
