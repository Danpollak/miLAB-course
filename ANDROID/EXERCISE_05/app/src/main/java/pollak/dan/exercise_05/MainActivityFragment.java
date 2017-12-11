package pollak.dan.exercise_05;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    String SEARCH_HTML_START = "_H1m _ees\"><span dir=\"ltr\">";
    String SEARCH_HTML_END = "</span>";
    String GOOGLE_URL = "https://www.google.com/search?q=";
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View searchView = inflater.inflate(R.layout.fragment_main, container, false);
        final RequestQueue requestQueue = Volley.newRequestQueue(searchView.getContext());
        Button searchButton = (Button)searchView.findViewById(R.id.button_search);
        final EditText searchValue = (EditText)searchView.findViewById(R.id.edit_search);
        final TextView results = (TextView)searchView.findViewById(R.id.top_result);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchString = searchValue.getText().toString();
                String url =  GOOGLE_URL+searchString.replaceAll(" ","%20");
                StringRequest searchReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override

                    public void onResponse(String response) {
                        int indexStart = response.indexOf(SEARCH_HTML_START);
                        String startIndexString = response.substring(indexStart+SEARCH_HTML_START.length());
                        int indexEnd = startIndexString.indexOf(SEARCH_HTML_END);
                        String firstResultHeader = startIndexString.substring(0,indexEnd);
                        results.setText(firstResultHeader);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast errorToast = Toast.makeText(searchView.getContext(), "Search Failed", Toast.LENGTH_SHORT);
                        errorToast.show();
                    }
                });
                requestQueue.add(searchReq);
            }
        });
        return searchView;
    }
}
