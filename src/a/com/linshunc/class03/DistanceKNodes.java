package a.com.linshunc.class03;

import class03.Code08_DistanceKNodes.Node;

import java.util.*;

public class DistanceKNodes {

    public static List<Node> distanceKNodes(Node root, Node target, int K) {
        if (root == null) {
            return new ArrayList<>();
        }
        // 处理出一个父节点的图
        Map<Node, Node> parentMap = new HashMap<>();
        parentMap.put(root, null);
        makeParentNode(parentMap, root);

        List<Node> res = new ArrayList<>();
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(target);
        Set<Node> set = new HashSet<>();
        set.add(target);
        int level = 0;
        int curLevelSize;
        while (queue.size() > 0) {
            curLevelSize = queue.size();
            for (int i = 0; i < curLevelSize; i++) {
                Node cur = queue.poll();
                if (level == K) {
                    res.add(cur);
                }
                if (cur.left != null && !set.contains(cur.left)) {
                    queue.add(cur.left);
                    set.add(cur.left);
                }
                if (cur.right != null && !set.contains(cur.right)) {
                    queue.add(cur.right);
                    set.add(cur.right);
                }
                if (parentMap.get(cur) != null && !set.contains(parentMap.get(cur))) {
                    queue.add(parentMap.get(cur));
                    set.add(parentMap.get(cur));
                }
            }
            level++;
            if (level > K) {
                break;
            }
        }

        return res;
    }

    public static void makeParentNode(Map<Node, Node> parentMap, Node cur) {
        if (cur.left != null) {
            parentMap.put(cur.left, cur);
            makeParentNode(parentMap, cur.left);
        }
        if (cur.right != null) {
            parentMap.put(cur.right, cur);
            makeParentNode(parentMap, cur.right);
        }
    }

    public static void main(String[] args) {
        Node n0 = new Node(0);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);

        n3.left = n5;
        n3.right = n1;
        n5.left = n6;
        n5.right = n2;
        n1.left = n0;
        n1.right = n8;
        n2.left = n7;
        n2.right = n4;

        Node root = n3;
        Node target = n5;
        int K = 2;

        List<Node> ans = distanceKNodes(root, target, K);

        for (Node o1 : ans) {
            System.out.println(o1.value);
        }

    }

}
