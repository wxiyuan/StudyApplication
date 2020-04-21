package com.example.myapplication.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {UserEntity.class}, exportSchema = false, version = 1)
public abstract class UserDatabase extends RoomDatabase {

    public abstract UserDao userDao();
}
