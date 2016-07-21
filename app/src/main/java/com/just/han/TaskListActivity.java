package com.just.han;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.just.han.model.CheckTask;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskListActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.task_list_view)
    RecyclerView taskListView;

    private List<CheckTask> list = new ArrayList<>();
    private TaskListAdapter taskListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        ButterKnife.bind(this);
        taskListView.setLayoutManager(new LinearLayoutManager(this));
        taskListAdapter = new TaskListAdapter();
        taskListView.setAdapter(taskListAdapter);
        getData();
    }

    private void getData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                String url = "http://192.168.1.73:4350/MobileApi/GetTaskForDownload";
                RequestParams params = new RequestParams(url);
                params.addBodyParameter("UserId", "7418D8AA-1742-42C3-8BB7-DF2118297AD9");
                x.http().post(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        showTaskInfo(result);
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
        }.start();
    }

    private void showTaskInfo(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            if (jsonObject.getString("result") != null) {

                String data = jsonObject.getString("data");
                Gson gson = new Gson();
                list = gson.fromJson(data, new TypeToken<List<CheckTask>>() {
                }.getType());
                taskListAdapter.notifyDataSetChanged();
            }

        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {

    }

    class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskViewHolder> {

        @Override
        public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(TaskListActivity.this);
            View view = inflater.inflate(R.layout.activity_task_item, parent, false);
            return new TaskViewHolder(view);
        }

        @Override
        public void onBindViewHolder(TaskViewHolder holder, int position) {
            CheckTask checkTask = list.get(position);
            switch (checkTask.TaskStatus == null ? "" : checkTask.TaskStatus) {
                case "0":
                    holder.iv_Task_Route.setImageResource(R.drawable.task_route);
                    holder.tv_task_status.setText("已下发");
                    break;
                case "1":
                    holder.iv_Task_Route.setImageResource(R.drawable.task_route_c);
                    holder.tv_task_status.setText("执行中");
                    break;
                case "2":
                    holder.iv_Task_Route.setImageResource(R.drawable.task_route_f);
                    holder.tv_task_status.setText("已完成");
                    break;
                default:
                    break;
            }
            String during = "0";
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                long t = sdf.parse(checkTask.EndTime).getTime() - sdf.parse(checkTask.StartTime).getTime();
                during = ((int) (t / 60000L)) + "";
            } catch (Exception e) {
                e.printStackTrace();
            }
            holder.tv_time_check.setText(checkTask.StartTime + " —— " + checkTask.EndTime);
            holder.tv_Todayt_TotalTime.setText(during);
            holder.tv_Todayt_Attention.setText(checkTask.TaskAttention);
            holder.tv_Today_TaskName.setText(checkTask.TaskName);
            holder.tv_Today_TaskCode.setText(checkTask.TaskCode);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class TaskViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.time_check)
            TextView tv_time_check;
            @BindView(R.id.time_total)
            TextView tv_Todayt_TotalTime;
            @BindView(R.id.task_name)
            TextView tv_Today_TaskName;
            @BindView(R.id.task_attention)
            TextView tv_Todayt_Attention;
            @BindView(R.id.task_route)
            ImageView iv_Task_Route;
            @BindView(R.id.task_code)
            TextView tv_Today_TaskCode;
            @BindView(R.id.task_status)
            TextView tv_task_status;

            public TaskViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }

}
