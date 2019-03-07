package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/15 18:25
 */
public class ValidNumber {


    /**
     *65. Valid Number
     * DescriptionHintsSubmissionsDiscussSolution
     * Validate if a given string can be interpreted as a decimal number.
     *
     * Some examples:
     * "0" => true
     * " 0.1 " => true
     * "abc" => false
     * "1 a" => false
     * "2e10" => true
     * " -90e3   " => true
     * " 1e" => false
     * "e3" => false
     * " 6e-1" => true
     * " 99e2.5 " => false
     * "53.5e93" => true
     * " --6 " => false
     * "-+3" => false
     * "95a54e53" => false
     *
     * Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one. However, here is a list of characters that can be in a valid decimal number:
     *
     * Numbers 0-9
     * Exponent - "e"
     * Positive/negative sign - "+"/"-"
     * Decimal point - "."
     * Of course, the context of these characters also matters in the input.
     *
     * Update (2015-02-10):
     * The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, please click the reload button to reset your code definition.
     *
     * @param s
     * @return
     */
    public boolean isNumber(String s) {

        return s.trim().matches("[-+]?((\\d+(\\.\\d*)?)|\\.\\d+)(e[-+]?\\d+)?");
    }

    public boolean isNumber2(String s) {
        char[] data = s.toCharArray();
        int st = 0;
        int len = s.length();
        while ((st < len) && (data[st] <= ' ')) {
            st++;
        }
        while ((st < len) && (data[len - 1] <= ' ')) {
            len--;
        }
        boolean isLittlePoint = false;
        boolean isE = false;
        boolean isDigit = false;
        for (int i = st; i < len; i++) {
            char ch =  data[i];
            if(ch >= 48 && ch <= 57)
            {
                isDigit = true;

            } else if(ch == 46)
            {
                if(isLittlePoint || isE)return false; // has e or has point.
                isLittlePoint = true;
            } else if(ch == 'e')  // before e/E, there must be have a digit
            {
                if (isE || !isDigit) return false;
                isE = true;
                isDigit = false;
            } else if (ch == '-' || ch == '+')
            {
               if( i != st && data[i-1] != 'e')return false;
            }else {
                return false;
            }

        }

        return isDigit == true;
    }


    /**
     *
     * https://github.com/normanyahq/LeetCodeSolution/blob/master/Valid Number/valid_number_dfa.svg
     *
     * @param s
     * @return
     */
    public boolean isNumberDFA(String s) {

        int currentState = 1;
        char[] val = s.toCharArray();
        for (int i = 0; i < val.length; i++) {
            char ch = val[i];
            if(ch <= '9' && ch >= '0')
            {
                if(currentState <= 3)currentState = 3;  // state 1/state 2 /state 3 to state 3
                else if(currentState <=5)   // state 4/state 5 to state 5
                {
                    currentState = 5;
                }else if(currentState <=8)
                {
                    currentState = 8;// state 6 /state 7 to state 8,
                }
                else return false;
            }else if(ch == '-' || ch == '+')
            {
                if(currentState == 1 || currentState == 6)currentState++;
                else return false;
            }else if(ch == 'e')
            {
                if(currentState == 3 || currentState == 5)currentState = 6;
                else return false;
            }else if(ch == ' ')
            {
                if(currentState == 1)
                {
                    currentState = 1;
                }else if(currentState == 3 || currentState == 5 || currentState == 8 || currentState == 9)
                {
                    currentState = 9;
                }else return false;
            }else if(ch == '.')
            {
                if(currentState == 1 || currentState == 2)
                {
                    currentState = 4;
                }else if(currentState == 3)
                {
                    currentState = 5;
                }else return false;
            }else return false;
        }

        return currentState == 3 || currentState == 5 || currentState == 8 || currentState == 9;
    }
    public static void main(String[] args) {
        ValidNumber validNumber = new ValidNumber();
        System.out.println(validNumber.isNumber2(" -54.53061"));
        System.out.println(validNumber.isNumber2(" -.53061"));
        System.out.println(validNumber.isNumberDFA(" -.53061"));
        System.out.println(validNumber.isNumberDFA(" -54.53061"));
        System.out.println(validNumber.isNumberDFA(" -5a4.53061"));
        System.out.println(validNumber.isNumberDFA(" -54.5e3061"));
        System.out.println(validNumber.isNumberDFA("1 4"));
    }
}
