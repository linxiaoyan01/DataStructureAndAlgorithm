package com.algorithm.linkedList;

import java.util.Scanner;

/**基于单链LRU算法
 *
 * @author Yuery
 * @date 2020/8/5/0005 - 10:14
 */
public class LRUBaseLinkedList<T> {
    public static void main(String[] args){
        LRUBaseLinkedList list = new LRUBaseLinkedList();
        Scanner sc = new Scanner(System.in);
        while(true){
            list.add(sc.nextInt());
            list.printAll();
        }
    }
    public class SNode<T>{
        private T element;
        private SNode next;
        public SNode(T element){
            this.element = element;
        }
        public SNode(T element, SNode next){
            this.element = element;
            this.next = next;
        }
        public SNode(){
            this.next = null;
        }
        public T getElement(){
            return element;
        }
        public void setElement(T element){
            this.element = element;
        }
        public void setNext(SNode next){
            this.next = next;
        }
        public SNode getNext(){
            return next;
        }
    }

//    默认链表容量
    private final static Integer DEFAULT_CAPACITY = 10;
//    头节点
    private SNode<T> headNode;
//    链表长度
    private Integer length;
//    链表容量
    private Integer capacity;
    public LRUBaseLinkedList(){
        this.headNode = new SNode<T>();
        this.capacity = DEFAULT_CAPACITY;
        this.length = 0;
    }
    public LRUBaseLinkedList(Integer capacity){
        this.headNode = new SNode<>();
        this.capacity = capacity;
        this.length = 0;
    }
    private void printAll(){
        SNode node = headNode.getNext();//头节点就是哨兵节点，没有储存值
        while(node != null){
            System.out.println(node.getElement() + ",");
            node = node.getNext();
        }
        System.out.println();
    }
//    删除preNode节点下一个元素
    private void deleteElementOptim(SNode preNode){
        SNode temp = preNode.getNext();
        preNode.setNext(temp.getNext());
        temp = null;
        length--;
    }
    //链表头部插入节点
    private void insertElementBegin(T data){
        SNode next = headNode.getNext();
        headNode.setNext(new SNode(data, next));
        length++;
    }
    //获取查找到元素的前一个节点
    private SNode findPreNode(T data){
        SNode node = headNode;
        while (node.getNext()!= null){
            if(data.equals(node.getNext().getElement())){
                return node;
            }
            node = node.getNext();
        }
        return null;
    }
    //删除尾节点
    private void deleteElementAtEnd(){
        SNode ptr = headNode;
        //空链表直接返回
        if(ptr.getNext() == null){
            return;
        }
        //倒数第二个节点
        while(ptr.getNext().getNext() != null){
            ptr = ptr.getNext();//此时ptr为倒数第二个节点
        }
        SNode tmp = ptr.getNext();//tmp为最后一个节点
        ptr.setNext(null);
        tmp = null;
        length--;
    }
    public void add(T data){
        SNode preNode = findPreNode(data);
        //链表中存在，删除原数据，再插入到链表的头部
        if(preNode != null){
            deleteElementOptim(preNode);
            insertElementBegin(data);
        }else{
            if(length >= this.capacity){
                //删除尾节点
                deleteElementAtEnd();
            }
            insertElementBegin(data);
        }
    }


}
