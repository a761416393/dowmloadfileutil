import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class DownloadWithRange implements Runnable {
    private String localFilePath;
    private String filePath;
    private long start;
    private long end;

    public DownloadWithRange(String localFilePath, String filePath, long start, long end) {
        this.localFilePath = localFilePath;
        this.filePath = filePath;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        try {
            URLConnection urlConnection = getHttp();
            //URLConnection.setRequestProperty("Range","bytes="+start+"-"+end);//向指定的字节位置写入信息
            urlConnection.addRequestProperty("Range","bytes="+start+"-"+end);
            File file = new File(filePath);
            System.out.println(filePath);
            RandomAccessFile out =null;
            if(file!=null){
                out = new RandomAccessFile(file,"rwd");
            }
            out.seek(start); //写的指针调到开始的位置
            InputStream in = urlConnection.getInputStream();//从链接中获取流的数据

            byte[] b = new byte[1024];
            int len = 0;
            while((len=in.read(b))!=-1){
               out.write(b,0,len);//  将 len 个字节从指定 byte 数组写入到此文件，并从偏移量 off 处开始。
            }
            in.close();;
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private URLConnection getHttp() throws IOException {
        URL url=null;
        if(localFilePath!=null){
            url = new URL(localFilePath);
        }
        URLConnection httpURLConnection =  url.openConnection(); //通过url的打开获取http的链接
        httpURLConnection.setReadTimeout(5000);//设置链接超时时间
        //httpURLConnection.setRequestMethod("GET");//设置链接的请求方式
        return httpURLConnection;
    }
}
