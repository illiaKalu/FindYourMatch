package com.dev.illiakalu.findyourmatch.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.dev.illiakalu.findyourmatch.Person;
import com.dev.illiakalu.findyourmatch.R;

import java.util.ArrayList;

/**
 * Created by sonicmaster on 1/19/16.
 */
public class PersonListAdapter extends ArrayAdapter<Person> {

    public PersonListAdapter(Context context, ArrayList<Person> persons)  {
        super(context, 0, persons);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        final Person person = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_task_item, parent, false);
        }
        Button bt = (Button) convertView.findViewById(R.id.likeButton);
        bt.setText(person.getName());

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(person);
            }
        });

        // Return the completed view to render on screen
        return convertView;

    }

}
