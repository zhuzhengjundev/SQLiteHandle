package cn.bishebang.studentstatusmanage;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import cn.bishebang.studentstatusmanage.adapter.XinxiItem;
import cn.bishebang.studentstatusmanage.adapter.XinxiItemAdapter;
import cn.bishebang.studentstatusmanage.sqlite.SQLHandle;

public class StuListActivity extends AppCompatActivity {

    private ListView listView;
    private XinxiItem xinxiItem;
    private List<XinxiItem> xinxiItemList;
    private XinxiItemAdapter xinxiItemAdapter;

    private String _banji, _zhuanye;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_list);
        findViewById(R.id.activity_stu_list_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        _banji = intent.getStringExtra("nianji");
        _zhuanye = intent.getStringExtra("zhuanye");
        TextView textView = findViewById(R.id.activity_stu_list_title);
        if (_banji.isEmpty()) {
            textView.setText(_zhuanye + "专业的学生信息");
        }else {
            textView.setText(_banji + "班的学生信息");
        }

        listView = findViewById(R.id.activity_stu_list_ListView);
        xinxiItemList = new ArrayList<>();
        xinxiItemAdapter = new XinxiItemAdapter(StuListActivity.this, R.layout.adapter_xinxi, xinxiItemList);
        listView.setAdapter(xinxiItemAdapter);
        Cursor cursor = new SQLHandle(StuListActivity.this).queryAllData("xinxi");
        if (cursor.moveToFirst()) {
            do {
                if (_banji.isEmpty()) {
                    if (cursor.getString(cursor.getColumnIndex("zhuanye")).equals(_zhuanye)) {
                        xinxiItem = new XinxiItem(cursor.getString(cursor.getColumnIndex("id")),
                                cursor.getString(cursor.getColumnIndex("xuehao")),
                                cursor.getString(cursor.getColumnIndex("name")),
                                cursor.getString(cursor.getColumnIndex("dianhua")),
                                cursor.getString(cursor.getColumnIndex("banji")),
                                cursor.getString(cursor.getColumnIndex("zhuanye")));
                        xinxiItemList.add(xinxiItem);
                        xinxiItemAdapter.notifyDataSetChanged();
                    }
                }else {
                    if (cursor.getString(cursor.getColumnIndex("banji")).equals(_banji)) {
                        xinxiItem = new XinxiItem(cursor.getString(cursor.getColumnIndex("id")),
                                cursor.getString(cursor.getColumnIndex("xuehao")),
                                cursor.getString(cursor.getColumnIndex("name")),
                                cursor.getString(cursor.getColumnIndex("dianhua")),
                                cursor.getString(cursor.getColumnIndex("banji")),
                                cursor.getString(cursor.getColumnIndex("zhuanye")));
                        xinxiItemList.add(xinxiItem);
                        xinxiItemAdapter.notifyDataSetChanged();
                    }
                }
            } while (cursor.moveToNext());
        }
    }
}
