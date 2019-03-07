package com.hui.algorithm;

/** Introduction to Algorithms chapter six, heap sort
 * @author: shenhaizhilong
 * @date: 2018/7/6 11:29
 */
public class BinaryHeap {
    private int currentSize;
    private int[] values;


    public BinaryHeap() {
    }

    /**
     * 得到左孩子节点的索引
     *
     * @param i
     * @return
     */
    private int left(int i) {
        return (i<<1) + 1;
    }


    // 让根节点为i的子树重新遵循最大堆的性质, 循环法
    private void maxHeapify(int i) {

        int leftChildIndex;
        int largestIndex ;
        int rightChildIndex;
       while (true)
        {
            leftChildIndex = left(i);
            rightChildIndex = leftChildIndex + 1;
            if(leftChildIndex < currentSize && values[leftChildIndex] > values[i])
            {
                largestIndex = leftChildIndex;
            }else {
                largestIndex = i;
            }

            if(rightChildIndex < currentSize && values[rightChildIndex] > values[largestIndex])
            {
                largestIndex = rightChildIndex;
            }

            if(largestIndex == i)
            {
                break;
            }
            swap(values, i, largestIndex);
            // 交换后，可能违背最大堆的性质，因此需要对子树进行判断，这时largestIndex 不是left Index, 就是right Index
            i = largestIndex;

        }
    }



    // 让根节点为i的子树重新遵循最大堆的性质，递归方法
//    private void maxHeapify(int[] arrays, int i)
//    {
//        int largest;
//        int L = left(i);
//        int R = L + 1;
//
//
//        if(L <arrays.length && arrays[L] > arrays[i])
//        {
//            largest = L;
//        }else {
//            largest = i;
//        }
//
//        if(R < arrays.length && arrays[R] > arrays[largest])
//        {
//            largest = R;
//        }
//
//
//        if(largest != i)
//        {
//            swap(arrays, largest, i);
//            maxHeapify(arrays, largest);
//        }
//
//
//
//    }

    private void swap(int[] arrays, int i, int j)
    {
        int temp = arrays[i];
        arrays[i] = arrays[j];
        arrays[j] = temp;
    }


    public void buildBinaryHeap(int[] arrays)
    {
        currentSize = arrays.length;
        values = arrays;
       for (int i = arrays.length/2 -1; i>=0; i--)
       {
           maxHeapify(i);
       }
    }

    public void heapSort()
    {
       for(int i = currentSize -1; i > 0; i--)
       {
           swap(values, 0, i);   //delete max
           currentSize = currentSize -1;  // heap size -1
           maxHeapify(0);
       }

    }

}
