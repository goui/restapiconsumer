package fr.goui.restapiconsumer.login.presenter;

import fr.goui.restapiconsumer.IPresenter;
import fr.goui.restapiconsumer.login.view.ILoginView;

/**
 *
 */
public class LoginPresenter implements IPresenter<ILoginView> {

    private ILoginView mLoginView;

    @Override
    public void attachView(ILoginView view) {
        mLoginView = view;
    }

    @Override
    public void detachView() {
        mLoginView = null;
    }
}
