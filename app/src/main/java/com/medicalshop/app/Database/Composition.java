package com.medicalshop.app.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "compositions")
public class Composition {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "composition_id")
    public Long compositionId;

    @ColumnInfo(name = "composition_name")
    public String compositionName;

    @Override
    public String toString()
    {
        if (compositionId!=null)
            return  compositionId+" "+compositionName;
        else
            return compositionName;
    }


}
