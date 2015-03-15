package com.B00277083;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;

import com.B00277083.R;

import java.util.Calendar;

/**
 * Created by Administrator on 2015/3/14.
 */
public class DatePickerDialog extends DialogFragment {

    private Button mOK,mNO;
    private DatePicker mDatePicker;
    private MainActivity mActivty;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivty = (MainActivity) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return inflater.inflate(R.layout.dialog_date_pick,null);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mDatePicker = (DatePicker) view.findViewById(R.id.date_picker);
        mOK = (Button) view.findViewById(R.id.btn_ok);
        mNO = (Button) view.findViewById(R.id.btn_no);

        mOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int year = mDatePicker.getYear();
                int month = mDatePicker.getMonth();
                int day = mDatePicker.getDayOfMonth();

                mActivty.setTime(year,(month+1),day);


                dismiss();
            }
        });

        mNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        mDatePicker.init(year,month,day,new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            }
        });

    }



}
