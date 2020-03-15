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
import cn.bishebang.studentstatusmanage.adapter.ZhuanyeItem;
import cn.bishebang.studentstatusmanage.adapter.ZhuanyeItemAdapter;
import cn.bishebang.studentstatusmanage.sqlite.SQLHandle;

public class Activity_3 extends AppCompatActivity {

    private ListView listView;
    private ZhuanyeItem zhuanyeItem;
    private List<ZhuanyeItem> zhuanyeItemList;
    private ZhuanyeItemAdapter zhuanyeItemAdapter;

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        findViewById(R.id.acticity_3_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.acticity_3_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_3.this, Activity_3_Add.class);
                intent.putExtra("id", "");
                intent.putExtra("name", "");
                startActivity(intent);
            }
        });
        editText = findViewById(R.id.acticity_3_EditText);
        listView = findViewById(R.id.acticity_3_ListView);
        zhuanyeItemList = new ArrayList<>();
        zhuanyeItemAdapter = new ZhuanyeItemAdapter(Activity_3.this, R.layout.adapter_zhuanye, zhuanyeItemList);
        listView.setAdapter(zhuanyeItemAdapter);

        findViewById(R.id.acticity_3_Btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sousuo = editText.getText().toString().trim();
                if (sousuo.isEmpty()) {
                    zhuanyeItemList.clear();
                    Cursor cursor = new SQLHandle(Activity_3.this).queryAllData("zhuanye");
                    if (cursor.moveToFirst()) {
                        do {
                            zhuanyeItem = new ZhuanyeItem(cursor.getString(cursor.getColumnIndex("id")),
                                    cursor.getString(cursor.getColumnIndex("name")));
                            zhuanyeItemList.add(zhuanyeItem);
                            zhuanyeItemAdapter.notifyDataSetChanged();
                        } while (cursor.moveToNext());
                    }
                }else{
                    zhuanyeItemList.clear();
                    Cursor cursor = new SQLHandle(Activity_3.this).queryOneData("zhuanye","name",sousuo);
                    if (cursor.moveToFirst()) {
                        do {
                            zhuanyeItem = new ZhuanyeItem(cursor.getString(cursor.getColumnIndex("id")),
                                    cursor.getString(cursor.getColumnIndex("name")));
                            zhuanyeItemList.add(zhuanyeItem);
                        } while (cursor.moveToNext());
                    }
                    zhuanyeItemAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        zhuanyeItemList.clear();
        Cursor cursor = new SQLHandle(Activity_3.this).queryAllData("zhuanye");
        if (cursor.moveToFirst()) {
            do {
                zhuanyeItem = new ZhuanyeItem(cursor.getString(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("name")));
                zhuanyeItemList.add(zhuanyeItem);
                zhuanyeItemAdapter.notifyDataSetChanged();
            } while (cursor.moveToNext());
        }
    }
}
