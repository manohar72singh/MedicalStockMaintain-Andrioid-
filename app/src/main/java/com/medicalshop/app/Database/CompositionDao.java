package com.medicalshop.app.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CompositionDao {
    @Query("select * from Compositions order by composition_name")
    List<Composition> getAll();


    @Query("select * from Compositions where composition_name like :data order by composition_name")
    List<Composition> getFiltered(String data);

    @Query("select * from Compositions where composition_id=:cid")
    Composition getcompositionType(long cid);

    @Insert
    void insertAll(Composition...compositions);

    @Delete
    void delete(Composition composition);

    @Update
    void update (Composition composition);

}
