package com.example.easygocms.datacontrolactivities;

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

public class DataExportActivity extends AppCompatActivity {
    WebView webViewdataExport;
    SharedPreferences sharedPrefLogin;
    String URL="http://easygocms.com/autodialerapp/data_export.php?adminid=";
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_export);
        sharedPrefLogin = getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        URL = URL + sharedPrefLogin.getString("admin_id", "")+"&userid=" + sharedPrefLogin.getString("user_id","");
        ImageView imageView=findViewById(R.id.backtohomescreen);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(DataExportActivity.this, HomeScreenActivity.class);
                startActivity(intent);

            }
        });
        webViewdataExport=findViewById(R.id.webviewdataexport);
        webViewdataExport.setWebViewClient(new WebViewClient());
        webViewdataExport.getSettings().setJavaScriptEnabled(true);
        webViewdataExport.loadUrl(URL);
    }
}
