package com.medicalshop.app.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "medicine_compositions")
public class MedicineComposition {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "medicine_composition_id")
    public long medicineCompositionId;

    //foreginekey
    @ColumnInfo(name = "medicine_id")
    public long medicineId;

    @ColumnInfo(name = "composition_id")
    public long compositionId;

}
