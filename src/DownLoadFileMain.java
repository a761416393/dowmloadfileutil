import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class DownLoadFileMain {
    public static void main(String []args){
        DownloadFileWithThreadPool down = new DownloadFileWithThreadPool();
        try {
            down.getFileWithThreadPool("file:///Users/chenhao/Desktop/7DaysCode/Day3/HomeworkAnswer/Challenge1/images/banner.jpg","/Users/chenhao/Desktop/test/a.jpg",6);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //以读写方式打开并写入一行文本
//        File fis = new File("/Users/chenhao/Desktop/test/a.txt");
//        RandomAccessFile raf = null;
//        try {
//            raf = new RandomAccessFile(fis, "rw");
//
//        byte[] writeStr = "this is a demo!".getBytes();
//        raf.write(writeStr);
//        raf.close();
//        //以只读方式打开并读取一行数据
//        RandomAccessFile rafRead = new RandomAccessFile("/Users/chenhao/Desktop/test/a.txt", "r");
//        System.out.println(rafRead.readLine());
//        rafRead.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
