package com.medicalshop.app.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "stocks")
public class Stock {
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "stock_id")
    public Long stockId;

    //Foreginekey
    @ColumnInfo(name = "medicine_id")
    public long medicineId ;

    @ColumnInfo(name = "mfg")
    public Date mfg;

    @ColumnInfo(name = "exp")
    public Date exp;

    @ColumnInfo(name = "mrp")
    public int mrp;

    @ColumnInfo(name = "qty")
    public int qty;

    @ColumnInfo(name = "batchNo")
    public String batchNo;

    @ColumnInfo(name = "sold")
    public int sold;

    //foreginekey
    @ColumnInfo(name = "wholesaler_id")
    public long wholesalerid;

    @ColumnInfo(name = "addedOn")
    public Date addedOn;

    @ColumnInfo(name = "billId")
    public String billId;

}
