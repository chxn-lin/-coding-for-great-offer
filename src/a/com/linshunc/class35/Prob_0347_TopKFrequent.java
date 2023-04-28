package a.com.linshunc.class35;

import class35.Problem_0347_TopKFrequentElements;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Prob_0347_TopKFrequent {

    private static class Node{
        int val;
        int times;
        public Node(int val) {
            this.val = val;
            this.times = 1;
        }
    }

    public static int[] topKFrequent(int[] nums, int k) {
        if (nums.length < 1) {
            return new int[0];
        }
        int[] ans = new int[k];
        Map<Integer, Node> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.get(num).times++;
            }
            else {
                map.put(num, new Node(num));
            }
        }
        PriorityQueue<Node> minHeap = new PriorityQueue<>((a, b) -> b.times -  a.times);
        for (Node value : map.values()) {
            minHeap.add(value);
        }
        for (int i = 0; i < ans.length; i++) {
            ans[i] = minHeap.poll().val;
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {213,23,4,2,2,3,5,3,3,2,4,2,3,2,23,4,2,4,5,5,4,6,7};
        int k = 5;
        System.out.println(Arrays.toString(topKFrequent(nums, k)));
        System.out.println(Arrays.toString(Problem_0347_TopKFrequentElements.topKFrequent(nums, k)));
    }

}
