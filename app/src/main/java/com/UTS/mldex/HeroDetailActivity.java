package com.UTS.mldex;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HeroDetailActivity extends AppCompatActivity {

    public static final String EXTRA_HERO = "extra_hero";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_detail);

        ImageView imgPhoto = findViewById(R.id.img_detail_photo);
        TextView tvName = findViewById(R.id.tv_detail_name);
        TextView tvRole = findViewById(R.id.tv_detail_role);
        TextView tvAge = findViewById(R.id.tv_detail_age);
        TextView tvGender = findViewById(R.id.tv_detail_gender);
        TextView tvWeapon = findViewById(R.id.tv_detail_weapon);
        TextView tvLore = findViewById(R.id.tv_detail_lore);

        Hero hero = getIntent().getParcelableExtra(EXTRA_HERO);

        if (hero != null) {
            imgPhoto.setImageResource(hero.getPhoto());
            tvName.setText(hero.getName());
            tvRole.setText("Role: " + hero.getRole());
            tvAge.setText("Age: " + hero.getAge());
            tvGender.setText("Gender: " + hero.getGender());
            tvWeapon.setText("Weapon: " + hero.getWeapon());
            tvLore.setText(hero.getLore());
        }
        
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Detail " + (hero != null ? hero.getName() : "Hero"));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
