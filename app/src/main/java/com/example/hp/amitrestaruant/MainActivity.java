package com.example.hp.amitrestaruant;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.hp.amitrestaruant.Restaurants.RestaurantActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo actiNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (actiNetworkInfo != null) {
            setContentView(R.layout.activity_main);


            ImageButton Imgregistration = (ImageButton) findViewById(R.id.imgRegistration);
            Imgregistration.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent inty = new Intent(MainActivity.this, Registration.class);
                    startActivity(inty);
                }
            });

             ImageButton img=(ImageButton)findViewById(R.id.men);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intt = new Intent(MainActivity.this, RestaurantActivity.class);
                    startActivity(intt);
                }
            });


        } else {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

            // Setting Dialog Title
            alertDialog.setTitle("Sorry");

            // Setting Dialog Message
            alertDialog.setMessage("You Should open Network First");

            // On pressing Settings button


            alertDialog.show();

        }
    }



}
