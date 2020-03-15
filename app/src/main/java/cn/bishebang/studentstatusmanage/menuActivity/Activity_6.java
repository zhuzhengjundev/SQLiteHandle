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
import cn.bishebang.studentstatusmanage.adapter.LuntanItem;
import cn.bishebang.studentstatusmanage.adapter.LuntanItemAdapter;
import cn.bishebang.studentstatusmanage.sqlite.SQLHandle;

public class Activity_6 extends AppCompatActivity {

    private ListView listView;
    private LuntanItem luntanItem;
    private List<LuntanItem> luntanItemList;
    private LuntanItemAdapter luntanItemAdapter;

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_6);
        findViewById(R.id.acticity_6_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.acticity_6_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_6.this, Activity_6_Add.class);
                intent.putExtra("id", "");
                intent.putExtra("title", "");
                intent.putExtra("content", "");
                startActivity(intent);
            }
        });
        editText = findViewById(R.id.acticity_6_EditText);
        listView = findViewById(R.id.acticity_6_ListView);
        luntanItemList = new ArrayList<>();
        luntanItemAdapter = new LuntanItemAdapter(Activity_6.this, R.layout.adapter_luntan, luntanItemList);
        listView.setAdapter(luntanItemAdapter);

        findViewById(R.id.acticity_6_Btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sousuo = editText.getText().toString().trim();
                if (sousuo.isEmpty()) {
                    luntanItemList.clear();
                    Cursor cursor = new SQLHandle(Activity_6.this).queryAllData("luntan");
                    if (cursor.moveToFirst()) {
                        do {
                            luntanItem = new LuntanItem(cursor.getString(cursor.getColumnIndex("id")),
                                    cursor.getString(cursor.getColumnIndex("title")),
                                    cursor.getString(cursor.getColumnIndex("content")));
                            luntanItemList.add(luntanItem);
                            luntanItemAdapter.notifyDataSetChanged();
                        } while (cursor.moveToNext());
                    }
                }else{
                    luntanItemList.clear();
                    Cursor cursor = new SQLHandle(Activity_6.this).queryOneData("luntan","title",sousuo);
                    if (cursor.moveToFirst()) {
                        do {
                            luntanItem = new LuntanItem(cursor.getString(cursor.getColumnIndex("id")),
                                    cursor.getString(cursor.getColumnIndex("title")),
                                    cursor.getString(cursor.getColumnIndex("content")));
                            luntanItemList.add(luntanItem);
                        } while (cursor.moveToNext());
                    }
                    luntanItemAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        luntanItemList.clear();
        Cursor cursor = new SQLHandle(Activity_6.this).queryAllData("luntan");
        if (cursor.moveToFirst()) {
            do {
                luntanItem = new LuntanItem(cursor.getString(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("title")),
                        cursor.getString(cursor.getColumnIndex("content")));
                luntanItemList.add(luntanItem);
                luntanItemAdapter.notifyDataSetChanged();
            } while (cursor.moveToNext());
        }
    }
}
