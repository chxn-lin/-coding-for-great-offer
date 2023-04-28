package a.com.linshunc.class35;

import java.util.Arrays;

import static class35.Code03_WatchMovieMaxTime.maxEnjoy3;
import static class35.Code03_WatchMovieMaxTime.randomMovies;

public class Code03_Watch3MovieMaxTime {

    public static int myMaxEnjoy1(int[][] movies) {
        if (movies.length < 3) {
            return -1;
        }
        Arrays.sort(movies, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        return process(movies, 0, 3, 0);
    }

    private static int process(int[][] movies, int index, int rest, int pre) {
        if (rest == 0) {
            return 0;
        }
        if (index == movies.length) {
            return -1;
        }
        int res = process(movies, index + 1, rest, pre);
        if (movies[index][0] >= pre) {
            int p2 = process(movies, index + 1, rest - 1, movies[index][1]);
            if (p2 != -1) {
                res = Math.max(res, p2 + movies[index][1] - movies[index][0]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 10;
        int t = 20;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * n) + 1;
            int[][] movies = randomMovies(len, t);
            int ans1 = myMaxEnjoy1(movies);
            int ans3 = maxEnjoy3(movies);
            if (ans1 != ans3) {
                for (int[] m : movies) {
                    System.out.println(m[0] + " , " + m[1]);
                }
                System.out.println(ans1);
                System.out.println("出错了");
            }
        }
        System.out.println("测试结束");
    }
}
