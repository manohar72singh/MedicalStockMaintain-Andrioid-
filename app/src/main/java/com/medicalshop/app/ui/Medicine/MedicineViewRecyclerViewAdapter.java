package com.medicalshop.app.ui.Medicine;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.medicalshop.app.Database.Medicine;
import com.medicalshop.app.Database.MedicineWithCompanyName;
import com.medicalshop.app.Database.MedicineWithCompositions;
import com.medicalshop.app.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

 public class MedicineViewRecyclerViewAdapter extends RecyclerView.Adapter<MedicineViewRecyclerViewAdapter.ViewHolder> {
    private MedicineWithCompositions[] medicines;


    public MedicineViewRecyclerViewAdapter(MedicineWithCompositions[] medicines)
    {
        this.medicines=medicines;
    }
    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.medicine_viewrecyclerview_item_layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.setMedicine(medicines[position]);
    }

    @Override
    public int getItemCount() {
        return medicines.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView medicineName,medicineCompanyName,medicineCompositionName,medicineType;
        private MedicineWithCompositions medicine;
        public ViewHolder( View view) {
            super(view);
            medicineName=view.findViewById(R.id.medicineViewRecyclerviewItemMedicineNameTextView);
            medicineCompanyName=view.findViewById(R.id.medicineViewRecyclerViewItemItemCompanyNameTextView);
            medicineType=view.findViewById(R.id.medicineViewRecyclerViewItemItemTypeNameTextView);
            medicineCompositionName=view.findViewById(R.id.medicineViewRecyclerViewItemCompositionTextView);
        }
        public void setMedicine(MedicineWithCompositions medicine)
        {
            this.medicine=medicine;
            medicineName.setText(medicine.medicine.medicineName.toUpperCase());
            medicineCompanyName.setText("By "+medicine.medicine.companyName.toUpperCase());
            medicineCompositionName.setText(medicine.getCompositionsName().toUpperCase());
            medicineType.setText(medicine.medicine.type+"");
        }
    }
}
