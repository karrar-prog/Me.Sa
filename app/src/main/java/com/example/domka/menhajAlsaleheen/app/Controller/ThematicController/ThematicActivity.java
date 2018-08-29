package com.example.domka.menhajAlsaleheen.app.Controller.ThematicController;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.domka.menhajAlsaleheen.R;
import com.example.domka.menhajAlsaleheen.app.Controller.MainController.Start_Activity;

public class ThematicActivity extends AppCompatActivity
{ Button btn_bak;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thematic);
        My_refrence();
        My_ClickListner();
    }

    private void My_ClickListner()
    {
        final Intent i0 = new Intent(this, Start_Activity.class);
        //------ back button onclick ---------------------
        btn_bak.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(i0);
                finish();
            }
        });
        //------ -------
    }

    private void My_refrence()
    {
        btn_bak = (Button) findViewById(R.id.btn_back);
    }
}
