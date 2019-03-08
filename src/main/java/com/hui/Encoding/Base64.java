package com.hui.Encoding;


/**
 * Created by zhang.lianhui on 6/11/2017.
 */
public class Base64
{
    // base 64 alphabet
    // https://tools.ietf.org/html/rfc4648#section-4
    final static char[] digits = {
            'A' , 'B' , 'C' , 'D' , 'E' , 'F' ,
            'G' , 'H' , 'I' , 'J' , 'K' , 'L' ,
            'M' , 'N' , 'O' , 'P' , 'Q' , 'R' ,
            'S' , 'T' , 'U' , 'V' , 'W' , 'X' ,
            'Y' , 'Z' , 'a' , 'b' , 'c' , 'd' ,
            'e' , 'f' , 'g' , 'h' , 'i' , 'j' ,
            'k' , 'l' , 'm' , 'n' , 'o' , 'p' ,
            'q' , 'r' , 's' , 't' , 'u' , 'v' ,
            'w' , 'x' , 'y' , 'z' , '0' , '1' ,
            '2' , '3' , '4' , '5' , '6' , '7' ,
            '8' , '9' , '+' , '/'
    };

    public static long getCharIndexNum(char ch)
    {
        int num = (int)ch;
        if (num >=48&& num<=57)
        {
            // 0-9
            return num -48+52;
        }
        else if (num>=97&& num<=122)
        {
            // a-z
            return num-97+26;
        }
        else if (num>=65&&num<=90)
        {
            // A-Z
            return num-65;
        }
        else if (num ==43)
        {
            // '+'
            return 62;
        }
        else if(num ==47)
        {
            // '/'
            return 63;
        }
        return 0;
    }

    /**
     * convert base10 to base64 str.
     * @param number
     * @param shiftNum
     * @return String
     */
    private static String CompressNumber(long number, int shiftNum)
    {
        char[] buf = new char[64];
        int charPos = 64;
        int radix = 1<<shiftNum;
        long mask = radix -1;
        do {
            buf[--charPos] = digits[(int)(number & mask )];
            number = number >>>shiftNum;
        }while (number !=0);

        return new String(buf, charPos, (64-charPos));
    }

    public static String CompressNumber(long number)
    {
        return CompressNumber(number,6);
    }
    /**
     * convert base64 str to base10 number
     * @param str
     * @return
     */
    public static long UnCompressNumber(String str)
    {
        long result =0;
        for (int i = str.length() -1; i >=0; i--) {
            if (i == str.length() -1)
            {
                result += getCharIndexNum(str.charAt(i));
                continue;
            }
            for (int j=0; j<digits.length;j ++)
            {
                if (str.charAt(i) == digits[j])
                {
                    result +=((long)j)<<6*(str.length() -1-i);
                }
            }
        }
        return result;
    }


    public static void main(String[] args)
    {
        int number =10;
        number >>>=1;
//        number = number>>7;
//        number = number <<6;
        long index1;

        char[] mychars = {'A','Z','a','z','0','9','+','/'};
        for (char c :
                digits) {
            index1 = getCharIndexNum(c);
            System.out.print(digits[(int)index1]);
        }
        System.out.println();
        long nn = 100000000;
        String base64 = CompressNumber(nn, 6);
        System.out.println(base64);
        long mm = UnCompressNumber(base64);
        System.out.println(mm);
       // System.out.println(number);
        System.out.println(UnCompressNumber("pmAfnMaAAhpVM6IDFI427"));
        System.out.println(UnCompressNumber("quAPKoQAACP9XSSjcw257"));
        System.out.println(CompressNumber(3030725682914356867L));
    }



}
