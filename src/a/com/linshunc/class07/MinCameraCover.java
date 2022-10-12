package a.com.linshunc.class07;

import class07.Code02_MinCameraCover;
import class07.Code02_MinCameraCover.TreeNode;

public class MinCameraCover {

    // 表示当前节点，被覆盖情况
    private static class Info {
        long coverAndHasCarmera;
        long coverAndHasNoCarmera;
        long unCover;

        public Info(long coverAndHasCarmera, long coverAndHasNoCarmera, long unCover) {
            this.coverAndHasCarmera = coverAndHasCarmera;
            this.coverAndHasNoCarmera = coverAndHasNoCarmera;
            this.unCover = unCover;
        }
    }

    public static int minCameraCover1(TreeNode root) {
        Info info = process(root);
        return (int) Math.min(info.coverAndHasCarmera, Math.min(info.coverAndHasNoCarmera, info.unCover + 1));
    }

    private static Info process(TreeNode root) {
        if (root == null) {
            return new Info(Integer.MAX_VALUE, 0, Integer.MAX_VALUE);
        }
        Info left = process(root.left);
        Info right = process(root.right);
        long coverAndHasNoCarmera = Math.min(left.coverAndHasCarmera
                        + Math.min(right.coverAndHasCarmera, right.coverAndHasNoCarmera)
                , right.coverAndHasCarmera
                        + Math.min(left.coverAndHasCarmera, left.coverAndHasNoCarmera));
        long unCover = left.coverAndHasNoCarmera + right.coverAndHasNoCarmera;
        long coverAndHasCarmera = Math.min(left.unCover,
                Math.min(left.coverAndHasNoCarmera, left.coverAndHasCarmera))
                + Math.min(right.unCover,
                Math.min(right.coverAndHasNoCarmera, right.coverAndHasCarmera))
                + 1;
        return new Info(coverAndHasCarmera, coverAndHasNoCarmera, unCover);
    }

    private static class Info2 {
        // 1表示给覆盖了，当前节点有摄像头，2表示没有摄像头，3表示未给覆盖；
        int status;
        int carmeraNum;

        public Info2(int status, int carmeraNum) {
            this.status = status;
            this.carmeraNum = carmeraNum;
        }
    }

    public static int minCameraCover2(TreeNode root) {
        Info2 info = process2(root);
        int res;
        if (info.status == 1 || info.status == 2) {
            res = info.carmeraNum;
        } else {
            res = info.carmeraNum + 1;
        }
        return res;
    }

    private static Info2 process2(TreeNode root) {
        if (root == null) {
            return new Info2(2, 0);
        }
        Info2 left = process2(root.left);
        Info2 right = process2(root.right);
        // 1表示给覆盖了，当前节点有摄像头，2表示没有摄像头，3表示未给覆盖；
        int status = 0;
        int carmeraNum = 0;
        if (left.status == 3 || right.status == 3) {
            status = 1;
            carmeraNum = left.carmeraNum + right.carmeraNum + 1;
        }
        else if (left.status == 1 || right.status == 1) {
            status = 2;
            carmeraNum = left.carmeraNum + right.carmeraNum;
        } else {
            status = 3;
            carmeraNum = left.carmeraNum + right.carmeraNum;
        }
        return new Info2(status, carmeraNum);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        root.left = new TreeNode();
        root.right = new TreeNode();
        root.right.right = new TreeNode();

        System.out.println(minCameraCover2(root));

    }

}
