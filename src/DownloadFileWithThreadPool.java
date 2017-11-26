import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DownloadFileWithThreadPool {
    public void getFileWithThreadPool(String urlLocation,String filePath,int poolLength) throws IOException {
        Executor threadPool = Executors.newFixedThreadPool(poolLength); //创建线程池
        long len = getContentLength(urlLocation);
        for(int i=0;i<poolLength;i++){
            long start = i*len/poolLength;
            long end = (i+1)*len/poolLength-1;
            if(i==poolLength-1){
                end=len;
            }
            DownloadWithRange download = new DownloadWithRange(urlLocation,filePath,start,end);
            threadPool.execute(download);
        }

    }

    public long getContentLength(String urlLocation) throws IOException {
        URL url = null;
        if(urlLocation!=null){
            url = new URL(urlLocation);
        }
        URLConnection con =  url.openConnection();
        con.setReadTimeout(5000);
       // con.setRequestMethod("GET");
        long len = con.getContentLength();
        return len;

    }
}
