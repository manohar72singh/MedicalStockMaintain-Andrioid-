package com.medicalshop.app.ui.Medicine;

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
import com.medicalshop.app.Database.Medicine;
import com.medicalshop.app.Database.MedicineCompositionWithNameDao;
import com.medicalshop.app.Database.MedicineDao;
import com.medicalshop.app.Database.MedicineWithCompanyName;
import com.medicalshop.app.Database.MedicineWithCompanyNameDao;
import com.medicalshop.app.Database.MedicineWithCompositions;
import com.medicalshop.app.R;

import java.util.List;

public class MedicineFragment extends Fragment {

    private RecyclerView recyclerView;
    private MedicineViewRecyclerViewAdapter medicineViewRecyclerViewAdapter;
    private Handler handler;

    public MedicineFragment() {
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
        return inflater.inflate(R.layout.fragment_medicine, container, false);
    }
    @Override
    public void onViewCreated(View view,Bundle bundle)
    {
        Button addMedicine=(Button)view.findViewById(R.id.addMedicine);
        final NavController navController= Navigation.findNavController(view);
        addMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_medicine_to_addMedicineFragment);
            }
        });

        recyclerView=view.findViewById(R.id.medicineView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        handler=new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                MedicineWithCompanyNameDao medicineWithCompanyNameDao= AppDatabase.getdb(getActivity()).medicineWithCompanyNameDao();
                List<MedicineWithCompanyName> medicines=medicineWithCompanyNameDao.getAllMedicines();
                MedicineCompositionWithNameDao medicineCompositionWithNameDao=AppDatabase.getdb(getActivity()).medicineCompositionWithNameDao();
                MedicineWithCompositions medicineWithCompositions [] =new MedicineWithCompositions[medicines.size()];
                for (int i=0;i<medicineWithCompositions.length;i++)
                {
                    medicineWithCompositions[i]=new MedicineWithCompositions();
                    medicineWithCompositions[i].medicine=medicines.get(i);
                    medicineWithCompositions[i].compositions=medicineCompositionWithNameDao.getAllCompositionOf(medicineWithCompositions[i].medicine.medicineId);
                }

                medicineViewRecyclerViewAdapter=new MedicineViewRecyclerViewAdapter(medicineWithCompositions);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.setAdapter(medicineViewRecyclerViewAdapter);
                    }
                });
            }
        }).start();
    }
}