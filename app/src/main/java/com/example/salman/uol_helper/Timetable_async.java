package com.example.salman.uol_helper;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by SALMAN on 5/16/2017.
 */

public class Timetable_async extends AsyncTask<String, String, String> {
        private static Context context;
        ProgressDialog progressDialog;
        OkHttpClient client = new OkHttpClient();
        final ArrayList<Timetable> list = new ArrayList<Timetable>();

        public Timetable_async(Context c) {
            context = c;
        }

        Onstart onstart;

        public interface Onstart {
            void getstart(ArrayList<Timetable> list);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            onstart = (Onstart) context;
            progressDialog = ProgressDialog.show(context,
                    "",

                    "Wait......");
        }

        @Override
        protected String doInBackground(String... params) {

                Request request = new Request.Builder()
                        .url("http://192.168.43.32/mad_project/time")
                        .get()
                        .build();
                try {
                    Response response = client.newCall(request).execute();
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
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray arr = jsonObject.getJSONArray("timetable");

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject o = arr.getJSONObject(i);
                    list.add(new Timetable(o.getString("id"), o.getString("day"), o.getString("time_slot"), o.getString("room_no"), o.getString("course_name"), o.getString("status")));
                }
                onstart.getstart(list);


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
}
