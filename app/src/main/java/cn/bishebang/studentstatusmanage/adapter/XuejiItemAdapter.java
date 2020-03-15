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
import cn.bishebang.studentstatusmanage.menuActivity.Activity_2_Add;
import cn.bishebang.studentstatusmanage.sqlite.SQLHandle;

public class XuejiItemAdapter extends ArrayAdapter<XuejiItem> {

    private Context mContext;
    private int resourceId;
    private List<XuejiItem> xuejiItemList;

    public XuejiItemAdapter(Context context, int resource, List<XuejiItem> xuejiItemList) {
        super(context, resource, xuejiItemList);
        mContext = context;
        resourceId = resource;
        this.xuejiItemList = xuejiItemList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        XuejiItem xuejiItem = getItem(position);
        View view;
        XuejiItemAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new XuejiItemAdapter.ViewHolder();
            viewHolder.linearLayout = (LinearLayout) view.findViewById(R.id.adapter_xueji_LinearLayout);
            viewHolder.textView_stuname = (TextView) view.findViewById(R.id.adapter_xueji_stuname);
            viewHolder.textView_ruxuenianfen = (TextView) view.findViewById(R.id.adapter_xueji_ruxuenianfen);
            viewHolder.textView_shifozaiji = (TextView) view.findViewById(R.id.adapter_xueji_shifozaiji);
            viewHolder.imageView = (ImageView) view.findViewById(R.id.adapter_xueji_ImageView);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (XuejiItemAdapter.ViewHolder) view.getTag();
        }
        viewHolder.textView_stuname.setText(xuejiItem.getStuname());
        viewHolder.textView_ruxuenianfen.setText(xuejiItem.getRuxuenianfen());
        viewHolder.textView_shifozaiji.setText(xuejiItem.getShifozaiji());
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Activity_2_Add.class);
                intent.putExtra("id", xuejiItemList.get(position).getId());
                intent.putExtra("stuname", xuejiItemList.get(position).getStuname());
                intent.putExtra("ruxuenianfen", xuejiItemList.get(position).getRuxuenianfen());
                intent.putExtra("shifozaiji", xuejiItemList.get(position).getShifozaiji());
                mContext.startActivity(intent);
            }
        });
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (new SQLHandle(mContext).delData("xueji", xuejiItemList.get(position).getId())) {
                    Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT).show();
                    remove(xuejiItemList.get(position));
                }else{
                    Toast.makeText(mContext, "删除失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    private class ViewHolder {
        LinearLayout linearLayout;
        TextView textView_stuname;
        TextView textView_ruxuenianfen;
        TextView textView_shifozaiji;
        ImageView imageView;
    }
}