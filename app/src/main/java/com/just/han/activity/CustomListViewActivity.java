package com.just.han.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.just.han.MainActivity;
import com.just.han.R;
import com.just.han.utils.DebugLog;
import com.just.han.views.CustomListView;
import com.just.han.views.LoadMoreListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomListViewActivity extends AppCompatActivity {

    @BindView(R.id.list_view)
    CustomListView customListView;

    List<String> list = new ArrayList<>();
    ListViewAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DebugLog.i("onCreate");
        DebugLog.w("onCreate");
        DebugLog.e("onCreate");

        setContentView(R.layout.activity_custom_list_view);
        ButterKnife.bind(this);
        getListViewData();
        customListView.setOnLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                getListViewData();
            }
        });
        listAdapter = new ListViewAdapter(CustomListViewActivity.this);
        customListView.setAdapter(listAdapter);

    }

    private void getListViewData() {
        RequestParams params = new RequestParams("https://www.baidu.com");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                int size = list == null ? 0 : list.size();
                for (int i = size; i < size + 10; i++) {
                    list.add(i + "");
                }
                listAdapter.notifyDataSetChanged();
                customListView.onLoadMoreComplete();
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

    class ListViewAdapter extends BaseAdapter {
        private LayoutInflater inflater;

        public ListViewAdapter(Context context) {
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
            return list.size();
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder;
            if (view != null) {
                holder = (ViewHolder) view.getTag();
            } else {
                view = inflater.inflate(R.layout.whatever, viewGroup, false);
                holder = new ViewHolder(view);
                view.setTag(holder);
            }
            holder.textView.setText(list.get(i));
            return view;
        }
    }

    class ViewHolder {
        @BindView(R.id.list_text_view)
        TextView textView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
