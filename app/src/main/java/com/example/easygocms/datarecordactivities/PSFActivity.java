package com.example.easygocms.datarecordactivities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.easygocms.R;
import com.example.easygocms.activities.HomeScreenActivity;

public class PSFActivity extends AppCompatActivity {

    WebView webViewPsf;
    SharedPreferences sharedPrefLogin;
    String URL="http://easygocms.com/autodialerapp/psf.php?adminid=";
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psf);
        sharedPrefLogin = getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        URL = URL + sharedPrefLogin.getString("admin_id", "")+"&userid=" + sharedPrefLogin.getString("user_id","");

        ImageView imageView=findViewById(R.id.backtohomescreen);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(PSFActivity.this, HomeScreenActivity.class);
                startActivity(intent);
                finish();
            }
        });

        webViewPsf=findViewById(R.id.webviewpsf);
        webViewPsf.setWebViewClient(new WebViewClient());
        webViewPsf.getSettings().setJavaScriptEnabled(true);
        webViewPsf.loadUrl(URL);
    }
}
