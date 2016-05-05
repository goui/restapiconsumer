package fr.goui.restapiconsumer.login.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.goui.restapiconsumer.R;
import fr.goui.restapiconsumer.account.view.CreateAccountActivity;
import fr.goui.restapiconsumer.login.presenter.LoginPresenter;
import fr.goui.restapiconsumer.main.view.MainActivity;

/**
 *
 */
public class LoginActivity extends AppCompatActivity implements ILoginView {

    private LoginPresenter mPresenter;

    private Resources mResources;

    @BindView(R.id.login_email_edittext)
    EditText mEmailEditText;

    @BindView(R.id.login_password_edittext)
    EditText mPasswordEditText;

    @BindView(R.id.login_sign_in_button)
    Button mSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mResources = getResources();

        mPresenter = new LoginPresenter();
        mPresenter.attachView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSignInButton.setEnabled(true);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @OnClick(R.id.login_sign_in_button)
    public void signIn() {
        mSignInButton.setEnabled(false);
        mPresenter.signIn(mEmailEditText.getText().toString(), mPasswordEditText.getText().toString());
    }

    @OnClick(R.id.login_create_acount_textview)
    public void createAccount() {
        startActivityForResult(new Intent(this, CreateAccountActivity.class), mResources.getInteger(R.integer.intent_request_code_account_creation));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK
                && requestCode == mResources.getInteger(R.integer.intent_request_code_account_creation)) {
            mEmailEditText.setText(data.getStringExtra(mResources.getString(R.string.intent_extra_email)));
            mPasswordEditText.setText(data.getStringExtra(mResources.getString(R.string.intent_extra_password)));
        }
    }

    @Override
    public void onCompleted() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        mSignInButton.setEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
        mPresenter = null;
    }
}
