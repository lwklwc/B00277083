package com.B00277083;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.B00277083.R;
import com.B00277083.entity.Information;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;


public class ConfirmActivity extends ActionBarActivity {

    private TextView etArrival, etStay, etAdults, etNum16, etNum5, mTvPark, mTvAccommodation, mTvPet, mTvOver21;

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        initUI();
    }

    private void initUI() {
        final Information information = getIntent().getParcelableExtra(Information.class.getName());

        mButton = (Button) findViewById(R.id.button2);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String writeFileName = "travel.txt";
//                    writeObject(writeFileName,information);
                    writeText(writeFileName,information.toString());
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(ConfirmActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        etArrival = (TextView) findViewById(R.id.tv_arrival);
        etStay = (TextView) findViewById(R.id.tv_stay);
        etAdults = (TextView) findViewById(R.id.tv_adults);
        etNum16 = (TextView) findViewById(R.id.tv_num16);
        etNum5 = (TextView) findViewById(R.id.tv_num5);
        mTvPark = (TextView) findViewById(R.id.tv_park);
        mTvAccommodation = (TextView) findViewById(R.id.tv_accommodation);
        mTvPet = (TextView) findViewById(R.id.tv_bring_pet);
        mTvOver21 = (TextView) findViewById(R.id.tv_over_21);

        mTvPark.setText(information.park);
        mTvAccommodation.setText(information.accommodation);

        etArrival.setText(information.arrivalDate);
        etStay.setText(information.stayDays);
        etAdults.setText(information.adultsNum);
        etNum16.setText(information.under16);
        etNum5.setText(information.under5);

        mTvPet.setText(information.pet ? "YES" : "NO");
        mTvOver21.setText(information.over21 ? "YES" : "NO");

    }


    public void writeObject(String filename,Object obj)throws Exception{
        File file = new File(Environment.getExternalStorageDirectory(), filename);
        OutputStream out = new FileOutputStream(file,true) ;
        ObjectOutputStream oos = new ObjectOutputStream(out) ;
        oos.writeObject(obj) ;
        oos.close() ;
    }

    public void writeText(String fileName,String text)throws Exception{
        File file = new File(Environment.getExternalStorageDirectory(), fileName);
        OutputStream out = new FileOutputStream(file,true) ;
        OutputStreamWriter osw = new OutputStreamWriter(out);
        osw.write(text);
        osw.close() ;
    }

}
