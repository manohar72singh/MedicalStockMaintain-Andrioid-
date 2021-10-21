package com.medicalshop.app.ui.graphics;



import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.medicalshop.app.R;


public class Graphics extends Fragment {

    public Graphics() {
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
        return inflater.inflate(R.layout.fragment_graphics, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle bundle)
    {
        MyCustomView myCustomView =view.findViewById(R.id.myCustomView);
    }
}