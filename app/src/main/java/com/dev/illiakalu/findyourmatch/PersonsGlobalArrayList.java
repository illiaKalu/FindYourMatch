package com.dev.illiakalu.findyourmatch;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by sonicmaster on 1/24/16.
 */
public class PersonsGlobalArrayList {

    public ArrayList<Person> getPersonsList() {
        return personsList;
    }

    public void setPersonsList(ArrayList<Person> personsList) {
        this.personsList = personsList;
    }


    private ArrayList<Person> personsList = new ArrayList<>(10);

    private static volatile PersonsGlobalArrayList instance;

    public PersonsGlobalArrayList() {

    }

    public static PersonsGlobalArrayList getInstance() {

        if (instance == null) {
            synchronized (PersonsGlobalArrayList.class) {
                instance = new PersonsGlobalArrayList();
            }
        }
        return instance;
    }

    public int searchForPersonsId(int id){
        for (int i = 0; i < personsList.size(); i++) {
            if (personsList.get(i).getId() == id){
                return i;
            }
        }
        return -1;
    }

    public Person searchPersonById(int id){
        for (int i = 0; i < personsList.size(); i++) {
            if (personsList.get(i).getId() == id)
                return personsList.get(i);
        }
        return null;
    }

}
