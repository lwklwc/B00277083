package com.B00277083;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.B00277083.R;

/**
 * Created by YeXiang on 15/3/12.
 */
public class SplashActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //隐藏
        this.getSupportActionBar().hide();
    }

    public void enter(View v) {
        this.finish();
        startActivity(new Intent(this, MainActivity.class));
    }

}
