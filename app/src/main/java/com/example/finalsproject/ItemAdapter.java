package com.example.finalsproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> implements ItemTouchHelperAdapter{

    private ArrayList<Recipe> data;
    private ItemClickListener mClickListener;
    private ItemTouchHelperAdapter mTouchHelperAdapter;
    private Context context;

    public ItemAdapter(Context context, ArrayList<Recipe> data) {
        this.context=context;
        this.data=data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.cell_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(data.get(position)!=null){
            holder.text.setText(data.get(position).getName());
            holder.image.setImageResource(Integer.parseInt(data.get(position).getImageKey()));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i=fromPosition;i<toPosition;i++) {
                Collections.swap(data,i,i+1);
            }
        } else {
            for (int i=fromPosition;i>toPosition;i--) {
                Collections.swap(data, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition,toPosition);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView text;
        ImageView image;
        ImageView imageMenu;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text_name);
            image = itemView.findViewById(R.id.image_name);
            imageMenu=itemView.findViewById(R.id.imageMenu);
            image.setOnClickListener(this);
            text.setOnClickListener(this);
            imageMenu.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.text_name:
                    if (mClickListener != null) mClickListener.onItemClick(v, getAdapterPosition());
                    break;
                case R.id.image_name:
                    if (mClickListener != null) mClickListener.onItemClick(v, getAdapterPosition());
                    break;
                case R.id.imageMenu:
                    showPopupMenu(v);
                    break;
                default:
                    break;
            }
        }
        private void showPopupMenu(final View view){
            //creating a popup menu
            PopupMenu popup = new PopupMenu(view.getContext(), imageMenu);
            //inflating menu from xml resource
            popup.inflate(R.menu.options_menu);
            //adding click listener
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.edit_button:
                            goToUpdateForm(view,data.get(getAdapterPosition()));
                            //handle menu1 click
                            return true;
                        case R.id.delete_button:
                            getPositionDeleteRecipe(getAdapterPosition());
                            Toast.makeText(view.getContext(),"Item Deleted",Toast.LENGTH_SHORT).show();
                            return true;
                        default:
                            return false;
                    }
                }

            });
            //displaying the popup
            popup.show();
        }
        //place holder method na nakuha ng int position.
        private void getPositionDeleteRecipe(int position){
            Recipe recipe = data.get(position);// Get the position from the array
            //Extracts the id
            String id = recipe.getId();
            deleteRecipe(id);
            data.remove(position);
            notifyDataSetChanged();
        }

        //method that deletes the particular node
        private void deleteRecipe(String id) {
            //reference now points to the particular node
            DatabaseReference deleteReference = FirebaseDatabase.getInstance().getReference("Recipes").child(id);
            deleteReference.removeValue();

            //Optional: Make a Toast notification
        }
        // Method that has a parameter of the id of the particular recipe
        private void goToUpdateForm(View v,Recipe recipe) {
            Intent intent = new Intent(v.getContext(), UpdateActivity.class); //new intent
            intent.putExtra("recipeId", recipe);//transfer the id to the next actvity
            v.getContext().startActivity(intent);
            notifyDataSetChanged();
        }
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }


    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    //============GETTERS==================

    //conveience
    public ArrayList<Recipe> getData(){
        return data;
    }
}
