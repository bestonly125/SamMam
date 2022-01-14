package com.infosec.mysammap.ui.home;


import android.graphics.drawable.Drawable;

import androidx.fragment.app.Fragment;

import com.infosec.mysammap.R;
import com.infosec.mysammap.model.Person;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyImgItemReader{
    /*
     * This matches only once in whole input,
     * so Scanner.next returns whole InputStream as a String.
     * http://stackoverflow.com/a/5445161/2183804
     */
    private static final String REGEX_INPUT_BOUNDARY_BEGINNING = "\\A";



    public List<Person> read(InputStream inputStream) throws JSONException {
        List<Person> items = new ArrayList<Person>();
        String json = new Scanner(inputStream).useDelimiter(REGEX_INPUT_BOUNDARY_BEGINNING).next();
        JSONArray array = new JSONArray(json);
        for (int i = 0; i < array.length(); i++) {
            String title = null;
            String img = null;
            JSONObject object = array.getJSONObject(i);
            double lat = object.getDouble("lat");
            double lng = object.getDouble("lng");
            if (!object.isNull("title")) {
                title = object.getString("title");
            }
            if (!object.isNull("image")) {
                img = object.getString("image");
            }
            items.add(new Person(lat, lng, title, getResId(img,R.drawable.class)));
        }
        return items;
    }


    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
