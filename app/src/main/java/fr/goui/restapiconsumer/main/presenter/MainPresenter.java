package fr.goui.restapiconsumer.main.presenter;

import fr.goui.restapiconsumer.main.view.IMainView;

/**
 *
 */
public class MainPresenter implements IPresenter<IMainView> {

    private IMainView mMainView;

    @Override
    public void attachView(IMainView view) {
        mMainView = view;
    }

    @Override
    public void detachView() {
        mMainView = null;
    }

}
