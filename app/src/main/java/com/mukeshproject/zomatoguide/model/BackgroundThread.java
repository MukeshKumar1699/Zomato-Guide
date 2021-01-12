package com.mukeshproject.zomatoguide.model;

import android.os.Handler;
import android.os.HandlerThread;

import com.mukeshproject.zomatoguide.listeners.LooperPreparedListener;


public class BackgroundThread extends HandlerThread {

    private final LooperPreparedListener looperPreparedListener;
    public Handler handler;

    public BackgroundThread(String name, LooperPreparedListener looperPreparedListener) {
        super(name);
        this.looperPreparedListener = looperPreparedListener;
    }

    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        handler = new Handler(getLooper());
        looperPreparedListener.onLooperPrepared();
    }

    public void addTaskToQueue(Runnable task) {
        handler.post(task);
    }
}
