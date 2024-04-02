package com.example.applications.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "personal_massage")
public class PersonalMassageEntity {
    @PrimaryKey
    @NonNull
    public String token;

    /*用户住址*/
    @ColumnInfo
    public String user_address;

    /*图像地址*/
    @ColumnInfo
    public String avatar_path;

    /*用户语言*/
    public String user_language;

    /*用户手机号*/
    public String user_phone;

    /*用户主题*/
    public String user_skin;

    /*用户留言*/
    public String user_leave_message;

//    @Override
//    public String toString() {
//        return "RoomTestEntity{" +
//                "id=" + id + '\'' +
//                ",key=" + key + '\'' +
//                ", name='" + name + '\'' +
//                ", sex=" + sex +
//                // 可以添加其他字段...
//                '}';
//    }
}




