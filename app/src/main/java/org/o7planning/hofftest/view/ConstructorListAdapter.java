package org.o7planning.hofftest.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.o7planning.hofftest.R;
import org.o7planning.hofftest.model.Models;
import org.o7planning.hofftest.service.ListModel;

import java.util.ArrayList;

public class ConstructorListAdapter extends RecyclerView.Adapter<ConstructorListAdapter.MyViewHolder> {

    private ArrayList<Models> constructorList;

    private final Context context;

    SharedPreferences prefLike;

    public ConstructorListAdapter(ArrayList<Models> constructorList, Context context) {
        this.constructorList = constructorList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        if (prefLike == null) {
            prefLike = parent.getContext().getSharedPreferences("PREF_LIKE", Context.MODE_PRIVATE);
        }
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(constructorList.get(position));
    }

    @Override
    public int getItemCount() {
        return constructorList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        Models currentModel;

        TextView title;
        ImageView image;
        TextView description;
        TextView price;
        boolean flag = true;

        ImageView like;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.txtTitle);
            price = itemView.findViewById(R.id.textPrice);
            image = itemView.findViewById(R.id.imageCatalog);
            description = itemView.findViewById(R.id.txtDescription);

            like = itemView.findViewById(R.id.like);

            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (prefLike.getBoolean(currentModel.getSectionId(), false)) {
                        like.setImageResource(R.drawable.heart);
                        saveDataImageLike(currentModel.getSectionId(), false);
                    } else {
                        like.setImageResource(R.drawable.heartq);
                        saveDataImageLike(currentModel.getSectionId(), true);
                    }
                    notifyItemRangeChanged(0, constructorList.size(), false);
                }
            });
        }

        public void bind(Models model) {
            currentModel = model;

            description.setText(model.getSectionDesc());
            title.setText(model.getSectionTitle());
            price.setText(model.getPrice());
            Picasso.get()
                    .load(model.getSectionImage())
                    .into(image);

            if (prefLike.getBoolean(currentModel.getSectionId(), false)) {
                like.setImageResource(R.drawable.heart);
            } else {
                like.setImageResource(R.drawable.heartq);
            }
        }

        public void saveDataImageLike(String id, boolean flag) {
            SharedPreferences.Editor editor = prefLike.edit();
            editor.putBoolean(id, flag);
            editor.apply();
        }
    }
}