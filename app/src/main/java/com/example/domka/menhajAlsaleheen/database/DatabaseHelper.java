package com.example.domka.menhajAlsaleheen.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.compat.BuildConfig;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by domka on 2017-04-10.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context mycontext;
    private static String DB_NAME = "db_project.db";
    private static String DB_PATH ="/data/data/"+ BuildConfig.APPLICATION_ID+"/databases/";
    public SQLiteDatabase myDataBase;

    public DatabaseHelper(Context context) throws IOException {
        super(context,DB_NAME,null,1);
        this.mycontext=context;
        boolean dbexist = checkdatabase();
        if (dbexist) {
            System.out.println("Database exists");
            opendatabase();
        } else {
            System.out.println("Database doesn't exist");
            createdatabase();
        }
    }

    public void createdatabase() throws IOException {
        boolean dbexist = checkdatabase();
        if(dbexist) {
            System.out.println(" Database exists.");
        } else {
            this.getReadableDatabase();
            try {
                copydatabase();
            } catch(IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkdatabase() {

        boolean checkdb = false;
        try {
            String myPath = DB_PATH + DB_NAME;
            File dbfile = new File(myPath);
            checkdb = dbfile.exists();

        } catch(SQLiteException e) {
            System.out.println("Database doesn't exist");
        }
        return checkdb;
    }

    private void copydatabase() throws IOException {
        //Open your local db as the input stream
        InputStream myinput = mycontext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        if (!mycontext.getDir("databases" , MODE_PRIVATE).exists())
            if (!mycontext.getDir("databases" , MODE_PRIVATE).mkdir())
                return;

        File file = new File(mycontext.getDir("databases" , MODE_PRIVATE) , "db_project.db");
//        String outfilename = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        //error here
        OutputStream myoutput = new FileOutputStream(file);

        // transfer byte to inputfile to outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myinput.read(buffer))>0) {
            myoutput.write(buffer,0,length);
        }

        //Close the streams
        myoutput.flush();
        myoutput.close();
        myinput.close();
    }

    public void opendatabase() throws SQLException {
        //Open the database
        SQLiteDatabase sqLiteDatabase = SQLiteDatabase.openDatabase(mycontext.getDir("databases" , MODE_PRIVATE).getPath() , null , SQLiteDatabase.OPEN_READWRITE);

        if (new File(mycontext.getDir("databases" , MODE_PRIVATE) , "db_project.db").exists())
            System.out.println("ddodododod");

        myDataBase = SQLiteDatabase.openDatabase(new File(mycontext.getDir("databases" , MODE_PRIVATE) , "db_project.db").getPath() , null , SQLiteDatabase.OPEN_READWRITE);

    }

    public synchronized void close() {
        if(myDataBase != null) {
            myDataBase.close();
        }
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}