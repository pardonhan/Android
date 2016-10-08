package com.just.han.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.just.han.R;
import com.just.han.views.SpecificationsView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HanFL on 2016/9/29.
 * <p>
 * PropertyAdapter
 */

public class PropertyAdapter extends BaseAdapter {

    private Context mContext;
    private List<Map<String,  String[]>> mList;
    private ArrayList<Map<String, TextView[]>> mViewList;


    //用于保存用户的属性集合
    private HashMap<String, String> selectProMap = new HashMap<>();

    /**
     * 返回选中的属性
     *
     * @return SelectProMap
     */
    public HashMap<String, String> getSelectProMap() {
        return selectProMap;
    }


    public void setSelectProMap(HashMap<String, String> selectProMap) {
        this.selectProMap = selectProMap;
    }


    public PropertyAdapter(Context context, List<Map<String,  String[]>> list) {
        super();
        this.mContext = context;
        this.mList = list;
        mViewList = new ArrayList<>();
        // drawableNormal = mContext.getResources().getDrawable(R.drawable.tv_property_label);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            // 获取list_item布局文件的视图
            convertView = LayoutInflater.from(this.mContext).inflate(R.layout.lv_property_item, parent, false);
            holder = new ViewHolder();
            // 获取控件对象
            holder.tvPropName = (TextView) convertView.findViewById(R.id.tv_property_name);
            //holder.llPropContents=(LinearLayout)convertView.findViewById(R.id.ll_property_content);
            //holder.tlPropContents=(TableLayout)convertView.findViewById(R.id.ll_property_content);
            // 设置控件集到convertView
            holder.vgPropContents = (SpecificationsView) convertView.findViewById(R.id.specifications_view);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (this.mList != null) {
            String type =  this.mList.get(position).get("type")[0];
            String[] lables =  this.mList.get(position).get("lable");
            holder.tvPropName.setText(type);//规格名称
            //动态加载标签
            //判断布局中的子控件是否为0，如果不为0，就不添加了，防止ListView滚动时重复添加
            if (holder.vgPropContents.getChildCount() == 0) {
                TextView[] textViews = new TextView[lables.length];
                //设置每个标签的文本和布局
                //TableRow tr=new TableRow(mContext);
                for (int i = 0; i < lables.length; i++) {
                    TextView textView = new TextView(mContext);
                    textView.setGravity(17);
                    textView.setPadding(25, 15, 25, 15);
                    textViews[i] = textView;
                    //textViews[i].setBackgroundResource(R.drawable.tv_property_label);
                    textViews[i].setText(lables[i]);
                    textViews[i].setTag(i);
                    textViews[i].setTextColor(Color.WHITE);
                    //textViews[i].setBackgroundColor(Color.parseColor("#EE5500"));
                    //tr.addView(textViews[i]);
                    // holder.llPropContents.addView(textViews[i]);
                    holder.vgPropContents.addView(textViews[i]);
                }
                //holder.tlPropContents.addView(tr);
                //绑定标签的Click事件
                for (TextView textView : textViews) {
                    textView.setTag(textViews);
                    textView.setOnClickListener(new LableClickListener(type));
                }
                //把控件存起来
                //mapView.put(type, textViews);
                //mViewList.add(mapView);
            }
            /**
             * 判断之前是否已选中标签
             **/
            if (selectProMap.get(type) != null) {
                for (int h = 0; h < holder.vgPropContents.getChildCount(); h++) {
                    TextView v = (TextView) holder.vgPropContents.getChildAt(h);
                    if (selectProMap.get(type).equals(v.getText().toString())) {
                        v.setBackgroundColor(Color.parseColor("#EE5500"));
                        v.setTextColor(Color.parseColor("#FFFFFF"));
                        selectProMap.put(type, v.getText().toString());
                    }
                }
            }
        }
        return convertView;
    }

    /**
     * 定义item对象
     */
    public class ViewHolder {
        TextView tvPropName;
        LinearLayout llPropContents;
        SpecificationsView vgPropContents;
        TableLayout tlPropContents;
    }

    class LableClickListener implements View.OnClickListener {
        private String type;

        public LableClickListener(String type) {
            this.type = type;
        }

        @Override
        public void onClick(View v) {
            TextView[] textViews = (TextView[]) v.getTag();
            TextView tv = (TextView) v;
            for (TextView textView : textViews) {
                //让点击的标签背景变成橙色，字体颜色变为白色
                if (tv.equals(textView)) {
                    textView.setBackgroundColor(Color.parseColor("#EE5500"));
                    textView.setTextColor(Color.parseColor("#FFFFFF"));
                    selectProMap.put(type, textView.getText().toString());
                } else {
                    //其他标签背景变成白色，字体颜色为黑色
                    //textViews[i].setBackgroundDrawable(drawableNormal);
                    //textViews[i].setBackgroundResource(R.drawable.tv_property_label);
                    textView.setTextColor(Color.parseColor("#000000"));
                }
            }
        }
    }
}
