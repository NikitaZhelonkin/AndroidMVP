package ru.nikitazhelonkin.sample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import ru.nikitazhelonkin.mvp.android.MvpFragment;

public class MainFragment extends MvpFragment<MainPresenter, MainView> implements MainView {

    private TextView mCounter;

    private Button mButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mButton = view.findViewById(R.id.start_btn);
        mCounter = view.findViewById(R.id.counter);
        mButton.setOnClickListener(v -> getPresenter().onBtnClick());
    }

    @Override
    protected MainPresenter onCreatePresenter() {
        return new MainPresenter(new CounterModel());
    }

    @Override
    public void setButtonText(String text) {
        mButton.setText(text);
    }

    @Override
    public void setCounter(int counter) {
        mCounter.setText(String.valueOf(counter));
    }

}
