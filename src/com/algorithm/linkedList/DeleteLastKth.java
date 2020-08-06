package com.algorithm.linkedList;

import java.util.LinkedList;

/**
 * @author Yuery
 * @date 2020/8/6/0006 - 13:42
 */
//删除倒数第K个结点
//首先获取倒数第k个节点
public class DeleteLastKth {
    public static void main(String[] args){
        LinkedList<Node> linkedList= new LinkedList<>();
        for(int i = 0; i<10 ;i++){
            Node newNode = new Node(i,null);
            linkedList.add(newNode);
        }

        for(int i = 0; i< 10; i++){
            if(i == 9){
                linkedList.get(9).next = null;
                break;
            }
            linkedList.get(i).next = linkedList.get(i+1);

        }
        System.out.println(linkedList.get(1));
        deleteLastKth(linkedList.get(0),7);
        printAll(linkedList.get(0));

    }


    static class Node{
        int data;
        Node next;
        public Node(int data,Node next){//可以说是用来标记下标的
            this.data = data;
            this.next = next;
        }

    }
    public static Node deleteLastKth( Node head, int k){
        Node former = head, latter = head;
        for(int i = 1; i < k; i++){
            former = former.next;
        }//此时former走了k-1步
        if(former == null) return head;
        Node pre = null;
        while(former.next != null){//former走到了最后一个节点，此时latter走到第k个节点，所以要用pre获取第k-1个节点
            former = former.next;
            pre = latter;
            latter = latter.next;
        }
        pre.next = pre.next.next;
        return head;

    }
//得到倒数第k个节点
    public static Node getKthFromEnd(Node head, int k){
        Node former = head, latter = head;
        for(int i = 0; i < k; i++){
            former = former.next;
        }
        while(former != null){
            former = former.next;
            latter = latter.next;
        }
        return latter;
    }
    public static void printAll(Node list){
        Node  p = list;
        while(p != null){
            System.out.print(p.data +" ");
            p = p.next;
        }
        System.out.println();
    }
}
