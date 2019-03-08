package com.hui.String;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/31 12:52
 */
public class AllSubstrings {

    public List<String> getAllSubstrs(String str)
    {
        List<String> list = new ArrayList<>();
        dfs(str, 0, new StringBuilder(),list);
        return list;
    }

    private void dfs(String str, int idx, StringBuilder sb, List<String> res)
    {
        if(idx >= str.length())return;

        for (int i = idx; i < str.length() ; i++) {
            sb.append(str.charAt(i));
            res.add(sb.toString());
            dfs(str, i + 1, sb, res);
            sb.deleteCharAt(sb.length() -1);

        }
    }

    public static void main(String[] args) {
        AllSubstrings allSubstrings = new AllSubstrings();
        System.out.println(allSubstrings.getAllSubstrs("abc"));
    }
}
