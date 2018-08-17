package com.example.hp.amitrestaruant.Menubranches;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.amitrestaruant.Branches.PizzaHut;
import com.example.hp.amitrestaruant.Branches.adapterpizza;
import com.example.hp.amitrestaruant.Httphandler;
import com.example.hp.amitrestaruant.R;
import com.example.hp.amitrestaruant.Restaurants.RestaurantActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by HP on 17/12/2016.
 */
public class MenuPizzaHut extends AppCompatActivity {

   public String UrLL;
   public static String ID="id";
   public static String Name = "name";
   public static String Prrice = "price";
   public static String TYPE = "type";
   public static String DISCRAption = "discraption";
    ArrayList<HashMap<String,String>> array;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menupizzahut);

        SharedPreferences prefs = getSharedPreferences(com.example.hp.amitrestaruant.Branches.PizzaHut.MY_PREFS, MODE_PRIVATE);
        String iddd=prefs.getString("IDde", null);
        UrLL="http://amit-learning.com/myPizza/Api_v2.php?function=getMenuData&branchID="+iddd;
        new DownloadJSON().execute();


    }
      private class DownloadJSON  extends AsyncTask<Void,Void,Void> {

          @Override
          protected void onPreExecute() {
              mProgressDialog=new ProgressDialog(MenuPizzaHut.this);
              mProgressDialog.setMessage("Loading ..");
              mProgressDialog.setTitle("Android JSON Parse Tutorial");
              mProgressDialog.show();
          }


          @Override
          protected Void doInBackground(Void... params) {

              Httphandler a=new Httphandler();
              String jason=a.makeServiceCall(UrLL);

              if(jason!=null){
                  try {
                      array=new ArrayList<>();
                      JSONObject jsonObject=new JSONObject(jason);
                      JSONArray jsonArray=jsonObject.getJSONArray("data");
                      for (int i=0;i<jsonArray.length();i++) {
                          HashMap<String,String> hasha=new HashMap<>();
                          JSONObject jasonn = jsonArray.getJSONObject(i);
                          String iddd=jasonn.getString("id");
                          String nameee=jasonn.getString("name");
                          String address=jasonn.getString("price");
                          String Type=jasonn.getString("type");
                          String Discraaption=jasonn.getString("description");
                          hasha.put(ID,iddd);
                          hasha.put(Name,nameee);
                          hasha.put(Prrice,address);
                          hasha.put(TYPE,Type);
                          hasha.put(DISCRAption,Discraaption);
                          array.add(hasha);
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
              ListView listy = (ListView) findViewById(R.id.ListMenuPizzaHut);
              AdapterMenu arrayAda = new AdapterMenu(MenuPizzaHut.this, R.layout.itemmenupizzahut, array);
              listy.setAdapter(arrayAda);
              mProgressDialog.dismiss();
              listy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                  @Override
                  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                      Intent intt=new Intent(MenuPizzaHut.this,com.example.hp.amitrestaruant.MenuOrder.Order.class);

                      String namee = ((TextView) view.findViewById(R.id.TextName)).getText().toString();
                      String pricee = ((TextView) view.findViewById(R.id.TextPrice)).getText().toString();
                      String typee = ((TextView) view.findViewById(R.id.TextType)).getText().toString();
                      String dicraption = ((TextView) view.findViewById(R.id.TextDiscraption)).getText().toString();
                      SharedPreferences.Editor shared=getSharedPreferences("Data",MODE_PRIVATE).edit();
                      shared.putString("NAME",namee);
                      shared.putString("PRICE",pricee);
                      shared.putString("type",typee);
                      shared.putString("DISCraptopn",dicraption);
                      shared.commit();
                      startActivity(intt);

                  }
              });



          }

      }

}


