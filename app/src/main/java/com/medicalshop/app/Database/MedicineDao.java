package com.medicalshop.app.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MedicineDao {
    @Query("select * from medicines")
    List<Medicine> getAll();

    @Insert
    long  insert(Medicine medicines);

    @Delete
    void delete(Medicine medicine);

}
