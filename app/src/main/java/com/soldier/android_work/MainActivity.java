package com.soldier.android_work;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.soldier.android_work.util.SaveInfo;

import java.util.Map;

/**
 * 登录界面
 */
public class MainActivity extends AppCompatActivity {

    private EditText et_username;
    private EditText et_password;
    private EditText et_password2;
    private Button btn_login;
    private Button btn_register;
    private CheckBox checkbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Author:soldier");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 读取本地是否保存了账号密码
        Map<String, String> userInfo = SaveInfo.getSaveInformation(this);
        if (userInfo != null) {
            et_username.setText(userInfo.get("username"));
            et_password.setText(userInfo.get("password"));
        }

        // 获取页面组件对象
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        et_password2 = (EditText) findViewById(R.id.reg_password2);
        checkbox = (CheckBox) findViewById(R.id.checkBox);
        btn_login = (Button) findViewById(R.id.button_login);
        btn_register = (Button) findViewById(R.id.button_register);
        // 为按钮添加单击时间监听
        btn_login.setOnClickListener(new MyButton());
        btn_register.setOnClickListener(new MyButton());
    }

    public class MyButton implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String username = et_username.getText().toString().trim();
            String password = et_password.getText().toString().trim();
            switch (view.getId()) {
                //当点击登录按钮时
                case R.id.button_login:
                    if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                        Toast.makeText(MainActivity.this, "密码或账号不能为空", Toast.LENGTH_SHORT).show();
                    } else {
                        if (checkbox.isChecked()) {
                            //保存密码的操作
                        }
                        //消息提示
                        Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        // 页面跳转
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                    break;
                //当点击注册按钮事件时
                case R.id.button_register:
                    Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }
}
