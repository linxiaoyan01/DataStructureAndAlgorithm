package com.algorithm.linkedList;

import java.util.LinkedList;

/**
 * @author Yuery
 * @date 2020/8/4/0004 - 13:09
 */

//视频解释代码： https://www.bilibili.com/video/BV1c7411x7p9?from=search&seid=15597529252111833359
    // 手写链表实现： https://zhuanlan.zhihu.com/p/74436158
public class JosephusProblem {
    public static void main(String[] arg){
        JosephusProblem josephusProblem  = new JosephusProblem();
        int result1 = josephusProblem.LastRemaining_Solution_link(10,3);
        int result2 = josephusProblem.LastRemaining_Solution_math(10,3);
        System.out.println(result1);
        System.out.println(result2);
        //通过手写链表来实现，此处很奇怪，没有得出结果，可能有Bug
        //int result3 = josephusProblem.solve(3,2);
        //System.out.println(result3);
    }
    public int LastRemaining_Solution_link(int n, int m){
        //约瑟夫问题
        //通过链表来模拟

        if(n == 0 || m == 0)
            return -1;
        LinkedList<Integer> list = new LinkedList<>();
        for(int i = 0; i< n; i++){
            list.add(i);
        }
        int removeIndex = 0;
        while(list.size() != 1){
            removeIndex = (removeIndex + m -1)% list.size();
            list.remove(removeIndex);
        }
        return list.get(0);
    }


    public int LastRemaining_Solution_math(int n, int m){
        //通过数学公式+迭代
        //复杂度分析：时间复杂度：O(n)，需要求解的函数值有 n 个。
        //空间复杂度：O(1)，只使用常数个变量。
        //f(n,m) = (f(n-1, m) + m) % n
        //f(1) = 0    f(2) = (f(1) + m ) % 2
        if(n == 0 || m == 0)
            return -1;
        int last = 0;
        for(int i = 2; i <= n; i++){
            last = (last + m) % i;
        }
        return last;
    }
    static class Node{
        int date;
        Node next;
        public Node(int date){//可以说是用来标记下标的
            this.date = date;
        }
    }
    //用自己手写的链表来实现的,时间复杂度为O(n*m),空间复杂度为O(n);
    public  int solve(int n, int m){
        if(m == 1 || n < 2)
            return n;
        //创建环形链表
        Node head = createLinkedList(n);
        //遍历删除
        int count = 1;
        Node cur = head;
        Node pre = null;//前驱节点

        while(head.next != head){//循环直到剩下一个节点
            //删除节点
            if(count == m){
                count = 1;
                pre.next = cur.next;
                cur = pre.next;
            }else{//pre 和 cur 同时右移
                count++;
                pre = cur;
                cur = cur.next;
            }
        }
        return head.date;//把节点的下标返回
    }
    //手写循环链表
    static Node createLinkedList(int n){
        Node head = new Node(1);//创建下标为1的头节点
        Node next = head;//哨兵节点，如果不能理解可以到极客时间看王争的数据结构和算法之美
        for(int i = 2; i<=n; i++){//可见这里的空间复杂度为O(n)
            Node tmp = new Node(i);
            next.next = tmp;
            next = next.next;
        }
        //记得将首尾串联
        next.next = head;
        return head;//把整条链表的头节点返回
    }
}
