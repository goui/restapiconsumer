package fr.goui.restapiconsumer.main.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.goui.restapiconsumer.R;
import fr.goui.restapiconsumer.main.adapter.UserAdapter;
import fr.goui.restapiconsumer.main.presenter.MainPresenter;
import fr.goui.restapiconsumer.model.User;

public class MainActivity extends AppCompatActivity implements IMainView {

    private MainPresenter mPresenter;

    private UserAdapter mUserAdapter;

    @BindView(R.id.main_recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.no_user_found_textview)
    TextView mNoUserFoundTextView;

    @BindView(R.id.main_progressbar)
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPresenter = new MainPresenter();
        mPresenter.attachView(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(null);

        mPresenter.loadUsers();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
        mPresenter = null;
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
        mNoUserFoundTextView.setVisibility(View.GONE);
    }

    @Override
    public void refreshUserList(List<User> listOfUsers) {
        if (mUserAdapter == null) {
            mUserAdapter = new UserAdapter(this);
            mRecyclerView.setAdapter(mUserAdapter);
        }
        mUserAdapter.setListOfUsers(listOfUsers);

        // visibility
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(listOfUsers.size() == 0 ? View.GONE : View.VISIBLE);
        mNoUserFoundTextView.setVisibility(listOfUsers.size() == 0 ? View.VISIBLE : View.GONE);
    }
}
