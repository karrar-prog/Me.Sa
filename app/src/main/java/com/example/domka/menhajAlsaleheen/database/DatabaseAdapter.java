package com.example.domka.menhajAlsaleheen.database;

/**
 * Created by domka on 2017-04-10.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Ali on 2015-11-13.
 */
public class DatabaseAdapter {

    private SQLiteDatabase db = null;
    private Context context;
    private String databaseName;
    public static final String db_location = "/data/data/com.example.domka.almenhaj/databases/";

    public static boolean isDatabaseExist(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }

    public DatabaseAdapter(Context context) {
        this.context = context;
        this.databaseName = "db_project.db";
    }

    public void open() {


        db = SQLiteDatabase.openDatabase(new File(context.getDir("databases" , MODE_PRIVATE) , "db_project.db").getPath() , null , SQLiteDatabase.OPEN_READWRITE);
        System.out.println("Database Path : " + db.getPath() + " , " + db.isOpen());
    }

    public void beginTransaction() {
        db.beginTransaction();
    }

    public void commitTransaction() {
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void rollbackTransaction() {
        db.endTransaction();
    }

    public void execute(String SQL) {
        db.execSQL(SQL);
    }

    public void execute(String SQL, Object[] parameters) {
        db.execSQL(SQL, parameters);
    }

    public Table select(String SQL) {
        Cursor cursor = db.rawQuery(SQL, null);
        return new Table(cursor);
    }

    public Table select(String SQL, String[] parameters) {
        Cursor cursor = db.rawQuery(SQL, parameters);
        return new Table(cursor);
    }

    public void close() {
        db.close();
    }

}
