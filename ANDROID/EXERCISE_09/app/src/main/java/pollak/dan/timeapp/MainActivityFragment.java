package pollak.dan.timeapp;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View fragmentView = inflater.inflate(R.layout.fragment_main, container, false);
        Button refreshButton = (Button)(fragmentView.findViewById(R.id.refresh_button));
        final TextView timeDisplay = (TextView)(fragmentView.findViewById(R.id.text_time));
        final TimeAgent timeAgent = new TimeAgent(getContext());
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.show();
                progressDialog.setMessage("Getting Time");
                timeAgent.getTime(new TimeAgent.timeRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.hide();
                        timeDisplay.setText(response);
                    }

                    @Override
                    public void onError() {
                        progressDialog.hide();
                        Toast errorToast = Toast.makeText(fragmentView.getContext(), "Couldn't Get Time", Toast.LENGTH_SHORT);
                        errorToast.show();
                    }
                });
            }
        });

        return fragmentView;
    }
}
