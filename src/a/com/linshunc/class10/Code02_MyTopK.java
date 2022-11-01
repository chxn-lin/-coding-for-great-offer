package a.com.linshunc.class10;

import class10.Code02_TopK;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class Code02_MyTopK {

    // 小根堆
    private Node[] arr;
    private int heapSize;

    private Comparator<Node> comp = (o1, o2) -> o1.num == o2.num ? o2.str.compareTo(o1.str) : o1.num - o2.num;
    Map<String, Node> constantMap = new HashMap<>();
    Map<Node, Integer> indexMap = new HashMap<>();

    public Code02_MyTopK(int size) {
        this.arr = new Node[size];
    }

    public void add(String str){
        if (str == null || str.length() < 1) {
            return ;
        }
        Node cur = constantMap.get(str);
        if (cur == null) {
            cur = new Node(1, str);
            constantMap.put(str, cur);
        }
        else {
            cur.num += 1;
        }
        if (heapSize < 1) {
            // 内部为空，表示是第一个元素
            arr[heapSize] = cur;
            indexMap.put(cur, heapSize++);
        }
        else {
            // 还需要考虑堆内没有满的情况
            if (indexMap.containsKey(cur)) {
                // 在堆内
                heapify(arr, indexMap.get(cur), heapSize);
            }
            // 堆外
            else {
                Node top = arr[0];
                if (heapSize < arr.length) {
                    // 没满
                    arr[heapSize] = cur;
                    indexMap.put(cur, heapSize);
                    heapInsert(arr, heapSize++);
                }
                else {
                    if (comp.compare(top, cur) < 0) {
                        // 说明比最上面的元素大，所以需要把顶部元素挤出去
                        indexMap.remove(arr[0]);
                        indexMap.put(cur, 0);
                        arr[0] = cur;
                        heapify(arr, 0, heapSize);
                    }
                }
            }
        }
    }

    // 插入
    private void heapInsert(Node[] arr, int index) {
        int parentIndex = (index - 1) >> 1;
        while (parentIndex >= 0 && comp.compare(arr[index], arr[parentIndex]) < 0) {
            sw(arr, index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) >> 1;
        }
    }

    // 堆化
    private void heapify(Node[] arr, int index, int heapSize) {
        int sonIndex1 = index * 2 + 1;
        int smallSonIndex;
        while (sonIndex1 < heapSize && comp.compare(arr[index], arr[sonIndex1]) > 0) {
            if (sonIndex1 + 1 < index) {
                smallSonIndex = comp.compare(arr[sonIndex1], arr[sonIndex1 + 1]) > 0 ? sonIndex1 : sonIndex1 + 1;
            }
            else {
                smallSonIndex = sonIndex1;
            }
            sw(arr, smallSonIndex, index);
            index = smallSonIndex;
            sonIndex1 = index * 2 + 1;
        }
    }

    private void sw(Node[] arr, int index1, int index2) {
        Node temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
        indexMap.put(arr[index1], index1);
        indexMap.put(arr[index2], index2);
    }

    public List<String> topK(){
        List<String> list = new ArrayList();
        for (int i = 0; i < heapSize; i++) {
            list.add(arr[i].str);
        }
        Collections.sort(list);
        return list;
    }

    private static class Node {
        int num;
        String str;
        public Node(int num, String str) {
            this.num = num;
            this.str = str;
        }
    }

    public static void main(String[] args) {
        List<List<String>> ans = new ArrayList<>();
        List<List<String>> ans2 = new ArrayList<>();
        String[] str = {"123", "12", "32", "12", "32", "123", "12", "123"};
        Code02_TopK top = new Code02_TopK(2);
        for (String s : str) {
            top.add(s);
            ans.add(top.topk());
        }
        System.out.println();
        for (int i = 0; i < ans.size(); i++) {
            System.out.println(ans.get(i));
        }
        System.out.println("==================");
        Code02_MyTopK myTop = new Code02_MyTopK(2);
        for (String s : str) {
            myTop.add(s);
            ans2.add(myTop.topK());
        }
        for (int i = 0; i < ans2.size(); i++) {
            System.out.println(ans2.get(i));
        }
    }
}
