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
import cn.bishebang.studentstatusmanage.adapter.KechengItem;
import cn.bishebang.studentstatusmanage.adapter.KechengItemAdapter;
import cn.bishebang.studentstatusmanage.sqlite.SQLHandle;

public class Activity_5 extends AppCompatActivity {

    private ListView listView;
    private KechengItem kechengItem;
    private List<KechengItem> kechengItemList;
    private KechengItemAdapter kechengItemAdapter;

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);
        findViewById(R.id.acticity_5_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.acticity_5_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_5.this, Activity_5_Add.class);
                intent.putExtra("id", "");
                intent.putExtra("name", "");
                startActivity(intent);
            }
        });
        editText = findViewById(R.id.acticity_5_EditText);
        listView = findViewById(R.id.acticity_5_ListView);
        kechengItemList = new ArrayList<>();
        kechengItemAdapter = new KechengItemAdapter(Activity_5.this, R.layout.adapter_kecheng, kechengItemList);
        listView.setAdapter(kechengItemAdapter);

        findViewById(R.id.acticity_5_Btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sousuo = editText.getText().toString().trim();
                if (sousuo.isEmpty()) {
                    kechengItemList.clear();
                    Cursor cursor = new SQLHandle(Activity_5.this).queryAllData("kecheng");
                    if (cursor.moveToFirst()) {
                        do {
                            kechengItem = new KechengItem(cursor.getString(cursor.getColumnIndex("id")),
                                    cursor.getString(cursor.getColumnIndex("name")));
                            kechengItemList.add(kechengItem);
                            kechengItemAdapter.notifyDataSetChanged();
                        } while (cursor.moveToNext());
                    }
                }else{
                    kechengItemList.clear();
                    Cursor cursor = new SQLHandle(Activity_5.this).queryOneData("kecheng","name",sousuo);
                    if (cursor.moveToFirst()) {
                        do {
                            kechengItem = new KechengItem(cursor.getString(cursor.getColumnIndex("id")),
                                    cursor.getString(cursor.getColumnIndex("name")));
                            kechengItemList.add(kechengItem);
                        } while (cursor.moveToNext());
                    }
                    kechengItemAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        kechengItemList.clear();
        Cursor cursor = new SQLHandle(Activity_5.this).queryAllData("kecheng");
        if (cursor.moveToFirst()) {
            do {
                kechengItem = new KechengItem(cursor.getString(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("name")));
                kechengItemList.add(kechengItem);
                kechengItemAdapter.notifyDataSetChanged();
            } while (cursor.moveToNext());
        }
    }
}
