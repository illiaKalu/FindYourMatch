package com.dev.illiakalu.findyourmatch.Fragments;

import android.app.ProgressDialog;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dev.illiakalu.findyourmatch.MainActivity;
import com.dev.illiakalu.findyourmatch.Person;
import com.dev.illiakalu.findyourmatch.PersonsGlobalArrayList;
import com.dev.illiakalu.findyourmatch.R;
import com.dev.illiakalu.findyourmatch.Utils.SharedPreferencesStorer;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testpackage.test_sdk.android.testlib.API;
import org.testpackage.test_sdk.android.testlib.interfaces.PersonsExtendedCallback;
import org.testpackage.test_sdk.android.testlib.interfaces.SuccessCallback;
import org.testpackage.test_sdk.android.testlib.services.UpdateService;
import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by sonicmaster on 1/17/16.
 */
public class GenerateFragment extends Fragment {

    private static final String TAG = "GenerateFragment";

    public GenerateFragment(){
        // Empty required constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_generate, container, false);

        Button generateButton = (Button) v.findViewById(R.id.generateUsersButton);

        final TextView errorText = (TextView) v.findViewById(R.id.errorText);
        errorText.setVisibility(View.INVISIBLE);

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generate(errorText);
            }
        });

        return v;

    }

    private void generate(final TextView errorText) {

        // ASync task for generating and listening for updates of persons
        new AsyncTask<Void, Void, Void>(){

            ProgressDialog _waitDialog;
            int updatedPersonId, tempUpdatedPersonNumber;
            JSONObject tempJSONObject;

            @Override
            protected void onPreExecute() {
                _waitDialog = new ProgressDialog(getActivity());
                _waitDialog.setMessage("Generating users. Please wait...");
                _waitDialog.setCancelable(false);
                _waitDialog.show();
            }

            @Override
            protected Void doInBackground(Void ... params) {

                API.INSTANCE.getPersons(SharedPreferencesStorer.getInstance(getContext()).getPageOfPersonsToLoad(),
                        new PersonsExtendedCallback() {
                    @Override
                    public void onResult(String persons) {

                        // fill up global arraylist pf persons and set up a service listener
                        // fill up arraylist
                        PersonsGlobalArrayList.getInstance().setPersonsList(getPersonsFromJson(persons));

                        // setup service listener
                        if (!PersonsGlobalArrayList.getInstance().getPersonsList().isEmpty())
                        API.INSTANCE.subscribeUpdates(new UpdateService.UpdateServiceListener() {
                            @Override
                            public void onChanges(String person) {
                                // we got the updated person, if it is in our global list of persons ( find it throw id) - modify its data
                                updatedPersonId = getUpdatedPersonId(person);
                                tempUpdatedPersonNumber = PersonsGlobalArrayList.getInstance().searchForPersonsId(updatedPersonId);
                                if (tempUpdatedPersonNumber != -1){
                                    try {
                                        tempJSONObject.getJSONObject(person);
                                        PersonsGlobalArrayList.getInstance().getPersonsList().get(tempUpdatedPersonNumber).setPhotoPath(tempJSONObject.getString("photo"));
                                        PersonsGlobalArrayList.getInstance().getPersonsList().get(tempUpdatedPersonNumber).setLocation(getCoordinates(tempJSONObject.getString("location")));
                                        PersonsGlobalArrayList.getInstance().getPersonsList().get(tempUpdatedPersonNumber).setStatus(tempJSONObject.getString("status"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                                Log.d(TAG, "updated person id is - " + updatedPersonId + " and the match result is - "
                                        + PersonsGlobalArrayList.getInstance().searchForPersonsId(updatedPersonId));
                            }

                        });
                    }


                            @Override
                    public void onFail(String reason) {
                        // handle fail
                    }
                });
                return null;
            }

            // method parse json and returns only person ID
            private int getUpdatedPersonId(String person) {
                try {
                    return new JSONObject(person).getInt("id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return -1;
            }

            @Override
            protected void onPostExecute(Void aVoid) {

                if (!PersonsGlobalArrayList.getInstance().getPersonsList().isEmpty()){
                    ((MainActivity)getActivity()).toResultAndMap();
                }else{
                    errorText.setVisibility(View.VISIBLE);
                }

                _waitDialog.dismiss();

            }

            // {"id":15,"location":"38.76553,-9.119707","photo":"https://goo.gl/zhbWp2","status":"none"}
            // method parse json and fill temp array list to return
            public ArrayList<Person> getPersonsFromJson(String jsonString) {
                ArrayList<Person> tempUsersList = new ArrayList<Person>(10);
                JSONArray personsJsonArray;
                JSONObject personJsonObject;
                try {
                    personsJsonArray = new JSONArray(jsonString);
                    for (int i = 0; i < personsJsonArray.length(); i++) {
                         personJsonObject = personsJsonArray.getJSONObject(i);
                         tempUsersList.add(new Person(personJsonObject.getInt("id"),
                                 getCoordinates(personJsonObject.getString("location")),
                                 personJsonObject.getString("photo"), personJsonObject.getString("status")));

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return tempUsersList;
            }

        }.execute(null, null, null);

    }

    // method creates LatLng obj ( for displaying on map ) from coordinate that comes as String
    private LatLng getCoordinates(String location) {
        String[] parts = location.split(",");
        return new LatLng(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));
    }


}
