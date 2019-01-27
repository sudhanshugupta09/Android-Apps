package com.gupta.sudhanshu.cs478.project3_sdmp_a2;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String INTENT = "edu.uic.cs478.f18.project3.AttractionsBroadCast";
    private static final int reqCode = 1;
    private static final String SUDHANSHU_PERMISSION ="edu.uic.cs478.f18.project3" ;
    private int action  = -1;
    private static final String ATTRACTION_NAME = "ATTRACTION_OPTION";

    private Button mButton ;
    private Receiver1 mSFReceiver ;
    private Receiver2 mNYReceiver ;
//    private MyReceiver3 mReceiver3 ;
    private IntentFilter mFilter1 ;
    private IntentFilter mFilter2 ;
//    private IntentFilter mFilter3 ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        IntentFilter ifil = new IntentFilter(getIntent(), getIntent().getIntExtra("NAME",code));

        mSFReceiver = new Receiver1();
        mNYReceiver = new Receiver2();

        mFilter1 = new IntentFilter(INTENT) ;
        mFilter1.setPriority(10);

        mFilter2 = new IntentFilter(INTENT) ;
        mFilter2.setPriority(10);

        registerReceiver(mSFReceiver, mFilter1) ;
        registerReceiver(mNYReceiver, mFilter2) ;

        Button btnChkPermission = (Button) findViewById(R.id.btnCheckPermission);

        btnChkPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                option = 0;
                if (ContextCompat.checkSelfPermission(getApplicationContext(),SUDHANSHU_PERMISSION)!= PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{SUDHANSHU_PERMISSION},reqCode);
                }else{
                    Toast.makeText(getApplicationContext(), "Already has permissions", Toast.LENGTH_LONG).show();
                }
            }
        });


    }




}
