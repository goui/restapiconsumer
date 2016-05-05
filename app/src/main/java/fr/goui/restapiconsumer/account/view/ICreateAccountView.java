package fr.goui.restapiconsumer.account.view;

import fr.goui.restapiconsumer.IView;

/**
 *
 */
public interface ICreateAccountView extends IView {

    void closeView();

    void showError(String message);
}
