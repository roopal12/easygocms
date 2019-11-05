package com.example.easygocms.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.easygocms.PrefManager;
import com.example.easygocms.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class LoginActivity extends AppCompatActivity {
    EditText Username,Password;
    Button Login;
    private String URLline = "";
    private ProgressDialog mProgress;
    SharedPreferences sharedPrefLogin;
     public static String publicUserId, publicAdminID;

      PrefManager prefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_main);
        writeLogFile();
        sharedPrefLogin = getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        ConnectivityManager ConnectionManager=(ConnectivityManager)getSystemService(this.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo=ConnectionManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()==true )
        {
           // Toast.makeText(LoginActivity.this, "Network Available", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(LoginActivity.this, "Network Not Available", Toast.LENGTH_LONG).show();


            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("INTERNET");
            builder.setMessage("Please connect your phone with internet");
            // add a button
            builder.setPositiveButton("OK", null);
            // create and show the alert dialog
            AlertDialog dialog = builder.create();
            dialog.show();

        }
        prefManager=new PrefManager(this);

        if(!prefManager.isFirstTimeLaunch()){
            startActivity(new Intent(LoginActivity.this,HomeScreenActivity.class));
            finish();
        }
        Username=findViewById(R.id.input_email);
        Password=findViewById(R.id.input_password);
        Login=findViewById(R.id.btn_login);
        mProgress =new ProgressDialog(this);
        String titleId="Login in...";
        mProgress.setTitle(titleId);
        mProgress.setMessage("Please Wait...");
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getLogin();

            }
        });

    }
//    create Log File and hadel error in application
    private void writeLogFile() {
        Log.e("in app controller", "writeLogFile");

        if (isExternalStorageWritable()) {

            File appDirectory = new File(Environment.getExternalStorageDirectory() + "/EasygocmsLog");
            File logDirectory = new File(appDirectory + "/log");
            File logFile = new File(logDirectory, "logcat" + System.currentTimeMillis() + ".txt");

            // create app folder
            if (!appDirectory.exists()) {
                appDirectory.mkdir();
            }

            // create log folder
            if (!logDirectory.exists()) {
                logDirectory.mkdir();
            }

            // clear the previous logcat and then write the new one to the file
            try {
                Process process = Runtime.getRuntime().exec("logcat -c");
                process = Runtime.getRuntime().exec("logcat -f " + logFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (isExternalStorageReadable()) {
            // only readable
        } else {
            // not accessible
        }
    }

    public boolean isExternalStorageWritable() {

        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* todo: Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {

        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }

        return false;
    }

    private void getLogin() {

        final String retrievedUserName = Username.getText().toString().trim();
        final String retrievedPassword = Password.getText().toString().trim();

        mProgress.show();
        URLline = "http://easygocms.com/autodialerapp/logincheck.php?userid=" + retrievedUserName + "&password=" + retrievedPassword;

        Log.d("getxxx", URLline);

        System.out.println("username" + retrievedUserName);
        System.out.println("password" + retrievedPassword);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLline,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);
                        if (retrievedUserName.isEmpty()) {
                            Username.setError("enter your username");
                            Username.setInputType(InputType.TYPE_CLASS_TEXT);
                        }
                        if (retrievedPassword.isEmpty()) {
                            Password.setError("enter your username");
                            Password.setInputType(InputType.TYPE_CLASS_NUMBER);
                        }
                        if (!retrievedUserName.isEmpty() && !retrievedPassword.isEmpty())
                        {
                            parseData(response);

                        }
                        mProgress.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
//                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);

    }
    private void parseData(String response) {
        try {

            JSONObject jsonObject = new JSONObject(response);
            if(jsonObject.getString("success").equals("1")) {
                JSONArray dataArray = jsonObject.getJSONArray("login");
                for (int i = 0; i < dataArray.length(); i++) {

                    JSONObject dataobj = dataArray.getJSONObject(i);

                    publicUserId = dataobj.getString("user_id");
                    publicAdminID = dataobj.getString("admin_id");

                    SharedPreferences.Editor editor = sharedPrefLogin.edit();
                    editor.putString("user_id", publicUserId);
                    editor.putString("admin_id",publicAdminID);
                    editor.apply();
                    editor.commit();


                    System.out.println("userid" + publicUserId);
                    System.out.println("admin" + publicAdminID);

                }

                Intent intent = new Intent(LoginActivity.this,HomeScreenActivity.class);
                startActivity(intent);
                finish();

            }

            else
            {

              Toast.makeText(LoginActivity.this,"Invalid User and Password",Toast.LENGTH_LONG).show();

            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
