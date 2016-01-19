package com.dev.illiakalu.findyourmatch.Fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dev.illiakalu.findyourmatch.Adapters.PersonListAdapter;
import com.dev.illiakalu.findyourmatch.Person;
import com.dev.illiakalu.findyourmatch.R;

import java.util.ArrayList;

/**
 * Created by sonicmaster on 1/19/16.
 */
public class PersonsFragment extends Fragment{

    ArrayList<Person> arrayOfUsers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_person, container, false);


        Person p = new Person(1,"vasya");
        Person p2 = new Person(1,"basya");

        arrayOfUsers = new ArrayList<Person>();

        PersonListAdapter adapter = new PersonListAdapter(getContext(), arrayOfUsers);

        adapter.add(new Person(1, "new dude"));

        // Attach the adapter to a ListView
        ListView listView = (ListView) v.findViewById(R.id.personsListView);
        listView.setAdapter(adapter);

        return v;
    }
}
