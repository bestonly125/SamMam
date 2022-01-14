package com.infosec.mysammap.model;

import android.graphics.drawable.Drawable;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class Person implements ClusterItem {

  public final String name;
  public final int profilePhoto;
  private final LatLng mPositon;


    public Person(LatLng positon,String name, int profilePhoto ) {
        this.name = name;
        this.profilePhoto = profilePhoto;
        mPositon = positon;
    }

    public Person(double lat, double lng, String title, int img) {
        this.mPositon = new LatLng(lat, lng);
        this.name = title;
        this.profilePhoto = img;
    }

    @Override
    public LatLng getPosition() {
        return mPositon;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getSnippet() {
        return null;
    }

    public String getName() {
        return name;
    }
}
