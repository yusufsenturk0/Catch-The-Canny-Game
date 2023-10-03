package com.yusufsenturk.catchthekennygame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;

    TextView textViewScore;
    TextView textViewTime;

    ImageView[] imageArray;
    Random random;
    ImageView atView;

    Runnable runnable;
    Handler handler;

    int num=0;
    int score=0;

    AlertDialog.Builder builder;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent=getIntent();
        random=new Random();
        atView=findViewById(R.id.imageView5);

        imageView1=findViewById(R.id.imageView);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);

        imageArray=new ImageView[] {imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};

        textViewScore=findViewById(R.id.textViewScore);
        textViewTime=findViewById(R.id.textViewTime);
        hideKenny();
        moveKenny();


    }

    public void hideKenny() {
        handler=new Handler();

        runnable=new Runnable() {
            @Override
            public void run() {
                atView.setVisibility(View.INVISIBLE);
                int num2=num;
                num=random.nextInt(9);
                while(num==num2){
                    num=random.nextInt(9);
                }
                imageArray[num].setVisibility(View.VISIBLE);
                atView=imageArray[num];
                handler.postDelayed(runnable,450);
            }
        };
        handler.post(runnable);


        for (ImageView image : imageArray) {
            if (image!=imageView5){
                image.setVisibility(View.INVISIBLE);
            }

        }
    }

    public void moveKenny(){
        new CountDownTimer(20000,1000){

            @Override
            public void onTick(long l) {

                textViewTime.setText("Time : "+l/1000);

            }
            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "Game finished", Toast.LENGTH_SHORT).show();
                handler.removeCallbacks(runnable);
                for (ImageView image : imageArray) {

                    image.setVisibility(View.INVISIBLE);

                }
                builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Restart");
                builder.setMessage("Do yuu want to restart the game?");
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Closed The Game", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Restart

                        finish();
                        startActivity(intent);
                    }
                });
                builder.show();
            }
        }.start();
    }

    public void Clicked(View view){
        score++;
        textViewScore.setText("Score : "+score);
    }




}