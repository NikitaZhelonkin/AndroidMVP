package ru.nikitazhelonkin.mvp;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.LinkedList;
import java.util.Queue;

public abstract class MvpPresenterBase<V extends MvpView> implements MvpPresenter<V> {

    public interface DoOnView<V> {
        void doOnView(@NonNull V v);
    }

    @Nullable
    private V view;

    private Queue<DoOnView<V>> pendingCommands;

    @Override
    @CallSuper
    public void onCreate() {
        pendingCommands = new LinkedList<>();
    }

    @Override
    @CallSuper
    public void attachView(@NonNull V view, boolean firstAttach) {
        this.view = view;
        executePendingCommands();
    }

    @Override
    @CallSuper
    public void detachView() {
        view = null;
    }

    @Override
    @CallSuper
    public void onDestroy() {
        pendingCommands.clear();
        pendingCommands = null;
    }


    private void executePendingCommands() {
        while (!pendingCommands.isEmpty()) {
            if (view == null) return;
            doOnView(pendingCommands.poll());
        }
    }

    protected void doOnView(DoOnView<V> doOnView) {
        if (pendingCommands == null) return;
        if (view != null) {
            doOnView.doOnView(view);
        } else {
            pendingCommands.add(doOnView);
        }
    }

    @Nullable
    public final V getView() {
        return view;
    }

}
