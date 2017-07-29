package com.sumit.dell_pc.sanisasakhis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Handler;

import com.sumit.dell_pc.sanisasakhis.auth.LoginActivity;

public class SplashActivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_activty);

        new Handler()
                .postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SplashActivty.this, LoginActivity.class));
                        finish();
                    }
                }, 1000);
    }
}
