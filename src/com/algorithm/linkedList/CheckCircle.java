package com.algorithm.linkedList;

/**
 * @author Yuery
 * @date 2020/8/5/0005 - 21:14
 */
// 教程链接 https://blog.csdn.net/sinat_27143551/article/details/84059131?biz_id=102&utm_term=%E6%A3%80%E6%B5%8B%E7%8E%AF&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduweb~default-0-84059131&spm=1018.2118.3001.4187
// 视频链接  https://www.bilibili.com/video/BV1W4411F7Jq?from=search&seid=7386950593437474644
public class CheckCircle {
    public static void main(String[] args){

    }

    static class Node{
        int data;
        Node next;
        public Node(int data){//可以说是用来标记下标的
            this.data = data;
        }
    }
    //链表检测环
    public static boolean checkCircle(Node list){
        if(list == null)
            return false;
        Node fast = list;
        Node slow = list;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;

            if(slow == fast) return true;
        }
        return false;
    }
    //找出环的入口点
    public static Node findEntrance(Node list){
        //找到相遇点
        if (list == null) return null;
        Node fast  = list, slow = list;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(slow == fast)break;
        }
        if(fast == null || fast.next == null){
            return null;
        }
        //如果有环，slow指向链表头，此时fast指向相遇点
        slow = list;
        while (slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
