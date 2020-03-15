package cn.bishebang.studentstatusmanage;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import cn.bishebang.studentstatusmanage.menuActivity.Activity_1;
import cn.bishebang.studentstatusmanage.menuActivity.Activity_2;
import cn.bishebang.studentstatusmanage.menuActivity.Activity_3;
import cn.bishebang.studentstatusmanage.menuActivity.Activity_4;
import cn.bishebang.studentstatusmanage.menuActivity.Activity_5;
import cn.bishebang.studentstatusmanage.menuActivity.Activity_6;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.mainActivity_banjiguanli_1).setOnClickListener(this);
        findViewById(R.id.mainActivity_xuejiguanli_2).setOnClickListener(this);
        findViewById(R.id.mainActivity_zhuanyeguanli_3).setOnClickListener(this);
        findViewById(R.id.mainActivity_xinxiguanli_4).setOnClickListener(this);
        findViewById(R.id.mainActivity_kechengguanli_5).setOnClickListener(this);
        findViewById(R.id.mainActivity_luntanguanli_6).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mainActivity_banjiguanli_1:
                startActivity(new Intent(MainActivity.this, Activity_1.class));
                break;
            case R.id.mainActivity_xuejiguanli_2:
                startActivity(new Intent(MainActivity.this, Activity_2.class));
                break;
            case R.id.mainActivity_zhuanyeguanli_3:
                startActivity(new Intent(MainActivity.this, Activity_3.class));
                break;
            case R.id.mainActivity_xinxiguanli_4:
                startActivity(new Intent(MainActivity.this, Activity_4.class));
                break;
            case R.id.mainActivity_kechengguanli_5:
                startActivity(new Intent(MainActivity.this, Activity_5.class));
                break;
            case R.id.mainActivity_luntanguanli_6:
                startActivity(new Intent(MainActivity.this, Activity_6.class));
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("提示");
        dialog.setMessage("确定退出？");
        dialog.setCancelable(false);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        dialog.show();
        return super.onKeyDown(keyCode, event);
    }
}
