package com.hui.String;

import java.util.ArrayList;
import java.util.List;


/**
 * @author: shenhaizhilong
 * @date: 2018/12/8 15:16
 *
 *
 * 93. Restore IP Addresses
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 *
 * Example:
 *
 * Input: "25525511135"
 * Output: ["255.255.11.135", "255.255.111.35"]
 *
 */
public class RestoreIPAddresses {


    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        if(s == null || s.length() < 4){
            return ans;
        }
        StringBuilder curr = new StringBuilder();

        dfs(s, 0, ans, curr,0);
        return ans;

    }

    private void dfs(String source, int idx, List<String> ans, StringBuilder curr, int count)
    {
        if(count > 4)return;  // the largest count is 4
        if(idx == source.length() && count == 4)
        {

            ans.add(curr.toString());
            return;
        }

        for (int i = 1; i < 4; i++) {
            if(idx + i > source.length())return;  // out of index
            String part = source.substring(idx, idx +i);
            if(source.length() - (idx +i) > (4 - count)*3)return; // remain string is to long
            if(part.charAt(0) == '0' && part.length() > 1 || Integer.valueOf(part) > 255)continue;  // not valid ip address
            int preLength = curr.length();
            curr.append(part);
            if(count < 3)
            {
                curr.append('.');
            }
            dfs(source, idx +i, ans, curr, count +1);
            curr.setLength(preLength);  // restore the StringBuilder
        }


    }


    public static void main(String[] args) {
        RestoreIPAddresses restoreIPAddresses = new RestoreIPAddresses();
        System.out.println(restoreIPAddresses.restoreIpAddresses("25525511135"));
        System.out.println(restoreIPAddresses.restoreIpAddresses("010010"));
    }
}
