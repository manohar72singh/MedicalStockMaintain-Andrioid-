package com.medicalshop.app.ui.composition;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.medicalshop.app.Database.AppDatabase;
import com.medicalshop.app.Database.Composition;
import com.medicalshop.app.Database.CompositionDao;
import com.medicalshop.app.R;

import java.util.List;


public class ViewComposition extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerViewAdaptor recyclerViewAdaptor;
    private Handler handler;


    public ViewComposition() {
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
        return inflater.inflate(R.layout.fragment_view_composition, container, false);

    }
    @Override
    public void onViewCreated(View view,Bundle bundle)
    {
        Button addComposition =(Button) view.findViewById(R.id.addCompositionButton);
        final NavController navController = Navigation .findNavController(view);
        addComposition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_viewComposition_to_addCompositionFragment);
            }
        });
        //set Adeptor in Recycler view
        recyclerView=view.findViewById(R.id.showAllCompositionRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                CompositionDao compositionDao = AppDatabase.getdb(getActivity()).compositionDao();
                List<Composition> compositionTypes = compositionDao.getAll();
                recyclerViewAdaptor=new RecyclerViewAdaptor(compositionTypes);
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