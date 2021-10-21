package com.medicalshop.app.ui.stock;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.medicalshop.app.Database.Stock;
import com.medicalshop.app.Database.StockWithMedicineAndWholesalerName;
import com.medicalshop.app.R;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.List;

public class StockViewRecyclerViewAdaptor extends RecyclerView.Adapter<StockViewRecyclerViewAdaptor.ViewHolder> {
    private List<StockWithMedicineAndWholesalerName> stocks;
    public StockViewRecyclerViewAdaptor(List<StockWithMedicineAndWholesalerName> stocks)
    {
        this.stocks=stocks;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.stock_view_recyclerview_item_layot,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.setStock(stocks.get(position));
    }

    @Override
    public int getItemCount() {
        return stocks.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView stockId,sMedicineName,sMedicineBatchNo,sMfgDate,sExpDate,sMrp,sWholesalerName,stockAdded,invoiceNo;
        private StockWithMedicineAndWholesalerName stock;
        private View view;
        public ViewHolder(View view) {
            super(view);
            this.view=view;
            stockId=view.findViewById(R.id.stockViewRecyclerViewItemStockIdTextView);
            sMedicineName=view.findViewById(R.id.stockViewRecyclerViewItemMedicineNameTextView);
            sMedicineBatchNo=view.findViewById(R.id.stockViewRecyclerViewItemMedicineBatchNoTextView);
            sMfgDate=view.findViewById(R.id.stockViewRecyclerViewItemMedicineMfgDateTextView);
            sExpDate=view.findViewById(R.id.stockViewRecyclerViewItemMedicineExpDateTextView);
            sMrp=view.findViewById(R.id.stockViewRecyclerViewItemMedicineMrpTextView);
            stockAdded=view.findViewById(R.id.stockViewRecyclerViewItemStockAddedDate);
            sWholesalerName=view.findViewById(R.id.stockViewRecyclerViewItemWholesalerNameTextView);
            invoiceNo=view.findViewById(R.id.stockViewRecyclerViewItemWholesalerInvoiceNoTextView);
        }
        public void setStock(StockWithMedicineAndWholesalerName stock)
        {
            this.stock=stock;
            stockId.setText(stock.stockId+"");
            sMedicineName.setText(stock.medicineName);
            SimpleDateFormat sdf=new SimpleDateFormat(view.getResources().getString(R.string.date_format));
            sMfgDate.setText(sdf.format(stock.mfg));
            sExpDate.setText(sdf.format(stock.exp));
            sMrp.setText(stock.mrp+"");
            stockAdded.setText(sdf.format(stock.addedOn));
            sMedicineBatchNo.setText(stock.batchNo);
            sWholesalerName.setText(stock.wholesalerName);
            invoiceNo.setText(stock.billId);
            //StockViewewholesalerName
        }
    }
}
