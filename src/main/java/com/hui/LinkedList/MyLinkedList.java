package com.hui.LinkedList;

/**
 *
 *
 * 707. Design Linked List
 * DescriptionHintsSubmissionsDiscussSolution
 * Design your implementation of the linked list. You can choose to use the singly linked list or the doubly linked list. A node in a singly linked list should have two attributes: val and next. val is the value of the current node, and next is a pointer/reference to the next node. If you want to use the doubly linked list, you will need one more attribute prev to indicate the previous node in the linked list. Assume all nodes in the linked list are 0-indexed.
 *
 * Implement these functions in your linked list class:
 *
 * get(index) : Get the value of the index-th node in the linked list. If the index is invalid, return -1.
 * addAtHead(val) : Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
 * addAtTail(val) : Append a node of value val to the last element of the linked list.
 * addAtIndex(index, val) : Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
 * deleteAtIndex(index) : Delete the index-th node in the linked list, if the index is valid.
 * Example:
 *
 * MyLinkedList linkedList = new MyLinkedList();
 * linkedList.addAtHead(1);
 * linkedList.addAtTail(3);
 * linkedList.addAtIndex(1, 2);  // linked list becomes 1->2->3
 * linkedList.get(1);            // returns 2
 * linkedList.deleteAtIndex(1);  // now the linked list is 1->3
 * linkedList.get(1);            // returns 3
 * Note:
 *
 * All values will be in the range of [1, 1000].
 * The number of operations will be in the range of [1, 1000].
 * Please do not use the built-in LinkedList library.
 *
 * @author: shenhaizhilong
 * @date: 2018/8/27 23:50
 */
public class MyLinkedList
{

    int size;
    Node head;
    Node tail;

    private class Node {
        int val;
        Node next;
        Node(int x) {
            this.val = x;
        }
    }

    /** Initialize your data structure here. */
    public MyLinkedList(){
        this.size = 0;
        this.head = null;
        this.tail = null;
    }


    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if(index < 0 || index >= this.size) {
            return -1;
        }
        int counter = 0;
        Node curr = head;
        while(counter != index) {
            curr = curr.next;
            counter++;
        }

        return curr.val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        Node newNode = new Node(val);
        newNode.next = this.head;
        this.head = newNode;
        if(this.size == 0)
        {
            this.tail = this.head;
        }

        this.size++;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        if(this.size == 0) {
            addAtHead(val);
            return;
        }
        Node newNode = new Node(val);
       tail.next = newNode;
       tail = tail.next;
        this.size++;
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if(index > this.size || index < 0) {
            return;
        }
        if(index == this.size) {
            addAtTail(val);
            return;
        }
        if(index == 0){
            addAtHead(val);
            return;
        }
        Node newNode = new Node(val);
        Node temp = head;
        int counter = 0;
        while(counter != index -1) {
            temp = temp.next;
            counter++;
        }
        newNode.next = temp.next;
        temp.next = newNode;
        this.size++;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {

        if(index < 0 || index >= this.size) {
            return;
        }
        Node current = head;
        if(index == 0) {
            head = current.next;
            this.size--;
            if(this.size == 0)
            {
                tail = head;
            }
        }
        else {
            Node pre = null;
            int counter =0;
            while(counter != index) {
                pre = current;
                current = current.next;
                counter++;
            }
            if(tail == current)
            {
                tail = pre;
            }
            pre.next = current.next;
            this.size--;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(size);
        Node curr = head;
        while (curr != null)
        {
            sb.append(curr.val +", ");
            curr = curr.next;
        }
        return sb.toString();
    }

    public static void main(String[] args) {

         MyLinkedList obj = new MyLinkedList();
//        int param_1 = obj.get(0);
//        System.out.println(param_1);
     //   obj.addAtHead(0);
       // obj.addAtTail(1);
        obj.addAtIndex(0,2);
        obj.addAtIndex(1,3);
       // obj.addAtIndex(1,4);
        obj.deleteAtIndex(1);
        System.out.println(obj.toString());
//        obj.deleteAtIndex(1);
//        obj.deleteAtIndex(1);
//        obj.deleteAtIndex(1);
//        obj.addAtIndex(0,2);
//        obj.addAtIndex(0,3);
//        obj.addAtIndex(0,4);
//        System.out.println(obj.toString());
//        obj.deleteAtIndex(0);
//        obj.deleteAtIndex(0);
//        obj.deleteAtIndex(0);
//        System.out.println(obj.toString());
//        obj.addAtHead(0);
//        obj.addAtTail(1);
//       // obj.deleteAtIndex(1);
//        //obj.deleteAtIndex(0);
//        System.out.println(obj.toString());
//        System.out.println(obj.get(0));
//        System.out.println(obj.get(3));
    }
}
