package com.medicalshop.app.ui.wholesaler;

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
import com.medicalshop.app.Database.Wholesaler;
import com.medicalshop.app.Database.WholesalerDao;
import com.medicalshop.app.R;


public class AddWholesalerFragment extends Fragment {
EditText wholesalerName,wholesalerAddress,wholesalerContectNo;
Button save;
Wholesaler wholesaler;
Handler handler;

    public AddWholesalerFragment() {
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
        return inflater.inflate(R.layout.fragment_add_wholesaler, container, false);
    }
    @Override
    public void onViewCreated(View view,Bundle bundle)
    {
        wholesalerName=view.findViewById(R.id.wholesalerName);
        wholesalerAddress=view.findViewById(R.id.wholesalerAddress);
        wholesalerContectNo=view.findViewById(R.id.wholesalerContactNo);
        save=view.findViewById(R.id.saveWholesalerButton);
        handler=new Handler();
        bundle=getArguments();
        if (bundle!=null)
        {
            long wid=bundle.getLong("wid");
            //Load interface
            if (wid!=0)
            {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        wholesaler=AppDatabase.getdb(getActivity()).wholesalerDao().getWholesaler(wid);
                        //Update Wholesaler
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                wholesalerName.setText(wholesaler.wholesalerName);
                                wholesalerAddress.setText(wholesaler.address);
                                wholesalerContectNo.setText(wholesaler.contactNo);
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
                    // code to current Wholesaler
                    wholesaler.wholesalerName = wholesalerName.getText().toString();
                    wholesaler.address = wholesalerAddress.getText().toString();
                    wholesaler.contactNo = wholesalerContectNo.getText().toString();

                    //Update in Table in new thrade
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            AppDatabase.getdb(getActivity()).wholesalerDao().update(wholesaler);
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    NavController navController = Navigation.findNavController(view);
                                    navController.navigate(R.id.action_addWholesalerFragment_to_wholesaler);
                                    Toast.makeText(view.getContext(), "Wholesaler Update succesfully", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).start();
                } else {


                    wholesaler = new Wholesaler();
                    if (wholesalerName.getText().toString().trim().equals("")) {
                        //Toast.makeText(getActivity(),"Enter Wholesaler Name First",Toast.LENGTH_LONG).show();
                        wholesalerName.setError("Enter Wholesaler Name First");
                        wholesalerName.requestFocus();
                        save.setEnabled(true);
                        return;
                    }
                    if (wholesalerName.getText().toString().trim().length() < 4) {
                        //Toast.makeText(getActivity(),"Wholesaler Name Must be mor than 4 character",Toast.LENGTH_LONG).show();
                        wholesalerName.setError("Wholesaler Name Must be mor than 4 character");
                        wholesalerName.requestFocus();
                        save.setEnabled(true);
                        return;
                    }
                    if (wholesalerAddress.getText().toString().trim().equals("")) {
                        //Toast.makeText(getActivity(),"Enter Wholesaler Address First",Toast.LENGTH_LONG).show();
                        wholesalerAddress.setError("Enter Wholesaler Address First");
                        wholesalerAddress.requestFocus();
                        save.setEnabled(true);
                        return;
                    }
                    if (wholesalerAddress.getText().toString().trim().length() < 4) {
                        //Toast.makeText(getActivity(),"Wholesaler Address Must be mor than 4 character",Toast.LENGTH_LONG).show();
                        wholesalerAddress.setError("Wholesaler Address Must be mor than 4 character");
                        wholesalerAddress.requestFocus();
                        save.setEnabled(true);
                        return;
                    }
                    if (wholesalerContectNo.getText().toString().trim().equals("")) {
                        //Toast.makeText(getActivity(),"Enter Contect No First",Toast.LENGTH_LONG).show();
                        wholesalerContectNo.setError("Enter Contect No First");
                        wholesalerContectNo.requestFocus();
                        save.setEnabled(true);
                        return;
                    }
                    if (wholesalerContectNo.getText().toString().trim().length() < 10) {
                        //Toast.makeText(getActivity(),"Wholesaler Contect No Must be mor than 10 character",Toast.LENGTH_LONG).show();
                        wholesalerContectNo.setError("Wholesaler Contect No Must be mor than 10 character");
                        wholesalerContectNo.requestFocus();
                        save.setEnabled(true);
                        return;
                    }
                    wholesaler.wholesalerName = wholesalerName.getText().toString();
                    wholesaler.address = wholesalerAddress.getText().toString();
                    wholesaler.contactNo = wholesalerContectNo.getText().toString();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                WholesalerDao wholesalerDao = AppDatabase.getdb(getContext()).wholesalerDao();
                                wholesalerDao.insertAll(wholesaler);
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getActivity(), "Wholesaler saved.", Toast.LENGTH_LONG).show();
                                        NavController navController = Navigation.findNavController(view);
                                        navController.navigate(R.id.action_addWholesalerFragment_to_wholesaler);
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