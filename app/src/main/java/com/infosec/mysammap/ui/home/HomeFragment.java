package com.infosec.mysammap.ui.home;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.maps.android.clustering.ClusterManager;
import com.infosec.mysammap.R;
import com.infosec.mysammap.model.MyItem;

import org.json.JSONException;


public abstract class HomeFragment extends Fragment implements OnMapReadyCallback {


    HomeViewModel homeViewModel;



    private GoogleMap mMap;
    private boolean mIsRestore;
    private static String TAG="mytag";
    protected int getLayoutId() {
        return R.layout.activity_maps;
    }




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        setRetainInstance(true);
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(getLayoutId(), container, false);
        setUpMap();


        Log.d(TAG, "HOME - найдем View-root");

        return root;
    }


    @Override
    public void onMapReady(GoogleMap map) {
        this.mMap = map;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(39.654515, 66.968847), 11));
        try {
            startDemo(mIsRestore);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setUpMap() {
//        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
        FragmentManager fm = getActivity().getSupportFragmentManager();/// getChildFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);

            mapFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map, mapFragment).commit();

        mapFragment.getMapAsync(HomeFragment.this);

    }

    /**
     * Run the demo-specific code.
     */
    protected abstract void startDemo(boolean isRestore) throws JSONException;

    protected GoogleMap getMap() {
        //Toast.makeText(this.getActivity(),"1",Toast.LENGTH_LONG).show();

        return mMap;
    }



}