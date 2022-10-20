package a.com.linshunc.class09;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

import static a.com.linshunc.class01.AOE.printArr;
import static class09.Code01_LightProblem.loopMinStep2;
import static class09.Code01_LightProblem.noLoopMinStep2;
import static class09.Code01_LightProblem.randomArray;

/*
 * 给定一个数组arr，长度为N，arr中的值不是0就是1
 * arr[i]表示第i栈灯的状态，0代表灭灯，1代表亮灯
 * 每一栈灯都有开关，但是按下i号灯的开关，会同时改变i-1、i、i+2栈灯的状态
 * 问题一：
 * 如果N栈灯排成一条直线,请问最少按下多少次开关,能让灯都亮起来
 * 排成一条直线说明：
 * i为中间位置时，i号灯的开关能影响i-1、i和i+1
 * 0号灯的开关只能影响0和1位置的灯
 * N-1号灯的开关只能影响N-2和N-1位置的灯
 *
 * 问题二：
 * 如果N栈灯排成一个圈,请问最少按下多少次开关,能让灯都亮起来
 * 排成一个圈说明：
 * i为中间位置时，i号灯的开关能影响i-1、i和i+1
 * 0号灯的开关能影响N-1、0和1位置的灯
 * N-1号灯的开关能影响N-2、N-1和0位置的灯
 *
 * */
public class Code01_LightPro {

