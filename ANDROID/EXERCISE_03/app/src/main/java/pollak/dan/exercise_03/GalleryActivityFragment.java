package pollak.dan.exercise_03;

import android.media.Image;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static android.content.ContentValues.TAG;

/**
 * A placeholder fragment containing a simple view.
 */
public class GalleryActivityFragment extends Fragment {

    public GalleryActivityFragment() {
    }
    final String[] starks = {"Ned", "Catelyn", "Robb","Sansa","Arya","Bran", "Rickon", "Jon Snow"};
    final int[] starksImg = {R.mipmap.img_ned,R.mipmap.img_catelyn,R.mipmap.img_robb,R.mipmap.img_sansa,R.mipmap.img_arya,R.mipmap.img_bran,R.mipmap.img_rickon, R.mipmap.img_snow };

    final String[] lannisters = {"Tywin", "Cersei", "Jaime", "Tyrion","Joffery", "Myrcella", "Tommen"};
    final int[] lannistersImg = {R.mipmap.img_tywin,R.mipmap.img_cersei,R.mipmap.img_jaime,R.mipmap.img_tyrion,R.mipmap.img_joffery,R.mipmap.img_myrcella,R.mipmap.img_tommen };

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            final View galleryView = inflater.inflate(R.layout.fragment_gallery, container, false);
            RecyclerView recView = (RecyclerView)galleryView.findViewById(R.id.family_viewer);
            recView.setLayoutManager(new LinearLayoutManager(galleryView.getContext()));
            final String houseName = getActivity().getIntent().getStringExtra("<House>");
            final String[] characterList;
            final int[] characterImages;
            Log.e(TAG, houseName );
            if(houseName.equals("Stark")){

                characterList = starks;
                characterImages = starksImg;
            }
            else {
                characterList = lannisters;
                characterImages = lannistersImg;
            }
            RecyclerView.Adapter recAdapter = new RecyclerView.Adapter() {
                class ViewHolder extends RecyclerView.ViewHolder {
                    TextView text;
                    ImageView image;
                    public ViewHolder(View itemView){
                        super(itemView);
                        text = (TextView)itemView.findViewById(R.id.charName);
                        image = (ImageView)itemView.findViewById(R.id.charImage);
                    }
                }

                @Override
                public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                    View itemView = inflater.inflate(R.layout.item_view, parent, false);
                    return new ViewHolder(itemView);
                }

                @Override
                public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                    ((ViewHolder)holder).text.setText(characterList[position]);
                    ((ViewHolder)holder).image.setImageResource(characterImages[position]);

                }

                @Override
                public int getItemCount() {
                    return characterList.length;
                }
            };
            recView.setAdapter((recAdapter));
            return galleryView;
    }
}
