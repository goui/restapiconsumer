package fr.goui.restapiconsumer;

/**
 *
 */
public interface IPresenter<T> {

    void attachView(T view);

    void detachView();

}
