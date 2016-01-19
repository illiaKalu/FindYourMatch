package com.dev.illiakalu.findyourmatch;


import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.dev.illiakalu.findyourmatch.Fragments.GenerateFragment;
import com.dev.illiakalu.findyourmatch.Fragments.MatchFragment;
import com.dev.illiakalu.findyourmatch.Fragments.ResultAndMapFragment;
import com.dev.illiakalu.findyourmatch.Fragments.SplashFragment;
import com.dev.illiakalu.findyourmatch.Utils.SharedPreferencesStorer;

import org.testpackage.test_sdk.android.testlib.API;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private boolean _showSplash = true;
    private boolean doubleBackToExitPressedOnce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "mainActivity ");

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "start initializing API from parallel thread");
                API.INSTANCE.init(getApplicationContext());
            }
        }).start();

        toSplash();

    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    public void toSplash() {
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.container, new SplashFragment())
                .commit();
    }

    public void toResultAndMap() {
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.container, new ResultAndMapFragment())
                .commit();
    }

    public void toGenerate() {
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.container, new GenerateFragment())
                .commit();
    }

    public void toMatch() {
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.container, new MatchFragment())
                .commit();
    }


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            // Standard back button implementation (for example this could close the app)
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);

    }
}
