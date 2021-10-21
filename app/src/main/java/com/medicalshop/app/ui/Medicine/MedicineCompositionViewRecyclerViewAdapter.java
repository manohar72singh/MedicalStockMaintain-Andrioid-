package com.medicalshop.app.ui.Medicine;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.medicalshop.app.Database.Composition;
import com.medicalshop.app.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MedicineCompositionViewRecyclerViewAdapter extends RecyclerView.Adapter<MedicineCompositionViewRecyclerViewAdapter.ViewHolder> {
    private List<Composition> compositions;

    public MedicineCompositionViewRecyclerViewAdapter(List<Composition> compositions) {
        this.compositions = compositions;
    }

    public Composition getItem(int index)
    {
        return compositions.get(index);
    }
    public boolean addNewComposition(Composition composition) {
        if (composition != null) {
            //search the incoming composition is alredy avilable or not
            boolean found = false;
            for (Composition comp : compositions)
                if (comp.compositionId == composition.compositionId) {
                    found = true;
                    break;
                }
            if (!found) {
                compositions.add(composition);
                return true;
            } else
                return false;

        }
        return false;
    }
    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.medicine_compositionview_recyclerview_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.setMedicineComposition(compositions.get(position));
    }

    @Override
    public int getItemCount() {
        return compositions.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {
        private Composition composition;
        private TextView medicineCompositionId,medicineCompositionName,removeComposition;

        public ViewHolder(View view)
        {
            super(view);
            medicineCompositionId=view.findViewById(R.id.medicineCompositionviewRecyclerViewItemIdtextView);
            medicineCompositionName=view.findViewById(R.id.medicineCompositionViewRecyclerViewItemCompositionNameTextView);
            removeComposition=view.findViewById(R.id.medicineCompositionViewRecyclerViewItemDeleteTextView);

            removeComposition.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Remove current composition from compositions list
                    //Find the position of seleted composition in list
                    int i =0;
                    for (Composition composition1 : compositions)
                    {
                        if (composition1.compositionId==composition.compositionId)
                        break;
                        i++;
                    }
                    compositions.remove(i);
                    //Notify Adaptor for change s in data set
                    MedicineCompositionViewRecyclerViewAdapter.this.notifyDataSetChanged();

                }
            });
        }

        public void setMedicineComposition(Composition composition)
        {
            this.composition=composition;
            medicineCompositionId.setText(composition.compositionId+"");
            medicineCompositionName.setText(composition.compositionName);


        }

    }
}
