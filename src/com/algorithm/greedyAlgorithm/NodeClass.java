package com.algorithm.greedyAlgorithm;

/**
 * @author Yuery
 * @date 2020/9/3/0003 - 10:14
 */
public class NodeClass {

    private int num;
    private String ch;

    private NodeClass left;
    private NodeClass right;

    public NodeClass() {
    }

    public NodeClass( String ch , int num) {
        this.num = num;
        this.ch = ch;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }

    public NodeClass getLeft() {
        return left;
    }

    public void setLeft(NodeClass left) {
        this.left = left;
    }

    public NodeClass getRight() {
        return right;
    }

    public void setRight(NodeClass right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "NodeClass{" +
                "num=" + num +
                ", ch='" + ch + '\'' +
                "\n left=" + left +
                "\t right=" + right +
                '}';
    }
}
