/*
 * Copyright (c) 2017. By Noor Nabiul Alam Siddiqui
 */

package com.sazal.siddiqui.getuserinfo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    @BindView(R.id.input_email) EditText inputEmail;
    @BindView(R.id.input_password) EditText inputPassword;
    @BindView(R.id.btn_login) Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.btn_login)
    public void onClick(View view) {
        if (!validate()) {
            onValidateFailed();
            return;
        }

        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    private void onValidateFailed() {

    }

    private boolean validate() {
        boolean valid = true;

        String  email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();

        if (email.isEmpty() || password.length() < 3 || password.length() > 50 ) {
            inputEmail.setError("user name length between 3 to 50 ");
            valid = false;
        } else {
            inputEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6 || password.length() > 50) {
            inputPassword.setError("6 to 50 alphanumeric password");
            valid = false;
        } else {
            inputPassword.setError(null);
        }

        return valid;
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        // moveTaskToBack(true);
        new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Are you sure to exit !!!")
                .setPositiveButton("YES", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("NO", null)
                .show();
    }
}
