package com.example.salman.uol_helper;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by SALMAN on 5/16/2017.
 */

public class Login_Async extends AsyncTask<String, String, String> {
        private static Context context;
        SharedPreferences sharedPreferences;
        ProgressDialog progressDialog;
        Response response;


        public Login_Async(Context c) {
            context = c;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(context,
                    "Login",

                    "Wait......");

        }

        OkHttpClient client = new OkHttpClient();

        @Override
        protected String doInBackground(String... params) {
            sharedPreferences = context.getSharedPreferences("mypreference2", Context.MODE_PRIVATE);

                RequestBody formBody = new FormBody.Builder()
                        .add("email", params[0])
                        .add("password", params[1])
                        .build();
                Request request = new Request.Builder()
                        .url("http://192.168.43.32/mad_project/authenticate")
                        .post(formBody)
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
        protected void onPostExecute(String o) {
            super.onPostExecute(o);
            progressDialog.dismiss();
            String a = "";
            String b = "";
            String c = "";
            if (o.equals("false")) {
                Toast.makeText(context, "Connection Failed", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    JSONObject jsonObject = new JSONObject(o);
                    a = jsonObject.getString("error ");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (a.equals("invalid_credentials")) {
                    progressDialog.dismiss();
                    Toast.makeText(context, "Invalid username or password", Toast.LENGTH_SHORT).show();


                } else {
                    try {
                        JSONObject jsonObject = new JSONObject(o);
                        a = jsonObject.getJSONObject("credentials").getString("email");
                        b = jsonObject.getJSONObject("credentials").getString("password");
                        c = jsonObject.getJSONObject("0").getString("token");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("email", a);
                    editor.putString("password", b);
                    editor.putString("token", c);

                    editor.commit();

//            Intent intent = new Intent(context, Activityb.class);
//            context.startActivity(intent);

                    Toast.makeText(context, "login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);

//
                }
            }
        }
    }


