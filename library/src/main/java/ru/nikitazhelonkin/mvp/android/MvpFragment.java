package ru.nikitazhelonkin.mvp.android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import ru.nikitazhelonkin.mvp.MvpPresenter;
import ru.nikitazhelonkin.mvp.MvpView;

public abstract class MvpFragment<P extends MvpPresenter<V>, V extends MvpView> extends Fragment {

    private P mPresenter;

    private boolean mOnCreate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mOnCreate = true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mOnCreate) {
            mPresenter = PresenterProvider.of(this).get(this::onCreatePresenter);
            mOnCreate = false;
        }
        if (mPresenter != null)
            mPresenter.attachView((V) this, savedInstanceState == null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null)
            mPresenter.detachView();
    }

    public P getPresenter() {
        return mPresenter;
    }

    protected abstract P onCreatePresenter();
}
