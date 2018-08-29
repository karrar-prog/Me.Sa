package com.example.domka.menhajAlsaleheen.myDialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;

/**
 * Created by domka on 2017-04-11.
 */

public  class MyDialog {
    public static AlertDialog alert;



    public static void  show_message(String message , Context context, int milsec)
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                    }
                });
        alert = builder.create();
        alert.show();

        Runnable myScreen = new Runnable() {
            @Override
            public void run() {
                closeDialog();
            }
        };
        Handler myHandler = new Handler();
        myHandler.postDelayed(myScreen,milsec);
    }
    private static void closeDialog() {
        alert.hide();
    }
}
