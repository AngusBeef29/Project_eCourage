package com.example.sfene_000.project_ecourage.tools.breathingActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.sfene_000.project_ecourage.R;

public class breathingActivity1 extends AppCompatActivity {

    private ProgressBar progressBar;
    private Button progressButton;
    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathing_1);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressButton = (Button) findViewById(R.id.progressButton);

        progressButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                progressStatus = 0;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(progressStatus < 300) {
                            progressStatus += 1;
                            handler.post(new Runnable(){
                                public void run(){
                                    progressBar.setProgress(progressStatus);
                                }
                            });
                            try {
                                Thread.sleep(1000);
                            }
                            catch(InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });
    }
}
