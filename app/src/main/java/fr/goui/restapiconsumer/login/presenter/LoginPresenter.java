package fr.goui.restapiconsumer.login.presenter;

import fr.goui.restapiconsumer.IPresenter;
import fr.goui.restapiconsumer.MyApplication;
import fr.goui.restapiconsumer.login.view.ILoginView;
import fr.goui.restapiconsumer.model.User;
import fr.goui.restapiconsumer.network.NetworkService;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *
 */
public class LoginPresenter implements IPresenter<ILoginView> {

    private ILoginView mLoginView;

    private Subscription mSubscription;

    @Override
    public void attachView(ILoginView view) {
        mLoginView = view;
    }

    @Override
    public void detachView() {
        mLoginView = null;
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }

    public void signIn(String email, String password) {
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
        MyApplication application = MyApplication.get(mLoginView.getContext());
        NetworkService service = application.getNetworkService();
        mSubscription = service.signIn(email, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {
                        mLoginView.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoginView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(User user) {
                        // do nothing
                    }
                });
    }
}
