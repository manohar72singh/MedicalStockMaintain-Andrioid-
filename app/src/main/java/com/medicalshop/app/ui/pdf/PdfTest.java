package com.medicalshop.app.ui.pdf;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.medicalshop.app.R;
import com.medicalshop.app.ui.graphics.MyCustomView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class PdfTest extends Fragment
{


    public PdfTest() {
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
        return inflater.inflate(R.layout.fragment_pdf_test, container, false);
    }

    @Override
    public void onViewCreated(View view,Bundle bundle)
    {
        Button createPdf=view.findViewById(R.id.pdfButton);
        createPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkPermission())
                {
                    requestPermission();
                    return;
                }
                // create an object for pdfdocument
                PdfDocument pdfDocument = new PdfDocument();
                // create pageinfo
                PdfDocument.PageInfo myPageInfo =new PdfDocument.PageInfo.Builder(3508,2480,1).create();
                // create page
                PdfDocument.Page page = pdfDocument.startPage(myPageInfo);
                // get canvas of page
                Canvas canvas = page.getCanvas();
                // draw on canvas
                Paint paint= new Paint();
                MyCustomView.drawBill(canvas,3508,2480,2480);
                // finish the page
                pdfDocument.finishPage(page);
                // write pdf data to file
                //open to the file
                try {
                    File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"Janesh1.pdf");
                    // write pdf data to file
                    pdfDocument.writeTo(new FileOutputStream(file));
                    Toast.makeText(getContext(), "pdf created succesfully", Toast.LENGTH_SHORT).show();
                }
                catch (IOException error)
                {
                    Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    // Createing function to check external stroge read write permission.

    private boolean checkPermission()
    {
        int permission1= ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),WRITE_EXTERNAL_STORAGE);
        int permission2=ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),READ_EXTERNAL_STORAGE);
        return permission1== PackageManager.PERMISSION_GRANTED && permission2== PackageManager.PERMISSION_GRANTED;
    }
    private void requestPermission()
    {
        ActivityCompat.requestPermissions(getActivity(),new String[]{WRITE_EXTERNAL_STORAGE,READ_EXTERNAL_STORAGE},200);
    }
}