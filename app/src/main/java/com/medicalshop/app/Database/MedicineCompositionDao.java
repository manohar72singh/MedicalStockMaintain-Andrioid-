package com.medicalshop.app.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MedicineCompositionDao {
    @Query("select * from medicine_compositions")
    List<MedicineComposition> getAll();

    @Insert
    void inesrtAll(MedicineComposition ...medicineCompositions);

    @Delete
    void delete(MedicineComposition medicineComposition);
}
