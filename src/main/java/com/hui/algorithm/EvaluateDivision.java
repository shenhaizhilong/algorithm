package com.hui.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/5 16:26
 *
 *399. Evaluate Division
 * DescriptionHintsSubmissionsDiscussSolution
 * Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.
 *
 * Example:
 * Given a / b = 2.0, b / c = 3.0.
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
 * return [6.0, 0.5, -1.0, 1.0, -1.0 ].
 *
 * The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.
 *
 * According to the example above:
 *
 * equations = [ ["a", "b"], ["b", "c"] ],
 * values = [2.0, 3.0],
 * queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 * The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
 *
 *
 * common root
 *
 *
 * 1. find :
 * a / b = 2.0 ; ratio[a] = 2, parent[a] = b
 * ==> b is the parent of a
 * ==> a = ratio.get(a) * parent.get(a);
 * "parent" restores the parent of the node; "ratio" restores factor. The formula is "node = parent * factor"
 * For example, "x / y = 2.0". Here, y is the parent of x; and the factor is 2.0.
 * If we also have "y / z = 3.0", which means that z is the final parent of x due to path compression; and the factor turns out to be 6.0.
 *
 *
 *
 *
 */
public class EvaluateDivision {

    Map<String,String> parent;
    Map<String,Double> ratio;
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {

        parent = new HashMap<>();
        ratio = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            union(equations[i][0], equations[i][1], values[i]);
        }

        double[] ans = new double[queries.length];
        for (int i = 0; i < ans.length; i++) {
            String node1 = queries[i][0];
            String node2 = queries[i][1];
            if(!parent.containsKey(node1) || !parent.containsKey(node2) || !find(node1).equals(find(node2)))
            {
                ans[i] = -1.0D;
            }else {

                // node1 and node2 share the common parent
                // so, ratio[a] = a/root, ratio[b] = b/root, then a/b = ratio[a]/ratio[b]
                ans[i] = ratio.get(node1)/ratio.get(node2);
            }
        }

        return ans;
    }

    private void union(String node1, String node2, double val)
    {
        if(!parent.containsKey(node1))
        {
            parent.put(node1,node1);
            ratio.put(node1, 1.0D);
        }

        if(!parent.containsKey(node2))
        {
            parent.put(node2,node2);
            ratio.put(node2, 1.0D);
        }

        String p1 = find(node1);
        String p2 = find(node2);

        parent.put(p1,p2);
        ratio.put(p1, val*ratio.get(node2)/ratio.get(node1));

    }


    /**
     *             before path compression:    after the path compression
     *                 c  1                      c  1
     *                /                         / \
     *               /                        /    \
     *              b  2                    b  2     a 6
     *             /
     *           /
     *          a   3
     *
     *
     *
     *
     *
     *
     *
     *
     *
     * @param node
     * @return
     */
    private String find(String node)
    {

        String father = parent.get(node);
        if(node.equals(father))
        {
            return node;
        }

        String grandpa = find(father); // path compression
        parent.put(node, grandpa);
        ratio.put(node, ratio.get(node)*ratio.get(father));
        return grandpa;

    }

    public static void main(String[] args) {


        String[][] equations = {{"x1","x2"},{"x2","x3"},{"x3","x4"},{"x4","x5"}};
        double[] values = {3.0, 4.0, 5.0, 6.0};
        String[][] queries = {{"x1","x5"}};
        EvaluateDivision evaluateDivision = new EvaluateDivision();
        Matrix.print(evaluateDivision.calcEquation(equations,values,queries));
    }

}
