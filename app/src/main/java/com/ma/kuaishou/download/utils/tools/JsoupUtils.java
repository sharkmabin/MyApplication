package com.ma.kuaishou.download.utils.tools;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JsoupUtils {

    public static void getDataByJsoup(final String url,final DownloadUrlCallback callback){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(url)
                            .timeout(5000) // 设置超时时间
                            .get(); // 使用GET方法访问URL

                    Elements elements = doc.select("div.video");
                    for (Element element:elements){
                        String downloadurl = element.select("video").attr("src"); // 新闻内容链接
                        Log.e(JsoupUtils.class.getSimpleName(),downloadurl) ;
                        callback.downloadUrl(downloadurl);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }) .start();
    }
}
