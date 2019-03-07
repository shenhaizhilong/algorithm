package com.hui.algorithm;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author: shenhaizhilong
 * @date: 2018/7/8 9:20
 */
public class Sort {

    private static final int cutoff = 11;

    public static void bubleSort(int[] array)
    {
        int N = array.length;
        boolean swapped = false;
        while (true)
        {
            swapped = false;
            for (int i = 1; i < N; i++) {
                if(array[i -1] > array[i])
                {
                    swap(array, i, i-1);
                    swapped = true;
                }
            }
            N = N -1;
            if(!swapped)break;
        }
    }

    /**
     * InsertSort , best case is O(n), worst case is O(n^2), average case is O(n^2)
     * @param array
     */
    public static void insertionSort(int[] array)
    {

        insetSort(array, 0, array.length -1);
    }

    public static void insetSort(int[] array, int start, int end)
    {
        int temp, j;
        for (int i = start +1; i <= end; i++) {
            temp = array[i];
            for ( j = i; j>start && array[j-1] > temp ; j--) {
                array[j] = array[j-1];
            }
            array[j] = temp;
        }
    }


    /**
     * InsertSort , best case is O(n), worst case is O(n^2), average case is O(n^2)
     * * @param array
     * @param <T>
     */

    public static <T extends Comparable<? super T>> void insertSort(T[] array)
    {
        insertSort(array, 0, array.length -1);
    }

    /**
     * Insert Sort
     * @param array
     * @param start
     * @param end
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void insertSort(T[] array, int start, int end)
    {

        T temp;
        int j;
        for (int i = start +1; i <= end ; i++) {
            temp = array[i];
            for (j = i; j > start && temp.compareTo(array[j-1]) <0 ; j--) {
                array[j] = array[j-1];
            }
            array[j] = temp;
        }
    }


    /**
     *  Shell sort, using shell's increments., 使用希尔增量的希尔排序的最坏情形运行时间为O(N^2)
     *
     * @param array
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void shellSort(T[] array)
    {
        int gap = array.length/2;
        int j;
        T temp;
        while (gap > 0)
        {
            for (int i = gap; i < array.length; i++) {
                temp = array[i];
                //大于temp的值向右移动,找到temp的正确索引位置然后插入temp,
                for(j = i; j >= gap && temp.compareTo(array[j-gap]) <0; j-=gap)
                {
                    array[j] = array[j-gap];
                }
                array[j] = temp;
            }

            gap = gap/2;
        }
    }

    /**
     * Shell sort, but using Hibbard's increments.{1, 3, 7..., 2^k -1}, 使用Hibbard 增量的希尔排序的最坏情形运行时间是O(N^(3/2)),平均情形模拟结果被认为是O(N^(5/4))
     * @param a an array of Comparable items
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void shellSort2(T[] a)
    {
        int k = (int)(Math.log10(a.length)/Math.log10(2));
        int gap,j;
        while (k>0)
        {
            gap = (1<<k) -1;
            for(int i=gap; i<a.length; i++)
            {
                T temp = a[i];
                //找到temp的正确索引位置然后插入temp,大于temp的值向右移动
                for(j=i; j>=gap && temp.compareTo(a[j-gap])<0; j-=gap)
                    a[j] = a[j-gap];
                a[j] = temp;
            }
            k -=1;
        }
    }

    /**
     * shell sort, using knuth's increments.
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void shellSort3(T[] a)
    {
        int k = (int) (Math.log10(2*a.length -1)/Math.log10(3));
        int gap,j;
        while (k>=0)
        {
            gap = (int)((Math.pow(3.0,k)+1)/2);
            for(int i=gap; i<a.length; i++)
            {
                T temp = a[i];
                //找到temp的正确索引位置然后插入temp,大于temp的值向右移动
                for(j=i; j>=gap && temp.compareTo(a[j-gap])<0; j-=gap)
                    a[j] = a[j-gap];
                a[j] = temp;
            }
            k -=1;
        }
    }

    /**
     * shell sort, using Sedgewick's increments.
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void shellSort4(T[] a)
    {
        int[] gapArray = {1,5,19,41,109};
        int gap,j, k= gapArray.length-1;
        while (k>=0)
        {
            gap = gapArray[k];
            for(int i=gap; i<a.length; i++)
            {
                T temp = a[i];
                //找到temp的正确索引位置然后插入temp,大于temp的值向右移动
                for(j=i; j>=gap && temp.compareTo(a[j-gap])<0; j-=gap)
                    a[j] = a[j-gap];
                a[j] = temp;
            }
            k -=1;
        }
    }

    /**
     * private method for heap sort.
     * @param i
     * @return
     */
    private static int leftChild(int i)
    {
        return (i<<1) +1;
    }

