package com.sabbir.android_mysql_3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Sabbir on 010  10 05 17  May.
 */
public class Register extends Activity {

    EditText ET_NAME, ET_ROLL, ET_MOBILE, ET_USER_PASS;
    String name, roll, mobile, user_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        ET_NAME = (EditText) findViewById(R.id.name);
        ET_ROLL = (EditText) findViewById(R.id.roll);
        ET_MOBILE = (EditText) findViewById(R.id.mobile);
        ET_USER_PASS = (EditText) findViewById(R.id.new_user_pass);
    }


    public void userReg(View view){
        name = ET_NAME.getText().toString();
        roll = ET_ROLL.getText().toString();
        mobile = ET_MOBILE.getText().toString();
        user_pass = ET_USER_PASS.getText().toString();

        String method = "register";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method, name, roll, mobile, user_pass);
        finish();
    }
}
