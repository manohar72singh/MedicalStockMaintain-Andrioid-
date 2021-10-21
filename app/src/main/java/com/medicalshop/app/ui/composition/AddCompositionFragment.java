package com.medicalshop.app.ui.composition;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.medicalshop.app.Database.AppDatabase;
import com.medicalshop.app.Database.Composition;
import com.medicalshop.app.Database.CompositionDao;
import com.medicalshop.app.R;


public class AddCompositionFragment extends Fragment {

    EditText compositionName;
    Button save;
    Composition composition;
    Handler handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_composition, container, false);
    }
    @Override
    public void onViewCreated(View view,Bundle bundle)
    {
        save = view.findViewById(R.id.save);
        compositionName =view.findViewById(R.id.compositionName);
        handler =new Handler();
        bundle=getArguments();
        if (bundle != null)
        {
            long cid=bundle.getLong("cid");
            // load the composition of cid and update interface
            if (cid!=0)
            {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        composition=AppDatabase.getdb(getActivity()).compositionDao().getcompositionType(cid);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                compositionName.setText(composition.compositionName);
                                save.setText("update");
                            }
                        });
                    }
                }).start();
            }
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save.setEnabled(false);
                if (save.getText().toString().equalsIgnoreCase("update")) {
                    // code to current composition
                    composition.compositionName = compositionName.getText().toString();
                    //Update in table
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            AppDatabase.getdb(getActivity()).compositionDao().update(composition);
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    NavController navController = Navigation.findNavController(view);
                                    navController.navigate(R.id.action_addCompositionFragment_to_viewComposition);
                                    Toast.makeText(view.getContext(), " Composition Update succesfully ", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }).start();
                } else {

                    composition = new Composition();
                    if (compositionName.getText().toString().trim().equals("")) {
                        //Toast.makeText(getActivity(),"Enter composition First",Toast.LENGTH_LONG).show();
                        compositionName.setError("Enter composition Name First");
                        compositionName.requestFocus();
                        save.setEnabled(true);
                        return;
                    }
                    if (compositionName.getText().toString().trim().length() < 4) {
                        //Toast.makeText(getActivity(),"Composition Name must be more than 4 character ",Toast.LENGTH_LONG).show();
                        compositionName.setError("Composition Name must be more than 4 character");
                        compositionName.requestFocus();
                        save.setEnabled(true);
                        return;
                    }
                    composition.compositionName = compositionName.getText().toString();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                CompositionDao compositionTypeDao = AppDatabase.getdb(getContext()).compositionDao();
                                compositionTypeDao.insertAll(composition);
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getActivity(), "Composition saved.", Toast.LENGTH_LONG).show();
                                        NavController navController = Navigation.findNavController(view);
                                        navController.navigate(R.id.action_addCompositionFragment_to_viewComposition);
                                    }
                                });
                            } catch (Exception e) {
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                                        save.setEnabled(true);
                                    }
                                });
                            }

                        }
                    }).start();
                }
            }
        });
    }
}