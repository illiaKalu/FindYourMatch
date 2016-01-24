package com.dev.illiakalu.findyourmatch.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.dev.illiakalu.findyourmatch.MainActivity;
import com.dev.illiakalu.findyourmatch.Person;
import com.dev.illiakalu.findyourmatch.PersonsGlobalArrayList;
import com.dev.illiakalu.findyourmatch.R;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by sonicmaster on 1/17/16.
 */
public class MatchFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_match, container, false);

        // we can find liked person by ID, that we can find in arguments
        final Person matchPerson = PersonsGlobalArrayList.getInstance().searchPersonById(getArguments().getInt("id"));

        ImageView iv = (ImageView) v.findViewById(R.id.matchImageView);
        ImageLoader.getInstance().displayImage(matchPerson.getPhotoPath(), iv);

        Button back = (Button) v.findViewById(R.id.backButton);

        // go beck to result screen, do not forget to remove liked person
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonsGlobalArrayList.getInstance().getPersonsList().remove(matchPerson);
                ((MainActivity)getContext()).toResultAndMap();
            }
        });

        return v;
    }

}
