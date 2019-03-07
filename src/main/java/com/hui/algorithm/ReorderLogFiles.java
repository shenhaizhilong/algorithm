package com.hui.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/7 20:29
 *
 *
 *
 * 937. Reorder Log Files
 * DescriptionHintsSubmissionsDiscussSolution
 * You have an array of logs.  Each log is a space delimited string of words.
 *
 * For each log, the first word in each log is an alphanumeric identifier.  Then, either:
 *
 * Each word after the identifier will consist only of lowercase letters, or;
 * Each word after the identifier will consist only of digits.
 * We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at least one word after its identifier.
 *
 * Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.  The digit-logs should be put in their original order.
 *
 * Return the final order of the logs.
 *
 *
 *
 * Example 1:
 *
 * Input: ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
 * Output: ["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
 *
 * Note:
 *
 * 0 <= logs.length <= 100
 * 3 <= logs[i].length <= 100
 * logs[i] is guaranteed to have an identifier, and a word after the identifier.
 *
 *
 */
public class ReorderLogFiles {


    public String[] reorderLogFiles(String[] logs) {
        List<String> letterLog = new ArrayList<>();
        List<String> digitLog = new ArrayList<>();
        for(String log:logs)
        {
            int idx = log.indexOf(" ") +1;
            char chr = log.charAt(idx);
            if(chr >= '0' && chr <= '9')
            {
                digitLog.add(log);
            }else {
                letterLog.add(log);
            }
        }

        Collections.sort(letterLog, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.substring(o1.indexOf(" ") + 1).compareTo(o2.substring(o2.indexOf(" ") +1));
            }
        });
        letterLog.addAll(digitLog);
        return letterLog.toArray(new String[]{});

    }

    public static void main(String[] args) {
        ReorderLogFiles reorderLogFiles = new ReorderLogFiles();
        Matrix.print(reorderLogFiles.reorderLogFiles(new String[]{"a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"}));
    }
}
