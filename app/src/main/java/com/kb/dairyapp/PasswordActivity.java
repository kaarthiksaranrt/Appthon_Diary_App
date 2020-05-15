package com.kb.dairyapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hanks.passcodeview.PasscodeView;

public class PasswordActivity extends AppCompatActivity {
    PasscodeView passcodeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        passcodeView =findViewById(R.id.passcode_view);
        String passcode = "1234";
        passcodeView.setPasscodeLength(4).setLocalPasscode(passcode)
        .setListener(new PasscodeView.PasscodeViewListener() {
            @Override
            public void onFail() {
                Toast.makeText(getApplicationContext(),"Password is Wrong",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(String number) {
                Intent i  = new Intent(PasswordActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
