package com.medicalshop.app.ui.company;

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

import com.medicalshop.app.Database.Company;
import com.medicalshop.app.Database.Composition;
import com.medicalshop.app.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RecyclerViewAdeptor extends RecyclerView.Adapter<RecyclerViewAdeptor.ViewHolder> {
    private List<Company> companys;
    public RecyclerViewAdeptor(List<Company> companys)
    {
        this.companys =companys;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        //View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.company_view_recyclerview_item_layout,parent,false);
        //return new ViewHolder(view);
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.company_view_recyclerview_item_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

        holder.setCompany(companys.get(position));
    }

    @Override
    public int getItemCount() {
        return companys.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView companyId,companyName,companyContectNo,companyAddress,companyDelete,companyEdit;
        private Company company;
        public ViewHolder(View view)
        {
            super(view);
            companyId=view.findViewById(R.id.companyViewRecyclerViewItemItemIdTextView);
            companyName=view.findViewById(R.id.companyViewRecyclerViewItemItemNameTextView);
            companyAddress=view.findViewById(R.id.companyViewRecyclerViewItemItemAddressTextView);
            companyContectNo=view.findViewById(R.id.companyViewRecyclerViewItemItemcontectNoTextView);
            companyDelete=view.findViewById(R.id.companyViewRecyclerViewItemItemDeleteTextView);
            companyEdit=view.findViewById(R.id.editCompany);

            //EVENT
            companyEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(view.getContext(),"You Have Selected company With id"+company.companyId+ "To Edit",Toast.LENGTH_LONG).show();

                    Bundle bundle = new Bundle();
                    bundle.putLong("cid",company.companyId);
                    NavController navController = Navigation.findNavController(view);
                    navController.navigate(R.id.action_company_to_addCompanyFragment,bundle);



                }
            });



            companyDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(view.getContext(),"You Have Selected company With id"+company.companyId+ "To Delete",Toast.LENGTH_LONG).show();

                }
            });
        }

        public void setCompany(Company company)
        {
            this.company=company;
            companyId.setText(company.companyId+"");
            companyName.setText(company.companyName);
            companyAddress.setText(company.address);
            companyContectNo.setText(company.contactNo);
        }


    }
}
