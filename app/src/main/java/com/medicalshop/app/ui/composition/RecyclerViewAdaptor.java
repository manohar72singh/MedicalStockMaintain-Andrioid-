package com.medicalshop.app.ui.composition;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.medicalshop.app.Database.Composition;
import com.medicalshop.app.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class RecyclerViewAdaptor extends RecyclerView.Adapter<RecyclerViewAdaptor.ViewHolder> {
    private List<Composition> compositions;
    public RecyclerViewAdaptor(List<Composition> compositions){
        this.compositions = compositions;
    }
    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.composition_view_recyclerview_item_layot, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.setComposition(compositions.get(position));

    }

    @Override
    public int getItemCount() {
        return compositions.size();
    }
//s

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView  compositionId,compositionName,compositionDelete,compositionEdit;
       private Composition compositionType;
        public ViewHolder(View view){
            super(view);
            compositionId=view.findViewById(R.id.compositionViewRecyclerViewItemItemIdTextView);
            compositionName=view.findViewById(R.id.compositionViewRecyclerViewItemItemNameTextView);
            compositionDelete=view.findViewById(R.id.compositionViewRecyclerViewItemItemDeleteTextView);
            compositionEdit= view.findViewById(R.id.compositionViewRecyclerViewItemItemEditTextView);

            // write event handler for any componenet


            compositionEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(view.getContext(),"You Have Selected CompositionId With id"+compositionType.compositionId+ "To Edit",Toast.LENGTH_LONG).show();
                    Bundle bundle=new Bundle();
                    bundle.putLong("cid",compositionType.compositionId);
                    NavController navController = Navigation.findNavController(view);
                    navController.navigate(R.id.action_viewComposition_to_addCompositionFragment,bundle);
                }
            });

            compositionDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(view.getContext()," Delete Item Having Id:"+compositionId.getText()+", Name:"+compositionName.getText(),Toast.LENGTH_LONG).show();


                }
            });
        }




        public  void setComposition(Composition compositionType)
        {
            this.compositionType=compositionType;
            compositionId.setText(compositionType.compositionId+"");
            compositionName.setText(compositionType.compositionName);
        }

    }
}
