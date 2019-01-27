package com.gupta.sudhanshu.cs478.sdmpproject2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ListDealers extends Activity {

    private ListView dealersListView;
    private ArrayList<CarsDetails> cars;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dealers_list);

        // get the selected car's position
        Integer position = this.getIntent().getExtras().getInt("index");

        // get the List View description from the layout file
        dealersListView = (ListView) findViewById(R.id.listview_dealers);

        // create Car object to det details for the selected car
        cars = CarsDetails.createList();

        // pass the position of the selected car to the adaper to inflate proper view
        DealersViewAdapter dealerAdapter = new DealersViewAdapter(this, cars.get(position).dealers);
        dealersListView.setAdapter(dealerAdapter);
    }
}
