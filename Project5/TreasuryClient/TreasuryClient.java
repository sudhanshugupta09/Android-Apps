package com.gupta.sudhanshu.cs478.project5_treasuryclient;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gupta.sudhanshu.cs478.TreasuryCommon.TreasuryCommonAidl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreasuryClient extends AppCompatActivity {

    private TreasuryCommonAidl mTreasuryInterface;
    private boolean mIsBound = false;

    int[] resultMonthlyAvgCash;
    int[] resultDailyAvgCash;

    int apiResult1 = 0;
    int apiResult2 = 1;
    Thread forAPIThread;



    public static final int API_MONTHLY_CASH= 0;
    public static final int API_DAILY_CASH= 1;




    Handler handler;


    int apiSelected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treasury_client);


        Button btn_unbindService = (Button) findViewById(R.id.btnUnBindService);
        Button btn_bindService = (Button) findViewById(R.id.btnStartService);

        final EditText etYear = (EditText) findViewById(R.id.etYear);
        final EditText etMonth = (EditText) findViewById(R.id.etMonth);
        final EditText etDate = (EditText) findViewById(R.id.etDate);
        final EditText etNumberofDays = (EditText) findViewById(R.id.etInteger);

        Spinner spinnerAPISelection = (Spinner) findViewById(R.id.apiSelection);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.APIOptions, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAPISelection.setAdapter(adapter);
        spinnerAPISelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 1){
                    // pass the vale to avgMonthlyCash
                    apiSelected = 0;
                    etYear.setVisibility(View.VISIBLE);


                }
                else if(position == 2){
                    apiSelected = 1;
                    etYear.setVisibility(View.VISIBLE);
                    etMonth.setVisibility(View.VISIBLE);
                    etDate.setVisibility(View.VISIBLE);
                    etNumberofDays.setVisibility(View.VISIBLE);
                }
                else{
                    Toast.makeText(parent.getContext(), "Choose a valid input", Toast.LENGTH_LONG);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do Nothing

            }
        });

        btn_bindService.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(mIsBound){
                    if(apiSelected == 0) {
                        Log.i("Bind Button Click", "api selected 0");
                        Message message = handler.obtainMessage(API_MONTHLY_CASH);
                        handler.sendMessage(message);
                        Log.i("Bind Button Click", "Message Sent");
                    }
                    else if(apiSelected ==1){
                        Log.i("Bind Button Click", "api selected 1");
                        Message message = handler.obtainMessage(API_DAILY_CASH);
                        handler.sendMessage(message);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "wrong selection", Toast.LENGTH_LONG);
                    }

                }

            }


        });

        btn_unbindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mIsBound) {
                    unbindService(mConnection);
                    mIsBound = false;
                    Toast.makeText(TreasuryClient.this, "SERVICE STOPPED!!",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(TreasuryClient.this, "START THE SERVICE FIRST!!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        forAPIThread = new Thread(new Runnable() {
            @SuppressLint("HandlerLeak")
            @Override
            public void run() {
                Looper.prepare();


                handler = new Handler(){

                    @Override
                    public void handleMessage(Message msg){

                        switch(msg.what){

                            case API_MONTHLY_CASH:

                                int year = Integer.parseInt(etYear.getText().toString());
                                try {
                                    resultMonthlyAvgCash = mTreasuryInterface.monthlyAvgCash(year);
                                    Intent i = new Intent(TreasuryClient.this, ListViewActivity.class);
                                    i.putExtra("MonthAvg",resultMonthlyAvgCash);
                                    i.putExtra("APIResult", apiResult1);
                                    startActivity(i);
                                } catch (RemoteException e) {
                                    e.printStackTrace();
                                }
                                break;

                            case API_DAILY_CASH:
                                int year2 = Integer.parseInt(etYear.getText().toString());
                                int month = Integer.parseInt(etMonth.getText().toString());
                                int date  = Integer.parseInt(etDate.getText().toString());
                                int numberOfDays = Integer.parseInt(etNumberofDays.getText().toString());

                                try {
                                    resultDailyAvgCash = mTreasuryInterface.dailyCash(year2, month, date, numberOfDays);
                                    Intent i = new Intent(TreasuryClient.this, ListViewActivity.class);
                                    //https://stackoverflow.com/questions/7578236/how-to-send-hashmap-value-to-another-activity-using-an-intent
                                    i.putExtra("DailyAvg", resultDailyAvgCash);
                                    i.putExtra("APIResult", apiResult2);
                                    startActivity(i);
                                } catch (RemoteException e) {
                                    e.printStackTrace();
                                }
                                break;

                            default:
                                break;
                        }
                    }
                };
                Looper.loop();
            }
        });
        forAPIThread.start();


    }

//    @Override
//    protected void onStart() {
//        Intent i = new Intent(TreasuryCommonAidl.class.getName());
//        ResolveInfo info = getPackageManager().resolveService(i, 0);
//        i.setComponent(new ComponentName(info.serviceInfo.packageName, info.serviceInfo.name));
////        Intent intent = new Intent(this, TreasuryCommonAidl.class);
//        startService(i);
//        super.onStart();
//    }

    @Override
    protected void onResume() {
        super.onResume();
        Context mcontext;

        if (!mIsBound) {

            boolean b = false;
            Intent i = new Intent(TreasuryCommonAidl.class.getName());
            Log.i("Treasury Class",""+TreasuryCommonAidl.class.getName());
            ResolveInfo info = getPackageManager().resolveService(i, 0);
            i.setComponent(new ComponentName(info.serviceInfo.packageName, info.serviceInfo.name));
            b = bindService(i, this.mConnection, Context.BIND_AUTO_CREATE);
            if (b) {
                Log.i("", "Ugo says bindService() succeeded!");
            } else {
                Log.i("", "Ugo says bindService() failed!");
            }

        }
    }

    // Unbind from KeyGenerator Service
    @Override
    protected void onPause() {

        super.onPause();
    }

    @Override
    protected void onStop() {
        if (mIsBound) {
            unbindService(this.mConnection);
        }

        super.onStop();
    }

    private final ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder iservice) {
            mTreasuryInterface = TreasuryCommonAidl.Stub.asInterface(iservice);
            mIsBound = true;
        }

        public void onServiceDisconnected(ComponentName className) {
            mTreasuryInterface = null;
            mIsBound = false;
        }
    };

}
