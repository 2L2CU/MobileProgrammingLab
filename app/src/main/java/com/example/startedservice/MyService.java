package com.example.startedservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;


public class MyService extends Service {

    private final IBinder binder = new MyBinder(); //for user to communicate
    IncrementTask incrementTask;
    private int baseCounter = 0;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(getApplicationContext(), "Your bound service has been started", Toast.LENGTH_SHORT).show();
        incrementTask = new IncrementTask(baseCounter);
        return binder;
    }

    //client method
    public String getCounter() {
        return String.valueOf(incrementTask.getInnerCounter());
    }

    public class MyBinder extends Binder { //makes <access-public-methods> object for client

        MyService getService() {
            return MyService.this;
        }
    }

    class IncrementTask {
        int innerCounter;
        Timer timer = new Timer();
        TimerTask incrementTask = new TimerTask() {
            @Override
            public void run() {
                innerCounter += 3;
            }
        };

        public IncrementTask(int count) {
            innerCounter = count;
            timer.schedule(incrementTask, 5000, 5000);
        }

        public int getInnerCounter() {
            return innerCounter;
        }
    }
}
