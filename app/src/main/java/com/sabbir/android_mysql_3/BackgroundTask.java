package com.sabbir.android_mysql_3;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Sabbir on 010  10 05 17  May.
 */

public class BackgroundTask extends AsyncTask<String, Void, String> {
    AlertDialog alertDialog;
    Context ctx;
    public boolean one = true;

    BackgroundTask(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Login Information....");
    }

    @Override
    protected String doInBackground(String... params) {
        String reg_url = "http://10.0.2.2/webapp/register.php";
        String login_url = "http://10.0.2.2/webapp/login.php";

        /*String reg_url = "http://58.97.243.55/webapp/register.php";
        String login_url = "http://58.97.243.55/webapp/login.php";
        */

        /*String reg_url = "http://192.168.64.1/webapp/register.php";
        String login_url = "http://192.168.64.1/webapp/login.php";*/

        /*String reg_url = "http://127.0.0.1/webapp/register.php";
        String login_url = "http://127.0.0.1/webapp/login.php";
        */
        String method = params[0];
        if (method.equals("register")) {
            String name = params[1];
            String roll = params[2];
            String mobile = params[3];
            String password = params[4];
            Integer id =1;
            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                //httpURLConnection.setDoInput(true);


                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data =
                        //Null + "&" +
                        URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                        URLEncoder.encode("roll", "UTF-8") + "=" + URLEncoder.encode(roll, "UTF-8") + "&" +
                        URLEncoder.encode("mobile", "UTF-8") + "=" + URLEncoder.encode(mobile, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8")
                        //URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(id), "UTF-8")
                        ;
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();


                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                //httpURLConnection.connect();
                httpURLConnection.disconnect();
                return "Registration Success...";

            } catch (MalformedURLException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }


        } else if (method.equals("login")) {

            String login_name = params[1];
            String login_pass = params[2];
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);


                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("login_name", "UTF-8") + "=" + URLEncoder.encode(login_name, "UTF-8") + "&" +
                        URLEncoder.encode("login_pass", "UTF-8") + "=" + URLEncoder.encode(login_pass, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "";
                String line = "";


                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                /*Intent intent = new Intent(BackgroundTask.this, MainActivitySabbir.class);
                startActivity(intent);*/


                // Calling the database activity if the login is a success :)
                if(response.equals("Login Success..Welcome")) {
                    Intent intent = new Intent(ctx, MainActivitySabbir.class);
                    ctx.startActivity(intent);
                }else{
                    //Toast.makeText(ctx, "You don't have access plz register.", Toast.LENGTH_LONG).show();
                    /*alertDialog.setMessage(response);
                    alertDialog.show();*/
                    /*ctx.runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(activity, "Hello", Toast.LENGTH_SHORT).show();
                        }
                    });*/
                    one=!one;
                }

                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        if (result.equals("Registration Success...")) {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        } /*else {
            alertDialog.setMessage(result);
            alertDialog.show();
            //Toast.makeText(getContext(), "clicked", Toast.LENGTH_LONG).show();
            //Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
            if (result == "Login Success..Welcome"){
                Intent intent = new Intent(ctx, MainActivitySabbir.class);
                ctx.startActivity(intent);
            }
        }*/
        if (!one){
            alertDialog.setMessage(result);
            alertDialog.show();
        }
        // This is perfect
    }
/*public void showToast(final String toast)
{
    runOnUiThread(new Runnable() {
        public void run()
        {
            Toast.makeText(MyActivity.this, toast, Toast.LENGTH_SHORT).show();
        }
    });
}*/

}