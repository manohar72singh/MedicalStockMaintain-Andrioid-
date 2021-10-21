package com.medicalshop.app.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StockDao {
    @Query("select * from stocks")
    List<Stock> getAll();

    @Insert
    void insertAll(Stock ...stocks);

    @Delete
    void delete(Stock stock);
}
