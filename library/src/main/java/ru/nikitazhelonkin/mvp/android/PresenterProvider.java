package ru.nikitazhelonkin.mvp.android;


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import ru.nikitazhelonkin.mvp.MvpPresenter;

class PresenterProvider {

    public interface Factory<T extends MvpPresenter> {
        T create();
    }

    private PresenterStore mPresenterStore;

    public static PresenterProvider of(Fragment fragment) {
        return new PresenterProvider(HolderFragment.holderFragmentFor(fragment).getPresenterStore());
    }

    public static PresenterProvider of(FragmentActivity activity) {
        return new PresenterProvider(HolderFragment.holderFragmentFor(activity).getPresenterStore());
    }

    private PresenterProvider(PresenterStore presenterStore) {
        mPresenterStore = presenterStore;
    }

    @SuppressWarnings("unchecked")
    public <T extends MvpPresenter> T get(@NonNull Factory<T> factory) {
        T presenter = (T) mPresenterStore.getPresenter();
        if (presenter == null) {
            presenter = factory.create();
            mPresenterStore.setPresenter(presenter);
        }
        return presenter;
    }

}
