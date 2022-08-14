package a.com.linshunc.class01;

import java.io.File;
import java.util.LinkedList;

import static class01.Code02_CountFiles.getFileNumber;

public class FindFileNum {

    public static int method1(String fileName){
        File file = new File(fileName);
        int res = 0;
        if (file.exists()) {
            if (file.isDirectory()) {
                LinkedList<File> queue = new LinkedList<>();
                queue.add(file);
                while (!queue.isEmpty()) {
                    File poll = queue.poll();
                    for (File listFile : poll.listFiles()) {
                        if (listFile.isDirectory()) {
                            queue.add(listFile);
                        } else {
                            res++;
                        }
                    }
                }
            } else {
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        // 你可以自己更改目录
        String path = "E:\\yusys";
        System.out.println(getFileNumber(path));
        System.out.println(method1(path));
    }

}
