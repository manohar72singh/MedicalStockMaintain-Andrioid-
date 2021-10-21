package com.medicalshop.app.Database;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MedicineWithCompanyNameDao {
    @Query("select * from MedicineWithCompanyName order by medicineName,companyName")
    public List<MedicineWithCompanyName> getAllMedicines();
}
