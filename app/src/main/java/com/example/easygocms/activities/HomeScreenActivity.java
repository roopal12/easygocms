package com.example.easygocms.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.app.ActivityCompat;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.easygocms.HelpScreenActivity;
import com.example.easygocms.PrefManager;
import com.example.easygocms.R;
import com.example.easygocms.datacallingreport.DisatisfieldpsfActivity;
import com.example.easygocms.datacallingreport.PSFSummaryActivity;
import com.example.easygocms.datacallingreport.RingingActivity;
import com.example.easygocms.datacallingreport.SummaryReportActivity;
import com.example.easygocms.datacontrolactivities.DataAlotActivity;
import com.example.easygocms.datacontrolactivities.DataDeleteActivity;
import com.example.easygocms.datacontrolactivities.DataExportActivity;
import com.example.easygocms.datacontrolactivities.DataImportActivity;
import com.example.easygocms.datarecordactivities.BookingCallActivity;
import com.example.easygocms.datarecordactivities.CallReminderActivity;
import com.example.easygocms.datarecordactivities.LostCustomerActivity;
import com.example.easygocms.datarecordactivities.PSFActivity;
import com.example.easygocms.datarecordactivities.SalesEnquiryActivity;
import com.example.easygocms.datarecordactivities.ServiecBookingActivity;
import com.example.easygocms.datarecordactivities.ServiecReminderActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HomeScreenActivity extends AppCompatActivity {

    // Global Variable

    LinearLayout DataAlotLinearLayout,NotificationLinearLayout;

    LinearLayout DataExportLinearLayout,DataDeleteLinearLayout;

    LinearLayout CALLServiecReminder,CALLPSF,CAlLostCustomer,CALLSalesEnquiry;

    LinearLayout DataReportSummaryReport, DataReportDisPSF, DataReportRinging, DataReportPsfSummary;

    LinearLayout CALLCallReminder,CALLbBookingCall,CAllServiecBooking,CALL2Ringing;

    private PrefManager prefManager;

    SharedPreferences sharedPrefLogin;
    ImageView imageViewlogout,imageViewHelp;

    private static final int STORAGE_PERMISSION_CODE = 100;
    private static final int REQUEST_PERMISSION_SETTING = 101;

    private String[] permissionRequired = new String[]{
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.PROCESS_OUTGOING_CALLS,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private static final String TAG = HomeScreenActivity.class.getSimpleName();

    private SharedPreferences permissionStatus,sharedPreferenStatus;
    private boolean sentToSettings = false;


    public static  String E2CN = "";
    public static  String Status="";


    String URLline="http://easygocms.com/callapp/appcallrequest.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        writeLogFile();
        prefManager=new PrefManager(this);
        prefManager.setFirstTimeLaunch(false);
        permissionStatus = getSharedPreferences("permissionStatus", MODE_PRIVATE);
        imageViewlogout=findViewById(R.id.logout);
        imageViewHelp=findViewById(R.id.help);
        sharedPrefLogin = getSharedPreferences("loginDetails", Context.MODE_PRIVATE);

        //DATA CONTROL

        DataAlotLinearLayout=findViewById(R.id.DataAllotLL);
        DataExportLinearLayout=findViewById(R.id.DataExportLL);
        DataDeleteLinearLayout=findViewById(R.id.DatadeleteLL);
        NotificationLinearLayout=findViewById(R.id.Notification);

        DataAlotLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(HomeScreenActivity.this, DataAlotActivity.class);
                startActivity(intent);
            }
        });

        DataExportLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(HomeScreenActivity.this, DataExportActivity.class);
                startActivity(intent);
            }
        });
        DataDeleteLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeScreenActivity.this, DataDeleteActivity.class);
                startActivity(intent);
            }
        });
        NotificationLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeScreenActivity.this, DataImportActivity.class);
                startActivity(intent);
            }
        });

