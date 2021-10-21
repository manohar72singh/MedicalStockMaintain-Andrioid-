package com.medicalshop.app.ui.wholesaler;

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
import com.medicalshop.app.Database.Wholesaler;
import com.medicalshop.app.Database.WholesalerDao;
import com.medicalshop.app.R;


import java.util.List;


public class WholesalerFragment extends Fragment {
    private RecyclerView recyclerView;
    private WholesalerViewRecyclerViewAdaptor recyclerViewAdaptor;
    private Handler handler;


    public WholesalerFragment() {
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
        return inflater.inflate(R.layout.fragment_wholesaler, container, false);
    }
    @Override
    public void onViewCreated(View view,Bundle bundle)
    {
        Button addWholesaler=(Button)view.findViewById(R.id.saveWholesaler);
        final NavController navController= Navigation.findNavController(view);
        addWholesaler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_wholesaler_to_addWholesalerFragment);
            }
        });
        recyclerView=view.findViewById(R.id.showAllWholesaler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        handler= new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                WholesalerDao wholesalerDao= AppDatabase.getdb(getActivity()).wholesalerDao();
                List<Wholesaler> wholesalers =wholesalerDao.getAll();
                recyclerViewAdaptor=new WholesalerViewRecyclerViewAdaptor(wholesalers);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.setAdapter(recyclerViewAdaptor);
                    }
                });
            }
        }).start();
    }
}