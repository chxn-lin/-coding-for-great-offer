package a.com.linshunc.class04;

import static a.com.linshunc.class01.AOE.printArr;
import static class04.Code06_MakeNo.isValid;
import static class04.Code06_MakeNo.makeNo;

public class MakeNo {

    public static int[] method1(int size){
        if (size < 1) {
            return new int[0];
        }

        return process(size);
    }

    private static int[] process(int size) {
        if (size == 1) {
            return new int[]{1};
        }
        int baseSize = (size + 1) / 2;
        int[] base = method1(baseSize);
        int[] res = new int[size];
        int index = 0;
        for (; index < baseSize; index++) {
            res[index] = base[index] * 2 - 1;
        }
        for (int i = 0; i < size - baseSize; i++, index++) {
            res[index] = res[i] * 2;
        }
        
        return res;
    }

    public static void main(String[] args) {
        printArr(method1(9));


		System.out.println("test begin");
		for (int N = 1; N < 1000; N++) {
			int[] arr = method1(N);
			if (!isValid(arr)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test end");
		System.out.println(isValid(makeNo(1042)));
		System.out.println(isValid(makeNo(2981)));
    }

}