    public static int noLoopRight(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] == 1 ? 0 : 1;
        }
        if (arr.length == 2) {
            return arr[0] != arr[1] ? Integer.MAX_VALUE : arr[0] == 1 ? 0 : 1;
        }
        return process(arr, 0);
    }

    private static int process(int[] arr, int index) {
        if (index == arr.length) {
            return vaild(arr) ? 0 : Integer.MAX_VALUE;
        }
        int p1 = process(arr, index + 1);
        change(arr, index);
        int p2 = process(arr, index + 1);
        change(arr, index);
        return Math.min(p1, p2 == Integer.MAX_VALUE ? p2 : p2 + 1);
    }

    private static boolean vaild(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                return false;
            }
        }
        return true;
    }

    private static void change(int[] arr, int i){
        if (i > 0) {
            arr[i - 1] ^= 1;
        }
        arr[i] ^= 1;
        if (i < arr.length - 1) {
            arr[i + 1] ^= 1;
        }
    }

    public static int noLoopRight2(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] == 1 ? 0 : 1;
        }
        if (arr.length == 2) {
            return arr[0] != arr[1] ? Integer.MAX_VALUE : arr[0] == 1 ? 0 : 1;
        }
        int p1 = process2(arr, 2, arr[0], arr[1]);
        int p2 = process2(arr, 2, arr[0] ^ 1, arr[1] ^ 1);
        if (p2 != Integer.MAX_VALUE) {
            p2 = p2 + 1;
        }
        return Math.min(p1, p2);
    }

    // 当前在哪个位置上，做选择，nextIndex - 1 = cur ，当前！
    // cur - 1 preStatus
    // cur  curStatus
    // 0....cur-2  全亮的！
    public static int process2(int[] arr, int nextIndex, int preStatus, int curStatus) {
        if (nextIndex == arr.length) {
            return preStatus != curStatus ? Integer.MAX_VALUE : preStatus == 1 ? 0 : 1;
        }
        if (preStatus == 0) {
            int p1 = process2(arr, nextIndex + 1, curStatus ^ 1, arr[nextIndex] ^ 1);
            return p1 == Integer.MAX_VALUE ? p1 : p1 + 1;
        } else {
            return process2(arr, nextIndex + 1, curStatus, arr[nextIndex]);
        }
    }

    // 非递归版本
    public static int noLoopRight3(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] == 1 ? 0 : 1;
        }
        if (arr.length == 2) {
            return arr[0] != arr[1] ? Integer.MAX_VALUE : arr[0] == 1 ? 0 : 1;
        }
        int p1 = process3(arr, arr[0], arr[1]);
        int p2 = process3(arr, arr[0] ^ 1, arr[1] ^ 1);
        if (p2 != Integer.MAX_VALUE) {
            p2 = p2 + 1;
        }
        return Math.min(p1, p2);
    }

    private static int process3(int[] arr, int preStatus, int curStatus) {
        int i = 2;
        int res = 0;
        while (i != arr.length) {
            if (preStatus == 0) {
                res++;
                preStatus = curStatus ^ 1;
                curStatus = arr[i] ^ 1;
                i++;
            } else {
                preStatus = curStatus;
                curStatus = arr[i];
                i++;
            }
        }
        return preStatus != curStatus ? Integer.MAX_VALUE : preStatus == 1 ? res : res + 1;
    }

    private static int loopRight1(int[] arr){
        if (arr == null || arr.length < 1) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] == 1 ? 0 : 1;
        }
        if (arr.length == 2) {
            return arr[0] != arr[1] ? Integer.MAX_VALUE : arr[0] == 1 ? 0 : 1;
        }
        if (arr.length == 3) {
            return arr[0] == arr[1] && arr[1] == arr[2] ? arr[0] ^ 1 : Integer.MAX_VALUE;
        }
        // 0, 1 都不变
        int p1 = loopProcess(arr, 3, arr[1], arr[2], arr[arr.length - 1], arr[0]);
        // 0 变 1 不变
        int p2 = loopProcess(arr, 3, arr[1] ^ 1, arr[2], arr[arr.length - 1] ^ 1, arr[0] ^ 1);
        // 0 不变 1 变
        int p3 = loopProcess(arr, 3, arr[1] ^ 1, arr[2] ^ 1, arr[arr.length - 1], arr[0] ^ 1);
        // 0 变 1 变
        int p4 = loopProcess(arr, 3, arr[1], arr[2] ^ 1, arr[arr.length - 1] ^ 1, arr[0]);
        p2 = p2 != Integer.MAX_VALUE ? p2 + 1 : Integer.MAX_VALUE;
        p3 = p3 != Integer.MAX_VALUE ? p3 + 1 : Integer.MAX_VALUE;
        p4 = p4 != Integer.MAX_VALUE ? p4 + 2 : Integer.MAX_VALUE;
        return Math.min(p1, Math.min(p2, Math.min(p3, p4)));
    }

    public static int loopProcess(int[] arr,
                               int nextIndex, int preStatus, int curStatus,
                               int endStatus, int firstStatus) {
        if (nextIndex == arr.length) {
            return preStatus != curStatus || endStatus != firstStatus ? Integer.MAX_VALUE : preStatus == 1 ? 0 : 1;
        }

        int p1 = 0;
        if (nextIndex == arr.length - 1) {
            // 这个位置的决定会影响到最后一个位置
            if (preStatus == 1) {
                p1 = loopProcess(arr, nextIndex + 1, curStatus, endStatus, endStatus, firstStatus);
            } else {
                p1 = loopProcess(arr, nextIndex + 1, curStatus ^ 1, endStatus ^ 1, endStatus ^ 1, firstStatus);
                p1 = p1 == Integer.MAX_VALUE ? p1 : p1 + 1;
            }
            return p1;
        } else {
            // 普遍位置
            if (preStatus == 1) {
                // 不要按
                p1 = loopProcess(arr, nextIndex + 1, curStatus, arr[nextIndex], endStatus, firstStatus);
            } else {
                p1 = loopProcess(arr, nextIndex + 1, curStatus ^ 1, arr[nextIndex] ^ 1, endStatus, firstStatus);
                p1 = p1 == Integer.MAX_VALUE ? p1 : p1 + 1;
            }
            return p1;
        }
    }

    private static int loopRight2(int[] arr){
        if (arr == null || arr.length < 1) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] == 1 ? 0 : 1;
        }
        if (arr.length == 2) {
            return arr[0] != arr[1] ? Integer.MAX_VALUE : arr[0] == 1 ? 0 : 1;
        }
        if (arr.length == 3) {
            return arr[0] == arr[1] && arr[1] == arr[2] ? arr[0] ^ 1 : Integer.MAX_VALUE;
        }
        // 0, 1 都不变
        int p1 = loopProcess2(arr, arr[1], arr[2], arr[arr.length - 1], arr[0]);
        // 0 变 1 不变
        int p2 = loopProcess2(arr, arr[1] ^ 1, arr[2], arr[arr.length - 1] ^ 1, arr[0] ^ 1);
        // 0 不变 1 变
        int p3 = loopProcess2(arr, arr[1] ^ 1, arr[2] ^ 1, arr[arr.length - 1], arr[0] ^ 1);
        // 0 变 1 变
        int p4 = loopProcess2(arr, arr[1], arr[2] ^ 1, arr[arr.length - 1] ^ 1, arr[0]);
        p2 = p2 != Integer.MAX_VALUE ? p2 + 1 : Integer.MAX_VALUE;
        p3 = p3 != Integer.MAX_VALUE ? p3 + 1 : Integer.MAX_VALUE;
        p4 = p4 != Integer.MAX_VALUE ? p4 + 2 : Integer.MAX_VALUE;
        return Math.min(p1, Math.min(p2, Math.min(p3, p4)));
    }

    public static int loopProcess2(int[] arr,
                                  int preStatus, int curStatus,
                                  int endStatus, int firstStatus) {
        int nextIndex = 3;
        int res = 0;
        while (nextIndex <= arr.length) {
            if (nextIndex == arr.length) {
                res = preStatus != curStatus || endStatus != firstStatus ? Integer.MAX_VALUE : preStatus == 1 ? res : res + 1;
            }
            else if (nextIndex == arr.length - 1) {
                // 这个位置的决定会影响到最后一个位置
                if (preStatus == 1) {
                    preStatus = curStatus;
                    curStatus = endStatus;
//                    endStatus = endStatus;
                } else {
                    res++;
                    preStatus = curStatus ^ 1;
                    curStatus = endStatus ^ 1;
                    endStatus = endStatus ^ 1;
                }
            }
            else {
                // 普遍位置
                if (preStatus == 1) {
                    // 不要按
                    preStatus = curStatus;
                    curStatus = arr[nextIndex];
//                    endStatus = endStatus;
                } else {
                    res++;
                    preStatus = curStatus ^ 1;
                    curStatus = arr[nextIndex] ^ 1;
//                    endStatus = endStatus;
                }
            }
            nextIndex++;
        }

        return res;
    }

    public static void main(String[] args) {
//        int[] arr = {0, 0 , 0, 0};
//        int ans1 = loopRight1(arr);
//        int ans3 = loopMinStep2(arr);
//        System.out.println(ans1);
//        System.out.println(ans3);
//        printArr(arr);

        System.out.println("如果没有任何Oops打印，说明所有方法都正确");
        System.out.println("test begin");
        int testTime = 20000;
        int lenMax = 12;
//        for (int i = 0; i < testTime; i++) {
//            int len = (int) (Math.random() * lenMax);
//            int[] arr = randomArray(len);
//            int ans1 = noLoopRight3(arr);
//            int ans3 = noLoopMinStep2(arr);
//            if (ans1 != ans3) {
//                System.out.println("1 Oops!");
//            }
//        }
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * lenMax);
            int[] arr = randomArray(len);
            int ans1 = loopRight2(arr);
            int ans3 = loopMinStep2(arr);
            if (ans1 != ans3) {
                printArr(arr);
                System.out.println("my   :" + ans1);
                System.out.println("true :" + ans3);
                System.out.println("2 Oops!");
            }
        }
        System.out.println("test end");
//
//        int len = 100000000;
//        System.out.println("性能测试");
//        System.out.println("数组大小：" + len);
//        int[] arr = randomArray(len);
//        long start = 0;
//        long end = 0;
//        start = System.currentTimeMillis();
//        noLoopMinStep2(arr);
//        end = System.currentTimeMillis();
//        System.out.println("noLoopMinStep2 run time: " + (end - start) + "(ms)");
//
//        start = System.currentTimeMillis();
//        loopMinStep2(arr);
//        end = System.currentTimeMillis();
//        System.out.println("loopMinStep2 run time: " + (end - start) + "(ms)");
    }

}
