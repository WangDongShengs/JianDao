package com.wds.jiandao;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.wds.base.BaseActivity;

public class MainActivity extends BaseActivity {

    private SharedPreferences name;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        super.initData();
        name = getSharedPreferences("name", Context.MODE_PRIVATE);
        int anInt = name.getInt("1", 0);
        if (anInt==1){
           initCountDownTimer();
        }else {
            dialogs();
        }
    }
    private void initCountDownTimer() {
        new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        }.start();
    }

    private void dialogs() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                //      .setIcon(icon)//设置标题的图片
                // .setTitle(title)//设置对话框的标题
                .setMessage("好好学习，天天向上")//设置对话框的内容
                //设置对话框的按钮
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        finish();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        YiDao();
                    }
                }).create();
        dialog.show();
    }
    private void YiDao() {
        SharedPreferences.Editor edit = name.edit();
        edit.putInt("1",1);
        edit.commit();
        Intent intent = new Intent(MainActivity.this, PagerActivity.class);
        startActivity(intent);
        finish();

    }

}
