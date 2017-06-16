package com.sabbir.android_mysql_3;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

/**
 * Created by Sabbir on 012  12 05 17  May.
 */

public class ApiConnector {

    public JSONArray GetAllCustomers(){
        //URL for getting all customers

        String url = "http://10.0.2.2:80/webapp/getAllCustomers.php";

        HttpEntity httpEntity = null;
        //URL httpEntity = null;

        try {
            DefaultHttpClient httpClient = new DefaultHttpClient(); //Default HttpClient
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            httpEntity = httpResponse.getEntity();


        } catch (ClientProtocolException e){
            //Signals error in http protocol
            e.printStackTrace();
            //log errors here


        } catch (IOException e){
            e.printStackTrace();
        }



        //Convert HttpEntity into JSON Array
        JSONArray jsonArray = null;
        if (httpEntity != null){
            try {
                String entityResponse = EntityUtils.toString(httpEntity);
                Log.e("Entity Response : ", entityResponse);
                jsonArray = new JSONArray(entityResponse);

            } catch (JSONException e){
                e.printStackTrace();

            } catch (IOException e){
                e.printStackTrace();
            }
        }

        return jsonArray;

    }
}
