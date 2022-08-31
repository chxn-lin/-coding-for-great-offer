package a.com.linshunc.class04;

import java.util.*;

public class TheSkyline {

    public static class Node {
        boolean isAdd;
        int index;
        int h;

        public Node(boolean isAdd, int index, int h) {
            this.isAdd = isAdd;
            this.index = index;
            this.h = h;
        }
    }

    public static class NodeCompate implements Comparator<Node> {


        @Override
        public int compare(Node o1, Node o2) {
            return o1.index - o2.index;
        }
    }

    public static List<List<Integer>> getSkyline(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new ArrayList<>();
        }
        Node[] nodes = new Node[matrix.length << 1];
        for (int i = 0; i < matrix.length; i++) {
            nodes[i * 2] = new Node(true, matrix[i][0], matrix[i][2]);
            nodes[i * 2 + 1] = new Node(false, matrix[i][1], matrix[i][2]);
        }
        Arrays.sort(nodes, new NodeCompate());
        TreeMap<Integer, Integer> keyMaxHeight = new TreeMap<>();
        TreeMap<Integer, Integer> times = new TreeMap<>();
        for (Node node : nodes) {
            if (node.isAdd) {
                if (times.containsKey(node.h)) {
                    times.put(node.h, times.get(node.h) + 1);
                } else {
                    times.put(node.h, 1);
                }
            } else {
                if (times.get(node.h) != 1) {
                    times.put(node.h, times.get(node.h) - 1);
                } else {
                    times.remove(node.h);
                }
            }
            if (times.isEmpty()) {
                keyMaxHeight.put(node.index, 0);
            } else {
                keyMaxHeight.put(node.index, times.lastKey());
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        Map.Entry<Integer, Integer> lastEntry = null;
        for (Map.Entry<Integer, Integer> entry : keyMaxHeight.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();

            if (lastEntry == null || value != lastEntry.getValue()) {
                List<Integer> curList = new ArrayList<>();
                curList.add(key);
                curList.add(value);
                res.add(curList);
            }
            lastEntry = entry;
        }

        return res;
    }

    public static void main(String[] args) {

        int[][] arr = {
                {1, 4, 5},
                {2, 4, 3},
        };
        List<List<Integer>> skyline = getSkyline(arr);
        System.out.println(skyline);
    }

}
