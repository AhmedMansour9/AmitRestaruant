package com.example.hp.amitrestaruant.Branches;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.amitrestaruant.Branches.PizzaHut;
import com.example.hp.amitrestaruant.DownloadImage.ImageLoader;
import com.example.hp.amitrestaruant.Map.latandlong;
import com.example.hp.amitrestaruant.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by HP on 15/12/2016.
 */
public class adapterpizza extends BaseAdapter {
    Activity activity;
    TextView idtext;
    public static  String MY_PR= "MyPrefsFil";
    ArrayList<HashMap<String ,String>> array=new ArrayList<>();
    LayoutInflater inflater;
    TextView textpizza;
    TextView addresss;
    ImageButton imaaaage;
    TextView textlonng;
    TextView textlatt;
    ImageLoader imageLoader;
    String LOOng;
    String LAAte;
    HashMap<String,String> hashMap=new HashMap<String, String>();

    public adapterpizza(Activity activity, int singleitemview, ArrayList<HashMap<String, String>> arr){
        this.activity =activity;
        this.array=arr;




    }
    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        inflater=(LayoutInflater)activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.itempizzahut,null);
        hashMap=array.get(position);

        textpizza = (TextView) v.findViewById(R.id.TextPizza);
       addresss=(TextView)v.findViewById(R.id.AdressPizzaHut);
        addresss.setText(hashMap.get(PizzaHut.Address));
        idtext=(TextView)v.findViewById(R.id.textIIId);

        idtext.setText(hashMap.get(PizzaHut.IDMenu));
        textpizza.setText(hashMap.get(PizzaHut.TexTT));
        textlonng=(TextView)v.findViewById(R.id.textlong);
        textlatt=(TextView)v.findViewById(R.id.textlate);
        textlonng.setText(hashMap.get(PizzaHut.Longtude));
        textlatt.setText(hashMap.get(PizzaHut.LatitudeEE));
        LOOng=textlonng.getText().toString();
        LAAte=textlatt.getText().toString();


        imaaaage=(ImageButton)v.findViewById(R.id.imgmap);


        imaaaage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                     latandlong a = new latandlong(activity);
                     if (!a.canGetLocation) {
                         AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);

                         // Setting Dialog Title
                         alertDialog.setTitle("GPS is settings");

                         // Setting Dialog Message
                         alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");

                         // On pressing Settings button
                         alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                             public void onClick(DialogInterface dialog, int which) {
                                 Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                 activity.startActivity(intent);
                             }
                         });

                         // on pressing cancel button
                         alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                             public void onClick(DialogInterface dialog, int which) {
                                 dialog.cancel();
                             }
                         });
                         alertDialog.show();
                     } else if (a.canGetLocation()) {

                         Intent inty = new Intent(activity.getApplication(), com.example.hp.amitrestaruant.Map.map.class);
//                            String   LOOng = ((TextView)v.findViewById(R.id.textlong)).getText().toString();
//                            String LAAte = ((TextView)v.findViewById(R.id.textlate)).getText().toString();

                         SharedPreferences.Editor sharedPreference = activity.getSharedPreferences("DDATA", activity.MODE_PRIVATE).edit();

                         sharedPreference.putString("LONG", LOOng);
                         sharedPreference.putString("LATY", LAAte);
//                         inty.putExtra("LONG",LOOng);
//                         inty.putExtra("LATY",LAAte);
                         sharedPreference.commit();


                         activity.startActivity(inty);
                     }


                 }

            });



        return v;
}}
