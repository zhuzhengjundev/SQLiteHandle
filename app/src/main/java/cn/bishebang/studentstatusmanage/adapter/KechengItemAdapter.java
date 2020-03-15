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
import cn.bishebang.studentstatusmanage.menuActivity.Activity_5_Add;
import cn.bishebang.studentstatusmanage.sqlite.SQLHandle;

public class KechengItemAdapter extends ArrayAdapter<KechengItem> {

    private Context mContext;
    private int resourceId;
    private List<KechengItem> kechengItemList;

    public KechengItemAdapter(Context context, int resource, List<KechengItem> kechengItemList) {
        super(context, resource, kechengItemList);
        mContext = context;
        resourceId = resource;
        this.kechengItemList = kechengItemList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        KechengItem kechengItem = getItem(position);
        View view;
        KechengItemAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new KechengItemAdapter.ViewHolder();
            viewHolder.linearLayout = (LinearLayout) view.findViewById(R.id.adapter_kecheng_LinearLayout);
            viewHolder.textView_name = (TextView) view.findViewById(R.id.adapter_kecheng_name);
            viewHolder.imageView = (ImageView) view.findViewById(R.id.adapter_kecheng_ImageView);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (KechengItemAdapter.ViewHolder) view.getTag();
        }
        viewHolder.textView_name.setText(kechengItem.getName());
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Activity_5_Add.class);
                intent.putExtra("id", kechengItemList.get(position).getId());
                intent.putExtra("name", kechengItemList.get(position).getName());
                mContext.startActivity(intent);
            }
        });
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (new SQLHandle(mContext).delData("kecheng", kechengItemList.get(position).getId())) {
                    Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT).show();
                    remove(kechengItemList.get(position));
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