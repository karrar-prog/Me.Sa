package com.example.domka.menhajAlsaleheen.app.Others;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.domka.menhajAlsaleheen.R;

public class AboutAppActivity extends AppCompatActivity
{
    LinearLayout li_about;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);
        li_about =(LinearLayout) findViewById(R.id.li_about);
        li_about.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });
    }
}
