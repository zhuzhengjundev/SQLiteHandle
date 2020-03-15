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
import cn.bishebang.studentstatusmanage.menuActivity.Activity_6_Add;
import cn.bishebang.studentstatusmanage.sqlite.SQLHandle;

public class LuntanItemAdapter extends ArrayAdapter<LuntanItem> {

    private Context mContext;
    private int resourceId;
    private List<LuntanItem> luntanItemList;

    public LuntanItemAdapter(Context context, int resource, List<LuntanItem> luntanItemList) {
        super(context, resource, luntanItemList);
        mContext = context;
        resourceId = resource;
        this.luntanItemList = luntanItemList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LuntanItem luntanItem = getItem(position);
        View view;
        LuntanItemAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new LuntanItemAdapter.ViewHolder();
            viewHolder.linearLayout = (LinearLayout) view.findViewById(R.id.adapter_luntan_LinearLayout);
            viewHolder.textView_title = (TextView) view.findViewById(R.id.adapter_luntan_title);
            viewHolder.textView_content = (TextView) view.findViewById(R.id.adapter_luntan_content);
            viewHolder.imageView = (ImageView) view.findViewById(R.id.adapter_luntan_ImageView);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (LuntanItemAdapter.ViewHolder) view.getTag();
        }
        viewHolder.textView_title.setText(luntanItem.getTitle());
        viewHolder.textView_content.setText(luntanItem.getContent());
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Activity_6_Add.class);
                intent.putExtra("id", luntanItemList.get(position).getId());
                intent.putExtra("title", luntanItemList.get(position).getTitle());
                intent.putExtra("content", luntanItemList.get(position).getContent());
                mContext.startActivity(intent);
            }
        });
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (new SQLHandle(mContext).delData("luntan", luntanItemList.get(position).getId())) {
                    Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT).show();
                    remove(luntanItemList.get(position));
                }else{
                    Toast.makeText(mContext, "删除失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    private class ViewHolder {
        LinearLayout linearLayout;
        TextView textView_title;
        TextView textView_content;
        ImageView imageView;
    }
}