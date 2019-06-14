package com.example.startedservice;

import android.app.IntentService;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.widget.Toast;


public class MyService extends IntentService {

    private Handler handler;
    private Binder binder = new Binder();

    public MyService() {
        super("BackgroundCounter");
    }

    @Override
    protected void onHandleIntent(@androidx.annotation.Nullable Intent intent) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "Your service has been started", Toast.LENGTH_SHORT).show();
            }
        });

        for (int i = 0; i < 6; i++) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            makeControlToast();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler = new Handler();
        return super.onStartCommand(intent, flags, startId);
    }

    public void makeControlToast() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "Service is still working", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
