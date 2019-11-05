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

public class LostCustomerActivity extends AppCompatActivity {
    WebView webViewLostCustomer;
    SharedPreferences sharedPrefLogin;
    String URL="http://easygocms.com/autodialerapp/lostcustomer.php?adminid=";
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_customer);
        sharedPrefLogin = getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        URL = URL + sharedPrefLogin.getString("admin_id", "")+"&userid=" +sharedPrefLogin.getString("user_id","");
        ImageView imageView=findViewById(R.id.backtohomescreen);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(LostCustomerActivity.this, HomeScreenActivity.class);
                startActivity(intent);
                finish();
            }
        });

        webViewLostCustomer=findViewById(R.id.webviewlostcustomer);
        webViewLostCustomer.setWebViewClient(new WebViewClient());
        webViewLostCustomer.getSettings().setJavaScriptEnabled(true);
        webViewLostCustomer.loadUrl(URL);
    }
}
