package com.medicalshop.app.Database;

import java.util.List;

public class  MedicineWithCompositions {
   public MedicineWithCompanyName medicine;
    public List<MedicineCompositionWithName> compositions;

    public String getCompositionsName()
    {
        String sCompositions="";
        for (MedicineCompositionWithName m:compositions)
        {
            sCompositions+=m.compositionName+", ";
        }
        sCompositions=sCompositions.substring(0,sCompositions.length()-2);
        return sCompositions;
    }

}

