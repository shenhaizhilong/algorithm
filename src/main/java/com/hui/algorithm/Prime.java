package com.hui.algorithm;
public class Prime
{

    public static boolean isPrime1( int n )
    {
        if( n == 2 || n == 3 )
            return true;

        if( n == 1 || (n &1) == 0 )
            return false;

        for( long i = 3; i * i <= n; i += 2 )
        {
            if( n % i == 0 )
            {
                System.out.println(i);
                return false;
            }
        }
        return true;
    }

    public static boolean isPrime2(int n)
    {

        if( n == 2 || n == 3 )
            return true;

        if( n == 1 || (n &0b01) == 0 )
            return false;
        long divisor = 3;
        while (divisor*divisor<=n)
        {
            if(n%divisor ==0)return false;
            divisor +=2;
        }
        return true;

    }



    public static boolean millerRabbin(int n, int a)
    {
        int r=0,s=n-1,j;
        return false;
    }

    public static boolean isPrime4(int n)
    {
        //https://en.wikipedia.org/wiki/Primality_test
//        The algorithm can be improved further by observing that all primes are of the form 6k ± 1,
//        with the exception of 2 and 3. This is because all integers can be expressed as (6k + i)
//        for some integer k and for i = −1, 0, 1, 2, 3, or 4; 2 divides (6k + 0), (6k + 2), (6k + 4);
//        and 3 divides (6k + 3). So, a more efficient method is to test if n is divisible by 2 or 3,
//        then to check through all the numbers of form 6k+-1 <=sqrt(n). This is 3 times as fast as testing all m.
        // method 4 is better than 3,2,1
        if(n<=1)return false;
        if(n<=3)return true;
        if((n&0x01)==0 || (n%3)==0)return false;
        long  i=5;
        while(i*i<=n)
        {
            if(n%i==0 || n%(i+2)==0)return false;
            i+=6;
        }
        return true;
    }


    public static boolean isPrime4(long n)
    {
        if(n<=1)return false;
        if(n<=3)return true;
        if((n&0x01)==0 || (n%3)==0)return false;
        long  i=5;
        long k =i*i;
        while((k =i*i)<=n && k>0)
        {
            if(n%i==0 || n%(i+2)==0)return false;
            i+=6;
        }
        return true;
    }

    public static boolean MillerRabin(int n,int a)
    {
        if(n<2)return false;
        // n = (2^r)*d+1
        if(n%a==0)
            return false;
        int x;
        int r=0, d = n-1; // n is odd, d must be even
        while ((d&0x01)!=1)
        {
            r++;
            d = d>>>1;
        }
        //System.out.println("a:" + a+", d:" + d+", n:" + n);
       long kk = montgomery(a, d, n);
      // System.out.println("KK:" + kk);
        if(kk==1)
            return true;
        for(int j=0;j<r;j++,kk=(kk*kk)%n)
        {
            if(kk==n-1)
                return true;
        }
        return false;
    }

    public static boolean isPrime5(int n)
    {
        int[] primes = {2,3,5,7,11,13,17,19};
        for (int i = 0; i < primes.length; i++) {
            if(n==primes[i])return true;
            if(!MillerRabin(n, primes[i]))
                return false;
        }
        return true;
    }
    public static int eraSieveCalcPrimeNumber(int n)
    {
        if(n<2)return 0;
        int totalCount=0;
        boolean[] notPrime = new boolean[n+1];
        for (int i=2; i<=n; i++)
        {
            for(int j = 2*i; j<=n; j+=i)
            {
                notPrime[j] = true;
            }
        }

        for (int i = 2; i < notPrime.length; i++) {
            if(!notPrime[i])totalCount++;
        }
        return totalCount;

    }

    public static int eraSieveCalcPrimeNumber2(int n)
    {
        if(n<2)return 0;
        int totalCount=1;
        boolean[] notPrime = new boolean[n+1];
        for (int i=3; i<=n; i+=2)  // 只需考虑奇数是不是素数
        {
            for(int j = 2*i; j<=n; j+=i)  //
            {
                System.out.println("j:" + j +", " + i);
                notPrime[j] = true;
            }
        }

        for (int i = 3; i < notPrime.length; i+=2) {
            if(!notPrime[i])totalCount++;
        }
        return totalCount;

    }

