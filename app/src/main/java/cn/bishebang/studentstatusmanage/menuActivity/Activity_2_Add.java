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

public class Activity_2_Add extends AppCompatActivity {

    private EditText editText_stuname,editText_ruxuenianfen,editText_shifozaiji;
    private List<AddModel> addModelList;

    private String _id,_stuname,_ruxuenianfen,_shifozaiji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2__add);
        findViewById(R.id.acticity_2_add_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initView();
    }

    private void initView() {
        addModelList = new ArrayList<>();
        editText_stuname = findViewById(R.id.acticity_2_add_stuname);
        editText_ruxuenianfen = findViewById(R.id.acticity_2_add_ruxuenianfen);
        editText_shifozaiji = findViewById(R.id.acticity_2_add_shifozaiji);
        Intent intent = getIntent();
        _id = intent.getStringExtra("id");
        _stuname = intent.getStringExtra("stuname");
        _ruxuenianfen = intent.getStringExtra("ruxuenianfen");
        _shifozaiji = intent.getStringExtra("shifozaiji");
        editText_stuname.setText(_stuname);
        editText_ruxuenianfen.setText(_ruxuenianfen);
        editText_shifozaiji.setText(_shifozaiji);

        findViewById(R.id.acticity_2_add_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stuname = editText_stuname.getText().toString().trim();
                String ruxuenianfen = editText_ruxuenianfen.getText().toString().trim();
                String shifozaiji = editText_shifozaiji.getText().toString().trim();
                addModelList.add(new AddModel("stuname",stuname));
                addModelList.add(new AddModel("ruxuenianfen",ruxuenianfen));
                addModelList.add(new AddModel("shifozaiji",shifozaiji));
                if (_id.isEmpty()) {
                    if (new SQLHandle(Activity_2_Add.this).addData("xueji", addModelList)) {
                        Toast.makeText(Activity_2_Add.this, "添加成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(Activity_2_Add.this, "添加失败", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    if (new SQLHandle(Activity_2_Add.this).updateData("xueji",_id, addModelList)) {
                        Toast.makeText(Activity_2_Add.this, "更新成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(Activity_2_Add.this, "更新失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
