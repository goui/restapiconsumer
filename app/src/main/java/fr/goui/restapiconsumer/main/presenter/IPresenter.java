package fr.goui.restapiconsumer.main.presenter;

/**
 *
 */
public interface IPresenter<T> {

    void attachView(T view);

    void detachView();

}
