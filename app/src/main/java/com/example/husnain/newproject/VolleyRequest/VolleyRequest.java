package com.example.husnain.newproject.VolleyRequest;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.husnain.newproject.Interfaces.AsyncResponse;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by Husnain on 11/14/2017.
 */

public class VolleyRequest {

    public AsyncResponse delegate = null;
    ProgressDialog progressDialog;

    public void volleyRequestCall(final Map<String, String> map, Context context, int method , String URL){
//        progressDialog = new ProgressDialog(context);
//        progressDialog.setMessage("Please wait....");
//        progressDialog.setCancelable(false);
//        progressDialog.show();
        StringRequest stringRequest = new StringRequest(method, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //progressDialog.dismiss();
//                        progressDialog.dismiss();
                        //Toast.makeText(Search_Activity.this, response, Toast.LENGTH_SHORT).show();
                        delegate.processFinish(response);
                        Log.e("In Volley Request ",response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //progressDialog.dismiss();
                        delegate.onError(error.toString());
                        Log.e("In Volley Error ",error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //map.put(UserNumber.KEY_NUMBER, num);
                return map;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        VolleySingleton.getmInstance(context).addtoRequestque(stringRequest);
    }

    public void volleyRequestCallwithProgressBar(final Map<String, String> map, Context context, int method , String URL){
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(method, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if( progressDialog != null && progressDialog.isShowing())
                            progressDialog.dismiss();
//                        progressDialog.dismiss();
                        //Toast.makeText(Search_Activity.this, response, Toast.LENGTH_SHORT).show();
                        delegate.processFinish(response);
                        Log.e("In Volley Request ",response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //delegate.processFinish("Error");
                        if(progressDialog != null && progressDialog.isShowing())
                            progressDialog.dismiss();
                        delegate.onError(error.toString());
                        //progressDialog.dismiss();
                        Log.e("In Volley Error ",error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //map.put(UserNumber.KEY_NUMBER, num);
                return map;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        VolleySingleton.getmInstance(context).addtoRequestque(stringRequest);
    }

    public void jsonVolleyRequest(Context context, final String mRequestBody, int method, String URL){

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(true);
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(method, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(progressDialog != null && progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                delegate.processFinish(response);
                Log.i("LOG_VOLLEY", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(progressDialog != null && progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                delegate.onError(error.toString());
                Log.e("LOG_VOLLEY", error.toString());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                    return null;
                }
            }
        };

        int socketTimeout = 30000; // 30 seconds. You can change it
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);

        VolleySingleton.getmInstance(context).addtoRequestque(stringRequest);
    }

}
