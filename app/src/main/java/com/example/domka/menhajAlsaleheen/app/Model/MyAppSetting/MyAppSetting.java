package com.example.domka.menhajAlsaleheen.app.Model.MyAppSetting;

import android.content.Context;

import com.example.domka.menhajAlsaleheen.database.DatabaseAdapter;
import com.example.domka.menhajAlsaleheen.database.Table;

/**
 * Created by domka on 2017-05-17.
 */
public class MyAppSetting
{
    private String night_read;
    private Integer font_size;
    private Integer font_no;
    private Context context;
    private String text_format;

    public MyAppSetting(Context context)
    {
        Table table;
        String sql = "SELECT * FROM setting where ID = 1";
        DatabaseAdapter db = new DatabaseAdapter(context);
        db.open();
        table = db.select(sql);
        db.close();
        //--------------------------------------------
        this.text_format = String.valueOf(table.get(0, "text_format"));
        this.night_read = String.valueOf(table.get(0, "night_read"));;
        this.font_size = Integer.valueOf(String.valueOf(table.get(0, "font_size")));
        this.font_no = Integer.valueOf(String.valueOf(table.get(0, "font_no")));
        this.context = context;
    }


    public String getText_format()
    {

        return this.text_format;
    }

    public void setText_format(String text_format)
    {
        String sql = "UPDATE setting SET text_format ='" + text_format + "' where ID = 1";
        DatabaseAdapter db = new DatabaseAdapter(context);
        db.open();
        db.execute(sql);
        db.close();
        this.text_format = text_format;
    }

    public String getNight_read()
    {

        return this.night_read;
    }

    public void setNight_read(String night_read)
    {
        String sql = "UPDATE setting SET night_read ='" + night_read + "' where ID = 1";
        DatabaseAdapter db = new DatabaseAdapter(context);
        db.open();
        db.execute(sql);
        db.close();
        this.night_read = night_read;
    }

    public Integer getFont_size()
    {

        return this.font_size;
    }

    public void setFont_size(Integer font_size)
    {
        String sql = "UPDATE setting SET font_size =" + font_size + " where ID = 1";
        DatabaseAdapter db = new DatabaseAdapter(context);
        db.open();
        db.execute(sql);
        db.close();
        this.font_size = font_size;
    }
    public void setFont_size_increment()
    {
        String sql = "UPDATE setting SET font_size = font_size + 1 where ID = 1";
        DatabaseAdapter db = new DatabaseAdapter(context);
        db.open();
        db.execute(sql);
        db.close();

    }
    public void setFont_size_decrement()
    {
        String sql = "UPDATE setting SET font_size = font_size - 1 where ID = 1";
        DatabaseAdapter db = new DatabaseAdapter(context);
        db.open();
        db.execute(sql);
        db.close();

    }

    public Integer getFont_no()
    {

        return this.font_no;
    }

    public void setFont_no(Integer font_no)
    {
        String sql = "UPDATE setting SET font_no =" + font_no + " where ID = 1";
        DatabaseAdapter db = new DatabaseAdapter(context);
        db.open();
        db.execute(sql);
        db.close();
        this.font_no = font_no;
    }
}
