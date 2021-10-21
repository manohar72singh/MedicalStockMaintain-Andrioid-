package com.medicalshop.app.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CompanyDao {
    @Query("select * from companies")
    List<Company> getAll();
    @Query("select * from companies where company_id =:cid")
    Company getCompany(long cid);
    @Insert
    void insertAll(Company ...companies);
    @Delete
    void delete(Company company);
    @Update
    void update(Company company);
}
