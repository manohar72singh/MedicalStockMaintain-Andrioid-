package com.medicalshop.app.Database;

import androidx.room.DatabaseView;

import java.util.Date;

@DatabaseView("select stockId,medicineId,medicines.medicine_name as medicineName,mfg,exp,mrp,addedOn,qty,batchNo,billId,wholesalerId,wholesalerName" +
        " from StockWithWholesalerName inner join medicines on medicineId= medicines.medicine_id")
public class StockWithMedicineAndWholesalerName {
    public long stockId;
    public long medicineId;
    public String medicineName;
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
