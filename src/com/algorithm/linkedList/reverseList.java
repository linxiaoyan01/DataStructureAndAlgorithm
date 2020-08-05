package com.algorithm.linkedList;

/**
 * @author Yuery
 * @date 2020/8/4/0004 - 23:23
 */
//视频教程： https://www.bilibili.com/video/BV1U7411A75v
public class reverseList {
    public static void main(String[] arg){
        reverseList reverseList = new reverseList();
        long start = System.nanoTime();
        printAll(reverseList.reverseList1(createLinkedList(10)));
        long end = System.nanoTime();
        System.out.println("程序运行时间："+ (end - start)+"ns");
        long start2 = System.nanoTime();
        printAll(reverseList.reverseList2(createLinkedList(10)));
        long end2 = System.nanoTime();
        System.out.println("程序运行时间:"+ (end2 - start2)+"ns");

    }
    //手写循环链表
    static ListNode createLinkedList(int n) {
        ListNode head = new ListNode(1);//创建下标为1的头节点
        ListNode next = head;//哨兵节点，如果不能理解可以到极客时间看王争的数据结构和算法之美
        for (int i = 2; i <= n; i++) {//可见这里的空间复杂度为O(n)
            ListNode tmp = new ListNode(i);
            next.next = tmp;
            next = next.next;
        }
//        //记得将首尾串联
//        next.next = head;

        return head;//把整条链表的头节点返回
    }
    static class ListNode{
        int data;
        ListNode next;
        public ListNode(int data){
            this.data = data;
        }
    }
    //通过递归的方法实现链表的反转
    public ListNode reverseList1(ListNode head){
        if(head == null || head.next == null) return head;

        ListNode newHead = reverseList1(head.next);
        head.next.next = head;//4指向5
        head.next  = null;//5指向null，5是头节点，下一个节点是空
        return newHead;
    }
    //通过迭代的方法实现链表的反转,无头节点链表
    public ListNode reverseList2(ListNode head){
        ListNode newHead = null;
        while(head  != null){
            ListNode tmp = head.next;
            head.next = newHead;
            newHead = head;
            head = tmp;
        }
        return newHead;
    }
    public static void printAll(ListNode head){
        ListNode p = head;
        while(p != null){
            System.out.print(p.data);
            p = p.next;
        }
        System.out.println();
    }
}
