package com.just.han.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.just.han.R;
import com.just.han.model.SpecialShop;
import com.just.han.views.CustomListView;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/29.
 * PullToRefreshScrollView
 */
@ContentView(R.layout.activity_scroll_refresh)
public class ScrollRefreshActivity extends AppCompatActivity {

    @ViewInject(R.id.pull_to_refresh_scroll)
    PullToRefreshScrollView pullToRefreshScrollView;
    @ViewInject(R.id.list_view)
    CustomListView customListView;
    List<SpecialShop> list = new ArrayList<>();
    MyAdapter myAdapter;
    private boolean isRefresh = false;
    private boolean isLoadMore = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        getSpecialShop("1");
        pullToRefreshScrollView.setMode(PullToRefreshBase.Mode.BOTH);
        pullToRefreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                isRefresh = true;
                getSpecialShop("1");
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                isLoadMore = true;
                getSpecialShop("1");
            }
        });
        myAdapter = new MyAdapter(this);
        customListView.setAdapter(myAdapter);

    }

    private void getSpecialShop(final String page) {
        RequestParams params = new RequestParams("http://114.215.92.83/jfshop/index.php/api/index/SpecialShop");
        params.addBodyParameter("page", page);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    String data = object.getString("data");
                    data = data.substring(1, data.length() - 1);
                    JSONObject dataJson = new JSONObject(data);
                    Gson gson = new Gson();
                    List<SpecialShop> gsonList = new ArrayList<>();
                    gsonList = gson.fromJson(dataJson.getString("list"), new TypeToken<List<SpecialShop>>() {
                    }.getType());
                    list.addAll(gsonList);
                    myAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (isRefresh || isLoadMore) {
                    pullToRefreshScrollView.onRefreshComplete();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    class MyAdapter extends BaseAdapter {

        private LayoutInflater inflater;

        public MyAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder;
            if (view != null) {
                holder = (ViewHolder) view.getTag();
            } else {
                view = inflater.inflate(R.layout.item_grid_view, viewGroup, false);
                holder = new ViewHolder();
                x.view().inject(holder, view);
                view.setTag(holder);
            }
            holder.textView.setText(list.get(i).productname);
            return view;
        }
    }

    class ViewHolder {
        @ViewInject(R.id.list_text_view)
        TextView textView;
    }
}
