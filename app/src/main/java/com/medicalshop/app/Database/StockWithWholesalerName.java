package com.medicalshop.app.Database;

import androidx.room.DatabaseView;

import java.util.Date;

@DatabaseView("select stocks.stock_id as stockId,"+
        " stocks.mfg as mfg,"+
        " stocks.exp as exp,"+
        " stocks.addedOn as addedOn,"+
        " stocks.mrp as mrp,"+
        " stocks.qty as qty,"+
        " stocks.batchNo as batchNo,"+
        " stocks.billid as billId,"+
        " stocks.wholesaler_id as wholesalerId,"+
        " stocks.medicine_id as medicineId,"+
        " wholesalers.wholesaler_name as wholesalerName from stocks inner join wholesalers on stocks.wholesaler_id = wholesalers.wholesaler_id")

public class StockWithWholesalerName {
    public long stockId;
    public long medicineId;
    public Date mfg;
    public Date exp;
    public Date addedOn;
    public int mrp;
    public int qty;
    public String batchNo;
    public String billId;
    public long wholesalerId;
    public String  wholesalerName;
}
