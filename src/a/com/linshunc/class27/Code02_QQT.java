package a.com.linshunc.class27;

import class27.Code02_MinPeople;

import java.util.Arrays;

public class Code02_QQT {

    // 企鹅厂每年都会发文化衫，文化衫有很多种，厂庆的时候，企鹅们都需要穿文化衫来拍照
    //一次采访中，记者随机遇到的企鹅，企鹅会告诉记者还有多少企鹅跟他穿一种文化衫
    //我们将这些回答放在answers数组里，返回鹅厂中企鹅的最少数量
    public static int numRabbits(int[] arr) {
        if (arr == null || arr.length < 1) {
           return 0;
        }
        Arrays.sort(arr);
        int res = 0;

        int lastNum = arr[0];
        int lastCount = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == lastNum) {
                lastCount++;
            }
            else {
                // 结算
                res += (lastCount + lastNum) / (lastNum + 1) * (lastNum + 1);
                lastCount = 1;
                lastNum = arr[i];
            }
        }
        res += (lastCount + lastNum) / (lastNum + 1) * (lastNum + 1);
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,2,5,2,2,5,3,6,5,2,2,3,2,22,3,3,4,1,2,3,3,2,2,2,7,5,2};
        int[] arr2 = Arrays.copyOf(arr, arr.length);

        System.out.println(numRabbits(arr));
        System.out.println(Code02_MinPeople.numRabbits(arr2));
    }

}