    public static void siftDown(int[] array, int i, int length)
    {
        int leftIndex;
        int rightIndex;
        int largestIndex;
        int rightMost = length -1;

        while (true)
        {
            leftIndex = leftChild(i);
            rightIndex = leftIndex + 1;
            largestIndex = i;
            if(leftIndex <= rightMost && array[leftIndex] > array[largestIndex])
            {
                largestIndex = leftIndex;
            }

            if(rightIndex <= rightMost && array[rightIndex] > array[largestIndex])
            {
                largestIndex = rightIndex;
            }

            if(largestIndex == i)
            {
                break;


            }

            swap(array, largestIndex, i);
            i = largestIndex;
        }

    }

    public static void heapSort(int[] array)
    {
        //build the heap

        for(int i = array.length/2 -1; i >= 0; i--)
        {
            siftDown(array, i, array.length);
        }

        //move the max to rightmost, and heap size = heap size -1
        for(int i = array.length -1; i>0; i--)
        {
            swap(array, 0, i);
            siftDown(array, 0, i);
        }

    }


    private static <T extends  Comparable<? super T>> void  percolateDown(T[] a, int i, int n)
    {
        T hole;
        int child;
        for(hole =a[i]; leftChild(i)<n; i = child)
        {
            child = leftChild(i);
            if(child!=n-1 && a[child].compareTo(a[child+1])<0)
                child++;
            if(hole.compareTo(a[child])<0)
                a[i] = a[child];
            else break;
        }
        a[i] = hole;
    }

    public static <T extends Comparable<? super T>> void  heapSort(T[] a)
    {
        //build the max heap
        for(int i=a.length/2 -1; i>=0; i--)
            percolateDown(a, i, a.length);
        // delete the max
        for(int i=a.length -1; i>0; i--)
        {
            // swap the last element and the max element.
            T temp = a[0];
            a[0] = a[i];
            a[i] = temp;
            percolateDown(a, 0, i);
        }

    }


    public static void mergeSort(int[] array) {
        int[] temparray = new int[array.length];
        mergeSort(array, temparray, 0, array.length - 1);

    }

    private static void mergeSort(int[] array, int[] temparray, int start, int end) {


        if (start < end) {
            int middle = (start + end) >>> 1;
            mergeSort(array, temparray, start, middle);
            mergeSort(array, temparray, middle + 1, end);
            merge(array, temparray, start, middle, end);
        }

    }

