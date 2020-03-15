package cn.bishebang.studentstatusmanage.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.bishebang.studentstatusmanage.R;
import cn.bishebang.studentstatusmanage.menuActivity.Activity_3_Add;
import cn.bishebang.studentstatusmanage.sqlite.SQLHandle;

public class ZhuanyeItemAdapter extends ArrayAdapter<ZhuanyeItem> {

    private Context mContext;
    private int resourceId;
    private List<ZhuanyeItem> zhuanyeItemList;

    public ZhuanyeItemAdapter(Context context, int resource, List<ZhuanyeItem> zhuanyeItemList) {
        super(context, resource, zhuanyeItemList);
        mContext = context;
        resourceId = resource;
        this.zhuanyeItemList = zhuanyeItemList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ZhuanyeItem zhuanyeItem = getItem(position);
        View view;
        ZhuanyeItemAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ZhuanyeItemAdapter.ViewHolder();
            viewHolder.linearLayout = (LinearLayout) view.findViewById(R.id.adapter_zhuanye_LinearLayout);
            viewHolder.textView_name = (TextView) view.findViewById(R.id.adapter_zhuanye_name);
            viewHolder.imageView = (ImageView) view.findViewById(R.id.adapter_zhuanye_ImageView);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ZhuanyeItemAdapter.ViewHolder) view.getTag();
        }
        viewHolder.textView_name.setText(zhuanyeItem.getName());
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Activity_3_Add.class);
                intent.putExtra("id", zhuanyeItemList.get(position).getId());
                intent.putExtra("name", zhuanyeItemList.get(position).getName());
                mContext.startActivity(intent);
            }
        });
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (new SQLHandle(mContext).delData("zhuanye", zhuanyeItemList.get(position).getId())) {
                    Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT).show();
                    remove(zhuanyeItemList.get(position));
                }else{
                    Toast.makeText(mContext, "删除失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    private class ViewHolder {
        LinearLayout linearLayout;
        TextView textView_name;
        ImageView imageView;
    }
}