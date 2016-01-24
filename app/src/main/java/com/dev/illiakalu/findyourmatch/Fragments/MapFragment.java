package com.dev.illiakalu.findyourmatch.Fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.illiakalu.findyourmatch.MainActivity;
import com.dev.illiakalu.findyourmatch.Person;
import com.dev.illiakalu.findyourmatch.PersonsGlobalArrayList;
import com.dev.illiakalu.findyourmatch.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by sonicmaster on 1/19/16.
 */
public class MapFragment extends Fragment {

    Bitmap bmp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_map, container, false);

        final GoogleMap googleMap;
        SupportMapFragment mapFrag = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map_container);

        googleMap = mapFrag.getMap();


        // bad solution
        new Thread(new Runnable() {
            
            @Override
            public void run() {
               for (final Person person: PersonsGlobalArrayList.getInstance().getPersonsList()){


                   bmp = ImageLoader.getInstance().loadImageSync(person.getPhotoPath());
                   // minimize photo for better displaying
                   bmp = Bitmap.createScaledBitmap(bmp,(int)(bmp.getWidth()*0.2), (int)(bmp.getHeight()*0.2), true);

                   getActivity().runOnUiThread(new Runnable() {
                       @Override
                       public void run() {
                           googleMap.addMarker(new MarkerOptions()
                                   .position(person.getLocation())
                                   .icon(BitmapDescriptorFactory.fromBitmap(bmp)));
                       }
                   });
               }



            }
        }).start();

        // move camera to markets
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(PersonsGlobalArrayList.getInstance().
                getPersonsList().get(0).getLocation(), 12));

        return v;
    }

}
