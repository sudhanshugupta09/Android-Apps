package com.gupta.sudhanshu.cs478.project5_treasuryservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class StatusActivity extends AppCompatActivity {


    TextView status1,status2,status3,status4, status5;

    TreasuryService TreasuryServ = new TreasuryService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        status1 = (TextView) findViewById(R.id.status1);
        status2 = (TextView) findViewById(R.id.status2);
        status3 = (TextView) findViewById(R.id.status3);
        status4 = (TextView) findViewById(R.id.status4);
        status5 = (TextView) findViewById(R.id.status5);

    }


    @Override
    public void onResume(){

        if(TreasuryService.SERVICE_STARTED){

            status1.setBackgroundResource(R.color.colorWhite);
            status2.setBackgroundResource(R.color.colorWhite);
            status3.setBackgroundResource(R.color.colorWhite);
            status4.setBackgroundResource(R.color.colorWhite);
            status5.setBackgroundResource(R.color.colorAccent);

        }

        if(TreasuryService.SERVICE_NOT_BOUND){

            status5.setBackgroundResource(R.color.colorWhite);
            status2.setBackgroundResource(R.color.colorWhite);
            status3.setBackgroundResource(R.color.colorWhite);
            status4.setBackgroundResource(R.color.colorWhite);
            status1.setBackgroundResource(R.color.colorAccent);

        }
        if(TreasuryService.SERVICE_BOUND_IDLE){
            status1.setBackgroundResource(R.color.colorWhite);
            status5.setBackgroundResource(R.color.colorWhite);
            status3.setBackgroundResource(R.color.colorWhite);
            status4.setBackgroundResource(R.color.colorWhite);
            status2.setBackgroundResource(R.color.colorAccent);



        }
        if(TreasuryService.SERVICE_BOUND_RUNNING){
            status1.setBackgroundResource(R.color.colorWhite);
            status2.setBackgroundResource(R.color.colorWhite);
            status5.setBackgroundResource(R.color.colorWhite);
            status4.setBackgroundResource(R.color.colorWhite);
            status3.setBackgroundResource(R.color.colorAccent);


        }

        if(TreasuryService.SERVICE_DESTROYED){
            status1.setBackgroundResource(R.color.colorWhite);
            status2.setBackgroundResource(R.color.colorWhite);
            status3.setBackgroundResource(R.color.colorWhite);
            status5.setBackgroundResource(R.color.colorWhite);
            status4.setBackgroundResource(R.color.colorAccent);

        }

        super.onResume();

    }

}
