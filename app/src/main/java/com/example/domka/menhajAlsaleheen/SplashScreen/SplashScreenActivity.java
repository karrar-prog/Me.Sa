package com.example.domka.menhajAlsaleheen.SplashScreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.example.domka.menhajAlsaleheen.R;
import com.example.domka.menhajAlsaleheen.app.Controller.MainController.Start_Activity;

public class SplashScreenActivity extends AppCompatActivity
{
    private Handler handler = new Handler();
    private int progressStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh_screen);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
       // progressBar.setBackgroundResource(R.drawable.progress_bar_res);
        progressStatus = 0;
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while (progressStatus < 100)
                {
                    // Update the progress status
                    progressStatus += 1;
                    // Try to sleep the thread for 20 milliseconds
                    try
                    {
                        Thread.sleep(10);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    // Update the progress bar
                    handler.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            progressBar.setProgress(progressStatus);
                            // Show the progress on TextView
                            // tv.setText(progressStatus+"");
                        }
                    });
                }
            }
        }).start();
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                startActivity(new Intent(SplashScreenActivity.this, Start_Activity.class));
                finish();
            }
        }, 1000);
    }
}
