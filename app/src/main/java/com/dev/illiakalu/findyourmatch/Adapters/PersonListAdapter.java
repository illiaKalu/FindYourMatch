package com.dev.illiakalu.findyourmatch.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import com.dev.illiakalu.findyourmatch.MainActivity;
import com.dev.illiakalu.findyourmatch.Person;
import com.dev.illiakalu.findyourmatch.PersonsGlobalArrayList;
import com.dev.illiakalu.findyourmatch.R;
import com.dev.illiakalu.findyourmatch.Utils.SharedPreferencesStorer;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by sonicmaster on 1/19/16.
 */
public class PersonListAdapter extends ArrayAdapter<Person> {

    private static final String TAG = "PersonListAdapter";

    public PersonListAdapter(Context context, ArrayList<Person> persons)  {
        super(context, 0, persons);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        final Person person = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        View v;
        v = LayoutInflater.from(getContext()).inflate(R.layout.item_task_item, parent, false);


        ImageView heartIcon = (ImageView) v.findViewById(R.id.heartImageView);
        Log.d(TAG, "current person id + status - " + person.getId() + " + " + person.getStatus());

        // wrong realization here !
        if (!person.getStatus().equals("none")){
            heartIcon.setVisibility(View.VISIBLE);
        }else{
            heartIcon.setVisibility(View.INVISIBLE);
        }

        ImageView iv = (ImageView) v.findViewById(R.id.imageView);
        ImageLoader.getInstance().displayImage(person.getPhotoPath(), iv);

        Button likeButton = (Button) v.findViewById(R.id.likeButton);
        Button dislikeButton = (Button) v.findViewById(R.id.dislikeButton);

        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // wrong realization here !
                if (!person.getStatus().equals("none")){
                    // pass ID of liked person to match screen
                    Bundle personId = new Bundle();
                    personId.putInt("id", person.getId());
                    ((MainActivity) getContext()).toMatch(personId);
                }else{
                    remove(person);
                }

                // we should change status to "like" here and save it somewhere

                // check if global array list is not empty
                if (PersonsGlobalArrayList.getInstance().getPersonsList().isEmpty()){
                    SharedPreferencesStorer.getInstance(getContext()).incrementPageOfPersons();
                    ((MainActivity) getContext()).toGenerate();
                }
            }
        });

        dislikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //make sense only if realization is right ( save user actions to DB )
                person.setStatus("dislike");
                remove(person);
                // check if global array list is not empty
                if (PersonsGlobalArrayList.getInstance().getPersonsList().isEmpty()){
                    SharedPreferencesStorer.getInstance(getContext()).incrementPageOfPersons();
                    ((MainActivity) getContext()).toGenerate();
                }
            }


        });


        // Return the completed view to render on screen
        return v;

    }


}
