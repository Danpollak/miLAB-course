package pollak.dan.exercise_04;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
        final View v = inflater.inflate(R.layout.fragment_main, container, false);
        Button startAlarmButton = (Button)v.findViewById(R.id.interval_button);
        final EditText intervalValue = (EditText)v.findViewById(R.id.interval_edit);
        final AlarmManager alarmManager = (AlarmManager)v.getContext().getSystemService(Context.ALARM_SERVICE);
        startAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String interval = intervalValue.getText().toString();
                sendNotification.startSendNotification(v.getContext(),interval);
            }
        });

        return v;
    }
}
