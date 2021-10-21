package com.medicalshop.app.ui.stock;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.medicalshop.app.Database.AppDatabase;
import com.medicalshop.app.Database.Stock;
import com.medicalshop.app.Database.StockDao;
import com.medicalshop.app.Database.StockWithMedicineAndWholesalerName;
import com.medicalshop.app.Database.StockWithMedicineAndWholesalerNameDao;
import com.medicalshop.app.R;

import java.util.List;


public class StockFragment extends Fragment {
    private RecyclerView recyclerView;
    private StockViewRecyclerViewAdaptor stockViewRecyclerViewAdaptor;
    private Handler handler;


    public StockFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,

                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stock, container, false);
    }
    @Override
    public void onViewCreated(View view,Bundle bundle)
    {
        Button addStock=(Button)view.findViewById(R.id.saveStockMedicine);
        final NavController navController=Navigation.findNavController(view);
        addStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_stock_to_addMedicineStock);
            }
        });

        recyclerView=view.findViewById(R.id.showAllStockMedicineViewrecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        handler =new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                StockWithMedicineAndWholesalerNameDao stockWithMedicineAndWholesalerNameDao = AppDatabase.getdb(getContext()).stockWithMedicineAndWholesalerNameDao();
                List<StockWithMedicineAndWholesalerName> stocks=stockWithMedicineAndWholesalerNameDao.getAll();
                stockViewRecyclerViewAdaptor=new StockViewRecyclerViewAdaptor(stocks);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.setAdapter(stockViewRecyclerViewAdaptor);
                    }
                });
            }
        }).start();
    }
}