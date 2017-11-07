package danpollak.exercise_02;

import android.support.v4.app.Fragment;
import android.os.Bundle;
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
//
        View myView = inflater.inflate(R.layout.fragment_main, container, false);
        Button myButton = (Button) myView.findViewById(R.id.pushToaster);
        final EditText textBox = (EditText) myView.findViewById(R.id.toasterText);
        myButton.setOnClickListener(new View.OnClickListener() {
//            @Override
            public void onClick(View v){
                Toast test = Toast.makeText(v.getContext(), textBox.getText(), Toast.LENGTH_SHORT);
                test.show();
            }
        });
        return myView;
    }
}