    public static int eraSieveCalcPrimeNumber3(int n)
    {
        //O（
        if(n<2)return 0;
        int totalCount=1;
        boolean[] notPrime = new boolean[n+1]; // 非素数设为true
        for (int i=3; i<=n; i+=2)   // 只需考虑奇数是不是素数
        {
            if(!notPrime[i])  //只需考虑素数，非素数不需要计算其倍数
            {
                for(int j = 2*i; j<=n; j+=i)  //只需要考虑素数的倍数，缺点是素数的倍数有可能重复，例如：i=3,j=195;i=5,j=195
                {
                    if((j&0x01)==1)    //素数的倍数不是素数
                    {
                        notPrime[j] = true;
                    }

                }
            }

        }

        for (int i = 3; i < notPrime.length; i+=2) {
            if(!notPrime[i])totalCount++;
        }
        return totalCount;

    }

    public static int eulerSieveCalcPrimeNumber4(int n)
    {
        if(n<2)return 0;
        int totalCount=1;
        boolean[] notPrime = new boolean[n+1]; // 非素数设为true
        int[] prime = new int[n+1];
        prime[0]=2;
        for (int i=3; i<=n; i+=2)   // 只需考虑奇数是不是素数
        {
            if(!notPrime[i])
            {
                prime[totalCount++] = i;
            }
            for(int j=1;i*prime[j]<=n && j<totalCount;j++)
            {
                //System.out.println("i: " + i +", j: " + j +", i*prime[j] " + i*prime[j]);
                notPrime[i*prime[j]] = true;
                if(i%prime[j]==0)  //任何一个合数，只能被它最小的质因数标记过一次
                    break;
            }
        }
        return totalCount;
    }



    public static int[] getPrimeArray(int n)
    {
        if(n<2)return null;
        int totalCount=1;
        boolean[] notPrime = new boolean[n+1]; // 非素数设为true
        int[] prime = new int[n+1];
        prime[0]=2;
        for (int i=3; i<=n; i+=2)   // 只需考虑奇数是不是素数
        {
            if(!notPrime[i])
            {
                prime[totalCount++] = i;
            }
            for(int j=1;i*prime[j]<=n && j<totalCount;j++)
            {
                //System.out.println("i: " + i +", j: " + j +", i*prime[j] " + i*prime[j]);
                notPrime[i*prime[j]] = true;
                if(i%prime[j]==0)  //任何一个合数，只能被它最小的质因数标记过一次
                    break;
            }
        }
        return prime;
    }



    public static int calculateNummber(int Nmax)
    {
        //please reference https://www.zhihu.com/question/24942373
        boolean[] isPrime = new boolean[Nmax +1];
        int[] prime = new int[Nmax];
        int totalPrimes = 1;
        for(int i = 3; i<=Nmax; i+=2)
            isPrime[i] = true; //除2的偶数不是素数，下行设置2为素数
        isPrime[2] = true;
        prime[0] = 2;//第一个素数
        for (int i = 3; i<=Nmax; i+=2)  //只需判断奇数是不是素数即可
        {
            if(isPrime[i])
                prime[totalPrimes++] = i;
            for(int j=1; i*prime[j]<=Nmax && j<totalPrimes; j++)
            {
                //System.out.println(i*prime[j]);
                isPrime[i*prime[j]] = false; // 素数的倍数必不是素数
                if(i%prime[j] ==0)
                    break;
            }
        }
        return totalPrimes;

    }


    public static long montgomery(int n, int p, int m)
    {

//        由于：C*k % n = (C % n)*(k % n) % n
//        所以令:
//        C1 = C*C % N =C^2 % N       1   15
//        C2 = C1*C % N =C^3 % N      3   7
//        C3 = C2*C2 % N =C^6 % N
//        C4 = C3*C % N =C^7 % N      7   3
//        C5 = C4*C4 % N =C^14 % N
//        C6 = C5*C % N =C^15 % N     15  1
//        蒙哥马利算法*/
        if(n<0 || m<=0)
            throw new IllegalArgumentException();
        if(p==0)return 1;
        long r=1;
        long nn = n;
        while (p>0)
        {
            if((p&0x01)==1)
                r = (r*nn)%m;
            nn=(nn*nn)%m;
            p=p>>1;
        }
        return r;
    }

