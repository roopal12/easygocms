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


public class ServiecReminderActivity extends AppCompatActivity {

    WebView webViewServiecReminder;
    SharedPreferences sharedPrefLogin;

    String URL = "http://easygocms.com/autodialerapp/servicereminder.php?adminid=";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serviec_reminder);

        sharedPrefLogin = getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        URL = URL + sharedPrefLogin.getString("admin_id", "")+"&userid=" +sharedPrefLogin.getString("user_id","");


          ImageView imageView = findViewById(R.id.backtohomescreen);
          imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ServiecReminderActivity.this, HomeScreenActivity.class);
                startActivity(intent);
                finish();
            }
        });

        webViewServiecReminder = findViewById(R.id.webviewservrem);
        webViewServiecReminder.setWebViewClient(new WebViewClient());
        webViewServiecReminder.getSettings().setJavaScriptEnabled(true);
        webViewServiecReminder.loadUrl(URL);
        System.out.println("ServiecReminderUrl" + URL);



    }

}

