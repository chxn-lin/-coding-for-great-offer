package a.com.linshunc.class33;

public class Prob_0251_Flatten2DVector {

    public static void main(String[] args) {
        int[][] arr = {
                {1,2,3,4},
                {},
                {5,6},
                {7,8,9,10,11},
        };
        Vector2D vector = new Vector2D(arr);
        while (vector.hasNext()) {
            System.out.println(vector.next());
        }
    }

    public static class Vector2D {
        int[][] arr;
        int row;
        int col;

        public Vector2D(int[][] arr) {
            this.arr = arr;
            this.row = 0;
            this.col = 0;
        }

        public int next() {
            if (row >= arr.length) {
                throw new RuntimeException();
            }
            int ans = arr[row][col];
            col++;
            if (col >= arr[row].length) {
                col = 0;
                row++;
                while (row < arr.length && arr[row].length <= 0) {
                    row++;
                }
            }

            return ans;
        }

        public boolean hasNext() {
            if (row >= arr.length) {
                return false;
            }
            return true;
        }
    }

}
