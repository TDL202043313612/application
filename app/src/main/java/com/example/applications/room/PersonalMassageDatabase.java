package com.example.applications.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {PersonalMassageEntity.class}, version = 1)
public abstract class PersonalMassageDatabase extends RoomDatabase {
    public abstract PersonalMassageDao roomTestDao();

    // 数据库迁移策略
    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {

        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // 在这里实现从版本1到版本2的迁移逻辑
            // 比如添加新表、修改表结构等
        }
    };

    // 构建数据库实例时添加迁移策略
    public static PersonalMassageDatabase getInstance(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), PersonalMassageDatabase.class, "Pursuit")
                .addMigrations(MIGRATION_1_2)
                .build();
    }
}