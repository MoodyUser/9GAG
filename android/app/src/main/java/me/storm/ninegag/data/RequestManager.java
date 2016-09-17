package me.storm.ninegag.data;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import me.storm.ninegag.App;

/**
 * Created by storm on 14-3-25.
 */
public class RequestManager {
    public static RequestQueue mRequestQueue = Volley.newRequestQueue(App.getContext());

    private RequestManager() {
        // no instances
    }

    public static void addRequest(Request<?> request, Object tag) {
        if (tag != null) {
            request.setTag(tag);
        }
        mRequestQueue.add(request);
    }

    public static void addRequest(GsonRequest<?> request, Object tag,String key) {
        // Getting the token.. why not
        Map<String, String> keys =
                new HashMap<String, String>();
        if (!key.equals("default")) {
            keys.put("AUTHORIZATION", key);
        }
        if (tag != null) {
            request.setTag(tag);
        }
        request.setHeaders(keys);
        mRequestQueue.add(request);
    }


    public static void cancelAll(Object tag) {
        mRequestQueue.cancelAll(tag);
    }
}