    public static void main(String[] args) {
//        final int Nmax = 100000000;
//        final int n=10;
//        double startTime = System.currentTimeMillis();
//        int primeNum=0;
//        for (int i = 0; i <n ; i++) {
//            primeNum = Prime.calculateNummber(Nmax);
//        }
//        double timeSpent = (System.currentTimeMillis() - startTime)/10000;
//        System.out.println("the prime number from 1 to " + Nmax + " is " + primeNum);
//        System.out.println("Time spent: " + timeSpent + " s");

//        startTime = System.currentTimeMillis();
//        primeNum = 0;
//        for (int i = 0; i < Nmax; i++) {
//            if(isPrime(i))
//                primeNum++;
//        }
//        timeSpent = (System.currentTimeMillis() - startTime)/1000;
//
//        System.out.println("the prime number from 1 to " + Nmax + " is " + primeNum);
//        System.out.println("Time spent: " + timeSpent + " s");
//
//        startTime = System.currentTimeMillis();
//        primeNum = 0;
//        for (int i = 0; i < Nmax; i++) {
//            if(isPrime2(i))
//                primeNum++;
//        }
//        timeSpent = (System.currentTimeMillis() - startTime)/1000;
//
//        System.out.println("the prime number from 1 to " + Nmax + " is " + primeNum);
//        System.out.println("Time spent: " + timeSpent + " s");

//        startTime = System.currentTimeMillis();
//        primeNum = Prime.eraSieveCalcPrimeNumber(Nmax);
//        timeSpent = (System.currentTimeMillis() - startTime)/1000;
//        System.out.println("the prime number from 1 to " + Nmax + " is " + primeNum);
//        System.out.println("Time spent: " + timeSpent + " s");
//
//        startTime = System.currentTimeMillis();
//        primeNum = Prime.eraSieveCalcPrimeNumber2(Nmax);
//        timeSpent = (System.currentTimeMillis() - startTime)/1000;
//        System.out.println("the prime number from 1 to " + Nmax + " is " + primeNum);
//        System.out.println("Time spent: " + timeSpent + " s");

//
//        primeNum=0;
//        startTime = System.currentTimeMillis();
//        for (int i = 0; i <n ; i++) {
//            primeNum = Prime.eulerSieveCalcPrimeNumber4(Nmax);
//        }
//
//        timeSpent = (System.currentTimeMillis() - startTime)/10000;
//        System.out.println("the prime number from 1 to " + Nmax + " is " + primeNum);
//        System.out.println("Time spent: " + timeSpent + " s");




        //Prime.eulaSieveCalcPrimeNumber4(100);

        // 费马小定理 如果p 是素数，且0<A<p,则 A^(p-1) %p=1； 如果等式不成立，则p必不是素数；
        //若等式成立，那么n很可能是素数，341满足此公式但341不是素数
        //System.out.println(isPrime(341));




//         startTime = System.currentTimeMillis();
//
//         primeNum =0;
//        for (int i = 2; i < Nmax; i++) {
//            if(Prime.isPrime3(i,5))
//                primeNum++;
//        }
//        timeSpent = (System.currentTimeMillis() - startTime)/1000;
//        System.out.println("the prime number from 1 to " + Nmax + " is " + primeNum);
//        System.out.println("Time spent: " + timeSpent + " s");
//        // the prime number from 1 to 100000 is 9592
////        Time spent: 0.0015 s
////        false
////        the prime number from 1 to 100000 is 9592
////        Time spent: 0.063 s
//        // Prime.isPrime3 可以用于素性测试，但并不是一个高性能的素数统计算法
//
//
//        System.out.println(Prime.isPrime3((1<<31)-1, 5));
//
//
//        BigInteger bigInteger = BigInteger.valueOf(((long)1<<63 )-1);
//        System.out.println(bigInteger);
//        System.out.println(bigInteger.add(bigInteger));
//
//        System.out.println(montgomery(3,5555, 10));


//        primeNum =0;
//        startTime = System.currentTimeMillis();
//        for (int i = 2; i < Nmax; i++) {
//            if(Prime.isPrime1(i))
//                primeNum++;
//        }
//        timeSpent = (System.currentTimeMillis() - startTime)/1000;
//        System.out.println("Method1 the prime number from 1 to " + Nmax + " is " + primeNum);
//        System.out.println("Method1 Time spent: " + timeSpent + " s");
//
//        primeNum =0;
//        startTime = System.currentTimeMillis();
//        for (int i = 2; i < Nmax; i++) {
//            if(Prime.isPrime2(i))
//                primeNum++;
//        }
//        timeSpent = (System.currentTimeMillis() - startTime)/1000;
//        System.out.println("Method2 the prime number from 1 to " + Nmax + " is " + primeNum);
//        System.out.println("Method2 Time spent: " + timeSpent + " s");
//
//
//        primeNum =0;
//        startTime = System.currentTimeMillis();
//        for (int i = 2; i < Nmax; i++) {
//            if(Prime.isPrime3(i,5))
//                primeNum++;
//        }
//        timeSpent = (System.currentTimeMillis() - startTime)/1000;
//        System.out.println("Method3 the prime number from 1 to " + Nmax + " is " + primeNum);
//        System.out.println("Method3 Time spent: " + timeSpent + " s");
//
//
//        primeNum =0;
//        startTime = System.currentTimeMillis();
//        for (int i = 2; i < Nmax; i++) {
//            if(Prime.isPrime4(i))
//                primeNum++;
//        }
//        timeSpent = (System.currentTimeMillis() - startTime)/1000;
//        System.out.println("Method4 the prime number from 1 to " + Nmax + " is " + primeNum);
//        System.out.println("Method4 Time spent: " + timeSpent + " s");

//
//        int nn = (1<<31)-1;
//        System.out.println(nn);
//        startTime = System.currentTimeMillis();
//        System.out.println(isPrime1(nn));
//        timeSpent = (System.currentTimeMillis() - startTime)/1000;
//        System.out.println(" Common Time spent: " + timeSpent + " s");
//
//        startTime = System.currentTimeMillis();
//        System.out.println(isPrime4(nn));
//        timeSpent = (System.currentTimeMillis() - startTime)/1000;
//        System.out.println(" Step6 Time spent: " + timeSpent + " s");
//
//        startTime = System.currentTimeMillis();
//        System.out.println(Prime.isPrime5(nn));
//        timeSpent = (System.currentTimeMillis() - startTime)/1000;
//        System.out.println(" MillerRabin Time spent: " + timeSpent + " s");
//        System.out.println(Prime.isPrime1(nn));
//        System.out.println(Prime.isPrime2(nn));
//        System.out.println(Prime.isPrime3(nn, 5));
//        System.out.println(Prime.isPrime4(nn));
//        System.out.println(Prime.isPrime5(nn));
//
//        startTime = System.currentTimeMillis();
//        System.out.println(BigInteger.probablePrime(31, new java.util.Random(47)));
//        timeSpent = (System.currentTimeMillis() - startTime)/1000;
//        System.out.println(" Time spent: " + timeSpent + " s");
//        long a = 21474836479L ;
//        a = (a>>31) + (a&0x7fffffff);
//        System.out.println(a);
//        a = 21474836479L;
//        a = (a&0x7fffffff) + (a>>31);
//        System.out.println(a);
//        System.out.println(a-0x7fffffff);


        System.out.println(isPrime2(5381));
        System.out.println(isPrime4(2166136261L));

                int nn = (1<<31)-1;
        System.out.println(nn);
        System.out.println(isPrime1(nn));
        System.out.println(isPrime4(0x5bd1e995));
        System.out.println(isPrime4(63689));
        System.out.println(isPrime4(378551));
        System.out.println(isPrime4(131));
        System.out.println(isPrime4(1313));
        System.out.println(isPrime4(13131));
        System.out.println(isPrime4(65599));
        System.out.println(isPrime4(0xAAAAAAAA));

        for (int i = 1000000; i <1000100 ; i++) {
            if(isPrime4(i))
            {
                System.out.println(i);
                break;
            }
        }
    }
}
