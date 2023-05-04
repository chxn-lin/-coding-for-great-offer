package a.com.linshunc.class34;

import java.util.PriorityQueue;

public class Prob_0295_FindMedianFromDataStream {

    public static void main(String[] args) {
        MedianFinder finder = new MedianFinder();
        finder.addNum(10);
        System.out.println(finder.findMedian());

        finder.addNum(5);
        System.out.println(finder.findMedian());

        finder.addNum(1);
        System.out.println(finder.findMedian());

        finder.addNum(2);
        System.out.println(finder.findMedian());

        finder.addNum(20);
        System.out.println(finder.findMedian());
    }

    static class MedianFinder {

        private PriorityQueue<Integer> maxHeap;
        private PriorityQueue<Integer> minHeap;

        public MedianFinder() {
            maxHeap = new PriorityQueue<>((a, b) -> b - a);
            minHeap = new PriorityQueue<>((a, b) -> a - b);
        }

        public void addNum(int num) {
            if (maxHeap.size() == 0 && minHeap.size() == 0) {
                maxHeap.add(num);
                return ;
            }
            if (num <= maxHeap.peek()) {
                maxHeap.add(num);
            }
            else {
                minHeap.add(num);
            }
            makeBalance();
        }

        public double findMedian() {
            double ans = 0d;
            if (maxHeap.size() == minHeap.size()) {
                ans = (Double.valueOf(maxHeap.peek()) + minHeap.peek()) / 2;
            }
            else if (maxHeap.size() > minHeap.size()) {
                ans = maxHeap.peek();
            }
            else {
                ans = minHeap.peek();
            }
            return ans;
        }

        private void makeBalance() {
            if (Math.abs(maxHeap.size() - minHeap.size()) == 2) {
                if (maxHeap.size() > minHeap.size()) {
                    minHeap.add(maxHeap.poll());
                }
                else {
                    maxHeap.add(minHeap.poll());
                }
            }
        }
    }

}
