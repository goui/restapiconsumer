package fr.goui.restapiconsumer.main.presenter;

import java.util.List;

import fr.goui.restapiconsumer.main.view.IMainView;
import fr.goui.restapiconsumer.model.User;
import fr.goui.restapiconsumer.network.NetworkService;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *
 */
public class MainPresenter implements IPresenter<IMainView> {

    private IMainView mMainView;

    private Subscription mSubscription;

    private List<User> mListOfUsers;

    @Override
    public void attachView(IMainView view) {
        mMainView = view;
    }

    @Override
    public void detachView() {
        mMainView = null;
        if(mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }

    public void loadUsers() {
        mMainView.showProgress();
        if(mSubscription != null) {
            mSubscription.unsubscribe();
        }
        NetworkService service = NetworkService.Factory.create();
        mSubscription = service.listUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<User>>() {
                    @Override
                    public void onCompleted() {
                        mMainView.refreshUserList(mListOfUsers);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mMainView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<User> listOfUsers) {
                        mListOfUsers = listOfUsers;
                    }
                });
    }
}
