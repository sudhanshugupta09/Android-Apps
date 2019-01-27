package com.gupta.sudhanshu.cs478.project3_sdmp_a1;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String ATTRACTIONS_INTENT = "edu.uic.cs478.f18.project3.AttractionsBroadCast";
    private static final String ATTRACTION_NAME = "ATTRACTION_OPTION";
    private static final int reqCodeNY = 1;
    private static final int reqCodeSF = 0;
    private static final String SUDHANSHU_PERMISSION ="edu.uic.cs478.f18.project3" ;

    private int attrOption = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSF = (Button) findViewById(R.id.btnSfAttraction);
        Button btnNY = (Button) findViewById(R.id.btnNyAttraction);

        // On click SanFrancisco Button
        btnSF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attrOption = 0;
                // check for permissions
                checkPermissionSF();
            }
        });

        // On click SanFrancisco Button
        btnNY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attrOption = 1;
                // check for permissions
                checkPermissionNY();
            }
        });
    }

    public void checkPermissionSF(){
        if (ContextCompat.checkSelfPermission(this,SUDHANSHU_PERMISSION)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{SUDHANSHU_PERMISSION},reqCodeSF);
        }
        else
        {
            callBroadcast();
        }
    }
    public void checkPermissionNY(){
        if (ContextCompat.checkSelfPermission(this,SUDHANSHU_PERMISSION)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{SUDHANSHU_PERMISSION},reqCodeNY);
        }
        else
        {
            callBroadcast();
        }
    }
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case reqCodeNY: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callBroadcast();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.perm_ng, Toast.LENGTH_LONG).show();
                }
                return;
            }
            case reqCodeSF: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callBroadcast();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.perm_ng, Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }
    public void callBroadcast(){
        Intent intent = new Intent(ATTRACTIONS_INTENT);
        intent.putExtra(ATTRACTION_NAME,attrOption);
        sendOrderedBroadcast(intent, null);
    }

}
