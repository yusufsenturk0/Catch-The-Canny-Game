package com.yusufsenturk.catchthekennygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity2 extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        intent=new Intent(getApplicationContext(),MainActivity.class);

    }
    public void Start(View view){
        startActivity(intent);
    }
}