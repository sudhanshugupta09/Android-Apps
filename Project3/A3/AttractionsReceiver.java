package com.gupta.sudhanshu.cs478.project3_sdmp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;

public class AttractionsReceiver extends BroadcastReceiver {
    private int action = -1;
//    private static final String SUDHANSHU_PERMISSION ="edu.uic.cs478.f18.project3" ;
    private String ATTRACTIONS_NAME = "ATTRACTION_OPTION";
//    private int action  = -1;



//    private static final String ATTRACTIONS_NAME = "ATTRACTION_OPTION";
    @Override
    public void onReceive(Context context, Intent intent) {

//        Log.i("rec", "receiver active");
        action = intent.getIntExtra(ATTRACTIONS_NAME,-1);
//        int permCheck = context.checkCallingOrSelfPermission(SUDHANSHU_PERMISSION);

//        if (PackageManager.PERMISSION_GRANTED == permCheck) {

            switch (action) {
                case 0:
                    Toast.makeText(context, "Broadcast received in A3", Toast.LENGTH_LONG).show();
                    context.startActivity(new Intent(context, AttractionActivity.class));
                    break;
                case 1:
                    Toast.makeText(context, "San Francisco Activity is under Construction", Toast.LENGTH_LONG).show();
//                context.startActivity(new Intent(context,RestActivity.class));
                    break;
                default:
                    Toast.makeText(context, "No data Available", Toast.LENGTH_LONG).show();

            }
//        }else{
//            Toast.makeText(context, "Dont have permissions", Toast.LENGTH_LONG).show();
        }
    }
//}
