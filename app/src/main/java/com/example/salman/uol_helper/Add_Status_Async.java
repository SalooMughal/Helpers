package com.example.salman.uol_helper;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by SALMAN on 5/16/2017.
 */

public class Add_Status_Async extends AsyncTask<String, String, String> {
        private static Context context;
        ProgressDialog progressDialog;
        OkHttpClient client = new OkHttpClient();
        Response response;

        public Add_Status_Async(Context c) {
            context = c;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(context,
                    "Login",

                    "Wait......");

        }

        @Override
        protected String doInBackground(String... params) {

                RequestBody formBody = new FormBody.Builder()
                        .add("status", params[1])
                        .build();

                Request request = new Request.Builder()
                        .url("http://192.168.43.32/mad_project/time/" + params[0])
                        .put(formBody)
                        .build();
                try {
                    response = client.newCall(request).execute();
                    return response.body().string();

                } catch (Exception e) {
                    e.printStackTrace();
                }


            return null;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            if (s.equals("updated")) {
                Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Wrong Input", Toast.LENGTH_SHORT).show();
            }

        }
}
