package com.sabbir.android_mysql_3;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sabbir on 012  12 05 17  May.
 */

public class MainActivitySabbir extends AppCompatActivity {
    private TextView responseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_sabbir);


        this.responseTextView = (TextView) this.findViewById(R.id.responseTextView);

        new GetAllCustomerTask().execute(new ApiConnector());
    }

    public void setTextToTextView(JSONArray jsonArray){
        String s = "";
        for (int i=0; i<jsonArray.length(); i++){
            JSONObject json = null;
            try{
                json = jsonArray.getJSONObject(i);
                /*s = s+
                        "Name: "+json.getString("name")+"\n"+
                        "User Name: "+json.getString("user_name")+"\n\n";*/
                s = s+
                        "Name: "+json.getString("name")+"\n"+
                        "Roll: "+json.getString("roll")+"\n"+
                        "Mobile: "+json.getString("mobile")+"\n\n";
            }catch (JSONException e){
                e.printStackTrace();
            }
        }

        this.responseTextView.setText(s);
    }

    private class GetAllCustomerTask extends AsyncTask<ApiConnector, Long, JSONArray>{

        @Override
        protected JSONArray doInBackground(ApiConnector... params) {
            // it is executed on Background thread
            return params[0].GetAllCustomers();
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray){
            setTextToTextView(jsonArray);
        }
    }
}
