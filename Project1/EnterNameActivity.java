package com.gupta.sudhanshu.cs478.sdmp_project1;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class EnterNameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Load the previous instance if any
        super.onCreate(savedInstanceState);

        // Bind to the layout file
        setContentView(R.layout.activity_enter_name);

        //Binding to the UI Element
        final EditText nameField = (EditText) findViewById(R.id.nameField);

        // Listener to capture changes in EditText Field
        nameField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                // Extract names from text field if soft return/enter key is pressed
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    String names[] = nameField.getText().toString().trim().replaceAll("\\s+"," ").split(" ");

                    if(names.length == 2) {

                        if (nameValidCheck(names[0]) && nameValidCheck(names[1])) {
                            Intent i = new Intent();

                            i.putExtra("FIRST_NAME", names[0]);
                            i.putExtra("LAST_NAME", names[1]);
                            Log.i("Name_activity_onEditorAction", "result_ok being set");
                            setResult(RESULT_OK, i);
                            finish();
                        }
                    }


                    // return failed status if inout checks fail EnterNameActivity.
                    String incorrect_name = nameField.getText().toString();

                    Intent i = new Intent();
                    i.putExtra("ENTERED_NAME", incorrect_name);
//                    i.putExtra("LAST_NAME", names[1]);
                    Log.i("Name_activity_onEditorAction", "result_canceled being set");
                    setResult(RESULT_CANCELED, i);
                    finish();
                }

                return false;
            }
        });
    }

    // function to check name validation
    protected boolean nameValidCheck(String name){
        return name.matches( "[a-zA-Z]*" );
    }
}
