package com.hui.UnionFindD;

import com.hui.Array.Matrix;

import java.util.*;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/26 9:56
 *
 * 721. Accounts Merge
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
 *
 * Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
 *
 * After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
 *
 * Example 1:
 * Input:
 * accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
 * Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
 * Explanation:
 * The first and third John's are the same person as they have the common email "johnsmith@mail.com".
 * The second John and Mary are different people as none of their email addresses are used by other accounts.
 * We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
 * ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
 * Note:
 *
 * The length of accounts will be in the range [1, 1000].
 * The length of accounts[i] will be in the range [1, 10].
 * The length of accounts[i][j] will be in the range [1, 30].
 *
 *
 */
public class AccountsMerge {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        int N = accounts.size();
        int[] parents = new int[N];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }

        Map<String, Integer> emailsId = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {
                String email = accounts.get(i).get(j);
                if (emailsId.containsKey(email))
                {
                    int person = emailsId.get(email);
                    int p1 = findParent(parents, i);
                    int p2 = findParent(parents, person);
                    if(p1 != p2)
                    {
                        parents[p1] = p2;
                    }

                }else {
                    emailsId.put(email, i);
                }
            }
        }

        Map<Integer, TreeSet<String>> users = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            int p = findParent(parents, i);
            List<String> emails = accounts.get(i);
            if(!users.containsKey(p))
            {
                users.put(p, new TreeSet<>());
            }
            users.get(p).addAll(emails.subList(1, emails.size()));
        }

        List<List<String>> res = new ArrayList<>();
        for(Integer idx: users.keySet())
        {
            String name = accounts.get(idx).get(0);
            ArrayList<String> emails = new ArrayList<>();
            emails.add(name);
            emails.addAll(users.get(idx));
            res.add(emails);
        }
        return res;

    }

    private int findParent(int[] parents, int idx)
    {
        while (idx != parents[idx])
        {
            parents[idx] = parents[parents[idx]];
            idx = parents[idx];
        }
        return idx;
    }

    public static void main(String[] args) {

        List<List<String>> lists = new ArrayList<>();
        lists.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
        lists.add(Arrays.asList("John", "johnnybravo@mail.com"));
        lists.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        lists.add(Arrays.asList("Mary", "mary@mail.com"));
        AccountsMerge accountsMerge = new AccountsMerge();

        List<List<String>> lists1 = accountsMerge.accountsMerge(lists);
        Matrix.print(lists1);

    }
}
