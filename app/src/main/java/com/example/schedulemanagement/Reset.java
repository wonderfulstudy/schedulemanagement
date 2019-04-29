package com.example.schedulemanagement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Reset extends AppCompatActivity {

    private EditText username;  //用户名编辑
    private EditText password1; //密码编辑
    private EditText password2; //密码确认
    private Button reset_button;//重置密码按钮
    private Button cancel_button;//取消按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //界面初始化
        setContentView(R.layout.activity_reset);

        username=findViewById(R.id.register_user);
        password1=findViewById(R.id.register_pwd);
        password2=findViewById(R.id.register_rpwd);
        reset_button=findViewById(R.id.register_button);
        cancel_button=findViewById(R.id.cancel_button);


        //注册按钮单击事件
        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //验证两次输入的密码是否相同
                String name=username.getText().toString().trim();
                String pwd1=password1.getText().toString().trim();
                String pwd2=password2.getText().toString().trim();

                if (pwd1.equals(pwd2)){
                    UserData userData = new UserData(name,pwd1,0);
                    DataManager userDataManager = new DataManager(Reset.this);
                    userDataManager.openDataBase();
                    userDataManager.insertUserData(userData);
                    userDataManager.closeDataBase();
                    Toast.makeText(Reset.this,"注册成功",Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(Reset.this,"输入密码不相同，请重新输入",Toast.LENGTH_LONG).show();
                    onCreate(null);
                }
            }
        });

        //取消按钮单击事件
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
