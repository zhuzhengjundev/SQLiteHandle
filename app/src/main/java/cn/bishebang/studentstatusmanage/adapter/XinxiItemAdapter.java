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
import cn.bishebang.studentstatusmanage.menuActivity.Activity_4_Add;
import cn.bishebang.studentstatusmanage.sqlite.SQLHandle;

public class XinxiItemAdapter extends ArrayAdapter<XinxiItem> {

    private Context mContext;
    private int resourceId;
    private List<XinxiItem> xinxiItemList;

    public XinxiItemAdapter(Context context, int resource, List<XinxiItem> xinxiItemList) {
        super(context, resource, xinxiItemList);
        mContext = context;
        resourceId = resource;
        this.xinxiItemList = xinxiItemList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        XinxiItem xinxiItem = getItem(position);
        View view;
        XinxiItemAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new XinxiItemAdapter.ViewHolder();
            viewHolder.linearLayout = (LinearLayout) view.findViewById(R.id.adapter_xinxi_LinearLayout);
            viewHolder.textView_xuehao = (TextView) view.findViewById(R.id.adapter_xinxi_xuehao);
            viewHolder.textView_name = (TextView) view.findViewById(R.id.adapter_xinxi_name);
            viewHolder.textView_dianhua = (TextView) view.findViewById(R.id.adapter_xinxi_dianhua);
            viewHolder.textView_banji = (TextView) view.findViewById(R.id.adapter_xinxi_banji);
            viewHolder.imageView = (ImageView) view.findViewById(R.id.adapter_xinxi_ImageView);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (XinxiItemAdapter.ViewHolder) view.getTag();
        }
        viewHolder.textView_xuehao.setText(xinxiItem.getXuehao());
        viewHolder.textView_name.setText(xinxiItem.getName());
        viewHolder.textView_dianhua.setText(xinxiItem.getDianhua());
        viewHolder.textView_banji.setText(xinxiItem.getBanji());
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Activity_4_Add.class);
                intent.putExtra("id", xinxiItemList.get(position).getId());
                intent.putExtra("xuehao", xinxiItemList.get(position).getXuehao());
                intent.putExtra("name", xinxiItemList.get(position).getName());
                intent.putExtra("dianhua", xinxiItemList.get(position).getDianhua());
                intent.putExtra("banji", xinxiItemList.get(position).getBanji());
                mContext.startActivity(intent);
            }
        });
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (new SQLHandle(mContext).delData("xinxi", xinxiItemList.get(position).getId())) {
                    Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT).show();
                    remove(xinxiItemList.get(position));
                }else{
                    Toast.makeText(mContext, "删除失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    private class ViewHolder {
        LinearLayout linearLayout;
        TextView textView_xuehao;
        TextView textView_name;
        TextView textView_dianhua;
        TextView textView_banji;
        ImageView imageView;
    }
}
