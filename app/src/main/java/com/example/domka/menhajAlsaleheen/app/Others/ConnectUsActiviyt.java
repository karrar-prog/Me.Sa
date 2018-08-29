package com.example.domka.menhajAlsaleheen.app.Others;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.domka.menhajAlsaleheen.R;

public class ConnectUsActiviyt extends AppCompatActivity
{
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_us_activiyt);
        webView = (WebView) findViewById(R.id.web_connect_us);
        final ProgressDialog pd = ProgressDialog.show(ConnectUsActiviyt.this, "يرجى الانتظار ..", "  -  تواصل معنا \n  - ارسل لنا اقتراحك  ", true);
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
        webView.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSeDgfqqr-zBvhgQfwVbcMZ1T6N53tOwKv9TKr9nGJnMqkwpvw/viewform");
    }
}
