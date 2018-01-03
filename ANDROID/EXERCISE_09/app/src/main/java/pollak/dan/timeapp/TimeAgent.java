package pollak.dan.timeapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class TimeAgent {
    Context context;
    String serverURL = "https://still-ravine-86304.herokuapp.com/getTime";
    RequestQueue queue;

    public TimeAgent(Context context){
        this.context = context;
        queue = Volley.newRequestQueue(context);
    }

    public interface timeRequestListener {
        public void onResponse(String response);
        public void onError();
    }

    public void getTime(final timeRequestListener listener) {
        StringRequest timeRequest = new StringRequest(Request.Method.GET, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError();
            }
        });
        queue.add(timeRequest);
    }
}
