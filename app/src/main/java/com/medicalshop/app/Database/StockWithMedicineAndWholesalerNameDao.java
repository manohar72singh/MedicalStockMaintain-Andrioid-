package com.medicalshop.app.Database;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StockWithMedicineAndWholesalerNameDao {
    @Query("select * from StockWithMedicineAndWholesalerName")
    public List<StockWithMedicineAndWholesalerName> getAll();
}
