package com.example.domka.menhajAlsaleheen.app.Others;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.domka.menhajAlsaleheen.R;

public class MapActivity extends AppCompatActivity
{
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);


        final ProgressDialog pd = ProgressDialog.show(MapActivity.this, "يرجى الانتظار ..", "تحميل الموقع الجغرافي \n لـ(معهد تراث الانبياء) \n للدراسات الحوزوية الالكترونية", true);
        webView = (WebView) findViewById(R.id.web_map);
        webView.getSettings().setJavaScriptEnabled(true); // enable javascript
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setWebViewClient(new WebViewClient()
        {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl)
            {
                //    Toast.makeText(MapActivity.class, description, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon)
            {

                pd.show();


            }

            @Override
            public void onPageFinished(WebView view, String url)
            {
                pd.dismiss();
                String webUrl = webView.getUrl();
            }
        });
        webView.loadUrl("https://www.google.iq/maps/place/%D9%85%D8%B9%D9%87%D8%AF+%D8%AA%D8%B1%D8%A7%D8%AB+%D8%A7%D9%84%D8%A7%D9%86%D8%A8%D9%8A%D8%A7%D8%A1%E2%80%AD/@31.9930986,44.3143917,302m/data=!3m2!1e3!4b1!4m20!1m12!4m11!1m3!2m2!1d44.3145419!2d31.9935198!1m6!1m2!1s0x155ed0a62f8781e5:0xe5f32afc9246567c!2z2YXYudmH2K8g2KrYsdin2Ksg2KfZhNin2YbYqNmK2KfYodiMINmG2KzZgSDYo9i02LHZgQ!2m2!1d44.3137216!2d31.9930986!3m6!1s0x155ed0a62f8781e5:0xe5f32afc9246567c!8m2!3d31.9930986!4d44.3137216!9m1!1b1?authuser=1");
    }
}
