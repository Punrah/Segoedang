package com.tripper.nfctripper.aqua;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.tripper.nfctripper.aqua.apihelper.TestService;
import com.tripper.nfctripper.aqua.apihelper.UserService;
import com.tripper.nfctripper.aqua.apihelper.RetrofitClient;
import com.tripper.nfctripper.aqua.model.Result;
import com.tripper.nfctripper.aqua.model.testModel.ResultTest;
import com.tripper.nfctripper.aqua.model.userModel.ResultUser;
import com.tripper.nfctripper.aqua.model.userModel.User;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private Realm realm;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm= Realm.getDefaultInstance();

        user=realm.where(User.class).findFirst();

        populate();








    }

    private void populate() {

        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Load data");
        progressDialog.show();

        TestService service = RetrofitClient.getClient().create(TestService.class);



        Call<ResultTest> userCall = service.getIndex(user.getToken());

        userCall.enqueue(new Callback<ResultTest>() {
            @Override
            public void onResponse(Call<ResultTest> call, Response<ResultTest> response) {
                progressDialog.dismiss();
                //onSignupSuccess();
                Log.d("onResponse", "" + response.body().getMessage());


                if(response.body().getSuccess()== true ) {

                    Toast.makeText(MainActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();



                    onLoginSuccess();


                }else {
                    Toast.makeText(MainActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    onLoginFailed();

                }
            }

            @Override
            public void onFailure(Call<ResultTest> call, Throwable t) {
                progressDialog.dismiss();
                Log.d("onFailure", t.toString());
                onLoginFailed();
            }
        });
    }

    private void onLoginFailed() {

        Intent i = new Intent(this,LoginActivity.class);
        startActivity(i);
        finish();
    }

    private void onLoginSuccess() {



    }
}
