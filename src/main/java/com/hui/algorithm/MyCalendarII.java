package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/6 14:19
 *731. My Calendar II
 * DescriptionHintsSubmissionsDiscussSolution
 * Implement a MyCalendarTwo class to store your events. A new event can be added if adding the event will not cause a triple booking.
 *
 * Your class will have one method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.
 *
 * A triple booking happens when three events have some non-empty intersection (ie., there is some time that is common to all 3 events.)
 *
 * For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a triple booking. Otherwise, return false and do not add the event to the calendar.
 *
 * Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 * Example 1:
 * MyCalendar();
 * MyCalendar.book(10, 20); // returns true
 * MyCalendar.book(50, 60); // returns true
 * MyCalendar.book(10, 40); // returns true
 * MyCalendar.book(5, 15); // returns false
 * MyCalendar.book(5, 10); // returns true
 * MyCalendar.book(25, 55); // returns true
 * Explanation:
 * The first two events can be booked.  The third event can be double booked.
 * The fourth event (5, 15) can't be booked, because it would result in a triple booking.
 * The fifth event (5, 10) can be booked, as it does not use time 10 which is already double booked.
 * The sixth event (25, 55) can be booked, as the time in [25, 40) will be double booked with the third event;
 * the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.
 * Note:
 *
 * The number of calls to MyCalendar.book per test case will be at most 1000.
 * In calls to MyCalendar.book(start, end), start and end are integers in the range [0, 10^9].
 *
 *
 *
 * https://leetcode.com/problems/my-calendar-ii/discuss/109528/nlogd-Java-solution-using-segment-tree-with-lazy-propagation-applicable-to-the-general-case-of-K-booking
 *
 *
 */
public class MyCalendarII {
    private SegmentTreeNode root;
    public MyCalendarII() {
        root = new SegmentTreeNode(0, 1_000_000_000,0);
    }

    public boolean book(int start, int end) {
        int k = query(root, start, end -1);
        if(k >= 2)return false;  // for k-booking issue, just update '2' to k -1
        update(root, start, end -1, 1);
        return true;
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

    public static void main(String[] args) {
        MyCalendarII myCalendarII = new MyCalendarII();
        System.out.println(myCalendarII.book(10,20));
        System.out.println(myCalendarII.book(50,60));
        System.out.println(myCalendarII.book(10,40));
        System.out.println(myCalendarII.book(5,15));
        System.out.println(myCalendarII.book(5,10));
        System.out.println(myCalendarII.book(25,55));
    }

}
