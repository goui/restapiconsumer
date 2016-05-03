package fr.goui.restapiconsumer.main.view;

import java.util.List;

import fr.goui.restapiconsumer.model.User;

/**
 *
 */
public interface IMainView extends IView {

    void showProgress();

    void refreshUserList(List<User> listOfUsers);
}
