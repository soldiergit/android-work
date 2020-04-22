package com.soldier.android_work;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.soldier.android_work.db.SQLite.DBUtils;
import com.soldier.android_work.entity.UserEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 通讯录界面
 */
public class AddressBookActivity extends AppCompatActivity {

    private EditText et_phone_name;
    private EditText et_phone_number;
    private Button button_add;
    private Button button_query;
    private Button button_update;
    private Button button_delete;
    private ListView userListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("通讯录");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_book);

        // 获取页面组件对象
        et_phone_name = (EditText) findViewById(R.id.et_phone_name);
        et_phone_number = (EditText) findViewById(R.id.et_phone_number);
        button_add = (Button) findViewById(R.id.button_add);
        button_query = (Button) findViewById(R.id.button_query);
        button_update = (Button) findViewById(R.id.button_update);
        button_delete = (Button) findViewById(R.id.button_delete);
        userListView = (ListView) findViewById(R.id.userListView);

        // 为按钮添加单击时间监听
        button_add.setOnClickListener(new MyButton());
        button_query.setOnClickListener(new MyButton());
        button_update.setOnClickListener(new MyButton());
        button_delete.setOnClickListener(new MyButton());
    }

    public class MyButton implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String phoneName = et_phone_name.getText().toString().trim();
            String phoneNumber = et_phone_number.getText().toString().trim();
            DBUtils dbUtils = new DBUtils(AddressBookActivity.this);
            switch (view.getId()) {
                //当点击添加按钮时
                case R.id.button_add:
                    if (TextUtils.isEmpty(phoneName) || TextUtils.isEmpty(phoneNumber)) {
                        Toast.makeText(AddressBookActivity.this, "姓名或电话不能为空", Toast.LENGTH_SHORT).show();
                    } else {
                        UserEntity userEntity = new UserEntity();
                        userEntity.setName(phoneName);
                        userEntity.setPhone(phoneNumber);
                        if (dbUtils.insert(userEntity)) {
                            //消息提示
                            Toast.makeText(AddressBookActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                        } else {
                            //消息提示
                            Toast.makeText(AddressBookActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                //当点击查询按钮时
                case R.id.button_query:
                    List<UserEntity> userEntities = new ArrayList<>();
                    if (TextUtils.isEmpty(phoneName)) {
                        userEntities = dbUtils.findById(null);
                    } else {
                        userEntities = dbUtils.findByName(phoneName);
                    }
                    // 需要 用到适配器,适配器的作用是实现数据的绑定，把数据绑定到条目的显示控件上
                    List<HashMap<String, Object>> data = new ArrayList<HashMap<String,Object>>();

                    for(UserEntity userEntity : userEntities){
                        HashMap<String, Object> item = new HashMap<String, Object>();
                        //存放数据的key自定义.名字随便取
                        item.put("id", userEntity.getId());
                        item.put("name", userEntity.getName());
                        item.put("phone", userEntity.getPhone());
                        data.add(item);
                    }
                    //第三个参数指定数据绑定在哪个条目界面上
                    SimpleAdapter adapter = new SimpleAdapter(AddressBookActivity.this, data, R.layout.user_item,
                            new String[]{"id", "name", "phone"}, new int[]{R.id.id, R.id.name, R.id.phone});
                    // 显示数据
                    userListView.setAdapter(adapter);
                    break;
                //当点击修改按钮时
                case R.id.button_update:
                    if (TextUtils.isEmpty(phoneName) || TextUtils.isEmpty(phoneNumber)) {
                        Toast.makeText(AddressBookActivity.this, "姓名或电话不能为空", Toast.LENGTH_SHORT).show();
                    } else {
                        UserEntity userEntity = new UserEntity();
                        userEntity.setName(phoneName);
                        userEntity.setPhone(phoneNumber);
                        if (dbUtils.update(userEntity)) {
                            //消息提示
                            Toast.makeText(AddressBookActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                        } else {
                            //消息提示
                            Toast.makeText(AddressBookActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                //当点击删除按钮时
                case R.id.button_delete:
                    if (TextUtils.isEmpty(phoneName)) {
                        Toast.makeText(AddressBookActivity.this, "姓名不能为空", Toast.LENGTH_SHORT).show();
                    } else {
                        UserEntity userEntity = new UserEntity();
                        userEntity.setName(phoneName);
                        userEntity.setPhone(phoneNumber);
                        if (dbUtils.delete(0)) {
                            //消息提示
                            Toast.makeText(AddressBookActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                        } else {
                            //消息提示
                            Toast.makeText(AddressBookActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
            }
        }
    }
}