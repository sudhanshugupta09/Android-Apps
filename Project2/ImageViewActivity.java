package com.gupta.sudhanshu.cs478.sdmpproject2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;

public class ImageViewActivity extends Activity {

//    Images from res folder
    ArrayList<Integer> carImagesHighRes = new ArrayList<Integer>(
            Arrays.asList(R.drawable.audi2, R.drawable.bmw2,
                    R.drawable.ford2, R.drawable.lamborghini2, R.drawable.maserati2,
                    R.drawable.rangerover2, R.drawable.mustang2, R.drawable.mercedes2,
                    R.drawable.toyota2));

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //set the layout for the activity
        setContentView(R.layout.full_image_view);

        // get the corresponding position for the high res image to be displayed on the screen
        final Integer pos = this.getIntent().getExtras().getInt("carPosition");
        final String manufacturer_url = this.getIntent().getExtras().getString("manufacturerUrl");
        final String manufacturer= this.getIntent().getExtras().getString("manufacturer");

        setTitle(manufacturer);

        //find the image view and set the image to be shown
        ImageView imageContainer = (ImageView) findViewById(R.id.imageFullView);
        imageContainer.setImageResource(carImagesHighRes.get(pos));




        //add listener for click
        imageContainer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                // create intent, put url in extra and start activity
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse(manufacturer_url));
                startActivity(browserIntent);

            }
        });



    }
}
