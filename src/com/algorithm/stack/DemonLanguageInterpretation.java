package com.algorithm.stack;


import java.util.Scanner;
import java.util.Stack;

/**
 * @author Yuery
 * @date 2020/8/7/0007 - 13:40
 */
public class DemonLanguageInterpretation {
    String cin;//输入的字符串
    char[] chars;//字符数组，目的是要对每一个字符一一检查
    public static void main(String[] args){
        DemonLanguageInterpretation test = new DemonLanguageInterpretation();
        test.translate();
    }
    public Boolean input(){
        Scanner reader=new Scanner(System.in);
        System.out.print("输入字符串:");
        cin=reader.next();//运行窗口会停留在这里等待输入
        int left_sum=0;
        int right_sum=0;
        chars=cin.toCharArray();//初始化chars字符串
        //检查括号是否匹配
        for(int i = chars.length-1;i>=0; i--){
            if('('== chars[i])
                left_sum++;
            else if(')' == chars[i])
                right_sum++;
        }
        if(left_sum != right_sum) {
            System.out.println("字符串的括号不匹配，无法解释魔王语言！！!");
            return false;
        }
        return true;
    }
    public void translate() {
        if (!input()) return;
        //括号匹配成功，执行以下代码2bnn
        String charB = "tsaedsae";
        String charA = "sae";
        Stack<Character> s = new Stack<>();//存放全部字符串
        Stack<Character> s1 = new Stack<>();//存储括号内的字符
        Stack<Character> s2 = new Stack<>();//暂时把右括号左边的字符存储在s2，随后要将其弹出放在s1
        Stack<Character> s3 = new Stack<>();//暂放括号内的解释
        Stack<Character> s4 = new Stack<>();//放置所有的结果

        for (int i = chars.length - 1; i >= 0; i--) {
            s.push(new Character(chars[i]));//不断从尾部将字符压入open栈中，栈顶为字符串的第一个字符
        }
        System.out.println("魔王语言解释成：");
        //核心代码
        while (s.size() > 0) {
            char out = s.pop().charValue();
            if (out == 'A'){
                //解释A
                stackA(s2);
            }
            else if (out == 'B'){
                //解释B
                stackB(s2);
            }
            else if (out != ')')
                s2.push(out);
            else if(out == ')') {
                s1.push(out);
                Character temp = s2.pop();
                while (temp.charValue() != '(') {
                    s1.push(temp);
                    temp = s2.pop();
                }
                Character e = s1.pop();//第一个字符
                Character tempp = s1.pop();
                while(tempp.charValue() != ')'){
                    s3.push(tempp);
                    tempp = s1.pop();
                }
                while(s3.size() > 0){
                    s2.push(e);
                    s2.push(s3.pop());
                }
                s2.push(e);
            }
        }
        System.out.println();
        //System.out.print();
        while(s2.size() >0){
            s4.push(s2.pop());
        }
        while(s4.size() > 0){
            System.out.print(s4.pop().charValue());
        }
        s.clear();
        s1.clear();
        s3.clear();
    }

    public void stackA(Stack s){
        s.push('s');
        s.push('a');
        s.push('e');
    }
    public void stackB(Stack s){
        s.push('t');
        stackA(s);
        s.push('d');
        stackA(s);
    }
}
