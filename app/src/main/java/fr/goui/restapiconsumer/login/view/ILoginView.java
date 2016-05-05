package fr.goui.restapiconsumer.login.view;

import fr.goui.restapiconsumer.IView;

/**
 *
 */
public interface ILoginView extends IView {

    void onCompleted();

    void showError(String message);
}
