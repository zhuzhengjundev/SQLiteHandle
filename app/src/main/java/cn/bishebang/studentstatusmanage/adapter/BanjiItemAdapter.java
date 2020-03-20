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
import cn.bishebang.studentstatusmanage.menuActivity.Activity_1_Add;
import cn.bishebang.studentstatusmanage.sqlite.SQLHandle;

public class BanjiItemAdapter extends ArrayAdapter<BanjiItem> {

    private Context mContext;
    private int resourceId;
    private List<BanjiItem> banjiItemList;

    public BanjiItemAdapter(Context context, int resource, List<BanjiItem> banjiItemList) {
        super(context, resource, banjiItemList);
        mContext = context;
        resourceId = resource;
        this.banjiItemList = banjiItemList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        BanjiItem banjiItem = getItem(position);
        View view;
        BanjiItemAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new BanjiItemAdapter.ViewHolder();
            viewHolder.linearLayout = (LinearLayout) view.findViewById(R.id.adapter_banji_LinearLayout);
            viewHolder.textView_nianji = (TextView) view.findViewById(R.id.adapter_banji_nianji);
            viewHolder.textView_zhuanye = (TextView) view.findViewById(R.id.adapter_banji_zhuanye);
            viewHolder.textView_num = (TextView) view.findViewById(R.id.adapter_banji_num);
            viewHolder.imageView = (ImageView) view.findViewById(R.id.adapter_banji_ImageView);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (BanjiItemAdapter.ViewHolder) view.getTag();
        }
        viewHolder.textView_nianji.setText(banjiItem.getNianji());
        viewHolder.textView_zhuanye.setText(banjiItem.getZhuanye());
        viewHolder.textView_num.setText(banjiItem.getNum());
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, StuListActivity.class);
                intent.putExtra("nianji", banjiItemList.get(position).getNianji());
                intent.putExtra("zhuanye", "");
                mContext.startActivity(intent);
            }
        });
        viewHolder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(mContext, Activity_1_Add.class);
                intent.putExtra("id", banjiItemList.get(position).getId());
                intent.putExtra("nianji", banjiItemList.get(position).getNianji());
                intent.putExtra("zhuanye", banjiItemList.get(position).getZhuanye());
                intent.putExtra("num", banjiItemList.get(position).getNum());
                mContext.startActivity(intent);
                return false;
            }
        });
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check(banjiItemList.get(position).getNianji())) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
                    dialog.setTitle("提示");
                    dialog.setMessage("这个年级还有学生，删除会将学生信息一起删除，确定删除吗？");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (new SQLHandle(mContext).delData("banji", banjiItemList.get(position).getId())) {
                                if (new SQLHandle(mContext).delData("xinxi","banji", banjiItemList.get(position).getNianji())) {
                                    Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT).show();
                                    remove(banjiItemList.get(position));
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
                }else{
                    if (new SQLHandle(mContext).delData("banji", banjiItemList.get(position).getId())) {
                        Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT).show();
                        remove(banjiItemList.get(position));
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
        TextView textView_nianji;
        TextView textView_zhuanye;
        TextView textView_num;
        ImageView imageView;
    }

    private boolean check(String _banji) {
        Cursor cursor = new SQLHandle(mContext).queryAllData("xinxi");
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(cursor.getColumnIndex("banji")).equals(_banji)) {
                    return true;
                }
            } while (cursor.moveToNext());
        }
        return false;
    }
}