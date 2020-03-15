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

public class Activity_1_Add extends AppCompatActivity {

    private EditText editText_nianji,editText_zhuanye,editText_num;
    private List<AddModel> addModelList;

    private String _id,_nianji,_zhuanye,_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1__add);
        findViewById(R.id.acticity_1_add_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initView();
    }

    private void initView() {
        addModelList = new ArrayList<>();
        editText_nianji = findViewById(R.id.acticity_1_add_nianji);
        editText_zhuanye = findViewById(R.id.acticity_1_add_zhuanye);
        editText_num = findViewById(R.id.acticity_1_add_num);
        Intent intent = getIntent();
        _id = intent.getStringExtra("id");
        _nianji = intent.getStringExtra("nianji");
        _zhuanye = intent.getStringExtra("zhuanye");
        _num = intent.getStringExtra("num");
        editText_nianji.setText(_nianji);
        editText_zhuanye.setText(_zhuanye);
        editText_num.setText(_num);

        findViewById(R.id.acticity_1_add_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nianji = editText_nianji.getText().toString().trim();
                String zhuanye = editText_zhuanye.getText().toString().trim();
                String num = editText_num.getText().toString().trim();
                addModelList.add(new AddModel("nianji",nianji));
                addModelList.add(new AddModel("zhuanye",zhuanye));
                addModelList.add(new AddModel("num",num));
                if (_id.isEmpty()) {
                    if (new SQLHandle(Activity_1_Add.this).addData("banji", addModelList)) {
                        Toast.makeText(Activity_1_Add.this, "添加成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(Activity_1_Add.this, "添加失败", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    if (new SQLHandle(Activity_1_Add.this).updateData("banji",_id, addModelList)) {
                        Toast.makeText(Activity_1_Add.this, "更新成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(Activity_1_Add.this, "更新失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
