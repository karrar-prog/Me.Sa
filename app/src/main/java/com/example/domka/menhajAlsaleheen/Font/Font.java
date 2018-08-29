package com.example.domka.menhajAlsaleheen.Font;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.domka.menhajAlsaleheen.app.Controller.MainController.Start_Activity;

/**
 * Created by domka on 2017-04-05.
 */
public class  Font extends Start_Activity
{
    private  void set_font(Context context, View view, int numberOfFont)
    {
        int n = 3;
        AssetManager mngr = context.getAssets();
        if (!(numberOfFont >= 1 && numberOfFont <= n))
        {
            return;
        }
        Typeface tf1;
        tf1 = Typeface.createFromAsset(mngr, "f" + numberOfFont + ".ttf");
        if (view instanceof EditText)
        {
            ((EditText) view).setTypeface(tf1);
        }
        else if (view instanceof TextView)
        {
            ((TextView) view).setTypeface(tf1);
        }
        else if (view instanceof Button)
        {
            ((Button) view).setTypeface(tf1);
        }
    }

    private  void set_font_size(Context content, View view, int sizeOfFont)
    {


        if (!(sizeOfFont >= 8 && sizeOfFont <= 50))
        {
            return;
        }

        if (view instanceof EditText)
        {
            ((EditText) view).setTextSize(sizeOfFont);
        }
        else if (view instanceof TextView)
        {
            ((TextView) view).setTextSize(sizeOfFont+3);
        }
        else if (view instanceof Button)
        {
            ((Button) view).setTextSize(sizeOfFont);
        } else if (view instanceof RadioButton)
        {
            ((RadioButton) view).setTextSize(sizeOfFont);
        }
    }
    public void set_font(Context context,Integer fontID,View...views)
    {
        for(View view : views)
            set_font(context, view, fontID);
    }

    public void set_font_size(Context context,Integer size_of_font,View...views)
    {
        for(View view : views)
            set_font_size(context, view, size_of_font);
    }



}
