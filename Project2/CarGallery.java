package com.gupta.sudhanshu.cs478.sdmpproject2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class CarGallery extends Activity {

    private ArrayList<CarsDetails> cars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_gallery);

        GridView gridCarsView = (GridView) findViewById(R.id.gridCarsView);
        TextView gridCarsTextView = (TextView) findViewById((R.id.gridCarsTextView));
        //context menu
        registerForContextMenu(gridCarsView);

        // fetch the cars and its details
        cars = CarsDetails.createList();

        // call the adapter to inflate the proper view
        CustomCarGallaryActivity customCarGallaryView = new CustomCarGallaryActivity(CarGallery.this, cars);
        gridCarsView.setAdapter(customCarGallaryView);

        gridCarsView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                CarsDetails selectedCar = cars.get(position);


                // create intent to pass the selected car's details to full image view activity
                Intent imageViewIntent = new Intent(CarGallery.this, ImageViewActivity.class);
//                imageViewIntent.putExtra("selectedCar", selectedCar.image_path);

//                Log.i("car position is ", Integer.toString(position));
//                imageViewIntent.putExtra("manufacturer", selectedCar.manufacturer.toString());
                imageViewIntent.putExtra("carPosition", position);
                imageViewIntent.putExtra("manufacturerUrl", selectedCar.manufacturer_url.toString());

                startActivity(imageViewIntent);
            }
        });

    }
    //define onCreateContextMenu to inflate the menu with the conext_menu defined in menu folder
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    //define onContextItemSelected() method to perform the action based on the item selected in the menu by the user
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        //get the information of the item user selected
        CarsDetails selectedCar = cars.get(info.position);

        //check which item user selected from context menu and start the new activity accordingly
        //for any item selected: create intent, put appropriate extra and start activity
        switch (item.getItemId()) {

            //if user selected to show full image
            case R.id.show_full_image:

                Intent imageViewIntent = new Intent(CarGallery.this, ImageViewActivity.class);
                imageViewIntent.putExtra("carPosition", info.position);
                imageViewIntent.putExtra("manufacturerUrl", selectedCar.manufacturer_url);
//
//                imageViewIntent.putExtra("selectedCar", selectedCar.image_path);
//                imageViewIntent.putExtra("manufacturer", selectedCar.manufacturer);
//                imageViewIntent.putExtra("manufacturerUrl", selectedCar.manufacturer_url);

                startActivity(imageViewIntent);

                return true;

            //if user selected to go to website of car manufacturer
            case R.id.website_link:

                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse(selectedCar.manufacturer_url.toString()));
                startActivity(browserIntent);

                return true;

            //if user selected to see list of dealers
            case R.id.dealers:
                Intent dealers_list = new Intent(CarGallery.this, ListDealers.class);
                dealers_list.putExtra("index", info.position);
                dealers_list.putExtra("company_name", selectedCar.manufacturer.toString());

                startActivity(dealers_list);

                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
