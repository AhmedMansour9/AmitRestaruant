package com.example.hp.amitrestaruant.Restaurants;

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
import com.example.hp.amitrestaruant.Branches.PizzaHut;
import com.example.hp.amitrestaruant.Menubranches.MenuPizzaHut;
import com.example.hp.amitrestaruant.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by HP on 15/12/2016.
 */
public class RestaurantActivity extends AppCompatActivity {
    private static final String TAG = RestaurantActivity.class.getSimpleName();
    private String UrL="http://amit-learning.com/myPizza/Api_v2.php?function=getRestaurantData";
    public static  String MY_PREFS_NAME = "MyPrefsFile";
     static String TexTT = "text";
     static String IMAGEE = "imaaaage";

     static String IDDD="id";
    ArrayList<HashMap<String,String>> arra;
    ProgressDialog mProgressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurants);

    new DownloadJSON().execute();


    }
        private class DownloadJSON  extends AsyncTask<Void,Void,Void> {



            @Override
            protected void onPreExecute() {
                mProgressDialog=new ProgressDialog(RestaurantActivity.this);
                mProgressDialog.setMessage("Loading ..");
                mProgressDialog.setTitle("Android JSON Parse Tutorial");
                mProgressDialog.show();
            }

            @Override
            protected Void doInBackground(Void... params) {
                Httphandler a=new Httphandler();
                String jason=a.makeServiceCall(UrL);
                if(jason!=null){
                    try {
                       arra=new ArrayList<>();
                        JSONObject  jsonObject=new JSONObject(jason);
                        JSONArray jsonArray=jsonObject.getJSONArray("data");
                        for (int i=0;i<jsonArray.length();i++) {
                            HashMap<String,String> hash=new HashMap<>();
                            JSONObject jasonn = jsonArray.getJSONObject(i);
                            String id=jasonn.getString("id");
                                String namee=jasonn.getString("name");
                                String imageee=jasonn.getString("img_url");
                              hash.put(IDDD,id);
                              hash.put(TexTT,namee);
                              hash.put(IMAGEE, imageee);
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
                ListView list = (ListView) findViewById(R.id.list);
                adapter arrayAdapter = new adapter(RestaurantActivity.this, R.layout.itemrestaurant, arra);
                list.setAdapter(arrayAdapter);
                mProgressDialog.dismiss();

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String contactId = ((TextView) view.findViewById(R.id.textid)).getText().toString();

                        HashMap<String,String > a = arra.get(position);



                            Intent inty = new Intent(RestaurantActivity.this,PizzaHut.class);
                            SharedPreferences.Editor sharedPreferences=getSharedPreferences(MY_PREFS_NAME,MODE_PRIVATE).edit();
                            sharedPreferences.putString("IDD", a.get(IDDD));
                            sharedPreferences.commit();
                            startActivity(inty);


                    }});
            }

//        final ProgressDialog pDialog = new ProgressDialog(this);
//        pDialog.setMessage("Loading...");
//        pDialog.show();
//        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
//                UrL, null,
//                new Response.Listener<JSONObject>() {
//
//                    @Override
//                    public void onResponse(JSONObject response) {
//
//                        Log.d(TAG, response.toString());
//                        try {
//                            JSONArray jsonArray=response.getJSONArray("data");
//                            for (int i=0;i<jsonArray.length();i++) {
//                                JSONObject jason = jsonArray.getJSONObject(i);
//                                String id=jason.getString("id");
//                                String namee=jason.getString("name");
//                                String imageee=jason.getString("img_url");
//
//                                m=new Movie(namee,imageee);
//
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//
//                        pDialog.hide();
//                    }
//                }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d(TAG, "Error: " + error.getMessage());
//                // hide the progress dialog
//                pDialog.hide();
//
//
//            }
//        });
//
//
//      request.add(jsonObjReq);

}}
