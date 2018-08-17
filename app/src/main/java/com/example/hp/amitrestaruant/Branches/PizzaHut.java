package com.example.hp.amitrestaruant.Branches;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.hp.amitrestaruant.Httphandler;
import com.example.hp.amitrestaruant.Menubranches.MenuPizzaHut;
import com.example.hp.amitrestaruant.R;
import com.example.hp.amitrestaruant.Restaurants.RestaurantActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by HP on 15/12/2016.
 */

public class PizzaHut extends AppCompatActivity {
    public static  String MY_PREFS= "MyPrefsFil";
    Httphandler a;
    private static final String TAG = PizzaHut.class.getSimpleName();
   static String IDMenu="id";

    static String TexTT = "text";
    static String Address = "addreess";
    static String Longtude = "longitude";
    static String LatitudeEE = "latitude";

    ArrayList<HashMap<String,String>> arra;
    ProgressDialog mProgressDialog;
    public String UrL;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstbranch);



        SharedPreferences prefs = getSharedPreferences(RestaurantActivity.MY_PREFS_NAME, MODE_PRIVATE);
       String idd=prefs.getString("IDD", null);

        new DownloadJSON().execute();
        UrL="http://amit-learning.com/myPizza/Api_v2.php?function=getBranchesData&resID="+idd;

    }

    private class DownloadJSON  extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            mProgressDialog=new ProgressDialog(PizzaHut.this);
            mProgressDialog.setMessage("Loading ..");
            mProgressDialog.setTitle("Android JSON Parse Tutorial");
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

             a=new Httphandler();
            String jason=a.makeServiceCall(UrL);

            if(jason!=null){
                try {
                    arra=new ArrayList<>();
                    JSONObject jsonObject=new JSONObject(jason);
                    JSONArray jsonArray=jsonObject.getJSONArray("data");
                    for (int i=0;i<jsonArray.length();i++) {
                        HashMap<String,String> hash=new HashMap<>();
                        JSONObject jasonn = jsonArray.getJSONObject(i);
                        String Id=jasonn.getString("id");
                        String namee=jasonn.getString("name");
                        String longude=jasonn.getString("longitude");
                        String latitude=jasonn.getString("latitude");
                        String address=jasonn.getString("address");

                        hash.put(IDMenu,Id);
                        hash.put(Longtude,longude);
                        hash.put(LatitudeEE,latitude);
                        hash.put(TexTT,namee);
                        hash.put(Address,address);
                        arra.add(hash);
                    }

                }

                catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),
                            "Couldn't get json from server. Check LogCat for possible errors!",
                            Toast.LENGTH_LONG)
                            .show();
                    return null;
                }



            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            mProgressDialog.dismiss();
            ListView listtt = (ListView) findViewById(R.id.ListPizzaHut);
            adapterpizza arrayAdapt = new adapterpizza(PizzaHut.this, R.layout.itempizzahut, arra);
            listtt.setAdapter(arrayAdapt);
            mProgressDialog.dismiss();

            listtt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                  Intent inty = new Intent(PizzaHut.this,com.example.hp.amitrestaruant.Menubranches.MenuPizzaHut.class);
                  String contact = ((TextView) view.findViewById(R.id.textIIId)).getText().toString();
                  String getLat = ((TextView) view.findViewById(R.id.textlate)).getText().toString();

                  SharedPreferences.Editor sharedPrefere=getSharedPreferences(MY_PREFS,MODE_PRIVATE).edit();
                  sharedPrefere.putString("IDde", contact);
                  sharedPrefere.commit();
                  startActivity(inty);

              }
          });


        }


    }


}
