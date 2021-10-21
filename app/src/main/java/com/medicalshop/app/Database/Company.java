package com.medicalshop.app.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "companies")
public class Company {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "company_id")
    public Long companyId;

    @ColumnInfo(name = "company_name")
    public String companyName;

    @ColumnInfo(name = "address")
    public String address;

    @ColumnInfo(name = "contactNo")
    public String contactNo;

    @ColumnInfo(name="contactNo2")
    public String contactNo2;

    @Override
    public String toString()
    {
        return companyId+" "+companyName;
    }
}
