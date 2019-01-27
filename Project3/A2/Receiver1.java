package com.gupta.sudhanshu.cs478.project3_sdmp_a2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

public class Receiver1 extends BroadcastReceiver {
    private int action = -1;
    private static final String SUDHANSHU_PERMISSION ="edu.uic.cs478.f18.project3" ;
    private String ATTRACTIONS_NAME = "ATTRACTION_OPTION";
    @Override
    public void onReceive(Context context, Intent intent) {
        action = intent.getIntExtra(ATTRACTIONS_NAME,-1);
        int permCheck = context.checkCallingOrSelfPermission(SUDHANSHU_PERMISSION);
        Log.i("This Receiver1", "this" + permCheck);

        if((action == 0) && (PackageManager.PERMISSION_GRANTED == permCheck)){
            Toast.makeText(context, "Attractions of San Francisco coming up!! ",
                    Toast.LENGTH_LONG).show() ;
            Log.i("BroadcastReceiver 1", "First receiver in action!!!") ;

        }else if (action == 0){
            Toast.makeText(context, "Caller Permissions Not present", Toast.LENGTH_LONG).show();
        }




    }
}
