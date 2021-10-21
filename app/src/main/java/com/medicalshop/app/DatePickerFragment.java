package com.medicalshop.app;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Formatter;

public  class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {
    private TextView eText;

    public DatePickerFragment(TextView editText)
    {
        eText=editText;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
        Formatter formatter=new Formatter();
        String selectedDate=formatter.format("%02d-%02d-%4d",dayOfMonth,month+1,year).toString();
        eText.setText(selectedDate);

    }
}