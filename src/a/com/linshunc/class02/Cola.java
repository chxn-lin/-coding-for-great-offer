package a.com.linshunc.class02;

import static class02.Code02_Cola.putTimes;

public class Cola {

    //购买的可乐数量是m，其中手头拥有的10、50、100的数量分别为a、b、c，
    //可乐的价格是x(x是10的倍数)
    public static int method1(int a, int b, int c, int m, int x){
        if (m == 0) {
            return 0;
        }
        int[] zhang = {c, b , a};
        int[] qian = {100, 50, 10};
        if (c * 100 + 50 * b + 10 * a < x * m) {
            return -1;
        }
        int puts = 0;
        int lastRestZhang = 0;
        int lastRestQian = 0;
        for (int i = 0; i < 3 && m != 0; i++) {
            // 处理第一瓶可乐数据
            int curQianBuyOneColaZhang = (x - lastRestQian - 1)/qian[i] + 1;
            if (curQianBuyOneColaZhang <= zhang[i]) {
                // 表示钱够买一瓶
                zhang[i] -= curQianBuyOneColaZhang;
                giveRest(zhang, qian, i + 1, lastRestQian + curQianBuyOneColaZhang * qian[i] - x, 1);
                m--;
                puts += curQianBuyOneColaZhang + lastRestZhang;
            } else {
                lastRestQian += qian[i] * zhang[i];
                lastRestZhang += zhang[i];
                continue;
            }
            // 处理单独用自己这个币种，购买可乐的情况
            curQianBuyOneColaZhang = (x - 1)/qian[i] + 1;
            int curZhangBuyMaxCola = Math.min(m, zhang[i] / curQianBuyOneColaZhang);
            giveRest(zhang, qian, i + 1, (curQianBuyOneColaZhang * qian[i] - x), curZhangBuyMaxCola);

            m -= curZhangBuyMaxCola;
            puts += curQianBuyOneColaZhang * curZhangBuyMaxCola;
            lastRestZhang = zhang[i] % curQianBuyOneColaZhang;
            lastRestQian = lastRestZhang * qian[i];
        }

        return puts;
    }

    private static void giveRest(int[] zhang, int[] qian, int index, int rest, int num) {
        for (int i = index; i < zhang.length; i++) {
            zhang[i] += (rest / qian[i]) * num;
            rest %= qian[i];
        }
    }

    public static void main(String[] args) {
//        int m = 3;
//        int x = 130;
//        int c = 1;
//        int b = 6;
//        int a = 0;
//
//        System.out.println("my : " + method1(a, b, c, m, x));
//        System.out.println("true : " + putTimes(m, a, b, c, x));

        int testTime = 1000;
        int zhangMax = 10;
        int colaMax = 10;
        int priceMax = 20;
        System.out.println("如果错误会打印错误数据，否则就是正确");
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int m = (int) (Math.random() * colaMax);
            int a = (int) (Math.random() * zhangMax);
            int b = (int) (Math.random() * zhangMax);
            int c = (int) (Math.random() * zhangMax);
            int x = ((int) (Math.random() * priceMax) + 1) * 10;
            int ans1 = putTimes(m, a, b, c, x);
            int ans2 = method1(a, b, c, m, x);
            if (ans1 != ans2) {
                System.out.println("int m = " + m + ";");
                System.out.println("int a = " + a + ";");
                System.out.println("int b = " + b + ";");
                System.out.println("int c = " + c + ";");
                System.out.println("int x = " + x + ";");

                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("test end");
    }

}
