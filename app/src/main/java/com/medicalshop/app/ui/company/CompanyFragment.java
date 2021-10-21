package com.medicalshop.app.ui.company;

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
import com.medicalshop.app.Database.Company;
import com.medicalshop.app.Database.CompanyDao;
import com.medicalshop.app.R;

import java.util.List;


public class CompanyFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerViewAdeptor recyclerViewAdeptor;
    private Handler handler;

    public CompanyFragment() {
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
        return inflater.inflate(R.layout.fragment_company, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle bundle)
    {
        Button addCompany=(Button) view.findViewById(R.id.addCompany);
        final NavController navController = Navigation.findNavController(view);
        addCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_company_to_addCompanyFragment);
            }
        });
        //set Adeptor in Recycler view
        recyclerView=view.findViewById(R.id.showAllCompanyRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        handler=new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                CompanyDao companyDao = AppDatabase.getdb(getActivity()).companyDao();
                List<Company> companys = companyDao.getAll();
                recyclerViewAdeptor=new RecyclerViewAdeptor(companys);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.setAdapter(recyclerViewAdeptor);
                    }
                });
            }
        }).start();
    }
}