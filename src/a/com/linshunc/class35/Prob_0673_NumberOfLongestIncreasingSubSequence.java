package a.com.linshunc.class35;

import class35.Problem_0673_NumberOfLongestIncreasingSubsequence;

public class Prob_0673_NumberOfLongestIncreasingSubSequence{

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 4, 7};
        System.out.println(Problem_0673_NumberOfLongestIncreasingSubsequence.findNumberOfLIS1(nums));
    }

    public static int findNumberOfLIS1(int[] nums) {
        if (nums.length < 1) {
            return 0;
        }
        int n = nums.length;
        int[] len = new int[n];
        int[] cnt = new int[n];
        len[0] = 1;
        cnt[0] = 1;
        int maxLen = 1;
        int allCnt = 1;
        for (int i = 1; i < n; i++) {
            int preLen = 0;
            int preCnt = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] >= nums[i] || preLen > len[j]) {
                    continue;
                }
                if (preLen < len[j]) {
                    preLen = len[j];
                    preCnt = cnt[j];
                }
                else {
                    preCnt += cnt[j];
                }
            }
            len[i] = preLen + 1;
            cnt[i] = preCnt;
            if (maxLen < len[i]) {
                maxLen = len[i];
                allCnt = cnt[i];
            }
            else {
                allCnt += cnt[i];
            }
        }

        return allCnt;
    }

}
