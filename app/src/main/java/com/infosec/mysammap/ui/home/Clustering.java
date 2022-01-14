package com.infosec.mysammap.ui.home;

import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.infosec.mysammap.R;
import com.infosec.mysammap.model.MyItem;
import com.infosec.mysammap.ui.home.bottom_sheet.BottomSheetFrag;

import org.json.JSONException;

import java.io.InputStream;
import java.util.List;

public class Clustering extends HomeFragment implements
        ClusterManager.OnClusterItemClickListener<MyItem>,
        ClusterManager.OnClusterClickListener<MyItem>,
        ClusterManager.OnClusterItemInfoWindowClickListener<MyItem>,
        ClusterManager.OnClusterInfoWindowClickListener<MyItem> {
        private ClusterManager<MyItem> mClusterManager;

        List<MyItem> items;
        BottomSheetBehavior sheetBehavior;


        @Override
        protected void startDemo(boolean isRestore) {
//            ClusteringImages clusteringImages = new ClusteringImages();
//            clusteringImages.startDemo(isRestore);
            if (!isRestore) {
                getMap();
            }else {Toast.makeText(this.getActivity(),"Cluster",Toast.LENGTH_LONG).show();}

            mClusterManager = new ClusterManager<MyItem>(this.getActivity(), getMap());



            getMap().setOnCameraIdleListener(mClusterManager);
            getMap().setOnMarkerClickListener(mClusterManager);
            getMap().setOnInfoWindowClickListener(mClusterManager);

            mClusterManager.setOnClusterClickListener(this);
            mClusterManager.setOnClusterInfoWindowClickListener(this);
            mClusterManager.setOnClusterItemClickListener(this);
            mClusterManager.setOnClusterItemInfoWindowClickListener(this);


            try {
                readItems();
            } catch (JSONException e) {
                Toast.makeText(this.getActivity(), "Problem reading list of markers.", Toast.LENGTH_LONG).show();
            }
        }

        private void readItems() throws JSONException {
            InputStream inputStream = getResources().openRawResource(R.raw.radar_search);
            items = new MyItemReader().read(inputStream);
            mClusterManager.addItems(items);

        }



    @Override
    public boolean onClusterItemClick(MyItem myItem) {



        for (int i = 0; i < items.size(); i++) {
            if (myItem.getTitle().equals(items.get(i).getTitle())) {
                // YOUR ACTION GOES HERE
                BottomSheetFrag bottomSheetFrag = new BottomSheetFrag();
                bottomSheetFrag.setTitleName(i);
                bottomSheetFrag.show(getChildFragmentManager(), bottomSheetFrag.getTag());
            }
        }



            return false;
    }

    @Override
    public boolean onClusterClick(Cluster<MyItem> cluster) {

        return false;
    }

    @Override
    public void onClusterItemInfoWindowClick(MyItem myItem) {

    }

    @Override
    public void onClusterInfoWindowClick(Cluster<MyItem> cluster) {

    }


//    private LatLng position() {
//        return new LatLng(39.654452, 66.975828);
//    }
    }

