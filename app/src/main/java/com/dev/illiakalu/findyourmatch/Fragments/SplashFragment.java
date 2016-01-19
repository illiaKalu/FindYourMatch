package com.dev.illiakalu.findyourmatch.Fragments;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.dev.illiakalu.findyourmatch.MainActivity;
import com.dev.illiakalu.findyourmatch.R;
import com.dev.illiakalu.findyourmatch.Utils.SharedPreferencesStorer;


/**
 * Created by sonicmaster on 1/17/16.
 */
public class SplashFragment extends Fragment {

    private static final String TAG = "SplashFragment";

    ProgressBar pb;

    public SplashFragment(){
        // required empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // find progress bar to show it
        View v = inflater.inflate(R.layout.fragment_splash, container, false);


        pb = ((ProgressBar) v.findViewById(R.id.progressBar));
        pb.getIndeterminateDrawable()
                .setColorFilter(Color.parseColor("#f3923b"), PorterDuff.Mode.SRC_IN);
        pb.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if (SharedPreferencesStorer.getInstance(getContext()).isFirstLaunch()) {
                    SharedPreferencesStorer.getInstance(getContext()).setIsFirstLaunch(false);
                    ((MainActivity)getActivity()).toGenerate();
                }else{
                    ((MainActivity)getActivity()).toResultAndMap();
                }
            }

        }, 5000);

        return v;
    }


}