//        DATA CALLING

        CALLServiecReminder=findViewById(R.id.llServiecReminder);
        CALLPSF=findViewById(R.id.llpsf);
        CAlLostCustomer=findViewById(R.id.llLostCustomer);
        CALLSalesEnquiry=findViewById(R.id.llsalesenquiry);


        CALLCallReminder=findViewById(R.id.callreminder);
        CALLbBookingCall=findViewById(R.id.bookingcall);
        CAllServiecBooking=findViewById(R.id.llServiecBooking);
        CALL2Ringing=findViewById(R.id.linealayringingcall);


        CALLServiecReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(HomeScreenActivity.this, ServiecReminderActivity.class);
                startActivity(intent);
            }
        });

        CALLPSF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeScreenActivity.this, PSFActivity.class);
                startActivity(intent);
            }
        });
        CAlLostCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeScreenActivity.this, LostCustomerActivity.class);
                startActivity(intent);
            }
        });
        CALLSalesEnquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeScreenActivity.this, SalesEnquiryActivity.class);
                startActivity(intent);
            }
        });

        CALLCallReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeScreenActivity.this, CallReminderActivity.class);
                startActivity(intent);
            }
        });
        CALLbBookingCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeScreenActivity.this, BookingCallActivity.class);
                startActivity(intent);
            }
        });

        CAllServiecBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeScreenActivity.this, ServiecBookingActivity.class);
                startActivity(intent);
            }
        });

        CALL2Ringing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(HomeScreenActivity.this, RingingCallMainActivity.class);
                startActivity(intent);
            }
        });

//       DATA REPORT

        DataReportSummaryReport=findViewById(R.id.llsummaryreport);
        DataReportDisPSF=findViewById(R.id.llDissatisfiedpsf);
        DataReportPsfSummary=findViewById(R.id.llpsfsummary);
        DataReportRinging=findViewById(R.id.LLRinging);


        DataReportSummaryReport.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(HomeScreenActivity.this, SummaryReportActivity.class);
               startActivity(intent);
           }
       });


        DataReportDisPSF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(HomeScreenActivity.this, DisatisfieldpsfActivity.class);
                startActivity(intent);

            }
        });

        DataReportPsfSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(HomeScreenActivity.this,PSFSummaryActivity.class);
                startActivity(intent);
            }
        });

        DataReportRinging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(HomeScreenActivity.this, RingingActivity.class);
                startActivity(intent);

            }
        });

         //LOGOUT OPTION MENU
        imageViewlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              PopupMenu popup = new PopupMenu(HomeScreenActivity.this, imageViewlogout);
                //inflating menu from xml resource
                popup.inflate(R.menu.options_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        int id = item.getItemId();
                        if(id == R.id.logouttitle){
                            final AlertDialog.Builder builder = new AlertDialog.Builder(HomeScreenActivity.this,R.style.AlertDialogTheme);
                            builder.setTitle(Html.fromHtml("<font color='#f00'> Logout !! </font>"));
                            builder.setMessage("Are you sure  you want to logout ?");
                            builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    dialog.dismiss();

                                }
                            });
                            builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    prefManager.clearSession(); //clear session
                                    Intent intent = new Intent(HomeScreenActivity.this, LoginActivity.class);
                                    startActivity(intent); //go to Login Screen
                                    finish(); //finish this activity);

                                }
                            });
                            final AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                         return true;
                    }


                });
                //displaying the popup
                popup.show();

            }
        });

        imageViewHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(HomeScreenActivity.this, HelpScreenActivity.class);
                startActivity(intent);

            }
        });

//       PERMISSION TO BE AUTO  CALLING

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

    private void writeLogFile() {
        Log.e("in app controller", "writeLogFile");

        if (isExternalStorageWritable()) {

            File appDirectory = new File(Environment.getExternalStorageDirectory() + "/EasyGoCmsLog");
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
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {

            case 1: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
//                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
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
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(HomeScreenActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                })
        {
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
    @Override
    protected void onStart() {
        super.onStart();
        if(ActivityCompat.checkSelfPermission(this, permissionRequired[0]) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, permissionRequired[1]) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, permissionRequired[2]) != PackageManager.PERMISSION_GRANTED ||
        ActivityCompat.checkSelfPermission(this,permissionRequired[3])!=PackageManager.PERMISSION_GRANTED){

            Log.e("onPermission", "Please Check All Permissions");

            if(ActivityCompat.shouldShowRequestPermissionRationale(this, permissionRequired[0]) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this, permissionRequired[1]) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this, permissionRequired[2])) {

                Log.e("onStart ", "Permission Allow");

            }
            else if(permissionStatus.getBoolean(permissionRequired[0], false)) {

                sentToSettings = true;
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivityForResult(intent, REQUEST_PERMISSION_SETTING);

                Log.e("onStart", "Please Allow All Permissions");

                Toast.makeText(HomeScreenActivity.this, "Allow Storage Permission", Toast.LENGTH_LONG).show();

                permissionDialog();

            }

            ActivityCompat.requestPermissions(this, permissionRequired, STORAGE_PERMISSION_CODE);

        } else {

            Log.d(TAG, "All Permissions Granted");

        }

    }

    private void permissionDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Allow Permission!");
        builder.setMessage("Allow All Permissions");
        builder.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
                dialog.cancel();
            }
        });
        builder.setNegativeButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
