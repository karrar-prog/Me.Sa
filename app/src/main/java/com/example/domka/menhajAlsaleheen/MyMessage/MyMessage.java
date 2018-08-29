package com.example.domka.menhajAlsaleheen.MyMessage;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;

/**
 * Created by karrar hassany on 2017-02-09.
 */
public class MyMessage
{
    public static AlertDialog alert;



    public static void  ShowMessage( Context context,String message , int milsec)
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
