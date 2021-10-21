package com.medicalshop.app.Database;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StockWithWholesalerNameDao {
    @Query("select * from StockWithWholesalerName")
    public List<StockWithWholesalerName> getAll();
}
