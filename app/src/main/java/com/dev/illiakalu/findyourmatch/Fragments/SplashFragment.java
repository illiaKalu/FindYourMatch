package com.dev.illiakalu.findyourmatch.Fragments;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.dev.illiakalu.findyourmatch.MainActivity;
import com.dev.illiakalu.findyourmatch.Person;
import com.dev.illiakalu.findyourmatch.PersonsGlobalArrayList;
import com.dev.illiakalu.findyourmatch.R;
import com.dev.illiakalu.findyourmatch.Utils.SharedPreferencesStorer;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.testpackage.test_sdk.android.testlib.API;
import org.testpackage.test_sdk.android.testlib.interfaces.SuccessCallback;

import java.util.ArrayList;


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

        new AsyncTask<ProgressBar, Void, Void>(){

            @Override
            protected void onPreExecute() {
                pb.setVisibility(View.VISIBLE);
            }

            @Override
            protected Void doInBackground(ProgressBar... params) {
                API.INSTANCE.init(getContext());
                ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getContext()).build();
                ImageLoader.getInstance().init(config);
                API.INSTANCE.refreshPersons(new SuccessCallback() {
                    @Override
                    public void onSuccess() {
                        // ?
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {

                if (SharedPreferencesStorer.getInstance(getContext()).isFirstLaunch()) {
                    SharedPreferencesStorer.getInstance(getContext()).setIsFirstLaunch(false);
                    ((MainActivity)getActivity()).toGenerate();
                }else{
                    // how it must be :
                    // load Data from DB
                    // fill up PersonsGlobalArrayList
                    // go to Result screen ((MainActivity)getActivity()).toResultAndMap();

                    // wrong way !
                    ((MainActivity)getActivity()).toGenerate();

                }

                pb.setVisibility(View.INVISIBLE);
            }

        }.execute(pb, null, null);



        return v;
    }


}
