package com.hui.algorithm;

import java.io.*;

/**
 * @author: shenhaizhilong
 * @date: 2018/7/20 14:24
 */
public class GenerateIpString {
    public static void generateIpStr(String prefix, String filename)
    {

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filename))))
        {
            for (int i = 0; i < 256; i++) {
                for (int j = 0; j < 256; j++) {
                    for (int k = 0; k < 10; k++) {
                        String ip = prefix + i +"." +j+" #" + k+"\r";
                        writer.write(ip);
                    }
                }
            }
        }catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {
        String prefix = "192.168.";
        String path = "C:\\Users\\zhang\\Desktop\\ip.txt";
        generateIpStr(prefix, path);
    }
}
