package pollak.dan.exercise_03;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
        View buttonsView = inflater.inflate(R.layout.fragment_main, container, false);
        final Button starkButton = (Button)buttonsView.findViewById(R.id.b_stark);
        Button lannisterButton = (Button)buttonsView.findViewById(R.id.b_lannister);
        starkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent moveToGallery = new Intent(view.getContext(), GalleryActivity.class);
                moveToGallery.putExtra("<House>", "Stark");
                getActivity().startActivity(moveToGallery);
            }
        });
        lannisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent moveToGallery = new Intent(view.getContext(), GalleryActivity.class);
                moveToGallery.putExtra("<House>", "Lannister");
                getActivity().startActivity(moveToGallery);
            }
        });
        return buttonsView;
    }
}
