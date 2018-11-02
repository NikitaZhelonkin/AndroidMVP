package ru.nikitazhelonkin.mvp.android;

import android.support.annotation.NonNull;

import ru.nikitazhelonkin.mvp.MvpPresenter;

class PresenterStore {

    private MvpPresenter mPresenter;

    public MvpPresenter getPresenter() {
        return mPresenter;
    }

    public void setPresenter(@NonNull MvpPresenter presenter) {
        clear();
        mPresenter = presenter;
        mPresenter.onCreate();
    }

    public void clear() {
        if (mPresenter != null)
            mPresenter.onDestroy();
        mPresenter = null;
    }


}