package com.just.han.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.just.han.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HttpConnActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_visit_web)
    Button visitWebBtn;// = null;
    @BindView(R.id.btn_download_img)
    Button downImgBtn;// = null;
    @BindView(R.id.textview_show)
    TextView showTextView = null;
    @BindView(R.id.imagview_show)
    ImageView showImageView = null;
    String resultStr = "";
    ProgressBar progressBar = null;
    ViewGroup viewGroup = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_conn);
        ButterKnife.bind(this);
        visitWebBtn.setOnClickListener(this);
        downImgBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_visit_web:
                showImageView.setVisibility(View.GONE);
                showTextView.setVisibility(View.VISIBLE);
                Thread visitBaiduThread = new Thread(new VisitWebRunnable());
                visitBaiduThread.start();
                try {
                    visitBaiduThread.join();
                    if (!resultStr.equals("")) {
                        showTextView.setText(resultStr);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_download_img:
                showImageView.setVisibility(View.VISIBLE);
                showTextView.setVisibility(View.GONE);
                String imgUrl = "http://www.shixiu.net/d/file/p/2bc22002a6a61a7c5694e7e641bf1e6e.jpg";
                new DownImgAsyncTask().execute(imgUrl);
                break;
        }
    }

    class VisitWebRunnable implements Runnable {
        @Override
        public void run() {
            resultStr = getURLResponse("https://www.baidu.com/");
        }
    }

    /**
     * 获取指定URL的响应字符串
     *
     * @param urlString 网络地址
     * @return 返回结果
     */
    private String getURLResponse(String urlString) {
        HttpURLConnection conn = null; //连接对象
        InputStream is = null;
        String resultData = "";
        try {
            URL url = new URL(urlString); //URL对象
            conn = (HttpURLConnection) url.openConnection(); //使用URL打开一个链接
            conn.setDoInput(true); //允许输入流，即允许下载
            conn.setDoOutput(true); //允许输出流，即允许上传
            conn.setUseCaches(false); //不使用缓冲
            conn.setRequestMethod("GET"); //使用get请求
            is = conn.getInputStream();   //获取输入流，此时才真正建立链接
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bufferReader = new BufferedReader(isr);
            String inputLine = "";
            while ((inputLine = bufferReader.readLine()) != null) {
                resultData += inputLine + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return resultData;
    }

    class DownImgAsyncTask extends AsyncTask<String, Void, Bitmap> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showImageView.setImageBitmap(null);
            showProgressBar();//显示进度条提示框

        }

        @Override
        protected Bitmap doInBackground(String... params) {
            return getImageBitmap(params[0]);
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            if (result != null) {
                dismissProgressBar();
                showImageView.setImageBitmap(result);
            }
        }
    }

    /**
     * 从指定URL获取图片
     *
     * @param url 图片地址
     * @return 返回图片bitmap
     */
    private Bitmap getImageBitmap(String url) {
        URL imgUrl;
        Bitmap bitmap = null;
        try {
            imgUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) imgUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 在母布局中间显示进度条
     */
    private void showProgressBar() {
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        progressBar.setVisibility(View.VISIBLE);
        viewGroup = (ViewGroup) findViewById(R.id.parent_view);
        viewGroup.addView(progressBar, params);
    }

    /**
     * 隐藏进度条
     */
    private void dismissProgressBar() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
            viewGroup.removeView(progressBar);
            progressBar = null;
        }
    }
}
