package com.soldier.android_work.db.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.soldier.android_work.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author soldier
 * @Date 2020/4/11 9:26
 * @Email:583406411@qq.com
 * @Version 1.0
 * @Description:
 */
public class DBUtils {

    private MyHelper helper;

    public DBUtils(Context context) {
        helper = new MyHelper(context, "user.db", null, 1);
    }

    /**
     * 添加
     */
    public boolean insert(UserEntity userEntity) {
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", userEntity.getName());
        values.put("phone", userEntity.getPhone());
        // 如果插入失败，则会返回-1
        long l = database.insert("user", null, values);
        database.close();
        return l!= -1;
    }

    /**
     * 查询
     */
    public List<UserEntity> findById(Integer id){
        SQLiteDatabase database = helper.getReadableDatabase();
        // 表名   返回指定数据咧，null时返回全部   查询条件 条件的值   分组  HAVING子句可以让我们筛选分组后的各组数据 排序
        Cursor cursor = null;
        if (id != null) {
            cursor = database.query("user", null, "id=?", new String[]{id + ""}, null, null, null);
        } else {
            // 查询全部
            cursor = database.query("user", null, null, null, null, null, null);
        }
        List<UserEntity> userEntities = new ArrayList<>();
        if (cursor.getCount() != 0) {
            UserEntity userEntity = null;
            while (cursor.moveToNext()){
                userEntity = new UserEntity();
                userEntity.setId(cursor.getInt(cursor.getColumnIndex("id")));
                userEntity.setName(cursor.getString(cursor.getColumnIndex("name")));
                userEntity.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
                userEntities.add(userEntity);
            }
        }
        cursor.close();
        database.close();
        return userEntities;
    }
    public List<UserEntity> findByName(String name){
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.query("user", null, "name=?", new String[]{name + ""}, null, null, null);
        List<UserEntity> userEntities = new ArrayList<>();
        if (cursor.getCount() != 0) {
            UserEntity userEntity = null;
            while (cursor.moveToNext()){
                userEntity = new UserEntity();
                userEntity.setId(cursor.getInt(cursor.getColumnIndex("id")));
                userEntity.setName(cursor.getString(cursor.getColumnIndex("name")));
                userEntity.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
                userEntities.add(userEntity);
            }
        }
        cursor.close();
        database.close();
        return userEntities;
    }

    /**
     * 修改
     */
    public boolean update(UserEntity userEntity) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("phone", userEntity.getPhone());
        // 表名   改变的值    条件  条件的值
        int number = db.update("user", values, " name =?", new String[]{userEntity.getName()});
        System.out.println("修改成功？"+number);
        db.close();
        return true;
    }

    /**
     * 删除
     */
    public boolean delete(long id){
        SQLiteDatabase db = helper.getWritableDatabase();
        int number = db.delete("user", "id=?", new String[]{id+""});
        System.out.println("删除成功？"+number);
        db.close();
        return true;
    }

}