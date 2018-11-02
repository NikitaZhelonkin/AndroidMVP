package ru.nikitazhelonkin.sample;

import android.os.Handler;

public class CounterModel {

    public interface Callback {
        void onTick();
    }

    private Handler mHandler;

    private int mCount;

    private boolean mStarted;

    private Callback mCallback;

    public CounterModel() {
        mHandler = new Handler();
    }

    public int getCount() {
        return mCount;
    }

    public boolean isStarted() {
        return mStarted;
    }

    public void start(Callback callback) {
        mCallback = callback;
        mStarted = true;
        mCount = 0;
        mHandler.post(mUpdateRunnable);
    }

    public void stop() {
        mStarted = false;
        mCallback = null;
        mHandler.removeCallbacksAndMessages(null);
    }

    private Runnable mUpdateRunnable = new Runnable() {
        @Override
        public void run() {
            mCount++;
            if (mCallback != null) mCallback.onTick();
            mHandler.postDelayed(this, 1000);
        }
    };


}
