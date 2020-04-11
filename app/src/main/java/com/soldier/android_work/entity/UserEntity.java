package com.soldier.android_work.entity;

/**
 * @Author soldier
 * @Date 2020/4/11 9:27
 * @Email:583406411@qq.com
 * @Version 1.0
 * @Description:用户实体
 */
public class UserEntity {

    private int id;
    private String name;
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}