package com.hui.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/26 21:02
 *
 *
 *722. Remove Comments
 *
 */
public class RemoveComments {

    public List<String> removeComments(String[] source) {

        List<String> res = new ArrayList<>();
        StringBuilder newLine = new StringBuilder();
        boolean inBlock = false;
        for(String line: source)
        {
            boolean inLine = false;
           char[] vals = line.toCharArray();
            for (int i = 0; i < vals.length; i++) {

                if(inBlock)
                {
                    if(i +1 < vals.length && vals[i] != '*' && vals[i +1] != '/')
                    {
                        continue;
                    }else if(i +1 < vals.length && vals[i] == '*' && vals[i+1] == '/') {
                       inBlock = false;
                       i++;
                    }
                }else if(!inLine && i +1 < vals.length && vals[i] == '/' && vals[i +1] == '/')
                {
                   break;
                }else if(!inBlock && i +1 < vals.length && vals[i] == '/' && vals[i +1] == '*')
                {
                    inBlock = true;
                    i++;
                } else {
                    newLine.append(vals[i]);
                }
            }

            if(!inBlock && newLine.length() > 0)
            {
                res.add(newLine.toString());
                newLine = new StringBuilder();
            }
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
