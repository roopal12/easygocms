package com.example.easygocms.datacontrolactivities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.easygocms.R;
import com.example.easygocms.activities.HomeScreenActivity;

public class DataDeleteActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_delete);
        ImageView imageView = findViewById(R.id.backtohomescreen);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DataDeleteActivity.this, HomeScreenActivity.class);
                startActivity(intent);
            }
        });
    }
}
