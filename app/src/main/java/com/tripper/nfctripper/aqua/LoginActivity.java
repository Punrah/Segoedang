package com.tripper.nfctripper.aqua;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tripper.nfctripper.aqua.apihelper.UserService;
import com.tripper.nfctripper.aqua.apihelper.RetrofitClient;
import com.tripper.nfctripper.aqua.model.userModel.ResultUser;
import com.tripper.nfctripper.aqua.model.userModel.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    private Realm realm;

    @BindView(R.id.input_email) EditText _emailText;
    @BindView(R.id.input_password) EditText _passwordText;
    @BindView(R.id.btn_login) Button _loginButton;
    @BindView(R.id.link_signup) TextView _signupLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        realm = Realm.getDefaultInstance();



        User user = ((MyApplication) this.getApplication()).getUser();

        if(user!=null) {
            onLoginSuccess();
        }


        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.



        UserService service = RetrofitClient.getClient().create(UserService.class);

        Call<ResultUser> userCall = service.userLogin(email,password);

        userCall.enqueue(new Callback<ResultUser>() {
            @Override
            public void onResponse(Call<ResultUser> call, Response<ResultUser> response) {
                progressDialog.dismiss();
                //onSignupSuccess();



                if(response.body().getSuccess()== true ) {


                    Toast.makeText(LoginActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();

                     User user = response.body().getDataUser().getUserList().get(0);
                     realm = Realm.getDefaultInstance();


                    realm.executeTransaction(r -> {
                       // realm.delete(User.class);
                        realm.insertOrUpdate(user);
                    });

                    onLoginSuccess();


                }else {
                    Toast.makeText(LoginActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    onLoginFailed();

                }
            }

            @Override
            public void onFailure(Call<ResultUser> call, Throwable t) {
                progressDialog.dismiss();
                Log.d("onFailure", t.toString());
                onLoginFailed();
            }
        });
    }






    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {


        _loginButton.setEnabled(false);
        Intent i = new Intent(LoginActivity.this,NavDrawerActivity.class);
        startActivity(i);
        finish();

    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (false) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}