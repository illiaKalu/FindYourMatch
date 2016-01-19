package com.dev.illiakalu.findyourmatch.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.illiakalu.findyourmatch.R;

/**
 * Created by sonicmaster on 1/17/16.
 */
public class GenerateFragment extends Fragment {

    public GenerateFragment(){
        // Empty required constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_generate, container, false);
        return v;

    }
}
