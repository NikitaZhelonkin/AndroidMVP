package ru.nikitazhelonkin.mvp.android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ru.nikitazhelonkin.mvp.MvpPresenter;
import ru.nikitazhelonkin.mvp.MvpView;

public abstract class MvpActivity<P extends MvpPresenter<V>, V extends MvpView> extends AppCompatActivity {

    private P mPresenter;

    @Override
    @SuppressWarnings("unchecked")
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mPresenter = PresenterProvider.of(this).get(this::onCreatePresenter);
        mPresenter.attachView((V) this, savedInstanceState == null);
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    public P getPresenter() {
        return mPresenter;
    }

    protected abstract P onCreatePresenter();
}
