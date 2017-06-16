package com.sabbir.android_mysql_3;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


//How are you ..
//Pushing into second branch..
public class MainActivity extends Activity {
    EditText ET_NAME, ET_PASS;
    String login_name, login_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ET_NAME = (EditText) findViewById(R.id.user_name);
        ET_PASS = (EditText) findViewById(R.id.user_pass);
    }

    public void userReg(View view) {
        startActivity(new Intent(this, Register.class));
    }

    public void userLogin(View view) {
        login_name = ET_NAME.getText().toString();
        login_pass = ET_PASS.getText().toString();
        String method = "login";
        AsyncTask<String, Void, String> res;
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method,login_name,login_pass);


        /*res = backgroundTask.execute(method, login_name, login_pass);

        //if (res == "Login Success..Welcome"){
        if (res.equals("Login Success..Welcome")) {
            Intent intent = new Intent(this, MainActivitySabbir.class);
            startActivity(intent);
            Toast.makeText(this, "WTF", Toast.LENGTH_LONG).show();
        }
        if (!res.equals("Login Success..Welcome")) {
            Toast.makeText(this, (AsyncTask) res, Toast.LENGTH_LONG).show();
        }*/
    }





/*
    private Boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public Boolean isOnline() {
        try {
            Process p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.com");
            int returnVal = p1.waitFor();
            boolean reachable = (returnVal==0);
            return reachable;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }*/
}