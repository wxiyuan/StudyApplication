package com.example.myapplication.room;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "users",
        indices = {@Index(value = {"first_name", "last_name"}), @Index("address")})
public class UserEntity {

    @PrimaryKey
    @NonNull
    public String id = "";

    @ColumnInfo(name = "first_name")
    public String firstName;
    @ColumnInfo(name = "last_name")
    public String lastName;
    public String address;

    @Ignore
    private Bitmap picture;
}
