package com.hui.algorithm;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/26 22:13
 *
 *
 *
 * 726. Number of Atoms
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a chemical formula (given as a string), return the count of each atom.
 *
 * An atomic element always starts with an uppercase character, then zero or more lowercase letters, representing the name.
 *
 * 1 or more digits representing the count of that element may follow if the count is greater than 1. If the count is 1, no digits will follow. For example, H2O and H2O2 are possible, but H1O2 is impossible.
 *
 * Two formulas concatenated together produce another formula. For example, H2O2He3Mg4 is also a formula.
 *
 * A formula placed in parentheses, and a count (optionally added) is also a formula. For example, (H2O2) and (H2O2)3 are formulas.
 *
 * Given a formula, output the count of all elements as a string in the following form: the first name (in sorted order), followed by its count (if that count is more than 1), followed by the second name (in sorted order), followed by its count (if that count is more than 1), and so on.
 *
 * Example 1:
 * Input:
 * formula = "H2O"
 * Output: "H2O"
 * Explanation:
 * The count of elements are {'H': 2, 'O': 1}.
 * Example 2:
 * Input:
 * formula = "Mg(OH)2"
 * Output: "H2MgO2"
 * Explanation:
 * The count of elements are {'H': 2, 'Mg': 1, 'O': 2}.
 * Example 3:
 * Input:
 * formula = "K4(ON(SO3)2)2"
 * Output: "K4N2O14S4"
 * Explanation:
 * The count of elements are {'K': 4, 'N': 2, 'O': 14, 'S': 4}.
 * Note:
 *
 * All atom names consist of lowercase letters, except for the first character which is uppercase.
 * The length of formula will be in the range [1, 1000].
 * formula will only consist of letters, digits, and round parentheses, and is a valid formula as defined in the problem.
 *
 */
public class NumberofAtoms {

    public String countOfAtoms(String formula) {
        if(formula == null || formula.isEmpty())return formula;
        Map<String,Integer> map = parse(formula);
        StringBuilder sb = new StringBuilder();
        for(String element: map.keySet())
        {
            sb.append(element);
            int count = map.get(element);
            if(count > 1)
            {
                sb.append(count);
            }
        }
        return sb.toString();
    }

    private Map<String,Integer> parse(String formula)
    {
        Map<String,Integer> map = new TreeMap<>();
        if(formula == null || formula.isEmpty())return map;
        int idx = 0;
        int N = formula.length();
        while (idx < N)
        {
            if(formula.charAt(idx) == '(')
            {
                int diff = 1;
                int end = idx +1;
                while (end < N )
                {
                    if(formula.charAt(end) == '(')diff++;
                    else if(formula.charAt(end) == ')')diff--;


                    if(diff == 0)
                    {
                        break;
                    }
                    end++;

                }
                // current chr(end) == ')';
                String sub = formula.substring(idx +1, end);
                end++; //   current chr(end) == ')';

                int count = 1; // default count
                int start = end;
                while (end < N && formula.charAt(end) >= '0' && formula.charAt(end) <= '9')end++;
                if(end > start)
                {
                    count = Integer.valueOf(formula.substring(start, end));
                }

                Map<String,Integer> subMap = parse(sub);
                for(String element: subMap.keySet())
                {
                    map.put(element, map.getOrDefault(element, 0) + subMap.get(element)*count);
                }
                idx = end ;


            }else {
                int end = idx +1;
                while (end < N && formula.charAt(end) >= 'a' && formula.charAt(end) <= 'z')end++;
                String element = formula.substring(idx, end);
                int count = 1;
                int start = end;
                while (end < N && formula.charAt(end) >= '0' && formula.charAt(end) <= '9')end++;

                if(end > start)
                {
                    count = Integer.valueOf(formula.substring(start, end));
                }

                map.put(element, map.getOrDefault(element, 0) + count);
                idx = end;
            }

        }

        return map;
    }

    public static void main(String[] args) {
        NumberofAtoms numberofAtoms = new NumberofAtoms();
        System.out.println(numberofAtoms.countOfAtoms("K4(ON(SO3)2)2"));
        System.out.println(numberofAtoms.countOfAtoms("H2O"));
        System.out.println(numberofAtoms.countOfAtoms("Mg(OH)2"));
    }
}
