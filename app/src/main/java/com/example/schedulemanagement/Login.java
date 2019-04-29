package com.example.schedulemanagement;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText userName;//用户名编辑
    private EditText userPwd;//密码编辑
    private Button login_button;//登陆按钮
    private Button register_button;//注册按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //初始化界面
        userName = findViewById(R.id.user);
        userPwd = findViewById(R.id.password);
        login_button = findViewById(R.id.login_button);
        register_button = findViewById(R.id.register_button);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*登陆按钮要执行的操作填写到以下代码中*/
                //获取EditText控件数据
                String user = userName.getText().toString().trim();
                String pwd = userPwd.getText().toString().trim();

                //判断输入是否为空
                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pwd)) {
                    Toast.makeText(Login.this, "账号或密码不能为空", Toast.LENGTH_LONG).show();
                } else {//验证账号
                    DataManager userDataManager = new DataManager(Login.this);
                    userDataManager.openDataBase();
                    boolean result = userDataManager.findUserByNameAndPwd(user, pwd);
                    if (!result) {
                        new AlertDialog.Builder(Login.this)
                                .setTitle("系统提示")
                                .setMessage("是否要注册新账户？")
                                .setPositiveButton("确定",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent intent = new Intent(Login.this, Reset.class);
                                                startActivity(intent);
                                            }
                                        })
                                .setNegativeButton("返回",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                finish();
                                            }
                                        }).show();
                        Toast.makeText(Login.this, "用户名或密码错误", Toast.LENGTH_LONG).show();
                        userDataManager.closeDataBase();
                    } else {
                        Intent intent = new Intent(Login.this,Calender.class);
                        startActivity(intent);
                        userDataManager.closeDataBase();
                    }
                }
            }
        });


        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*注册按钮要进行的操作代码添加到以下代码块中*/
                Intent intent = new Intent(Login.this, Reset.class);
                startActivity(intent);
            }
        });
    }
}
