package com.hui.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/7 14:23
 *
 *388. Longest Absolute File Path
 * DescriptionHintsSubmissionsDiscussSolution
 * Suppose we abstract our file system by a string in the following manner:
 *
 * The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:
 *
 * dir
 *     subdir1
 *     subdir2
 *         file.ext
 * The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.
 *
 * The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:
 *
 * dir
 *     subdir1
 *         file1.ext
 *         subsubdir1
 *     subdir2
 *         subsubdir2
 *             file2.ext
 * The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext and an empty second-level sub-directory subsubdir1. subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.
 *
 * We are interested in finding the longest (number of characters) absolute path to a file within our file system. For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and its length is 32 (not including the double quotes).
 *
 * Given a string representing the file system in the above format, return the length of the longest absolute path to file in the abstracted file system. If there is no file in the system, return 0.
 *
 * Note:
 * The name of a file contains at least a . and an extension.
 * The name of a directory or sub-directory will not contain a ..
 * Time complexity required: O(n) where n is the size of the input string.
 *
 * Notice that a/aa/aaa/file1.txt is not the longest file path, if there is another path aaaaaaaaaaaaaaaaaaaaa/sth.png.
 *
 */
public class LongestAbsoluteFilePath {

    public int lengthLongestPath(String input) {

        String[] dirs = input.split("\n");
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        int maxLen = 0;
        for(String dir: dirs)
        {
            int level = dir.lastIndexOf("\t") +1;   // the level, level start from 0
            while (stack.size() > level +1)stack.pop();  // find it's parent
            int len = stack.peek() + dir.length() - level + 1; // remove "/t" and add "/"
            stack.push(len);

            // dir is a file if contains '.'
            if(dir.lastIndexOf('.') != -1)
            {
                maxLen = Math.max(maxLen, len -1);
            }

        }

        return maxLen;
    }

    public int lengthLongestPath2(String input) {

        String[] dirs = input.split("\n");
        int[] stack = new int[dirs.length +1];
        int idx = 1;
        int maxLen = 0;
        for(String dir: dirs)
        {
            int level = dir.lastIndexOf("\t") +1;   // the level, level start from 0
            while (idx > level +1)idx--;  // find it's parent
            int len = stack[idx -1] + dir.length() - level + 1; // remove "/t" and add "/"
            stack[idx++] = len;

            // dir is a file if contains '.'
            if(dir.lastIndexOf('.') != -1)
            {
                maxLen = Math.max(maxLen, len -1);
            }

        }

        return maxLen;
    }


    public static void main(String[] args) {
        LongestAbsoluteFilePath longestAbsoluteFilePath = new LongestAbsoluteFilePath();
        System.out.println(longestAbsoluteFilePath.lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"));
        System.out.println(longestAbsoluteFilePath.lengthLongestPath2("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"));
    }

}
