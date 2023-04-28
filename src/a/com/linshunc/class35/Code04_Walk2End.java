package a.com.linshunc.class35;

import class35.Code04_WalkToEnd;

import java.util.PriorityQueue;

public class Code04_Walk2End {

    public static void main(String[] args) {
        int[][] map = {
                {1, 2, 0, 2, 1},
                {1, 2, 0, 2, 1},
                {1, 2, 0, 1, 1},
                {1, 1, 0, 2, 1},
                {1, 2, 2, 2, 0},
        };
        System.out.println(Code04_WalkToEnd.minCost(map));
        System.out.println(minCost(map));
    }

    public static int minCost(int[][] map) {
        if (map.length < 1 || map[0][0] == 2 || map[map.length - 1][map[map.length - 1].length - 1] == 2) {
            return -1;
        }
        boolean[][] hasIn = new boolean[map.length][map[0].length];
        PriorityQueue<Node> minHeap = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        add(minHeap, map, hasIn, 0, 0, 0);
        while (!minHeap.isEmpty()) {
            Node poll = minHeap.poll();
            if (poll.row == map.length - 1 && poll.col == map[poll.row].length - 1) {
                return poll.cost;
            }
            add(minHeap, map, hasIn, poll.row + 1, poll.col, poll.cost);
            add(minHeap, map, hasIn, poll.row - 1, poll.col, poll.cost);
            add(minHeap, map, hasIn, poll.row, poll.col + 1, poll.cost);
            add(minHeap, map, hasIn, poll.row, poll.col - 1, poll.cost);
        }

        return -1;
    }

    private static void add(PriorityQueue<Node> minHeap, int[][] map, boolean[][] hasIn, int row, int col, int pre) {
        if (col < 0 || row < 0 || row >= map.length || col >= map[row].length
                || hasIn[row][col]
                || map[row][col] == 2) {
            return;
        }
        hasIn[row][col] = true;
        minHeap.add(new Node(row, col, pre + (map[row][col] == 0 ? 2 : 1)));
    }

    private static class Node {
        int row;
        int col;
        int cost;
        public Node(int row, int col, int cost) {
            this.row = row;
            this.col = col;
            this.cost = cost;
        }
    }

}
