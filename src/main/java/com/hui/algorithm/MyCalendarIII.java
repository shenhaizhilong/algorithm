package com.hui.algorithm;

import java.util.TreeMap;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/6 19:08
 *
 *
 * 732. My Calendar III
 * DescriptionHintsSubmissionsDiscussSolution
 * Implement a MyCalendarThree class to store your events. A new event can always be added.
 *
 * Your class will have one method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.
 *
 * A K-booking happens when K events have some non-empty intersection (ie., there is some time that is common to all K events.)
 *
 * For each call to the method MyCalendar.book, return an integer K representing the largest integer such that there exists a K-booking in the calendar.
 *
 * Your class will be called like this: MyCalendarThree cal = new MyCalendarThree(); MyCalendarThree.book(start, end)
 * Example 1:
 *
 * MyCalendarThree();
 * MyCalendarThree.book(10, 20); // returns 1
 * MyCalendarThree.book(50, 60); // returns 1
 * MyCalendarThree.book(10, 40); // returns 2
 * MyCalendarThree.book(5, 15); // returns 3
 * MyCalendarThree.book(5, 10); // returns 3
 * MyCalendarThree.book(25, 55); // returns 3
 * Explanation:
 * The first two events can be booked and are disjoint, so the maximum K-booking is a 1-booking.
 * The third event [10, 40) intersects the first event, and the maximum K-booking is a 2-booking.
 * The remaining events cause the maximum K-booking to be only a 3-booking.
 * Note that the last event locally causes a 2-booking, but the answer is still 3 because
 * eg. [10, 20), [10, 40), and [5, 15) are still triple booked.
 *
 *
 * Note:
 *
 * The number of calls to MyCalendarThree.book per test case will be at most 400.
 * In calls to MyCalendarThree.book(start, end), start and end are integers in the range [0, 10^9].
 *
 */
public class MyCalendarIII {

    private SegmentTreeNode root;
    public MyCalendarIII() {
        root = new SegmentTreeNode(0, 1_000_000_000,0);
    }

    public int book(int start, int end) {

        update(root, start, end -1, 1);
        return root.k;
    }

    private static class SegmentTreeNode{
        int start;
        int end;
        int k;   //k-booking within the range [start, end], to determine whether this event can be booked or not.
        int lazy;
        SegmentTreeNode left;
        SegmentTreeNode right;

        public SegmentTreeNode(int start, int end, int k)
        {
            this.start = start;
            this.end = end;
            this.k  = k;
        }
    }

    //  the query function should return the maximum integer k such that there exists a k-booking within the range [i, j],
    // so that we can use the information to determine whether this event can be booked or not.

    private int query(SegmentTreeNode node, int start, int end)
    {
        normalize(node);
        if( start > end || node == null || start > node.end || end < node.start)return 0;
        if(start <= node.start && node.end <= end)return node.k;  // range[start,end] in this segment node.
        return Math.max(query(node.left, start,end), query(node.right, start,end));
    }


    //If this event can be booked, the update function then will update all segments within the range [i, j]
    // accordingly (in this case, increase the k value of all segments within the range [i, j] by 1).

    private void update(SegmentTreeNode node, int start, int end, int val)
    {
        // the node may have been marked lazy from previous steps, so we need to remove the laziness in order to avoid overwriting prior updates.
        normalize(node);
        // out of range
        if(start > end || node == null || start > node.end || end < node.start )return;

        // the query range in current segment
        if(start <= node.start && node.end <= end)
        {
            node.lazy = val; // mark this node as lazy
            normalize(node);
            return;
        }

        update(node.left, start, end, val);
        update(node.right, start, end, val);
        node.k = Math.max(node.left.k, node.right.k);

    }

    // lazy propagation
    private void normalize(SegmentTreeNode node)
    {
        if(node == null)return;

        //Update current segment node if it is marked lazy -- in this case, a node is considered lazy if its lazy field is greater than 0,
        // and the updating is done by adding the lazy field to the k field.
        if(node.lazy > 0) node.k += node.lazy;

        if(node.start < node.end)
        {
            if(node.left == null || node.right == null)
            {
                int middle = (node.start + node.end) >>> 1;
                node.left = new SegmentTreeNode(node.start, middle, node.k);
                node.right = new SegmentTreeNode(middle +1, node.end, node.k);
            }else if(node.lazy > 0)
            {
                //Push the laziness down to the child node
                node.left.lazy += node.lazy;
                node.right.lazy += node.lazy;
            }
        }


        node.lazy = 0;
    }



    // Summarize
    //This is to find the maximum number of concurrent ongoing event at any time.
    //
    //We can log the start & end of each event on the timeline, each start add a new ongoing event at that time,
    // each end terminate an ongoing event. Then we can scan the timeline to figure out the maximum number of ongoing event at any time.
    //
    //The most intuitive data structure for timeline would be array, but the time spot we have could be very sparse, so we can use sorted map to simulate the time line to save space.

    TreeMap<Integer,Integer> counter = new TreeMap<>();
    public int book2(int start, int end) {
        counter.put(start, counter.getOrDefault(start,0 ) + 1);
        counter.put(end, counter.getOrDefault(end,0) -1);
        int count = 0;
        int k = 0;
        for(int c:counter.values())
        {
            count += c;
            k = Math.max(k, count);
        }
        return k;

    }


    public static void main(String[] args) {
        MyCalendarIII myCalendarIII = new MyCalendarIII();
        System.out.println(myCalendarIII.book(10,20));
        System.out.println(myCalendarIII.book(50,60));
        System.out.println(myCalendarIII.book(10,40));
        System.out.println(myCalendarIII.book(5,15));
        System.out.println(myCalendarIII.book(5,10));
        System.out.println(myCalendarIII.book(25,55));
    }

}
