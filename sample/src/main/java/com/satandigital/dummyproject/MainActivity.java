package com.satandigital.dummyproject;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.satandigital.movieandtvinfo.SearchMediaActivity;
import com.satandigital.movieandtvinfo.models.MediaObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, SearchMediaActivity.class), 5063);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==5063 && resultCode==RESULT_OK){
            MediaObject mediaObject = data.getParcelableExtra("media");
            Toast.makeText(MainActivity.this, mediaObject.getOriginal_name(), Toast.LENGTH_SHORT).show();
        }
    }
}
