package com.UTS.mldex;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListHeroAdapter extends RecyclerView.Adapter<ListHeroAdapter.ListViewHolder> {
    private ArrayList<Hero> listHero;

    public ListHeroAdapter(ArrayList<Hero> list) {
        this.listHero = list;
    }

    public void setListHero(ArrayList<Hero> listHero) {
        this.listHero = listHero;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_hero, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        Hero hero = listHero.get(position);
        holder.imgPhoto.setImageResource(hero.getPhoto());
        holder.tvName.setText(hero.getName());
        holder.tvRole.setText(hero.getRole());

        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(holder.itemView.getContext(), "Kamu Memilih " + hero.getName(), Toast.LENGTH_SHORT).show();
            
            Intent intent = new Intent(holder.itemView.getContext(), HeroDetailActivity.class);
            intent.putExtra(HeroDetailActivity.EXTRA_HERO, hero);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listHero.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvRole;

        ListViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvRole = itemView.findViewById(R.id.tv_item_role);
        }
    }
}
