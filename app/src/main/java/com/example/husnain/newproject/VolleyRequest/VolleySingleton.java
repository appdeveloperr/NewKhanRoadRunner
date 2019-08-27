package com.example.husnain.newproject.VolleyRequest;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Husnain on 11/14/2017.
 */

public class VolleySingleton {

    private static VolleySingleton mInstance;
    private static Context mCtx;
    private RequestQueue requestQueue;


    private VolleySingleton(Context context){
        mCtx = context;
        requestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized VolleySingleton getmInstance(Context context){
        if (mInstance==null){
            mInstance = new VolleySingleton(context);
        }
        return mInstance;
    }

    public<T> void addtoRequestque(Request<T> request){
        getRequestQueue().add(request);
    }
}
