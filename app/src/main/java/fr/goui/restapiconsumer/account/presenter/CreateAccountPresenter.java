package fr.goui.restapiconsumer.account.presenter;

import fr.goui.restapiconsumer.IPresenter;
import fr.goui.restapiconsumer.MyApplication;
import fr.goui.restapiconsumer.account.view.ICreateAccountView;
import fr.goui.restapiconsumer.model.User;
import fr.goui.restapiconsumer.network.NetworkService;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *
 */
public class CreateAccountPresenter implements IPresenter<ICreateAccountView> {

    private ICreateAccountView mCreateAccountView;

    private Subscription mSubscription;

    @Override
    public void attachView(ICreateAccountView view) {
        mCreateAccountView = view;
    }

    @Override
    public void detachView() {
        mCreateAccountView = null;
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }

    public void createAccount(User user) {
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
        MyApplication application = MyApplication.get(mCreateAccountView.getContext());
        NetworkService service = application.getNetworkService();
        mSubscription = service.createUser(user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {
                        mCreateAccountView.closeView();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mCreateAccountView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(Boolean success) {
                        // do nothing
                    }
                });
    }
}