    private static void merge(int[] array, int[] temparray, int leftstart, int middle, int rightend) {
        int numberelements = rightend - leftstart + 1;
        int temppos = leftstart;
        int rightstart = middle + 1;
        while (leftstart <= middle && rightstart <= rightend) {
            if (array[leftstart] <= array[rightstart]) {
                temparray[temppos++] = array[leftstart++];
            } else {
                temparray[temppos++] = array[rightstart++];
            }
        }

        while (leftstart <= middle) {
            temparray[temppos++] = array[leftstart++];
        }

        while (rightstart <= rightend) {
            temparray[temppos++] = array[rightstart++];
        }

        for (int i = 0; i < numberelements; i++) {
            array[rightend] = temparray[rightend];
            rightend--;
        }
    }

    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }


    private static void quickSort(int[] array, int start, int end) {
        if (start < end) {

            int q = partition(array, start, end);
            quickSort(array, start, q-1);
            quickSort(array, q +1, end);
        }
    }

    //算法导论第七章

    /**
     * median of three method, pivot place into end
     * @param array
     * @param start
     * @param end
     * @return
     */
    private static int partition(int[] array, int start, int end)
    {

        int pivot = medianOfThree(array, start, end);
        int i = start -1;
        for (int j = start; j < end; j++) {
            if(array[j]< pivot)
            {
                i = i+1;
                swap(array, i, j);
            }
        }
        i = i +1;
        swap(array, i, end);
        return i;
    }


    /**
     * median of three method, pivot place into end -1
     * @param array
     */
    public static void quickSort2(int[] array) {
        quickSort2(array, 0, array.length - 1);
    }
    private static void quickSort2(int[] array, int start, int end) {
        if (start + cutoff <= end) {

            int q = partition2(array, start, end);
            quickSort2(array, start, q-1);
            quickSort2(array, q +1, end);
        }else {
            insetSort(array, start, end);
        }
    }

    private static int partition2(int[] array, int start, int end)
    {

        int pivot = medianOfThree2(array, start, end);
        int i = start;
        int j = end -1;
        for(;;)
        {
            while (array[++i] < pivot){}
            while (array[--j] > pivot){}
            if(i < j)
            {
                swap(array, i, j);
            }else {
                break;
            }

        }

        //restore pivot
        swap(array, i, end -1);
        return i;


    }
    private static void swap(int[] array, int i, int j)
    {
        if(i != j)
        {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    // right most it the median
    private static int medianOfThree(int[] array, int start, int end )
    {
        int middle = (start + end)>>>1;
        if(array[middle] < array[start])
            swap(array, middle, start);
        if(array[end] < array[start])
            swap(array, start, end);

        //place pivot (median) into right most
        if(array[middle] < array[end])
            swap(array, middle, end);
        return array[end];
    }

    // 最右边为最大值
    private static int medianOfThree2(int[] array, int start, int end )
    {
        int middle = (start + end)>>>1;
        if(array[middle] < array[start])
            swap(array, middle, start);
        if(array[end] < array[start])
            swap(array, start, end);
        if(array[end] < array[middle])
            swap(array, middle, end);

        //place median into (end -1), end is biggest value, left is smallest value
        swap(array, middle, end -1);
        return array[end -1];


    }

    /**
     * Radix sort an array of Strings
     * Assume all are all ASCII
     * Assume all have same length
     * @param arr
     * @param strLength
     */
    public static  void radixSort(String[] arr, int strLength)
    {
        final int BUCKETS = 256;

        //Build buckets
        ArrayList<String>[] buckets = new ArrayList[BUCKETS];
        for (int i = 0; i < BUCKETS; i++) {
            buckets[i] = new ArrayList<>();
        }

        for(int pos = strLength -1; pos >=0; pos--)
        {
            // in buckets
            for (String str :
                    arr) {
                buckets[str.charAt(pos)] .add(str);
            }

            // out buckets
            int index =0;
            for(ArrayList<String> thisBuckets : buckets)
            {
                for (String s :
                        thisBuckets) {
                    arr[index++] = s;
                }
                //clear this buckets
                thisBuckets.clear();
            }

        }

    }


    /**
     * Radix sort an array of Strings
     * Assume all are all ASCII
     * Assume all Strings have bounded by maxlen
     * @param arr
     * @param maxLen
     */
    public static void radixSort2(String[] arr, int maxLen)
    {
        final int BUCKETS = 256;
        ArrayList<String>[] worldLength = new ArrayList[maxLen +1];
        ArrayList<String>[] buckets = new ArrayList[BUCKETS];

        for(int i=0; i < worldLength.length; i++)
        {
            worldLength[i] = new ArrayList<>();
        }
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }

        //group by world length
        for (String s :
                arr) {
            worldLength[s.length()].add(s);
        }

        // restore arr
        int idx =0;
        for (ArrayList<String> w :
                worldLength) {
            for (String s :
                    w) {
                arr[idx++] = s;
            }
        }

        int startIndex = arr.length;
        for(int pos = maxLen -1; pos >=0; pos --)
        {
            startIndex -= worldLength[pos+1].size();
            // in buckets
            for (int i = startIndex; i < arr.length; i++) {
                buckets[arr[i].charAt(pos)].add(arr[i]);
            }

            idx = startIndex;
            // out buckets.
            for (ArrayList<String> thisBucket :
                    buckets) {
                for (String s :
                        thisBucket) {
                    arr[idx++] = s;
                }
                thisBucket.clear();
            }
        }
    }

    /**
     *  算法导论第九章 中位数和顺序统计量
     *  第k个顺序统计量是该集合中第k小的元素，例如,在一个集合中，最小值是第1个顺序统计量（i=0 数组索引位置)，最大值是第n个
     *  顺序统计量（i=n-1 数组索引位置），时间复杂度递推公式为T(n)=T(n/2) + theta(n) = O(n)
     *  a=1, b = 2, n^(logb(a) +e) = f(n)=n, 则e=1
     *  af(n/b)<=cf(n), n/2<=cn，对于足够大的n,只要c 满足 1/2 <= c <1 即可。
     * @param array
     * @param k
     * @return
     */
    public static int selectKthSmall(int[] array, int k)
    {

        if(k > array.length || k < 1)throw new IllegalArgumentException("k > array.length");
        return selectKthSmall(array, 0, array.length -1, k);
    }

    private static int selectKthSmall(int[] array,  int start, int end, int k)
    {
        if(start == end) return array[start];

        // pivot 所在的索引位置
        int q = selectKthPartition(array, start, end);
        //pivot 在数组里面是第kth 大
        int kth = q - start +1;
        if(k == kth)
        {
            return array[q];
        }
        else if(k < kth)   //k< kth 大,那第k 大元素必在左半部分
        {
            return selectKthSmall(array, start, q-1, k);
        }else {   //k> kth 大,那第k 大元素必在右半部分
            return selectKthSmall(array, q+1, end, k-kth);
        }



    }

    private static int selectKthPartition(int[] array, int start, int end)
    {
        int i = start -1;
        int j = end ;
        MyRandom myRandom = new MyRandom();
        int p = myRandom.nextInt(start, end);
        swap(array, p, end);
        int pivot = array[end];

        for(;;)
        {
            while (i <=end-1 && array[++i] < pivot){}
            while (j>=1 && array[--j] > pivot){}
            if(i < j)
            {
                swap(array, i,j);
            }else {
                break;
            }

        }
        //restore pivot
        swap(array, i, end);
        return i;
    }

    public static void shuffle(int[] nums)
    {
        Random random = new Random();
        int r;
        for (int i = 0; i < nums.length; i++) {
            r = random.nextInt(i + 1);
            swap(nums,i,r);
        }
    }


    /**
     *
     * for an example
     *  Array = {3,2,3,1,2,4,5,5,6}
     *
     *
     * len = 9
     * kthSmall
     * {1,2,2,3,3,4,5,5,6}
     *
     *   value, kthSmall ,kLargest
     *   1,     1,       9
     *   2,     2,       8,
     *   2,     3,       7,
     *   3,     4,       6,
     *   3,     5,       5,
     *   4,     6,       4,
     *   5,     7,       3,
     *   5,     8,       2,
     *   6,     9,       1,
     *
     * @param nums
     * @param kth
     * @return
     */
    public static int findKthLargest(int[] nums,int kth)
    {
        return selectKthSmall(nums, nums.length -kth + 1);
    }


    // random quick sort
    public static void randomQuickSort(int[] nums)
    {
        Random random = new Random();
        randomQuickSort(nums,0, nums.length -1, random);
    }

    public static void randomQuickSort(int[] nums, int start, int end, Random random)
    {
        if(start >= end)return;
        int[] range = partition(nums, start, end, random);
        randomQuickSort(nums, start, range[0], random);
        randomQuickSort(nums, range[1], end, random);
    }

    private static int[] partition(int[] nums, int startIdx, int endIdx, Random random)
    {
        int randomIdx = getRandomIdx(random, startIdx, endIdx);
        int randomNum = nums[randomIdx];
        int idx = startIdx;
        startIdx--;
        endIdx++;
        while (idx < endIdx)
        {
            if(nums[idx] < randomNum)
            {
                swap(nums, idx++, ++startIdx);
            }else if(nums[idx] > randomNum)
            {
                swap(nums, idx, --endIdx);
            }else {
                idx++;
            }
        }
        return new int[]{startIdx, endIdx};
    }

    private static int getRandomIdx(Random random, int startIdx, int endIdx)
    {
        int range = endIdx - startIdx +1;
        return random.nextInt(range) + startIdx;
    }


}
