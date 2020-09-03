package com.algorithm.greedyAlgorithm;



import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
//encoding.txt是准备要来编码的字符串
//把编好的字符串放在encodingTarget.txt
//把encodingTarget.txt的文件译码放在decodingTarget.txt文件

/**
 * @author Yuery
 * @date 2020/9/3/0003 - 8:18
 */
public class HuffmanCoding {
    private static String hafucode = "";
    private static Map<String,String> charMaps = new HashMap<>();
    private static Map<String,String> codeMaps = new HashMap<>();
    public static void main(String[] args) throws IOException {
        //读取需要编码的文件
        String str = readFile("d:/huffman/encoding.txt");
        //统计每个字母出现的次数
        HashMap<String , Integer> map = new HashMap<String , Integer>();
        for (int i = 0 ; i < str.length(); i++){
            String ch = str.charAt(i) + "";
            if(map.get(ch) == null){
                map.put(ch,1);
            }else {
                //为了防止覆盖
                map.put(ch , map.get(ch) + 1);
            }
        }
        //把每个节点的字符和个数都放在了arr
        ArrayList<NodeClass> arr = new ArrayList<>();
        //map遍历
        for(Map.Entry<String , Integer> en : map.entrySet()){
            System.out.println("key:" + en.getKey() + ",value:" + en.getValue() );
            NodeClass no = new NodeClass(en.getKey() , en.getValue());
            arr.add(no);
        }
        //构建哈夫曼树
        for(;;){
            if(arr.size() > 1){
                NodeClass[] data = getMinNodes(arr);
                NodeClass root = new NodeClass(null , data[0].getNum() + data[1].getNum());
                root.setLeft(data[0]);
                root.setRight(data[1]);
                arr.add(root);
            }else {
                break;
            }
        }
        //TREE就是最终的哈夫曼树
        NodeClass tree = arr.get(0);
        //System.out.println(tree);
        allView(tree,"", charMaps, codeMaps);
        //编码
        String huffmancode = encodeFile(str);
        //编好后写入文件
        writeFile("d:/huffman/encodingTarget.txt", huffmancode);
        //反编码
        reverseDecode("d:/huffman/encodingTarget.txt");

    }
    public static  void reverseDecode(String pathname) throws IOException {
        String huffmancode = readFile(pathname);
        int index = 0;
        String charStr = "";
        for(int i = 1 ; i <= huffmancode.length() ; i++){
            String string = hafucode.substring(index , i);
            if(codeMaps.get(string) != null){
                charStr += codeMaps.get(string);
                index = i;
            }
        }
        writeFile("d:/huffman/decodingTarget.txt",charStr);
    }

    public static String encodeFile(String str){
        for (int i = 0; i < str.length(); i++) {
            String ch = str.charAt(i) + "";
            hafucode += charMaps.get(ch);
        }
        return hafucode;
    }
    public static void writeFile(String pathname, String result) throws IOException {
        File f2 = new File(pathname);
        FileWriter fw = new FileWriter(f2);
        char[] cs = result.toCharArray();
        fw.write(cs);
        fw.close();
    }
    public static  String readFile(String pathname) throws IOException {
        File f1 = new File(pathname);
        //读取文件
        //创建基于文件的输入流
        FileReader fr = new FileReader(f1);
        //创建字节数组，其长度就是文件的长度
        char[] all = new char[(int) f1.length()];
        //以字节流的形式读取文件所有内容
        fr.read(all);
        String str = "";
        for(char b : all){
            str += b;
        }
        //关闭流
        fr.close();
        return str;
    }
    //取出最小的两个结点
    public static NodeClass[] getMinNodes(ArrayList<NodeClass> arr){
        NodeClass[] nos = new NodeClass[2];
        int index1;
        int index2;
        if(arr.get(0).getNum() <= arr.get(1).getNum()){
            index1 = 0;
            index2 = 1;
        }else {
            index1 = 1;
            index2 = 0;
        }
        for(int i = 2 ; i < arr.size() ; i++){
            if(arr.get(i).getNum() < arr.get(index1).getNum()){
                index2 = index1;
                index1 = i;
            }else if(arr.get(i).getNum() >= arr.get(index1).getNum() && arr.get(i).getNum() < arr.get(index2).getNum()){
                index2 = i;
            }
        }
        nos[0] = arr.get(index1);
        nos[1] = arr.get(index2);
        arr.remove(index1);
        if(index2 > index1){
            arr.remove(index2 - 1);//这里减一是因为下标为index1被删除了，所以长度减一了，index2也减一了
        }else{
            arr.remove(index2);
        }
        return nos;
    }
    //递归
    public static void allView(NodeClass tree , String code,  Map<String,String> charMaps ,  Map<String,String> codeMaps ){
        if(tree.getLeft() == null){
            //System.out.println(tree.getCh() + " : " + tree.getNum() + "  code:" + code );
            charMaps.put(tree.getCh(), code);//正向编码
            codeMaps.put(code, tree.getCh());//反编码
        }else{
            allView(tree.getLeft() , code + "0",charMaps, codeMaps);
            allView(tree.getRight() , code + "1",charMaps, codeMaps);
        }
    }
}
