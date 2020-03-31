package com.soldier.android_work;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * @Author soldier
 * @Date 2020/3/30 21:23
 * @Email:583406411@qq.com
 * @Version 1.0
 * @Description:登录成功显示
 */
public class HomeActivity extends ActionBarActivity {

    private Button btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("个人中心");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // 获取页面按钮对象
        btn_logout = (Button)findViewById(R.id.button_logout);
        // 为按钮添加单击时间监听
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //消息提示
                Toast.makeText(HomeActivity.this, "退出成功", Toast.LENGTH_SHORT).show();
                // 页面跳转
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}