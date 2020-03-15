package cn.bishebang.studentstatusmanage.menuActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import cn.bishebang.studentstatusmanage.R;
import cn.bishebang.studentstatusmanage.sqlite.AddModel;
import cn.bishebang.studentstatusmanage.sqlite.SQLHandle;

public class Activity_4_Add extends AppCompatActivity {

    private EditText editText_xuehao,editText_name,editText_dianhua,editText_banji;
    private List<AddModel> addModelList;

    private String _id,_xuehao,_name,_dianhua,_banji;

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
        editText_banji = findViewById(R.id.acticity_4_add_banji);
        Intent intent = getIntent();
        _id = intent.getStringExtra("id");
        _xuehao = intent.getStringExtra("xuehao");
        _name = intent.getStringExtra("name");
        _dianhua = intent.getStringExtra("dianhua");
        _banji = intent.getStringExtra("banji");
        editText_xuehao.setText(_xuehao);
        editText_name.setText(_name);
        editText_dianhua.setText(_dianhua);
        editText_banji.setText(_banji);

        findViewById(R.id.acticity_4_add_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String xuehao = editText_xuehao.getText().toString().trim();
                String name = editText_name.getText().toString().trim();
                String dianhua = editText_dianhua.getText().toString().trim();
                String banji = editText_banji.getText().toString().trim();
                addModelList.add(new AddModel("xuehao",xuehao));
                addModelList.add(new AddModel("name",name));
                addModelList.add(new AddModel("dianhua",dianhua));
                addModelList.add(new AddModel("banji",banji));
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
}
