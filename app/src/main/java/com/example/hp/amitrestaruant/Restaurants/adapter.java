package com.example.hp.amitrestaruant.Restaurants;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.amitrestaruant.DownloadImage.ImageLoader;
import com.example.hp.amitrestaruant.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by HP on 03/11/2016.
 */
public class adapter extends BaseAdapter {
     Activity activity;

    ArrayList<HashMap<String ,String>> arr=new ArrayList<>();
    LayoutInflater inflater;
     TextView TextRestaurant;
    TextView TEXtId;
    ImageView imaaaage;
    ImageLoader imageLoader;
    HashMap<String,String> hashMap=new HashMap<String, String>();
    public adapter(Activity activity, int singleitemview, ArrayList<HashMap<String, String>> arr){
        this.activity =activity;
         this.arr=arr;
//        this.arrayList=arr;
        this.imageLoader=new ImageLoader(activity);

}
    @Override
    public int getCount() {
        return arr.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {

//        TextView rank;
//        TextView country;
//        TextView population;
//        ImageView flag;
        inflater=(LayoutInflater)activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.itemrestaurant,null);
         hashMap=arr.get(position);

        TextRestaurant = (TextView) v.findViewById(R.id.textRestaurant);

        imaaaage=(ImageView)v.findViewById(R.id.Imagerestau);
//        imageLoader.DisplayImage(hashMap.get(MainActivity.FLAG),flag);
        TextRestaurant.setText(hashMap.get(RestaurantActivity.TexTT));
        TEXtId=(TextView)v.findViewById(R.id.textid);
        TEXtId.setText(hashMap.get(RestaurantActivity.IDDD));
        imageLoader.DisplayImage(hashMap.get(RestaurantActivity.IMAGEE),imaaaage);


        return v;
    }
}
