package a.com.linshunc.class01;

public class Near2Power {

    public static int method1(int num){
        num--;
        num |= num >>> 1;
        num |= num >>> 2;
        num |= num >>> 4;
        num |= num >>> 8;
        num |= num >>> 16;
        return num < 0 ? 1 : num + 1;
    }

    public static void main(String[] args) {
        int k = 19;
        System.out.println(method1(k));
    }

}
