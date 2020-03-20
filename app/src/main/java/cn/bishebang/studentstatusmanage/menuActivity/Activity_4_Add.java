package cn.bishebang.studentstatusmanage.menuActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import cn.bishebang.studentstatusmanage.R;
import cn.bishebang.studentstatusmanage.sqlite.AddModel;
import cn.bishebang.studentstatusmanage.sqlite.SQLHandle;

public class Activity_4_Add extends AppCompatActivity {

    private EditText editText_xuehao,editText_name,editText_dianhua;
    private List<AddModel> addModelList;

    private Spinner spinner;
    private List<String> stringList = new ArrayList<>();
    private ArrayAdapter arrayAdapter;

    private Spinner spinner2;
    private List<String> stringList2 = new ArrayList<>();
    private ArrayAdapter arrayAdapter2;

    private String sp_banji="", sp_zhuanye = "";

    private String _id,_xuehao,_name,_dianhua,_banji,_zhuanye;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4__add);
        findViewById(R.id.acticity_4_add_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initView();
    }

    private void initView() {
        addModelList = new ArrayList<>();
        editText_xuehao = findViewById(R.id.acticity_4_add_xuehao);
        editText_name = findViewById(R.id.acticity_4_add_name);
        editText_dianhua = findViewById(R.id.acticity_4_add_dianhua);

        spinner = findViewById(R.id.acticity_4_add_banji);
        arrayAdapter = new ArrayAdapter(Activity_4_Add.this,android.R.layout.simple_spinner_item,stringList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sp_banji = stringList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2 = findViewById(R.id.acticity_4_add_zhuanye);
        arrayAdapter2 = new ArrayAdapter(Activity_4_Add.this,android.R.layout.simple_spinner_item,stringList2);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        spinner2.setAdapter(arrayAdapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sp_zhuanye = stringList2.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        getData();
        if (stringList.size() == 0 || stringList2.size() == 0) {
            Toast.makeText(Activity_4_Add.this, "请去专业和班级模块添加专业和班级信息再来添加学生信息", Toast.LENGTH_SHORT).show();
        }

        Intent intent = getIntent();
        _id = intent.getStringExtra("id");
        _xuehao = intent.getStringExtra("xuehao");
        _name = intent.getStringExtra("name");
        _dianhua = intent.getStringExtra("dianhua");
        _banji = intent.getStringExtra("banji");
        _zhuanye = intent.getStringExtra("zhuanye");
        editText_xuehao.setText(_xuehao);
        editText_name.setText(_name);
        editText_dianhua.setText(_dianhua);
        setSpinnerDefaultValue(spinner, _banji);
        setSpinnerDefaultValue(spinner2, _zhuanye);

        findViewById(R.id.acticity_4_add_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String xuehao = editText_xuehao.getText().toString().trim();
                String name = editText_name.getText().toString().trim();
                String dianhua = editText_dianhua.getText().toString().trim();
                addModelList.add(new AddModel("xuehao",xuehao));
                addModelList.add(new AddModel("name",name));
                addModelList.add(new AddModel("dianhua",dianhua));
                addModelList.add(new AddModel("banji",sp_banji));
                addModelList.add(new AddModel("zhuanye",sp_zhuanye));
                if (sp_banji.isEmpty() || sp_zhuanye.isEmpty()) {
                    Toast.makeText(Activity_4_Add.this, "请去专业和班级模块添加专业和班级信息再来添加学生信息", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (_id.isEmpty()) {
                    if (new SQLHandle(Activity_4_Add.this).addData("xinxi", addModelList)) {
                        Toast.makeText(Activity_4_Add.this, "添加成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(Activity_4_Add.this, "添加失败", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    if (new SQLHandle(Activity_4_Add.this).updateData("xinxi",_id, addModelList)) {
                        Toast.makeText(Activity_4_Add.this, "更新成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(Activity_4_Add.this, "更新失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private void getData() {
        Cursor cursor = new SQLHandle(Activity_4_Add.this).queryAllData("banji");
        if (cursor.moveToFirst()) {
            do {
                stringList.add(cursor.getString(cursor.getColumnIndex("nianji")));
                arrayAdapter.notifyDataSetChanged();
            } while (cursor.moveToNext());
        }
        Cursor cursor2 = new SQLHandle(Activity_4_Add.this).queryAllData("zhuanye");
        if (cursor2.moveToFirst()) {
            do {
                stringList2.add(cursor2.getString(cursor2.getColumnIndex("name")));
                arrayAdapter2.notifyDataSetChanged();
            } while (cursor2.moveToNext());
        }
    }
    /**
     * spinner 接收默认值的Spinner
     * value 需要设置的默认值
     */
    private void setSpinnerDefaultValue(Spinner spinner, String value) {
        SpinnerAdapter apsAdapter = spinner.getAdapter();
        int size = apsAdapter.getCount();
        for (int i = 0; i < size; i++) {
            if (TextUtils.equals(value, apsAdapter.getItem(i).toString())) {
                spinner.setSelection(i,true);
                break;
            }
        }
    }
}
