package com.hui.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/9 17:51
 */
public class SimplifyPath {


    /**
     *71. Simplify Path
     * DescriptionHintsSubmissionsDiscussSolution
     * Given an absolute path for a file (Unix-style), simplify it.
     *
     * For example,
     * path = "/home/", => "/home"
     * path = "/a/./b/../../c/", => "/c"
     *
     * Corner Cases:
     *
     * Did you consider the case where path = "/../"?
     * In this case, you should return "/".
     * Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
     * In this case, you should ignore redundant slashes and return "/home/foo"
     *
     *
     * beats 95.4%
     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        if(path == null || path.length() == 0)return path;

        String[] p = path.split("\\/");
        Deque<String> deque = new ArrayDeque<>();
        for(String s:p)
        {
           if(!s.isEmpty())
           {
               if(s.equals("."))
               {
                   continue;
               }else if(s.equals("src/test"))
               {
                   deque.pollFirst();
               }else {
                   deque.addFirst(s);
               }
           }
        }
        StringBuilder sb = new StringBuilder(deque.size() +1);

        while (!deque.isEmpty())
        {
            sb.append('/');
            sb.append(deque.pollLast());
        }
        if(sb.length() == 0)
            return "/";

        return sb.toString();

    }

    public static void main(String[] args) {

        SimplifyPath simplifyPath = new SimplifyPath();
        System.out.println(simplifyPath.simplifyPath("/home/"));
        System.out.println(simplifyPath.simplifyPath("/../"));
        System.out.println(simplifyPath.simplifyPath("/home//foo/"));
        System.out.println(simplifyPath.simplifyPath("/a/./b/../../c/"));
    }
}
