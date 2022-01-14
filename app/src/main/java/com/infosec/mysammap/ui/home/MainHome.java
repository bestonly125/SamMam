package com.infosec.mysammap.ui.home;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.infosec.mysammap.R;

import org.json.JSONException;


public class MainHome extends HomeFragment {


    Clustering mClustering;
    ClusteringImages mClusteringImages;
    FragmentTransaction mFragmentTransaction;
    FloatingActionButton fabPlus;
    FloatingActionButton fabMinus;

    boolean zoom;





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main_home,container,false);

        mClustering = new Clustering();
        mClusteringImages  = new ClusteringImages();

        mFragmentTransaction = getChildFragmentManager().beginTransaction();
        mFragmentTransaction.add(R.id.container_home,mClustering);
        mFragmentTransaction.commit();


        fabPlus = (FloatingActionButton) root.findViewById(R.id.fabplus);
        fabPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(zoom == false){
                    mClustering.getMap().animateCamera(CameraUpdateFactory.zoomIn());
                }else{
                    mClusteringImages.getMap().animateCamera(CameraUpdateFactory.zoomIn());
                }



            }
        });


        fabMinus = (FloatingActionButton) root.findViewById(R.id.fabminus);
        fabMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(zoom == false) {
                    mClustering.getMap().animateCamera(CameraUpdateFactory.zoomOut());
                }else{
                    mClusteringImages.getMap().animateCamera(CameraUpdateFactory.zoomOut());
                }
            }
        });

        setHasOptionsMenu(true);

        return root;
    }

    @Override
    protected void startDemo(boolean isRestore) throws JSONException {

    }


    private void fragmentTrans(Fragment fragment){
        mFragmentTransaction = getChildFragmentManager().beginTransaction();
        mFragmentTransaction.replace(R.id.container_home,fragment);
        mFragmentTransaction.commit();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().getMenuInflater().inflate(R.menu.menu_home, menu);
    }


//    public void onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        super.onCreateOptionsMenu(menu, inflater);
//        getActivity().getMenuInflater().inflate(R.menu.menu_home, menu);
//
//    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.action_check:
                if(item.isChecked()){
                    // If item already checked then unchecked it
                    item.setChecked(false);
                    fragmentTrans(mClustering);
                    MenuColor(R.color.black,item);
                    zoom = false;
                }else{
                    // If item is unchecked then checked it
                    item.setChecked(true);
                    fragmentTrans(mClusteringImages);
                    MenuColor(R.color.white,item);
                    zoom = true;
                }

                return true;
        }




        return super.onOptionsItemSelected(item);
    }


    private void MenuColor(int color, MenuItem item){

        Drawable drawable =  item.getIcon();
        drawable.mutate();
        drawable.setColorFilter(getResources().getColor(color), PorterDuff.Mode.SRC_ATOP);
    }



}
