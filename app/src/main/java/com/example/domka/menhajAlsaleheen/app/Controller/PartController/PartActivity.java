package com.example.domka.menhajAlsaleheen.app.Controller.PartController;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.domka.menhajAlsaleheen.R;
import com.example.domka.menhajAlsaleheen.app.Controller.MainController.Start_Activity;

public class PartActivity extends AppCompatActivity
{
    Button btn_bak;
    Button btn_part1;
    Button btn_part2;
    Button btn_part3;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part);
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
        final Intent i1 = new Intent(this, PartContentActivity.class);
        i1.putExtra("part_id","1");
        //------ part1 button onclick ---------------------
        btn_part1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(i1);

            }
        });
        final Intent i2 = new Intent(this, PartContentActivity.class);
        i2.putExtra("part_id","2");
        //------ -------
        btn_part2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(i2);

            }
        });
        final Intent i3 = new Intent(this, PartContentActivity.class);
        i3.putExtra("part_id","3");
        //------ -------
        btn_part3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(i3);

            }
        });
        //------ -------
    }

    private void My_refrence()
    {
        btn_bak = (Button) findViewById(R.id.btn_back);
        btn_part1 = (Button) findViewById(R.id.btn_part1);
        btn_part2 = (Button) findViewById(R.id.btn_part2);
        btn_part3 = (Button) findViewById(R.id.btn_part3);

    }
}
