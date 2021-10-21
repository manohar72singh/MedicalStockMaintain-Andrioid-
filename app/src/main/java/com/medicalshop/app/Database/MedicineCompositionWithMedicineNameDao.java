package com.medicalshop.app.Database;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MedicineCompositionWithMedicineNameDao {
    @Query("select * from MedicineCompositionWithMedicineName")
    public List<MedicineCompositionWithMedicineName> getAll();


}
