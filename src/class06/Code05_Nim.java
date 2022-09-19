package class06;

public class Code05_Nim {

	// 保证arr是正数数组
	public static boolean printWinner(int[] arr) {
		int eor = 0;
		for (int num : arr) {
			eor ^= num;
		}
		if (eor == 0) {
//			System.out.println("后手赢");
			return false;
		} else {
//			System.out.println("先手赢");
			return true;
		}
	}

}
