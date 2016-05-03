package fr.goui.restapiconsumer.main.presenter;

import java.util.ArrayList;
import java.util.List;

import fr.goui.restapiconsumer.main.view.IMainView;
import fr.goui.restapiconsumer.model.User;

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

    public void loadUsers() {
        mMainView.showProgress();
        // TODO retrofit call
        List<User> users = new ArrayList<>();
        users.add(new User("John", "Doe"));
        users.add(new User("Jane", "Doe"));
        mMainView.refreshUserList(users);
    }
}
