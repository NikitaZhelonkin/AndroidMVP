package ru.nikitazhelonkin.sample;

import android.support.annotation.NonNull;
import android.util.Log;

import ru.nikitazhelonkin.mvp.MvpPresenterBase;

public class MainPresenter extends MvpPresenterBase<MainView> {

    private CounterModel mCounter;

    public MainPresenter(CounterModel counter){
        mCounter = counter;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mCounter = new CounterModel();
        Log.e("TAG", "onCreate");
    }

    @Override
    public void attachView(@NonNull MainView view, boolean firstAttach) {
        super.attachView(view, firstAttach);
        updateView();
        Log.e("TAG", "attachView");
    }

    public void onBtnClick() {
        if (mCounter.isStarted()) {
            mCounter.stop();
            updateView();
        } else {
            mCounter.start(this::updateView);
        }
    }

    private void updateView() {
        if (getView() == null) return;
        getView().setButtonText(mCounter.isStarted() ? "Stop" : "Start");
        getView().setCounter(mCounter.getCount());
    }

    @Override
    public void detachView() {
        super.detachView();
        Log.e("TAG", "detachView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCounter.stop();
        Log.e("TAG", "onDestroy");
    }
}
