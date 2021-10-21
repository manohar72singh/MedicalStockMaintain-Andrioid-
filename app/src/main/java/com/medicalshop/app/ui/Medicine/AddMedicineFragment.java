package com.medicalshop.app.ui.Medicine;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.medicalshop.app.Database.AppDatabase;
import com.medicalshop.app.Database.Company;
import com.medicalshop.app.Database.CompanyDao;
import com.medicalshop.app.Database.Composition;
import com.medicalshop.app.Database.CompositionDao;
import com.medicalshop.app.Database.Medicine;
import com.medicalshop.app.Database.MedicineComposition;
import com.medicalshop.app.Database.Stock;
import com.medicalshop.app.R;


import java.util.ArrayList;
import java.util.List;


public class AddMedicineFragment extends Fragment {

    EditText medicineName;
    CheckBox sh1,nrx;
    Button saveMedicine;
    Medicine medicine;
    private RecyclerView selectedCompositionRecyclerView;
    private MedicineCompositionViewRecyclerViewAdapter recyclerViewAdeptor;
    private Handler handler;
    private List <Composition> compositions ;
    private List<Company> companies;
    private Spinner spinner,companyspinner,medicineTypeSpinner;
    private ArrayAdapter <Composition> compositionArrayAdapterForSpinner;
    private ArrayAdapter<Company> companyArrayAdapterForSpinner;

    public AddMedicineFragment() {
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
        return inflater.inflate(R.layout.fragment_add_medicine, container, false);
    }
    @Override
    public  void onViewCreated(View view, Bundle bundle)
    {
        // Access All EditText,Spinner or Button
        medicineName=view.findViewById(R.id.medicineNameEditText);

        saveMedicine=view.findViewById(R.id.saveMedicine);
        medicineTypeSpinner=view.findViewById(R.id.medicineTypeSpinner);
        companyspinner=view.findViewById(R.id.medicineCompanyNameSpinner);
        spinner=view.findViewById(R.id.compositionSelectionSpinner);
        nrx=view.findViewById(R.id.nrxCheckBox);
        sh1=view.findViewById(R.id.shuduleH1CheckBox);



        EditText searchComposition= view.findViewById(R.id.searchCompositionEditText);
        selectedCompositionRecyclerView =view.findViewById(R.id.compositionSeletedRecyclerView);
        selectedCompositionRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        // Load All Compositions In Background Thread .
        handler=new Handler();


        new Thread(new Runnable() {
            @Override
            public void run() {
                //Company
                CompanyDao companyDao=AppDatabase.getdb(getActivity()).companyDao();
                companies=companyDao.getAll();
                //create Array Adeptor for Companysinner
                companyArrayAdapterForSpinner=new ArrayAdapter<Company>(getActivity(),android.R.layout.simple_spinner_item,companies);
                companyArrayAdapterForSpinner.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

                //Composition
                CompositionDao compositionDao = AppDatabase.getdb(getActivity()).compositionDao();
                compositions =compositionDao.getAll();
                Composition topComposition = new Composition();
                topComposition.compositionName="Select Composition";
                compositions.add(0,topComposition);
                //Create Array Adepter For CompositionSpinner.
                compositionArrayAdapterForSpinner= new ArrayAdapter<Composition>(getActivity(),android.R.layout.simple_spinner_item,compositions);
                compositionArrayAdapterForSpinner.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

                //Create Array Adeptor of MedicineType
                ArrayAdapter<CharSequence> medicineTypeAdapter=ArrayAdapter.createFromResource(getContext(),R.array.medicine_types, android.R.layout.simple_spinner_item);
                medicineTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                // Create Recycler View Adeptor Having No Item
                recyclerViewAdeptor=new MedicineCompositionViewRecyclerViewAdapter(new ArrayList<Composition>());

                //Set Adaptor in recyclerView in main thread

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        selectedCompositionRecyclerView.setAdapter(recyclerViewAdeptor);
                        spinner.setAdapter(compositionArrayAdapterForSpinner);
                        companyspinner.setAdapter(companyArrayAdapterForSpinner);

                        medicineTypeSpinner.setAdapter(medicineTypeAdapter);
                    }
                });
            }
        }).start();
        // Handale on Item Selected Listener Spinner

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0)
                return;
                // get the Item select

                if(!recyclerViewAdeptor.addNewComposition(compositions.get(position)))

                    Toast.makeText(getActivity(),"Already Selected",Toast.LENGTH_SHORT).show();

                recyclerViewAdeptor.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        searchComposition.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // featch the data from table of Enter that matches the enterd text and add in to composition spinner
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (s.toString().length()==0)
                        {
                            compositions=AppDatabase.getdb(getActivity()).compositionDao().getAll();
                            compositionArrayAdapterForSpinner= new ArrayAdapter<Composition>(getActivity(),android.R.layout.simple_spinner_item,compositions);
                            compositionArrayAdapterForSpinner.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

                        }
                        else
                        {
                            compositions=AppDatabase.getdb(getActivity()).compositionDao().getFiltered("%"+s.toString()+"%");
                            Composition topComposition = new Composition();
                            topComposition.compositionName="Select Composition";
                            compositions.add(0,topComposition);
                            compositionArrayAdapterForSpinner= new ArrayAdapter<Composition>(getActivity(),android.R.layout.simple_spinner_item,compositions);
                            compositionArrayAdapterForSpinner.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                        }

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                spinner.setAdapter(compositionArrayAdapterForSpinner);
                            }
                        });
                    }
                }).start();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // code for save medicine
        saveMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMedicine.setEnabled(false);
                medicine = new Medicine();

                if(!allIsWell())
                    return;
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        medicine.medicineName=medicineName.getText().toString();
                        //find the id of selected company
                        String scompanyId =companyspinner.getSelectedItem().toString(); // id company Name
                        // find the position of 1st space in scompanyId
                        int spos = scompanyId.indexOf(" ");
                        // Extract all charectors from 0 to spos from scompanyId
                        String scId= scompanyId.substring(0,spos);

                        medicine.medicinecompanyid=Integer.parseInt(scId);
                        medicine.type=medicineTypeSpinner.getSelectedItemPosition();
                        medicine.nrx=nrx.isChecked();
                        medicine.sh1=sh1.isChecked();
                        // add the current medicine in table and get the id

                        long medicineId = AppDatabase.getdb(getActivity()).medicineDao().insert(medicine);
                        //insert all compositions in composition table
                        //get all selected compositions

                        for(int i =0;i<recyclerViewAdeptor.getItemCount();i++)
                        {
                            MedicineComposition medicineComposition=new MedicineComposition();
                            medicineComposition.compositionId=recyclerViewAdeptor.getItem(i).compositionId;
                            medicineComposition.medicineId=medicineId;
                            AppDatabase.getdb(getActivity()).medicineCompositionDao().inesrtAll(medicineComposition);
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(),"New Medicine Added Sucessfully",Toast.LENGTH_SHORT).show();
                                NavController navController= Navigation.findNavController(view);
                                navController.navigate(R.id.action_addMedicineFragment_to_medicine);
                            }
                        });

                    }
                }).start();



            }
        });


    }

    public boolean allIsWell()
    {
        boolean well= true;
        if (medicineName.getText().toString().trim().equals(""))
        {
            medicineName.setError("Enter Medicine Name First");
            medicineName.requestFocus();
            well=false;
        }
        if (medicineName.getText().toString().trim().length()<4)
        {
            medicineName.setError("Enter Medicine Name Must be More Than 4 Character");
            medicineName.requestFocus();
            well=false;
        }

        if (!well)
            saveMedicine.setEnabled(true);
            return well;
    }
}