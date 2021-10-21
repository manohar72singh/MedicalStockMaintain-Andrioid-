package com.medicalshop.app.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DemoDao {
    @Query("select * from demo")
    List<Demo> getAll();
    @Insert
    void insertAll(Demo ... demos);

}
