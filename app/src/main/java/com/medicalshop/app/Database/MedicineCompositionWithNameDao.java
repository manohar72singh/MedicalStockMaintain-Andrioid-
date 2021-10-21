package com.medicalshop.app.Database;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MedicineCompositionWithNameDao {
    @Query("select * from MedicineCompositionWithName")
    public List<MedicineCompositionWithName> getAll();

    @Query("select * from MedicineCompositionWithName where medicineId=:medicineId")
    public List<MedicineCompositionWithName> getAllCompositionOf(long medicineId);


}
