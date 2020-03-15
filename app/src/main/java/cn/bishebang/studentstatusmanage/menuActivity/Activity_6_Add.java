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

public class Activity_6_Add extends AppCompatActivity {

    private EditText editText_title,editText_content;
    private List<AddModel> addModelList;

    private String _id,_title,_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_6__add);
        findViewById(R.id.acticity_6_add_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initView();
    }

    private void initView() {
        addModelList = new ArrayList<>();
        editText_title = findViewById(R.id.acticity_6_add_title);
        editText_content = findViewById(R.id.acticity_6_add_content);
        Intent intent = getIntent();
        _id = intent.getStringExtra("id");
        _title = intent.getStringExtra("title");
        _content = intent.getStringExtra("content");
        editText_title.setText(_title);
        editText_content.setText(_content);

        findViewById(R.id.acticity_6_add_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editText_title.getText().toString().trim();
                String content = editText_content.getText().toString().trim();
                addModelList.add(new AddModel("title",title));
                addModelList.add(new AddModel("content",content));
                if (_id.isEmpty()) {
                    if (new SQLHandle(Activity_6_Add.this).addData("luntan", addModelList)) {
                        Toast.makeText(Activity_6_Add.this, "添加成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(Activity_6_Add.this, "添加失败", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    if (new SQLHandle(Activity_6_Add.this).updateData("luntan",_id, addModelList)) {
                        Toast.makeText(Activity_6_Add.this, "更新成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(Activity_6_Add.this, "更新失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
