package com.medicalshop.app.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WholesalerDao {

    @Query("select * from wholesalers")
    List<Wholesaler> getAll();

    @Query("select * from wholesalers where wholesaler_id=:wid")
    Wholesaler getWholesaler(long wid);

    @Insert
    void insertAll(Wholesaler ...wholesalers);

    @Delete
    void delete(Wholesaler wholesaler);

    @Update
    void update(Wholesaler wholesaler);
}
