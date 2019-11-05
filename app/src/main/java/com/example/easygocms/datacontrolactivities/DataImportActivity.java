package com.example.easygocms.datacontrolactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.easygocms.NotifLostCustomerActivity;
import com.example.easygocms.NotifPSFActivity;
import com.example.easygocms.NotifSalesEnqActivity;
import com.example.easygocms.NotifSerRemindActivity;
import com.example.easygocms.R;
import com.example.easygocms.activities.HomeScreenActivity;
import com.example.easygocms.activities.RingingCallMainActivity;
import com.example.easygocms.dataringingcall.RCLostCustActivity;
import com.example.easygocms.dataringingcall.RCPspActivity;
import com.example.easygocms.dataringingcall.RCSalesEnqActivity;
import com.example.easygocms.dataringingcall.RCServicereminderActivity;

public class DataImportActivity extends AppCompatActivity {

    LinearLayout LLringingsereminder,LLringingpsf,LLringinglostcustomer,LLringingsalesenquiry;
    ImageView imageViewhome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_import);
        imageViewhome=findViewById(R.id.backtohomescreen);
        imageViewhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(DataImportActivity.this,HomeScreenActivity.class);
                startActivity(intent);
            }
        });
        LLringingsereminder=findViewById(R.id.ringingsereminder);
        LLringingsereminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DataImportActivity.this, NotifSerRemindActivity.class);
                startActivity(intent);
            }
        });
        LLringingpsf=findViewById(R.id.ringingpsf);
        LLringingpsf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DataImportActivity.this, NotifPSFActivity.class);
                startActivity(intent);
            }
        });
        LLringinglostcustomer=findViewById(R.id.ringinglostcustomer);
        LLringinglostcustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DataImportActivity.this, NotifLostCustomerActivity.class);
                startActivity(intent);
            }
        });
        LLringingsalesenquiry=findViewById(R.id.ringingsalesenquiry);
        LLringingsalesenquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DataImportActivity.this, NotifSalesEnqActivity.class);
                startActivity(intent);
            }
        });
    }
}
