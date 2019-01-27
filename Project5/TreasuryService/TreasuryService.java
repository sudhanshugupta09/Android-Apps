package com.gupta.sudhanshu.cs478.project5_treasuryservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
//import com.gupta.sudhanshu.cs478.TreasuryCommon.TreasuryCommAidl;
import com.gupta.sudhanshu.cs478.TreasuryCommon.TreasuryCommonAidl;
//import com.gupta.sudhanshu.cs478.TreasuryCommonAIDL.TreasuryCommonAidl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.net.URL;
import java.util.Map;


/*
monthly average cash
Select avg(open_today), year, month from t1 where year=2016 and account = "Federal Reserve Account" group by month;

daily cash
year
month
day
number - LIMIT

SELECT date, open_today, day, weekday  FROM t1
where date >= "2005-06-09"
and account = "Federal Reserve Account"
LIMIT 25;


 */

public class TreasuryService extends Service {

    public static boolean SERVICE_STARTED = true;
    public static boolean SERVICE_NOT_BOUND = true;
    public static boolean SERVICE_BOUND_IDLE = false;
    public static boolean SERVICE_BOUND_RUNNING = false;
    public static boolean SERVICE_DESTROYED = false;



    static final String TAG = "TreasuryServiceTag";
    int[] listMonthlyAvgCash;

    int SOME_INT= 0;

    @Override
    public void onCreate(){
        SERVICE_STARTED = false;
        SERVICE_NOT_BOUND = true;
        SERVICE_BOUND_IDLE = false;
        SERVICE_BOUND_RUNNING = false;
        SERVICE_DESTROYED = false;

        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent,int flags , int startId){

        SERVICE_STARTED = true;
        SERVICE_NOT_BOUND = false;
        SERVICE_BOUND_IDLE = false;
        SERVICE_BOUND_RUNNING = false;
        SERVICE_DESTROYED = false;


        return START_NOT_STICKY;


    }

    @Override
    public void onDestroy() {
        SERVICE_DESTROYED = true;
        SERVICE_BOUND_RUNNING = false;
        SERVICE_BOUND_IDLE = false;
        SERVICE_NOT_BOUND = false;

        Log.v("SERVICE","Service killed");
        super.onDestroy();

    }

    private final TreasuryCommonAidl.Stub mBinder = new TreasuryCommonAidl.Stub(){

        @Override
        public int[] monthlyAvgCash(int aYear){

            SERVICE_BOUND_RUNNING = true;
            SERVICE_BOUND_IDLE = false;

            String data = "";
            HttpURLConnection httpUrlConnection = null;


            final String DB_QUERY1;
            String URL = null;
            try {
                DB_QUERY1 = URLEncoder.encode("Select avg(open_today), year, month from t1 where year=" + aYear + " and account = \"Federal Reserve Account\" group by month;", "utf-8");
                URL = "http://api.treasury.io/cc7znvq/47d80ae900e04f2/sql/?q=" + DB_QUERY1;
                httpUrlConnection = (HttpURLConnection) new URL(URL).openConnection();
                InputStream in = new BufferedInputStream(httpUrlConnection.getInputStream());

                data = readStream(in);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (null != httpUrlConnection)
                    httpUrlConnection.disconnect();
            }
            JSONArray jsonArray = null;
            try {
                jsonArray = new JSONArray(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            int[] returnListMonthyAvg = new int[12];
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject curr = null;
                try {
                    curr = jsonArray.getJSONObject(i);
                    returnListMonthyAvg[i] = Math.round(Float.parseFloat(curr.getString("avg(open_today)")));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return returnListMonthyAvg;
        }

        @Override
        public int[] dailyCash(int aYear, int aMonth, int aDay, int aNumber) throws RemoteException {
//            return null;

            SERVICE_BOUND_RUNNING = true;
            SERVICE_BOUND_IDLE = false;

            String Day= String.format("%02d", aDay);
            String Month = String.format("%02d", aMonth);
            String Year = String.format("%04d", aYear);
            String NumDays = String.valueOf(aNumber);

            String DB_QUERY2_Date = Year+"-"+Month+"-"+Day;
            String data = "";
            HttpURLConnection httpUrlConnection = null;

            try {
                String DB_QUERY = URLEncoder.encode("SELECT date, open_today, day, weekday  FROM t1 where date >= \""+DB_QUERY2_Date+"\" and account = \"Federal Reserve Account\" LIMIT "+NumDays+";", "utf-8");
                String URL = "http://api.treasury.io/cc7znvq/47d80ae900e04f2/sql/?q="+ DB_QUERY;
                Log.i("URL Final", "" + URL);
                httpUrlConnection = (HttpURLConnection) new URL(URL).openConnection();
                InputStream in = new BufferedInputStream(httpUrlConnection.getInputStream());
                data = readStream(in);

                return formatQuery2Result(data, aNumber);

            } catch (MalformedURLException exception) {
                Log.e(TAG, "MalformedURLException");
            } catch (IOException exception) {
                Log.e(TAG, "IOException");
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (null != httpUrlConnection)
                    httpUrlConnection.disconnect();
            }
                return null;
        }


    };

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer data = new StringBuffer("");
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                data.append(line);

            }
        } catch (IOException e) {
            Log.e(TAG, "IOException");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data.toString();
    }

//    @androidx.annotation.Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("","In binderr");
        SERVICE_NOT_BOUND = false;
        SERVICE_BOUND_IDLE = true;
        return mBinder;
    }



    public int[] formatQuery2Result(String data, int NumOfDays) throws JSONException {
        int[] listDailyCash = new int[NumOfDays];
        JSONArray jsonArray = null;
        jsonArray = new JSONArray(data);
        for(int i=0;i<NumOfDays;i++)
        {
            JSONObject curr = null;
            curr = jsonArray.getJSONObject(i);
            //{"date":"2016-10-03","open_today":353312,"day":3,"weekday":"Monday"}
            listDailyCash[i] = Integer.parseInt(curr.getString("open_today"));

        }
        System.out.println(listDailyCash.length);
        for(int i=0; i<NumOfDays; i++){
            System.out.println(listDailyCash[i]);
        }
        return listDailyCash;
    }



}

