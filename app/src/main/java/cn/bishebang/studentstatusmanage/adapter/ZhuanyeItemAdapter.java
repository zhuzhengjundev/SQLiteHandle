package cn.bishebang.studentstatusmanage.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.List;

import cn.bishebang.studentstatusmanage.R;
import cn.bishebang.studentstatusmanage.StuListActivity;
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
                Intent intent = new Intent(mContext, StuListActivity.class);
                intent.putExtra("nianji", "");
                intent.putExtra("zhuanye", zhuanyeItemList.get(position).getName());
                mContext.startActivity(intent);
            }
        });
        viewHolder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(mContext, Activity_3_Add.class);
                intent.putExtra("id", zhuanyeItemList.get(position).getId());
                intent.putExtra("name", zhuanyeItemList.get(position).getName());
                mContext.startActivity(intent);
                return false;
            }
        });
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check(zhuanyeItemList.get(position).getName())) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
                    dialog.setTitle("提示");
                    dialog.setMessage("这个专业还有学生，删除会将学生信息一起删除，确定删除吗？");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (new SQLHandle(mContext).delData("banji", zhuanyeItemList.get(position).getId())) {
                                if (new SQLHandle(mContext).delData("xinxi","zhuanye", zhuanyeItemList.get(position).getName())) {
                                    Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT).show();
                                    remove(zhuanyeItemList.get(position));
                                }
                            }else{
                                Toast.makeText(mContext, "删除失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    dialog.show();
                }else {
                    if (new SQLHandle(mContext).delData("zhuanye", zhuanyeItemList.get(position).getId())) {
                        Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT).show();
                        remove(zhuanyeItemList.get(position));
                    }else{
                        Toast.makeText(mContext, "删除失败", Toast.LENGTH_SHORT).show();
                    }
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

    private boolean check(String _zhuanye) {
        Cursor cursor = new SQLHandle(mContext).queryAllData("xinxi");
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(cursor.getColumnIndex("zhuanye")).equals(_zhuanye)) {
                    return true;
                }
            } while (cursor.moveToNext());
        }
        return false;
    }
}