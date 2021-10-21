package com.medicalshop.app.ui.wholesaler;

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

import com.medicalshop.app.Database.Wholesaler;
import com.medicalshop.app.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WholesalerViewRecyclerViewAdaptor extends RecyclerView.Adapter<WholesalerViewRecyclerViewAdaptor.ViewHolder> {
    private List<Wholesaler> wholesalers;
    public WholesalerViewRecyclerViewAdaptor(List<Wholesaler> wholesalers)
    {
        this.wholesalers=wholesalers;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.wholesaler_view_recyclerview_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.setWholesaler(wholesalers.get(position));
    }

    @Override
    public int getItemCount() {
        return wholesalers.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{
        private TextView wholesalerId,wholesalerName,wholesalerAddress,wholesalerContectNo,wholesalerDeleteButton,wholesalerEditButton;
        private Wholesaler wholesaler;
        public ViewHolder(View view)
        {
            super(view);
            wholesalerId=view.findViewById(R.id.wholesalerViewRecyclerViewItemItemIdTextView);
            wholesalerName=view.findViewById(R.id.wholesalerViewRecyclerViewItemItemNameTextView);
            wholesalerAddress=view.findViewById(R.id.wholesalerViewRecyclerViewItemItemAddressTextView);
            wholesalerContectNo=view.findViewById(R.id.wholesalerViewRecyclerViewItemItemContectNoTextView);
            wholesalerDeleteButton=view.findViewById(R.id.wholesalerViewRecyclerViewItemItemDeleteTextView);
            wholesalerEditButton=view.findViewById(R.id.wholesalerViewRecyclerViewItemItemEditTextView);

            // Event delete or Update

            // .....UPDATE....


            wholesalerEditButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(view.getContext(),"You Have Selected Wholesaler With id"+wholesaler.wholesalerId+ "To Edit",Toast.LENGTH_LONG).show();
                    Bundle bundle=new Bundle();
                    bundle.putLong("wid",wholesaler.wholesalerId);
                    NavController navController= Navigation.findNavController(view);
                    navController.navigate(R.id.action_wholesaler_to_addWholesalerFragment,bundle);
                }
            });

            //........DELETE........

            wholesalerDeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(view.getContext(),"You Have Selected company With id"+wholesaler.wholesalerId + "To Delete",Toast.LENGTH_SHORT).show();
                }
            });

        }
        public void setWholesaler(Wholesaler wholesaler)
        {
            this.wholesaler=wholesaler;
            wholesalerId.setText(wholesaler.wholesalerId+"");
            wholesalerName.setText(wholesaler.wholesalerName);
            wholesalerAddress.setText(wholesaler.address);
            wholesalerContectNo.setText(wholesaler.contactNo);
        }
    }
}
