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
import cn.bishebang.studentstatusmanage.adapter.XuejiItem;
import cn.bishebang.studentstatusmanage.adapter.XuejiItemAdapter;
import cn.bishebang.studentstatusmanage.sqlite.SQLHandle;

public class Activity_2 extends AppCompatActivity {

    private ListView listView;
    private XuejiItem xuejiItem;
    private List<XuejiItem> xuejiItemList;
    private XuejiItemAdapter xuejiItemAdapter;

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        findViewById(R.id.acticity_2_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.acticity_2_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_2.this, Activity_2_Add.class);
                intent.putExtra("id", "");
                intent.putExtra("stuname", "");
                intent.putExtra("ruxuenianfen", "");
                intent.putExtra("shifozaiji", "");
                startActivity(intent);
            }
        });
        editText = findViewById(R.id.acticity_2_EditText);
        listView = findViewById(R.id.acticity_2_ListView);
        xuejiItemList = new ArrayList<>();
        xuejiItemAdapter = new XuejiItemAdapter(Activity_2.this, R.layout.adapter_xueji, xuejiItemList);
        listView.setAdapter(xuejiItemAdapter);

        findViewById(R.id.acticity_2_Btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sousuo = editText.getText().toString().trim();
                if (sousuo.isEmpty()) {
                    xuejiItemList.clear();
                    Cursor cursor = new SQLHandle(Activity_2.this).queryAllData("xueji");
                    if (cursor.moveToFirst()) {
                        do {
                            xuejiItem = new XuejiItem(cursor.getString(cursor.getColumnIndex("id")),
                                    cursor.getString(cursor.getColumnIndex("stuname")),
                                    cursor.getString(cursor.getColumnIndex("ruxuenianfen")),
                                    cursor.getString(cursor.getColumnIndex("shifozaiji")));
                            xuejiItemList.add(xuejiItem);
                            xuejiItemAdapter.notifyDataSetChanged();
                        } while (cursor.moveToNext());
                    }
                }else{
                    xuejiItemList.clear();
                    Cursor cursor = new SQLHandle(Activity_2.this).queryOneData("xueji","stuname",sousuo);
                    if (cursor.moveToFirst()) {
                        do {
                            xuejiItem = new XuejiItem(cursor.getString(cursor.getColumnIndex("id")),
                                    cursor.getString(cursor.getColumnIndex("stuname")),
                                    cursor.getString(cursor.getColumnIndex("ruxuenianfen")),
                                    cursor.getString(cursor.getColumnIndex("shifozaiji")));
                            xuejiItemList.add(xuejiItem);
                        } while (cursor.moveToNext());
                    }
                    xuejiItemAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        xuejiItemList.clear();
        Cursor cursor = new SQLHandle(Activity_2.this).queryAllData("xueji");
        if (cursor.moveToFirst()) {
            do {
                xuejiItem = new XuejiItem(cursor.getString(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("stuname")),
                        cursor.getString(cursor.getColumnIndex("ruxuenianfen")),
                        cursor.getString(cursor.getColumnIndex("shifozaiji")));
                xuejiItemList.add(xuejiItem);
                xuejiItemAdapter.notifyDataSetChanged();
            } while (cursor.moveToNext());
        }
    }
}
