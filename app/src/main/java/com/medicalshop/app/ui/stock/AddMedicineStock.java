package com.medicalshop.app.ui.stock;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.medicalshop.app.Database.AppDatabase;
import com.medicalshop.app.Database.Medicine;
import com.medicalshop.app.Database.MedicineDao;
import com.medicalshop.app.Database.Stock;
import com.medicalshop.app.Database.Wholesaler;
import com.medicalshop.app.Database.WholesalerDao;
import com.medicalshop.app.DatePickerFragment;
import com.medicalshop.app.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


public class AddMedicineStock extends Fragment {
    EditText batchNo,mrp,qty,invNo;
    TextView mfg,exp,addedDate;
    Button save;
    Handler handler;
    Stock stock;
    private Spinner medicineSpinner,wholesalerSpinner;
    private List<Medicine> medicines;
    private List<Wholesaler> wholesalers;
    private ArrayAdapter<Medicine> medicineArrayAdapterForSpinner;
    private ArrayAdapter<Wholesaler> wholesalerArrayAdapterForSpinner;

    public AddMedicineStock() {
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

        return inflater.inflate(R.layout.fragment_add_medicine_stock, container, false);
    }
    @Override
    public void onViewCreated(View view,Bundle bundle)
    {
        batchNo=view.findViewById(R.id.batchNoEditText);
        mrp=view.findViewById(R.id.mrpEditText);
        qty=view.findViewById(R.id.quantityEditText);
        invNo=view.findViewById(R.id.wholesalerInvoiceNoEditText);
        mfg=view.findViewById(R.id.mfgDateTextView);
        exp=view.findViewById(R.id.expDateTextView);
        addedDate=view.findViewById(R.id.addedTextView);
        medicineSpinner=view.findViewById(R.id.selectMedicineSpinner);
        wholesalerSpinner=view.findViewById(R.id.selectWholesalerSpinner);
        save=view.findViewById(R.id.saveStock);
        handler=new Handler();
        // Set Today Date in Added Date
        Calendar today = new GregorianCalendar();
        SimpleDateFormat sdf=new SimpleDateFormat(getResources().getString(R.string.date_format));
        addedDate.setText(sdf.format(today.getTime()));
         mfg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment dpf=new DatePickerFragment(mfg);
                dpf.show(getActivity().getSupportFragmentManager(),"MFG");
            }
        });

        exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment dpf=new DatePickerFragment(exp);
                dpf.show(getActivity().getSupportFragmentManager(),"EXP");
            }
        });

        addedDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment dpf=new DatePickerFragment(addedDate);
                dpf.show(getActivity().getSupportFragmentManager(),"ADDEDDATE");
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {

                // medicine
                MedicineDao medicineDao= AppDatabase.getdb(getActivity()).medicineDao();
                medicines=medicineDao.getAll();

                //Create Adeptor for medicinespinner
                medicineArrayAdapterForSpinner=new ArrayAdapter<Medicine>(getActivity(),android.R.layout.simple_spinner_item,medicines);
                medicineArrayAdapterForSpinner.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

                //Wholesaler
                WholesalerDao wholesalerDao=AppDatabase.getdb(getActivity()).wholesalerDao();
                wholesalers=wholesalerDao.getAll();

                //create Adeptor for medicineSpinner

                wholesalerArrayAdapterForSpinner=new ArrayAdapter<Wholesaler>(getActivity(), android.R.layout.simple_spinner_item,wholesalers);
                wholesalerArrayAdapterForSpinner.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                //set Adaptor
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        medicineSpinner.setAdapter(medicineArrayAdapterForSpinner);
                        wholesalerSpinner.setAdapter(wholesalerArrayAdapterForSpinner);
                    }
                });
            }
        }).start();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save.setEnabled(false);
                if (!allIsWell())
                    return;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        stock=new Stock();
                        String medicineId=medicineSpinner.getSelectedItem().toString();
                        int smpos=medicineId.indexOf(" ");
                        String scId=medicineId.substring(0,smpos);
                        stock.medicineId=Integer.parseInt(scId);

                        String wholesalerId=wholesalerSpinner.getSelectedItem().toString();
                        int swpos=medicineId.indexOf(" ");
                        String swId=wholesalerId.substring(0,swpos);
                        stock.wholesalerid=Integer.parseInt(swId);

                        SimpleDateFormat sdf=new SimpleDateFormat(getString(R.string.date_format));
                        try {
                            stock.mfg=sdf.parse(mfg.getText().toString());
                            stock.exp=sdf.parse(exp.getText().toString());
                            stock.addedOn=sdf.parse(addedDate.getText().toString());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        stock.mrp=Integer.parseInt(mrp.getText().toString());
                        stock.qty=Integer.parseInt(qty.getText().toString());
                        stock.billId=invNo.getText().toString();
                        stock.batchNo=batchNo.getText().toString();
                        AppDatabase.getdb(getActivity()).stockDao().insertAll(stock);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(),"stock Added",Toast.LENGTH_SHORT).show();
                                NavController navController= Navigation.findNavController(view);
                                navController.navigate(R.id.action_addMedicineStock_to_stock);
                                mrp.setText("");
                                exp.setText("");
                                mfg.setText("");
                                invNo.setText("");

                            }
                        });
                    }
                }).start();
            }
        });
    }

    public boolean allIsWell()
    {
        boolean well=true;
        if (batchNo.getText().toString().trim().equals(""))
        {
            batchNo.setError("Enter BatchNo First");
            batchNo.requestFocus();
            well=false;
        }
        if (batchNo.getText().toString().trim().length()<4)
        {
            batchNo.setError("Batch No More than 4 Character");
            batchNo.requestFocus();
            well=false;
        }
        if (mrp.getText().toString().trim().equals(""))
        {
            mrp.setError("Enter Mrp First");
            mrp.requestFocus();
            well=false;
        }
        if (qty.getText().toString().trim().equals(""))
        {
            qty.setError("Enter Medicine Quantity First");
            qty.requestFocus();
            well=false;
        }
        if (invNo.getText().toString().trim().equals(""))
        {
            invNo.setError("Enter Wholesaler Invoice No First");
            invNo.requestFocus();
            well=false;
        }
        if (mfg.getText().toString().trim().equals(""))
        {
            mfg.setError("Enter Manufacture Date");
            mfg.requestFocus();
            well=false;
        }
        if (exp.getText().toString().trim().equals(""))
        {
            exp.setError("Enter Expairy Date");
            exp.requestFocus();
            well=false;
        }
        if (addedDate.getText().toString().trim().equals(""))
        {
            addedDate.setError("Enter Medicine Added Date");
            addedDate.requestFocus();
            well=false;
        }
        if (!well)
            save.setEnabled(true);
            return well;
    }
}