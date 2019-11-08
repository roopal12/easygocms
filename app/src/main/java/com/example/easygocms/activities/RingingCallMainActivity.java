package com.example.easygocms.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.example.easygocms.R;
import com.example.easygocms.dataringingcall.RCLostCustActivity;
import com.example.easygocms.dataringingcall.RCPspActivity;
import com.example.easygocms.dataringingcall.RCSalesEnqActivity;
import com.example.easygocms.dataringingcall.RCServicereminderActivity;

public class RingingCallMainActivity extends AppCompatActivity {

      LinearLayout LLringingsereminder,LLringingpsf,LLringinglostcustomer,LLringingsalesenquiry;
      ImageView imageViewhome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ringing_call_main);

        imageViewhome=findViewById(R.id.backtohomescreen);
        imageViewhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(RingingCallMainActivity.this,HomeScreenActivity.class);
                startActivity(intent);
                finish();
            }
        });
        LLringingsereminder=findViewById(R.id.ringingsereminder);
        LLringingsereminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RingingCallMainActivity.this,RCServicereminderActivity.class);
                startActivity(intent);
                finish();
            }
        });
        LLringingpsf=findViewById(R.id.ringingpsf);
        LLringingpsf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RingingCallMainActivity.this, RCPspActivity.class);
                startActivity(intent);
                finish();
            }
        });
        LLringinglostcustomer=findViewById(R.id.ringinglostcustomer);
        LLringinglostcustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RingingCallMainActivity.this, RCLostCustActivity.class);
                startActivity(intent);
                finish();
            }
        });
        LLringingsalesenquiry=findViewById(R.id.ringingsalesenquiry);
        LLringingsalesenquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RingingCallMainActivity.this, RCSalesEnqActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
