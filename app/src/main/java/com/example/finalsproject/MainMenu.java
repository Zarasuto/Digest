package com.example.finalsproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends AppCompatActivity implements ItemAdapter.ItemClickListener, ValueEventListener{

    FloatingActionButton fabMain, fabOne;
    Float translationY = 100f;
    private static final String TAG = "MainActivity";
    OvershootInterpolator interpolator = new OvershootInterpolator();
    Boolean isMenuOpen = false;

    private DatabaseReference reference;
    private ArrayList<Recipe> recipeList;
    private RecyclerView recylerview;
    private ItemAdapter itemadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        initValues();
        setupGridLayoutRecyclerView();
        setupButtons();
    }
    private void setupGridLayoutRecyclerView(){
        recylerview = findViewById(R.id.recyclerview);
        itemadapter= new ItemAdapter(MainMenu.this,recipeList);
        itemadapter.setClickListener(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(MainMenu.this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(MainMenu.this, R.drawable.divider));
        recylerview.setLayoutManager(new LinearLayoutManager(this));
        recylerview.addItemDecoration(dividerItemDecoration);
        recylerview.setAdapter(itemadapter);
        ItemTouchHelper.Callback callback= new CustomItemTouchHelper(itemadapter);
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recylerview);
    }
    private void initValues(){
        reference = FirebaseDatabase.getInstance().getReference("Recipes");//Will create a branch named recipes
        System.out.println(FirebaseDatabase.getInstance().getReference("Recipes"));
        reference.addValueEventListener(this);
        recipeList = new ArrayList<>();
    }


    private void setupButtons() {
        FloatingActionButton addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, AddActivity.class);
                startActivity(intent);
                Recipe recipe;
                recipe = (Recipe) intent.getParcelableExtra("recipe");
                recipeList.add(recipe);
                itemadapter.notifyDataSetChanged();
                //new activity
                Toast.makeText(MainMenu.this, "ADD BUTTON DONE", Toast.LENGTH_SHORT).show();
            }
        });
        FloatingActionButton out = findViewById(R.id.logoff);
        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(MainMenu.this, LoginActivity.class);
                Toast.makeText(MainMenu.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
    //see info
    @Override
    public void onItemClick(View v, int position) {
        Recipe toUse= itemadapter.getData().get(position);// Class to work with, already have information
        Intent i=new Intent(MainMenu.this, DisplayInfo.class);
        i.putExtra("recipees",toUse);
        startActivity(i);
        //see info
        //Toast.makeText(MainMenu.this,"TEST",Toast.LENGTH_SHORT).show();
    }

    //I CAN'T ADD OBJECT TO ARRAYLIST IN ANONYMOUS CLASS, ARRRGHHHHHH
    //ALSO RETRIEVES DATA
    //NOTIFIES WHEN IS ITEM IS ADDED
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        recipeList.clear();// need to clear to refresh the list of the array list whenever a new input is added

        // Each recipeSnapshot will get the data of the specific branch
        for(DataSnapshot recipeSnapshot : snapshot.getChildren()) {
            Recipe recipe = recipeSnapshot.getValue(Recipe.class);

            recipeList.add(recipe);// adds the object into the arraylist
        }
        itemadapter.notifyDataSetChanged();
        // sa part na ito nilalagay yung recipe list sa adapter Ex: (nakita ko lang sa tutorial)
                /*ArtistListAdapter adapter = new ArtistListAdapter(AddActivity.this, artistList);
                listViewArtists.setAdapter(adapter);*/
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }

}
