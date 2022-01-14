package com.infosec.mysammap.ui.home.bottom_sheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.infosec.mysammap.R;
import com.infosec.mysammap.model.MyItem;
import com.infosec.mysammap.model.SheetPerson;
import com.infosec.mysammap.ui.home.MyItemReader;

import org.json.JSONException;

import java.io.InputStream;
import java.util.List;

public class BottomSheetFrag extends BottomSheetDialogFragment {
    TextView txtTitle, txtDescription;
    ImageView imgOrganization;
    List<SheetPerson> items;
    int titlePosition;




    public BottomSheetFrag() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.bottom_sheet,container,false);

        try {
            readItems();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        txtTitle = (TextView) root.findViewById(R.id.title_name);
        txtTitle.setText(items.get(titlePosition).getTitle());

        txtDescription = (TextView) root.findViewById(R.id.txt_description);
        txtDescription.setText(items.get(titlePosition).getDescription());

        imgOrganization = (ImageView) root.findViewById(R.id.img_object);
        imgOrganization.setImageResource(items.get(titlePosition).getImgID());


        return root;
    }



    public void setTitleName(int position){

        this.titlePosition = position;
    }



    private void readItems() throws JSONException {
        InputStream inputStream = getResources().openRawResource(R.raw.radar_search);
        items = new SheetPersonReader().read(inputStream);

    }


}
