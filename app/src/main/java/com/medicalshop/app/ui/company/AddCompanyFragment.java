package com.medicalshop.app.ui.company;

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
import com.medicalshop.app.Database.Company;
import com.medicalshop.app.Database.CompanyDao;
import com.medicalshop.app.R;


public class AddCompanyFragment extends Fragment {
    EditText companyName,address,contactNo;
    Button save;
    Company company;
    Handler handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_company, container, false);
    }
    @Override
    public void onViewCreated(View view,Bundle bundle)
    {
        companyName = view.findViewById(R.id.companyName);
        address = view.findViewById(R.id.address);
        contactNo = view.findViewById(R.id.contactNo);
        save=view.findViewById(R.id.saveCompany);
        handler=new Handler();
        bundle = getArguments();
        if (bundle != null)
        {
            long cid=bundle.getLong("cid");
            // Load The Company of cid and update on user interface
            if (cid!= 0)
            {
                // Because will not
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        company=AppDatabase.getdb(getActivity()).companyDao().getCompany(cid);
                        // update company data to  ui thrade
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                companyName.setText(company.companyName);
                                contactNo.setText(company.contactNo);
                                address.setText(company.address);
                                save.setText("Update");
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
                    // code to update current Company
                    company.companyName=companyName.getText().toString();
                    company.contactNo=contactNo.getText().toString();
                    company.address=address.getText().toString();

                    // update in Table in new threade

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            AppDatabase.getdb(getActivity()).companyDao().update(company);
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    NavController navController = Navigation.findNavController(view);
                                    navController.navigate(R.id.action_addCompanyFragment_to_company2);
                                    Toast.makeText(view.getContext()," Company Update succesfully ",Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }).start();
                } else {
                    // Code to add new Company to table

                    company = new Company();
                    if (companyName.getText().toString().trim().equals("")) {
                        //Toast.makeText(getActivity(),"Enter Company Name First",Toast.LENGTH_LONG).show();
                        companyName.setError("Enter Company Name First");
                        companyName.requestFocus();
                        save.setEnabled(true);
                        return;
                    }
                    if (companyName.getText().toString().trim().length() < 4) {
                        //Toast.makeText(getActivity(),"Company name must be more than 5 character",Toast.LENGTH_LONG).show();
                        companyName.setError("Company name must be more than 4 character");
                        companyName.requestFocus();
                        save.setEnabled(true);
                        return;
                    }
                    if (address.getText().toString().trim().equals("")) {
                        //Toast.makeText(getActivity(),"Enter Company Address First",Toast.LENGTH_LONG).show();
                        address.setError("Enter Company Address First");
                        address.requestFocus();
                        save.setEnabled(true);
                        return;
                    }
                    if (address.getText().toString().trim().length() < 4) {
                        //Toast.makeText(getActivity(),"Company Address must be more than 4 character",Toast.LENGTH_LONG).show();
                        address.setError("Company Address must be more than  character");
                        address.requestFocus();
                        save.setEnabled(true);
                        return;
                    }
                    if (contactNo.getText().toString().trim().equals("")) {
                        //Toast.makeText(getActivity(),"Enter ContectNo  First",Toast.LENGTH_LONG).show();
                        contactNo.setError("Enter ContectNo  First");
                        contactNo.requestFocus();
                        save.setEnabled(true);
                        return;
                    }
                    if (contactNo.getText().toString().trim().length() < 10) {
                        //Toast.makeText(getActivity(),"Company Contact must be more than 10 character",Toast.LENGTH_LONG).show();
                        contactNo.setError("Company Contact must be more than 10 character");
                        contactNo.requestFocus();
                        save.setEnabled(true);
                        return;
                    }
                    company.companyName = companyName.getText().toString();
                    company.address = address.getText().toString();
                    company.contactNo = contactNo.getText().toString();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                CompanyDao companyDao = AppDatabase.getdb(getContext()).companyDao();
                                companyDao.insertAll(company);
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getActivity(), "Company Name saved."+company.companyId, Toast.LENGTH_LONG).show();
                                        NavController navController = Navigation.findNavController(view);
                                        navController.navigate(R.id.action_addCompanyFragment_to_company2);
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