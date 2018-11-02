package ru.nikitazhelonkin.mvp.android;

import android.support.annotation.RestrictTo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

@RestrictTo(RestrictTo.Scope.LIBRARY)
public class HolderFragment extends Fragment {

    private static final String HOLDER_TAG = "ru.nikitazhelonkin.mvp.HolderFragment";

    private PresenterStore mPresenterStore = new PresenterStore();

    public HolderFragment() {
        setRetainInstance(true);
    }

    public PresenterStore getPresenterStore() {
        return mPresenterStore;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenterStore.clear();
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public static HolderFragment holderFragmentFor(FragmentActivity activity) {
        return holderFragmentFor(activity.getSupportFragmentManager());
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public static HolderFragment holderFragmentFor(Fragment parentFragment) {
        return holderFragmentFor(parentFragment.getChildFragmentManager());
    }

    private static HolderFragment holderFragmentFor(FragmentManager fm) {
        HolderFragment holder = findHolderFragment(fm);
        if (holder != null) {
            return holder;
        }
        holder = createHolderFragment(fm);
        return holder;
    }

    private static HolderFragment findHolderFragment(FragmentManager manager) {
        if (manager.isDestroyed()) {
            throw new IllegalStateException("Can't access Presenters from onDestroy");
        }

        Fragment fragmentByTag = manager.findFragmentByTag(HOLDER_TAG);
        if (fragmentByTag != null && !(fragmentByTag instanceof HolderFragment)) {
            throw new IllegalStateException("Unexpected "
                    + "fragment instance was returned by HOLDER_TAG");
        }
        return (HolderFragment) fragmentByTag;
    }

    private static HolderFragment createHolderFragment(FragmentManager fragmentManager) {
        HolderFragment holder = new HolderFragment();
        fragmentManager.beginTransaction().add(holder, HOLDER_TAG).commitAllowingStateLoss();
        return holder;
    }
}

