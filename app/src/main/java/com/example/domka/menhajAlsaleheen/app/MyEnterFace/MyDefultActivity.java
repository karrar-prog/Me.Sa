package com.example.domka.menhajAlsaleheen.app.MyEnterFace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import com.example.domka.menhajAlsaleheen.Font.Font;
import com.example.domka.menhajAlsaleheen.R;
import com.example.domka.menhajAlsaleheen.app.Controller.MainController.Start_Activity;

public class MyDefultActivity extends AppCompatActivity
{
    //copy from here
    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_content);
        MyRefrence();
        MyCilckListener();
        MyAnimation();
        //-----------------------------
        setFont(3);
        //--------------------
    }

    private void MyAnimation()
    {
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        btn_back.startAnimation(animation1);
    }

    private void MyCilckListener()
    {
        final Intent i0 = new Intent(this, Start_Activity.class);
        //----------------------------------
        btn_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(i0);
                finish();
            }
        });
    }

    private void MyRefrence()
    {
        btn_back = (Button) findViewById(R.id.btn_back);
        //-------------------------
    }

    private void setFont(int fontID)
    {
        //------------------------------------------------
        Font font = new Font();
        // font.set_font(this, fontID, t1, t2, t3, t4, t5, t6, t7);
        //-----------------------------------------------
    }
}
