package com.hui.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/29 16:54
 *
 *609. Find Duplicate File in System
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a list of directory info including directory path, and all the files with contents in this directory, you need to find out all the groups of duplicate files in the file system in terms of their paths.
 *
 * A group of duplicate files consists of at least two files that have exactly the same content.
 *
 * A single directory info string in the input list has the following format:
 *
 * "root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)"
 *
 * It means there are n files (f1.txt, f2.txt ... fn.txt with content f1_content, f2_content ... fn_content, respectively) in directory root/d1/d2/.../dm. Note that n >= 1 and m >= 0. If m = 0, it means the directory is just the root directory.
 *
 * The output is a list of group of duplicate file paths. For each group, it contains all the file paths of the files that have the same content. A file path is a string that has the following format:
 *
 * "directory_path/file_name.txt"
 *
 * Example 1:
 * Input:
 * ["root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"]
 * Output:
 * [["root/a/2.txt","root/c/d/4.txt","root/4.txt"],["root/a/1.txt","root/c/3.txt"]]
 * Note:
 * No order is required for the final output.
 * You may assume the directory name, file name and file content only has letters and digits, and the length of file content is in the range of [1,50].
 * The number of files given is in the range of [1,20000].
 * You may assume no files or directories share the same name in the same directory.
 * You may assume each given directory info represents a unique directory. Directory path and file info are separated by a single blank space.
 * Follow-up beyond contest:
 * Imagine you are given a real file system, how will you search files? DFS or BFS?
 * If the file content is very large (GB level), how will you modify your solution?
 * If you can only read the file by 1kb each time, how will you modify your solution?
 * What is the time complexity of your modified solution? What is the most time-consuming part and memory consuming part of it? How to optimize?
 * How to make sure the duplicated files you find are not false positive?
 *
 *
 */
public class FindDuplicateFileinSystem {


    public List<List<String>> findDuplicate(String[] paths) {
        Map<String,List<String>> map = new HashMap<>();
        for (int i = 0; i < paths.length; i++) {
            String[] values = paths[i].split(" ");
            String path = values[0];
            for (int j = 1; j < values.length; j++) {
                int idx = values[j].indexOf('(');
                String fileName = values[j].substring(0,idx);
                String content = values[j].substring(idx +1, values[j].length() -1);
                if(!map.containsKey(content))
                {
                    map.put(content, new ArrayList<>());
                }
                map.get(content).add(path +"/" + fileName);
            }
        }

        List<List<String>> ans = new ArrayList<>();
        for(List<String> v: map.values())
        {
            if(v.size() > 1) ans.add(v);
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] paths = {"root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"};
        FindDuplicateFileinSystem findDuplicateFileinSystem = new FindDuplicateFileinSystem();
        List<List<String>> ans = findDuplicateFileinSystem.findDuplicate(paths);
        for(List<String> v: ans)
        {
            System.out.println(v);
        }
    }

}
