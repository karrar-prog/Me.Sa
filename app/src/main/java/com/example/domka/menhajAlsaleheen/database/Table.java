package com.example.domka.menhajAlsaleheen.database;

import android.database.Cursor;

/**
 * Created by domka on 2017-04-10.
 */

public class Table
{

    private Object[][] tbl;
    private String[] ColumnNames;

    public Integer ROWS;
    public Integer COLUMNS;

    public Table(Cursor cursor)
    {
        if(cursor==null)
        {
            tbl = new Object[0][0];
            ROWS = 0;
            COLUMNS = 0;
            return;
        }

        ROWS = cursor.getCount();
        COLUMNS = cursor.getColumnCount();

        tbl = new Object[ROWS][COLUMNS];
        ColumnNames = cursor.getColumnNames();
        int columns = cursor.getColumnCount();
        int currentRow = 0;
        cursor.moveToFirst();

        while (cursor.moveToPosition(currentRow))
        {
            for (int i=0;i<columns;i++)
            {
                switch (cursor.getType(i))
                {
                    case Cursor.FIELD_TYPE_STRING:
                        tbl[currentRow][i] = cursor.getString(i);
                        break;
                    case Cursor.FIELD_TYPE_INTEGER:
                        tbl[currentRow][i] = cursor.getInt(i);
                        break;
                    case Cursor.FIELD_TYPE_FLOAT:
                        tbl[currentRow][i] = cursor.getDouble(i);
                        break;
                    case Cursor.FIELD_TYPE_NULL:
                        tbl[currentRow][i] = null;
                        break;
                    default:
                        tbl[currentRow][i] = cursor.getString(i);
                        break;
                }
            }
            currentRow++;
        }
        cursor.close();
    }



    public String get(int row , String column)
    {
        return tbl[row][getColumnIndex(column)].toString();
    }
    private int getColumnIndex(String columnName)
    {
        for (int i = 0; i < ColumnNames.length; i++)
            if(ColumnNames[i].equals(columnName))
                return i;
        return -1;
    }

}
