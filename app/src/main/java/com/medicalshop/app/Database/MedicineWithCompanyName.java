package com.medicalshop.app.Database;

import androidx.room.DatabaseView;

@DatabaseView("select medicines.medicine_id as medicineId," +
        "medicines.medicine_name as medicineName," +
        "medicines.nrx as nrx, " +
        "medicines.sh1 as sh1, " +
        "medicines.type as type, " +
        "medicines.medicine_company_id as medicine_company_id, " +
        "companies.company_name as companyName " +
        "from medicines inner join  companies on medicines.medicine_company_id = companies.company_id")



public class MedicineWithCompanyName {
   public long medicineId,medicine_company_id;
   public String medicineName,companyName;
   public boolean nrx,sh1;
   public int type;


}
