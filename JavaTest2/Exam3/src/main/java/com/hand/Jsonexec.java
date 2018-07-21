
package com.hand;

import com.google.gson.Gson;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jsonexec extends Thread{

    @SuppressWarnings("resource")
    public static void txtToJSON(String txtPath) throws IOException
    {
        File file = new File(txtPath);
        String jsonString = null;
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        Map<String,Object> map = new HashMap<String,Object>();
        while(line!=null)
        {
            String temp[]  = line.split(",");
            map.put("name", temp[0].split("=")[1].substring(1));

            map.put("open", temp[1]);
            map.put("close", temp[2]);

            map.put("current", temp[3]);
            map.put("high", temp[4]);

            map.put("low",temp[5]);

            List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();

            list.add(map);



            Gson gson =  new Gson();

            jsonString = gson.toJson(list);

            System.out.println("json字符串:"+jsonString);

            line = br.readLine();
        }

        Writer filewriter = new FileWriter("Exam3/tmp/股票编码.json");
        filewriter.append(jsonString);
        filewriter.close();


    }

    @Override
    public void run() {
        try {
            Jsonexec.txtToJSON("Exam3/tmp/123.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
