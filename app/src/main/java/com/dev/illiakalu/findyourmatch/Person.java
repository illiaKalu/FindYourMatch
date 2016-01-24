package com.dev.illiakalu.findyourmatch;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by sonicmaster on 1/19/16.
 */
public class Person {

    // {"id":15,"location":"38.76553,-9.119707","photo":"https://goo.gl/zhbWp2","status":"none"}

    public Person(int id, LatLng location, String photoPath, String status){
        this.id = id;
        this.location = location;
        this.photoPath = photoPath;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // "location":"38.76553,-9.119707"

    private int id;
    private LatLng location;
    private String photoPath;
    private String status;


    @Override
    public String toString() {
        return "" + this.id;
    }
}
