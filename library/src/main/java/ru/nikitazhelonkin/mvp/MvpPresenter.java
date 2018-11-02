package ru.nikitazhelonkin.mvp;

import android.support.annotation.NonNull;

public interface MvpPresenter<V extends MvpView> {

    void onCreate();

    void attachView(@NonNull V view, boolean firstAttach);

    void detachView();

    void onDestroy();

}
