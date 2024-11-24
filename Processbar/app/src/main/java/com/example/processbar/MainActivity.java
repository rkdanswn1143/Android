package com.example.processbar;

import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ProgressBar mProgress;
    private int mPorgressStatus = 0;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        mProgress = (ProgressBar) findViewById(R.id.progressBar2);
        mProgress.setMax(100);
        mProgress.setProgress(0);

        new Thread(new Runnable(){
            public void run(){
                while(mPorgressStatus < 100){
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e){

                    }mPorgressStatus = i++;

                    mProgress.post(new Runnable() {
                        @Override
                        public void run() {
                            mProgress.setProgress(mPorgressStatus);
                        }
                    });
                }
            }
        }).start();
    }
}