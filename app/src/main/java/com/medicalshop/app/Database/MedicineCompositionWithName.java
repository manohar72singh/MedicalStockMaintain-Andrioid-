package com.medicalshop.app.Database;

import androidx.room.DatabaseView;

@DatabaseView("select MedicineCompositionWithMedicineName.medicineCompositionId as medicineCompositionId," +
        " MedicineCompositionWithMedicineName.compositionId as compositionId," +
        " MedicineCompositionWithMedicineName.medicineId as medicineId," +
        " MedicineCompositionWithMedicineName.medicineName as medicineName," +
        " compositions.composition_name as compositionName from MedicineCompositionWithMedicineName inner join compositions on MedicineCompositionWithMedicineName.compositionId = compositions.composition_id")



public class MedicineCompositionWithName {
    public long medicineCompositionId;
    public long compositionId;
    public String compositionName;
    public long medicineId;
    public String medicineName;


}
