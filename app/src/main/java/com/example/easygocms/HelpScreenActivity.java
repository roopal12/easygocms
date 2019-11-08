package com.example.easygocms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.easygocms.activities.HomeScreenActivity;
import com.example.easygocms.datacallingreport.PSFSummaryActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HelpScreenActivity extends AppCompatActivity {

    WebView webviewhelp;
    ImageView imageViewhome;
    SharedPreferences sharedPrefLogin;
    String URL="http://easygocms.com/autodialerapp/help.php?adminid=";

    public static String E2CN = "";
    public static  String Status="";

    String URLline="http://easygocms.com/callapp/appcallrequest.php";
    private SharedPreferences sharedPreferenStatus;


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_screen);
        sharedPrefLogin = getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        URL = URL + sharedPrefLogin.getString("admin_id", "")+"&userid=" + sharedPrefLogin.getString("user_id","");
        webviewhelp=findViewById(R.id.webviewhelp);
        webviewhelp.setWebViewClient(new WebViewClient());
        webviewhelp.getSettings().setJavaScriptEnabled(true);
        webviewhelp.loadUrl(URL);
        imageViewhome=findViewById(R.id.backtohomescreen);
        imageViewhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HelpScreenActivity.this, HomeScreenActivity.class);
                startActivity(intent);
                finish();
            }
        });

        if (isPermissionGranted()) {

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    call_action();
                    handler.postDelayed(this, 3000);
                }
            }, 3000);

        }

    }

    private boolean isPermissionGranted() {

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG", "Permission is granted");
                return true;
            } else {

                Log.v("TAG", "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG", "Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {

            case 1: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
//                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }

            }

        }
    }
    //     AUTO CALL METHOD
    @SuppressLint("HardwareIds")
    public void call_action() {
        System.out.println("autocall method");
        String mobileIMEI = null;
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                == PackageManager.PERMISSION_GRANTED) {
            if (telephonyManager != null) {
                mobileIMEI = telephonyManager.getDeviceId();
                Log.d("Tag", "Mobile IMEI : " + mobileIMEI);
            }
        }


        sharedPrefLogin = getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        final String Admin_Id = sharedPrefLogin.getString("admin_id", "");
        System.out.println("adminid :" + Admin_Id);

        final String Imei_No = mobileIMEI;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLline,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(ServiecReminderActivity.this, response, Toast.LENGTH_LONG).show();
                        System.out.println("response" + response);
                        //parse json data
                        parseData(response);
                        // parsdata1(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(HelpScreenActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Admin_Id", Admin_Id);
                params.put("Imei_No", Imei_No);
                params.put("E2CN", E2CN);
                params.put("Status",Status);

                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);


    }

    //    RETRIVE JSON FROM API
    private void parseData(String response) {

        try {
            JSONArray dataArray = new JSONArray(response);
            for (int i = 0; i < dataArray.length(); i++) {

                JSONObject dataobj = dataArray.getJSONObject(i);

                E2CN = dataobj.getString("E2CN");
                Status=dataobj.getString("Status");

                sharedPreferenStatus = getSharedPreferences("StatusDetails", Context.MODE_PRIVATE);

                if (!E2CN.equals("")) {

                    SharedPreferences.Editor editor = sharedPreferenStatus.edit();
                    editor.putString("Status", Status);
                    editor.apply();
                    editor.commit();


                    System.out.println("mobile :" + E2CN);
                    System.out.println("status :" + Status);


                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + E2CN));

                    if(ActivityCompat.checkSelfPermission(this,
                            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                    {
                        return;
                    }
                    startActivity(callIntent);

                }

            }

        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }
}

