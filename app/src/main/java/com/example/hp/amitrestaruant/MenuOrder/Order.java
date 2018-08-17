package com.example.hp.amitrestaruant.MenuOrder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.amitrestaruant.R;
import com.example.hp.amitrestaruant.Registration;
import com.example.hp.amitrestaruant.Restaurants.RestaurantActivity;
import com.example.hp.amitrestaruant.SessionManager;

/**
 * Created by HP on 18/12/2016.
 */
public class Order extends AppCompatActivity {
    TextView textname;
    TextView textpric;
    TextView texttyp;
    TextView textdiscraptio;
    Button imgorder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuorder);

        SharedPreferences prefs = getSharedPreferences("Data", MODE_PRIVATE);
        String nam=prefs.getString("NAME", null);
        String pric=prefs.getString("PRICE", null);
        String typ=prefs.getString("type", null);
        String discrap=prefs.getString("DISCraptopn", null);


        textname = (TextView)findViewById(R.id.TextNamee);
        textpric=(TextView)findViewById(R.id.TextPricee);
        texttyp=(TextView)findViewById(R.id.TextTypee);
        textdiscraptio=(TextView)findViewById(R.id.TextDiscraptionn);
     imgorder=(Button)findViewById(R.id.Btnorder);
        textname.setText(nam);
        textpric.setText(pric);
        texttyp.setText(typ);
        textdiscraptio.setText(discrap);
        imgorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManager sison=new SessionManager(Order.this);
                if(sison.isLoggedIn()){
                    Toast.makeText(getBaseContext(),"Yoy Confirm Order true",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getBaseContext(),"False you should Make Registration first",Toast.LENGTH_LONG).show();
                    Intent innt=new Intent(Order.this, Registration.class);
                    startActivity(innt);
                }

            }
        });
    }
}
