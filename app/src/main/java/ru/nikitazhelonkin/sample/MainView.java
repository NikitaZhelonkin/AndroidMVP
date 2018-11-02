package ru.nikitazhelonkin.sample;

import ru.nikitazhelonkin.mvp.MvpView;

public interface MainView extends MvpView {

    void setButtonText(String text);

    void setCounter(int counter);

}
