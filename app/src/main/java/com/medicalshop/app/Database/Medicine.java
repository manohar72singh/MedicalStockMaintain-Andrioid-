package com.medicalshop.app.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "medicines")
public class Medicine {
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "medicine_id")
    public long medicineId;

    @ColumnInfo(name = "medicine_name")
    public String medicineName;

    //FOREGINKEY
    @ColumnInfo(name = "medicine_company_id")
    public long medicinecompanyid;

    @ColumnInfo(name = "nrx")
    public boolean nrx;

    @ColumnInfo(name = "sh1")
    public boolean sh1;

    @ColumnInfo(name = "type")
    public int type;


    @Override
    public String toString()
    {   
        return medicineId+" - "+medicineName.toUpperCase();
    }



}
