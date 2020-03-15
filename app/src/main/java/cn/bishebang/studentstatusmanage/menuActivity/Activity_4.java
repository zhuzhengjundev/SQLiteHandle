package cn.bishebang.studentstatusmanage.menuActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import cn.bishebang.studentstatusmanage.R;
import cn.bishebang.studentstatusmanage.adapter.XinxiItem;
import cn.bishebang.studentstatusmanage.adapter.XinxiItemAdapter;
import cn.bishebang.studentstatusmanage.sqlite.SQLHandle;

public class Activity_4 extends AppCompatActivity {

    private ListView listView;
    private XinxiItem xinxiItem;
    private List<XinxiItem> xinxiItemList;
    private XinxiItemAdapter xinxiItemAdapter;

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);
        findViewById(R.id.acticity_4_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.acticity_4_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_4.this, Activity_4_Add.class);
                intent.putExtra("id", "");
                intent.putExtra("xuehao","");
                intent.putExtra("name", "");
                intent.putExtra("dianhua","");
                intent.putExtra("banji", "");
                startActivity(intent);
            }
        });
        editText = findViewById(R.id.acticity_4_EditText);
        listView = findViewById(R.id.acticity_4_ListView);
        xinxiItemList = new ArrayList<>();
        xinxiItemAdapter = new XinxiItemAdapter(Activity_4.this, R.layout.adapter_xinxi, xinxiItemList);
        listView.setAdapter(xinxiItemAdapter);

        findViewById(R.id.acticity_4_Btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sousuo = editText.getText().toString().trim();
                if (sousuo.isEmpty()) {
                    xinxiItemList.clear();
                    Cursor cursor = new SQLHandle(Activity_4.this).queryAllData("xinxi");
                    if (cursor.moveToFirst()) {
                        do {
                            xinxiItem = new XinxiItem(cursor.getString(cursor.getColumnIndex("id")),
                                    cursor.getString(cursor.getColumnIndex("xuehao")),
                                    cursor.getString(cursor.getColumnIndex("name")),
                                    cursor.getString(cursor.getColumnIndex("dianhua")),
                                    cursor.getString(cursor.getColumnIndex("banji")));
                            xinxiItemList.add(xinxiItem);
                            xinxiItemAdapter.notifyDataSetChanged();
                        } while (cursor.moveToNext());
                    }
                }else{
                    xinxiItemList.clear();
                    Cursor cursor = new SQLHandle(Activity_4.this).queryOneData("xinxi", "xuehao", sousuo);
                    if (cursor.moveToFirst()) {
                        do {
                            xinxiItem = new XinxiItem(cursor.getString(cursor.getColumnIndex("id")),
                                    cursor.getString(cursor.getColumnIndex("xuehao")),
                                    cursor.getString(cursor.getColumnIndex("name")),
                                    cursor.getString(cursor.getColumnIndex("dianhua")),
                                    cursor.getString(cursor.getColumnIndex("banji")));
                            xinxiItemList.add(xinxiItem);
                        } while (cursor.moveToNext());
                    }
                    xinxiItemAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        xinxiItemList.clear();
        Cursor cursor = new SQLHandle(Activity_4.this).queryAllData("xinxi");
        if (cursor.moveToFirst()) {
            do {
                xinxiItem = new XinxiItem(cursor.getString(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("xuehao")),
                        cursor.getString(cursor.getColumnIndex("name")),
                        cursor.getString(cursor.getColumnIndex("dianhua")),
                        cursor.getString(cursor.getColumnIndex("banji")));
                xinxiItemList.add(xinxiItem);
                xinxiItemAdapter.notifyDataSetChanged();
            } while (cursor.moveToNext());
        }
    }
}
