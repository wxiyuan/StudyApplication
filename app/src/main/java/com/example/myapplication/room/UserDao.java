package com.example.myapplication.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDao {

    @Query("SELECT * FROM users")
    UserEntity[] loadAllUsers();

    @Query("SELECT * FROM users WHERE id == :id")
    UserEntity loadUserById(String id);

    @Query("SELECT * FROM users WHERE address == :address")
    UserEntity[] loadUsersByAddress(String address);

    @Insert
    long[] insertUsers(UserEntity... users);

    @Update
    int updateUsers(UserEntity... users);

    @Delete
    int deleteUsers(UserEntity... users);
}
