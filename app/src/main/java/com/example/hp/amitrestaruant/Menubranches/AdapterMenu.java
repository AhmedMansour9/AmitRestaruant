package com.example.hp.amitrestaruant.Menubranches;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.amitrestaruant.Branches.PizzaHut;
import com.example.hp.amitrestaruant.DownloadImage.ImageLoader;
import com.example.hp.amitrestaruant.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by HP on 17/12/2016.
 */
public class AdapterMenu extends BaseAdapter {
    TextView textid;
    Activity activity;
    ArrayList<HashMap<String ,String>> array=new ArrayList<>();
    LayoutInflater inflater;
    TextView textnamee;
    TextView textprice;
    TextView texttype;
    TextView textdiscraption;
    ImageView imaage;
    HashMap<String,String> hashMap=new HashMap<String, String>();
    public AdapterMenu(Activity activity, int singleitemview, ArrayList<HashMap<String, String>> arr) {
        this.activity = activity;
        this.array = arr;

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
    public View getView(int position, View convertView, ViewGroup parent) {

        inflater=(LayoutInflater)activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.itemmenupizzahut,null);
        hashMap=array.get(position);
//        textid=(TextView)v.findViewById(R.id.Texx);
        textnamee = (TextView) v.findViewById(R.id.TextName);
        textprice=(TextView)v.findViewById(R.id.TextPrice);
        texttype=(TextView)v.findViewById(R.id.TextType);
        textdiscraption=(TextView)v.findViewById(R.id.TextDiscraption);

        textnamee.setText(hashMap.get(MenuPizzaHut.Name));
        textprice.setText(hashMap.get(MenuPizzaHut.Prrice));
        texttype.setText(hashMap.get(MenuPizzaHut.TYPE));
        textdiscraption.setText(hashMap.get(MenuPizzaHut.DISCRAption));
    return v;
    }
}
