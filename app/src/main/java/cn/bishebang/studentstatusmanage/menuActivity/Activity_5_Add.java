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

public class Activity_5_Add extends AppCompatActivity {

    private EditText editText_name;
    private List<AddModel> addModelList;

    private String _id,_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5__add);
        findViewById(R.id.acticity_5_add_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initView();
    }

    private void initView() {
        addModelList = new ArrayList<>();
        editText_name = findViewById(R.id.acticity_5_add_name);
        Intent intent = getIntent();
        _id = intent.getStringExtra("id");
        _name = intent.getStringExtra("name");
        editText_name.setText(_name);

        findViewById(R.id.acticity_5_add_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText_name.getText().toString().trim();
                addModelList.add(new AddModel("name",name));
                if (_id.isEmpty()) {
                    if (new SQLHandle(Activity_5_Add.this).addData("kecheng", addModelList)) {
                        Toast.makeText(Activity_5_Add.this, "添加成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(Activity_5_Add.this, "添加失败", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    if (new SQLHandle(Activity_5_Add.this).updateData("kecheng",_id, addModelList)) {
                        Toast.makeText(Activity_5_Add.this, "更新成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(Activity_5_Add.this, "更新失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
