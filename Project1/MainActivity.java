package com.gupta.sudhanshu.cs478.sdmp_project1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {


    private static final String TAG = "MainActivity_Log tag";     // Log info Tag
    private boolean toastFlag = false;                            // flag to raise toast message

    // Declare the UI Elements in the activity
    protected Button nameActivityButton;                    // button to open nameActivity
    protected Button contactActivityButton;                 // button to open contactActivity

    // Declare fields to handle the names
    protected String firstName = "";
    protected String lastName = "";
    protected String entered_name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Mandatory step
        super.onCreate(savedInstanceState);

        // Connect to the layout
        setContentView(R.layout.activity_main);


        // Bind the layout elements to the corresponding fields
        nameActivityButton = (Button) findViewById(R.id.nameActivityButton);
        contactActivityButton = (Button) findViewById(R.id.contactActivityButton);

        // Setting up listeners for buttons
        nameActivityButton.setOnClickListener(nameActivityListener);
        contactActivityButton.setOnClickListener(contactActivityListener);

    }

    public View.OnClickListener nameActivityListener = new View.OnClickListener() {

        // Called when nameActivity button is pressed
        @Override
        public void onClick(View v){

            // call EnterNameActivity
            openEnterNameActivity();

        }
    };

    public View.OnClickListener contactActivityListener = new View.OnClickListener() {

        // Called when nameActivity button is pressed
//        @Override
        public void onClick(View v){

            // display toast message if name was invalid and exit
            if(toastFlag){
                Toast.makeText(getApplicationContext(), "Entered name  " + entered_name +  " is invalid!!", Toast.LENGTH_LONG).show();
                return;
            }

            // call ContactEditActivity passing the values of first and last names
            try {
                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                intent.putExtra(ContactsContract.Intents.Insert.NAME, firstName + ' ' + lastName);
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private void openEnterNameActivity(){

        Intent nameIntent = new Intent(this, EnterNameActivity.class);
        startActivityForResult(nameIntent, 1);
    }

    protected void onActivityResult(int code, int result_code, Intent data){

        // Update local variables for names to be passed in the second activity
        // update toastFlag to not raise a toast message
        if(result_code == RESULT_OK){
            firstName = data.getStringExtra("FIRST_NAME");
            lastName = data.getStringExtra("LAST_NAME");
            toastFlag = false;
        }

        else if(result_code == RESULT_CANCELED){
            entered_name = data.getStringExtra("ENTERED_NAME");
            toastFlag = true;

        }
        Log.i(TAG, "returned result from enter name activity is " + result_code);
    }


}
