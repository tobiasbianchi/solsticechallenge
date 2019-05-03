package com.tobias.solsticechallenge.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tobias.solsticechallenge.R;
import com.tobias.solsticechallenge.model.AppModel;
import com.tobias.solsticechallenge.model.Contact;
import com.tobias.solsticechallenge.modelView.ConctactInfoModelView;
import com.tobias.solsticechallenge.utils.ImageLoader;
import com.tobias.solsticechallenge.view.adapter.ContactDataListAdapter;

/**Activity for the display of information of a Contact*/
public class ContactInfoActivity extends AppCompatActivity {
    private ImageView userImage, favoriteButton;
    private TextView name, companyName;
    private RecyclerView contactDataRecycler;
    private ConctactInfoModelView modelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_info);
        prepareViewItems();
        loadModel();
    }

    private void prepareViewItems(){
        userImage = findViewById(R.id.contact_image);
        contactDataRecycler = findViewById(R.id.contact_list_data);
        contactDataRecycler.setLayoutManager(new LinearLayoutManager(this));
        contactDataRecycler.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        name = findViewById(R.id.contact_name);
        companyName = findViewById(R.id.contact_description);
        View backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> finish());
        favoriteButton = findViewById(R.id.favorite_button);
        favoriteButton.setOnClickListener(v -> toggleFavorite());
    }

    private void toggleFavorite(){
        if( modelView != null ){
            AppModel.getInstance().toggleFavorite(modelView.getId());
            modelView.setFavorite(!modelView.isFavorite());
            setFavoriteStar(modelView.isFavorite());
        }
    }

    private void loadModel(){
        Contact selected = AppModel.getInstance().getSelectedContact();
        if( selected != null ){
            modelView = new ConctactInfoModelView(this, selected);
            loadContact();
        }
    }

    private void setFavoriteStar(boolean isFavorite){
        int resource = isFavorite ? R.drawable.favorite_true : R.drawable.favorite_false;
        favoriteButton.setImageResource(resource);
    }

    private void loadContact(){
        ImageLoader.loadImage(getApplicationContext(), userImage, modelView.getImage(), R.drawable.user_large);
        contactDataRecycler.setAdapter(new ContactDataListAdapter(modelView.getData()));
        name.setText(modelView.getName());
        companyName.setText(modelView.getCompanyName());
        companyName.setVisibility(modelView.hasCompanyName() ? View.VISIBLE : View.GONE);
        setFavoriteStar(modelView.isFavorite());
    }
}
