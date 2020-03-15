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
import cn.bishebang.studentstatusmanage.adapter.BanjiItem;
import cn.bishebang.studentstatusmanage.adapter.BanjiItemAdapter;
import cn.bishebang.studentstatusmanage.sqlite.SQLHandle;

public class Activity_1 extends AppCompatActivity {

    private ListView listView;
    private BanjiItem banjiItem;
    private List<BanjiItem> banjiItemList;
    private BanjiItemAdapter banjiItemAdapter;

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        findViewById(R.id.acticity_1_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.acticity_1_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_1.this, Activity_1_Add.class);
                intent.putExtra("id", "");
                intent.putExtra("nianji", "");
                intent.putExtra("zhuanye", "");
                intent.putExtra("num", "");
                startActivity(intent);
            }
        });
        editText = findViewById(R.id.acticity_1_EditText);
        listView = findViewById(R.id.acticity_1_ListView);
        banjiItemList = new ArrayList<>();
        banjiItemAdapter = new BanjiItemAdapter(Activity_1.this, R.layout.adapter_banji, banjiItemList);
        listView.setAdapter(banjiItemAdapter);

        findViewById(R.id.acticity_1_Btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sousuo = editText.getText().toString().trim();
                if (sousuo.isEmpty()) {
                    banjiItemList.clear();
                    Cursor cursor = new SQLHandle(Activity_1.this).queryAllData("banji");
                    if (cursor.moveToFirst()) {
                        do {
                            banjiItem = new BanjiItem(cursor.getString(cursor.getColumnIndex("id")),
                                    cursor.getString(cursor.getColumnIndex("nianji")),
                                    cursor.getString(cursor.getColumnIndex("zhuanye")),
                                    cursor.getString(cursor.getColumnIndex("num")));
                            banjiItemList.add(banjiItem);
                            banjiItemAdapter.notifyDataSetChanged();
                        } while (cursor.moveToNext());
                    }
                }else{
                    banjiItemList.clear();
                    Cursor cursor = new SQLHandle(Activity_1.this).queryOneData("banji","zhuanye",sousuo);
                    if (cursor.moveToFirst()) {
                        do {
                            banjiItem = new BanjiItem(cursor.getString(cursor.getColumnIndex("id")),
                                    cursor.getString(cursor.getColumnIndex("nianji")),
                                    cursor.getString(cursor.getColumnIndex("zhuanye")),
                                    cursor.getString(cursor.getColumnIndex("num")));
                            banjiItemList.add(banjiItem);
                        } while (cursor.moveToNext());
                    }
                    banjiItemAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        banjiItemList.clear();
        Cursor cursor = new SQLHandle(Activity_1.this).queryAllData("banji");
        if (cursor.moveToFirst()) {
            do {
                banjiItem = new BanjiItem(cursor.getString(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("nianji")),
                        cursor.getString(cursor.getColumnIndex("zhuanye")),
                        cursor.getString(cursor.getColumnIndex("num")));
                banjiItemList.add(banjiItem);
                banjiItemAdapter.notifyDataSetChanged();
            } while (cursor.moveToNext());
        }
    }
}
