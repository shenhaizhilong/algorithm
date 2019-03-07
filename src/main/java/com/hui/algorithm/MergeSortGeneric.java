package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/7/7 18:50
 */
public class MergeSortGeneric {

    public static <T extends Comparable<? super T>> void mergeSort(T[] array)
    {
        T[] temparray = (T[])new Comparable[array.length];
        mergeSort(array, temparray,0, array.length -1);

    }

    private static <T extends Comparable<? super T>> void mergeSort(T[] array, T[] temparray, int start, int end)
    {

        if(start < end)
        {
            int middle = (start + end)>>>1;
            mergeSort(array, temparray, start, middle);
            mergeSort(array, temparray, middle + 1, end);
            merge(array, temparray, start, middle, end);
        }
    }

    private static <T extends Comparable<? super T>> void merge(T[] array, T[] temparray, int leftstart, int middle, int rightend)
    {
        int rightstart = middle + 1;
        int temppos = leftstart;
        int numberelements = rightend - leftstart + 1;


        while (leftstart<= middle && rightstart <=rightend)
        {
            if(array[leftstart].compareTo(array[rightstart]) <=0)
            {
                temparray[temppos++] = array[leftstart++];
            }else {
                temparray[temppos++] = array[rightstart++];
            }
        }

        //copy rest of left half

        while (leftstart <=middle)
        {
            temparray[temppos++] = array[leftstart++];
        }

        //copy rest of right half

        while (rightstart <=rightend)
        {
            temparray[temppos++] = array[rightstart++];
        }

        //copy temp array back to array
        for (int i = 0; i < numberelements; i++) {
            array[rightend] = temparray[rightend];
            rightend--;
        }
    }


}
