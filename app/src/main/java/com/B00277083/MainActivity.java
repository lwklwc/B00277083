package com.B00277083;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.B00277083.entity.Information;
import com.B00277083.R;



public class MainActivity extends ActionBarActivity {

    private Spinner parkSp;
    private Spinner accSp;

    private TextView mTotalPrice;
    private EditText etArrival, etStay, etAdults, etNum16, etNum5;

    private CheckBox cbPets, cbMember21;

    private int[] price = {35, 25, 15};

    private Information mInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();

    }

    private void initUI() {

        parkSp = (Spinner) findViewById(R.id.spinner_park);
        accSp = (Spinner) findViewById(R.id.spinner_acco);
        mTotalPrice = (TextView) findViewById(R.id.tv_total);

        ArrayAdapter<CharSequence> parkAdapter = ArrayAdapter.createFromResource(this,
                R.array.parks, android.R.layout.simple_spinner_item);

        parkSp.setAdapter(parkAdapter);

        ArrayAdapter<CharSequence> accoAdapter = ArrayAdapter.createFromResource(this,
                R.array.accommodation, android.R.layout.simple_spinner_item);
        accSp.setAdapter(accoAdapter);


        etArrival = (EditText) findViewById(R.id.et_arrival);
        etStay = (EditText) findViewById(R.id.et_stay);
        etAdults = (EditText) findViewById(R.id.et_adults);
        etNum16 = (EditText) findViewById(R.id.et_num16);
        etNum5 = (EditText) findViewById(R.id.et_num5);
        cbPets = (CheckBox) findViewById(R.id.checkbox_pets);
        cbMember21 = (CheckBox) findViewById(R.id.checkbox_member);
        etArrival.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog();
                datePickerDialog.show(getSupportFragmentManager(),DatePickerDialog.class.getName());

            }
        });
    }

    public void resetUI(){
        parkSp.setSelection(0);
        accSp.setSelection(0);
        etArrival.setText("");
        etStay.setText("");
        etAdults.setText("");
        etNum16.setText("");
        etNum5.setText("");
        cbPets.setChecked(false);
        cbMember21.setChecked(false);
        mTotalPrice.setText("");
        mInformation = null;
    }


    //TOTAL PRICE onclick
    public void calculate(View v) {
        try {
            int total =  getInformation().calculateTotalPrice();
            mTotalPrice.setText(total + "￡");
        } catch (NullPointerException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    //CHECK AVAILABILITY onclick
    public void check(View v) {
        Intent intent = new Intent(this, ConfirmActivity.class);
        try {
            if (mInformation == null) {
                mInformation = getInformation();
            }
            mInformation.pet = cbPets.isChecked();
            mInformation.over21 = cbMember21.isChecked();
            intent.putExtra(Information.class.getName(), (android.os.Parcelable) mInformation);
            startActivity(intent);
            resetUI();
        } catch (NullPointerException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public Information getInformation() {
        int parkPos = checkParkSelected();
        int accPos = checkAccSelected();

        Information information = checkInformation();
        SpinnerAdapter accSpAdapter = accSp.getAdapter();
        SpinnerAdapter parkSpAdapter = parkSp.getAdapter();

        information.accommodationPrice = price[accPos - 1];
        information.park = (String) parkSpAdapter.getItem(parkPos);
        information.accommodation = (String) accSpAdapter.getItem(accPos);

        return information;
    }

    private Information checkInformation() {
        String arrival = etArrival.getText().toString().trim();
        String stay = etStay.getText().toString().trim();
        String adults = etAdults.getText().toString().trim();
        String num16 = etNum16.getText().toString().trim();
        String num5 = etNum5.getText().toString().trim();

        if (TextUtils.isEmpty(arrival) || TextUtils.isEmpty(stay) || TextUtils.isEmpty(adults) && TextUtils.isEmpty(num16) && TextUtils.isEmpty(num5)) {
            throw new NullPointerException("please enter the complete information");
        }

        if (TextUtils.isEmpty(num16)) {
            num16 = "NONE";
        }
        if (TextUtils.isEmpty(num5)) {
            num5 = "NONE";
        }
        if (TextUtils.isEmpty(adults)) {
            adults = "NONE";
        }

        if(mInformation == null)
            mInformation  = new Information();

        mInformation.arrivalDate = arrival;
        mInformation.adultsNum = adults;
        mInformation.stayDays = stay;
        mInformation.under16 = num16;
        mInformation.under5 = num5;
        return mInformation;
    }

    private int checkParkSelected() {
        int parkPos = parkSp.getSelectedItemPosition();
        if (parkPos == 0) {
            throw new NullPointerException("Please select a park");
        }
        return parkPos;
    }

    private int checkAccSelected() {
        int accPos = accSp.getSelectedItemPosition();
        if (accPos == 0) {
            throw new NullPointerException("Please select a type of accommodation");
        }
        return accPos;
    }

    public void setTime(int year,int month,int day){

        etArrival.setText(String.format("%d-%02d-%02d",year,month,day));

     }


}
