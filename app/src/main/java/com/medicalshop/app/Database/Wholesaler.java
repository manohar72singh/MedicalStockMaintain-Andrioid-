package com.medicalshop.app.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "wholesalers")
public class Wholesaler {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "wholesaler_id")
    public Long wholesalerId;

    @ColumnInfo(name = "wholesaler_name")
    public String wholesalerName;

    @ColumnInfo(name = "contactNo")
    public String contactNo;

    @ColumnInfo(name = "address")
    public String address;

    @Override
    public String toString()
    {
        return wholesalerId+" - "+wholesalerName;
    }
}
