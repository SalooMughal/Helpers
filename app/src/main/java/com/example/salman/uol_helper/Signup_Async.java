package com.example.salman.uol_helper;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by SALMAN on 5/16/2017.
 */

public class Signup_Async extends AsyncTask<String,String,String>{
    private static Context context;
    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;
    OkHttpClient client = new OkHttpClient();

    public Signup_Async(Context c) {
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
        sharedPreferences = context.getSharedPreferences("mypreference2", Context.MODE_PRIVATE);

            RequestBody formBody = new FormBody.Builder()
                    .add("name", params[0])
                    .add("email", params[1])
                    .add("password", params[2])
                    .build();
            Request request = new Request.Builder()
                    .url("http://192.168.43.32/mad_project/userregister")
                    .post(formBody)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                return response.body().string();

            } catch (Exception e) {
                e.printStackTrace();
            }

        return null;

    }

    String name = null;
    String email = null;
    String password = null;

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s.equals("register")) {
            progressDialog.dismiss();
            Toast.makeText(context, "User Registered", Toast.LENGTH_SHORT).show();
            FragmentTransaction fragmentTransaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
            LoginFragment blankFragment = new LoginFragment();
            fragmentTransaction.replace(android.R.id.content, blankFragment).commit();
        } else {
            progressDialog.dismiss();
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        }
    }
}
