package com.just.han.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chanven.lib.cptr.loadmore.SwipeRefreshHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.just.han.R;
import com.just.han.adapter.SsqAdapter;
import com.just.han.base.DirectionInfo;
import com.just.han.handler.SsqHandler;
import com.just.han.model.SsqModel;
import com.just.han.utils.DebugLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SsqActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    public SwipeRefreshHelper swipeRefreshHelper;
    public SsqAdapter ssqAdapter;
    private List<SsqModel> list = new ArrayList<>();
    private SsqHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssq);
        ButterKnife.bind(this);
        handler = new SsqHandler(new WeakReference<>(this));
        initView();

    }


    private void initView() {
        swipeRefreshHelper = new SwipeRefreshHelper(swipeRefreshLayout);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshHelper.autoRefresh();
            }
        });
        swipeRefreshHelper.setOnSwipeRefreshListener(new SwipeRefreshHelper.OnSwipeRefreshListener() {
            @Override
            public void onfresh() {
                getSsqData();
            }
        });
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        ssqAdapter = new SsqAdapter(this, list);
        recyclerView.setAdapter(ssqAdapter);
    }

    private void getSsqData() {
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //创建一个Request
        final Request request = new Request.Builder()
                .url(DirectionInfo.CPApi)
                .build();
        //new call
        Call call = mOkHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                DebugLog.e(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String data = jsonObject.getString("data");
                    Gson gson = new Gson();
                    List<SsqModel> mList = gson.fromJson(data, new TypeToken<List<SsqModel>>() {
                    }.getType());
                    list.clear();
                    list.addAll(mList);
                    handler.sendEmptyMessage(handler.REFRESH_CODE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
