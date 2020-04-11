package com.soldier.android_work.db.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @Author soldier
 * @Date 2020/4/11 9:29
 * @Email:583406411@qq.com
 * @Version 1.0
 * @Description:SQLite的创建
 */
public class MyHelper extends SQLiteOpenHelper {

    /**
     * 构造方法
     * @param context   上下文对象
     * @param name      数据库名称
     * @param factory   游标工厂
     * @param version   数据库版本
     */
    public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "user.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table user(" +
                "id integer primary key autoincrement," +
                "name varchar(20)," +
                "phone varchar(20)" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}