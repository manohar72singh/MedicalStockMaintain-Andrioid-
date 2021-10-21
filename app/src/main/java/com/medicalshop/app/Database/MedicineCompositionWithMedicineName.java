package com.medicalshop.app.Database;

import androidx.room.DatabaseView;

@DatabaseView("select medicine_compositions.medicine_composition_id as medicineCompositionId," +
        " medicine_compositions.medicine_id as medicineId," +
        " medicine_compositions.composition_id as compositionId," +
        " medicines.medicine_name as medicineName from medicine_compositions inner join medicines on medicine_compositions.medicine_id = medicines.medicine_id")
public class MedicineCompositionWithMedicineName {
    public long medicineCompositionId;
    public long compositionId;
    public long medicineId;
    public String medicineName;
}
