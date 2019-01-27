package com.gupta.sudhanshu.cs478.project5_treasuryclient;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {
    ListView lv;
    int[] resultMonthly;
    int[] resultDaily;
    ArrayList list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        list=new ArrayList<String>();
        lv=(ListView)findViewById(R.id.listV);
        lv = (ListView) findViewById(R.id.listV);
        int whichAPIResult = this.getIntent().getExtras().getInt("APIResult");

        if(whichAPIResult == 0){
            // monthly display
            resultMonthly = this.getIntent().getIntArrayExtra("MonthAvg");
            for(int i=0; i<resultMonthly.length; i++){
                list.add("Month "+ (i+1) + ": "+Integer.toString(resultMonthly[i]));
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list );
            lv.setAdapter(arrayAdapter);
    }

        else if(whichAPIResult == 1){
            // daily display
            resultDaily = this.getIntent().getIntArrayExtra("DailyAvg");
            Log.i("2dn API Result List View",""+resultDaily[0]);
            for(int j=0; j<resultDaily.length; j++){
                list.add("Day "+ (j+1) + ": "+Integer.toString(resultDaily[j]));
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list );
            lv.setAdapter(arrayAdapter);
        }
        else{}
    }
//    @Override
//    public void boolean onBackPressed(){
//
//    }
}
