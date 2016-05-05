package fr.goui.restapiconsumer.account.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.goui.restapiconsumer.R;
import fr.goui.restapiconsumer.account.presenter.CreateAccountPresenter;
import fr.goui.restapiconsumer.model.User;

public class CreateAccountActivity extends AppCompatActivity implements ICreateAccountView {

    private CreateAccountPresenter mPresenter;

    private Resources mResources;

    @BindView(R.id.account_firstname_edittext)
    EditText mFirstNameEditText;

    @BindView(R.id.account_lastname_edittext)
    EditText mLastNameEditText;

    @BindView(R.id.account_email_edittext)
    EditText mEmailEditText;

    @BindView(R.id.account_password_edittext)
    EditText mPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);

        setResult(RESULT_CANCELED);

        mResources = getResources();

        mPresenter = new CreateAccountPresenter();
        mPresenter.attachView(this);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void closeView() {
        Intent intent = new Intent();
        intent.putExtra(mResources.getString(R.string.intent_extra_email), mEmailEditText.getText().toString());
        intent.putExtra(mResources.getString(R.string.intent_extra_password), mPasswordEditText.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.account_create_button)
    public void createAccount() {
        User user = new User(mFirstNameEditText.getText().toString(), mLastNameEditText.getText().toString());
        user.setEmail(mEmailEditText.getText().toString());
        user.setPassword(mPasswordEditText.getText().toString());
        mPresenter.createAccount(user);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
        mPresenter = null;
    }
}
