package com.UTS.mldex;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class ListHero extends AppCompatActivity {

    private RecyclerView rvHeroes;
    private ArrayList<Hero> list = new ArrayList<>();
    private ListHeroAdapter adapter;
    private TabLayout tabLayout;

    private final String[] roles = {"Marksman", "Fighter", "Tank", "Mage", "Assassin", "Support"};
    private final int[] roleIcons = {
        R.drawable.marksman_icon,
        R.drawable.fighter_icon,
        R.drawable.tank_icon,
        R.drawable.mage_icon,
        R.drawable.assassin_icon,
        R.drawable.support_icon
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_navbawah);

        rvHeroes = findViewById(R.id.rv_heroes);
        rvHeroes.setHasFixedSize(true);

        tabLayout = findViewById(R.id.tab_layout);

        list.addAll(getListHeroes());
        showRecyclerList();
        setupTabLayout();
    }

    private void setupTabLayout() {
        for (int i = 0; i < roles.length; i++) {
            TabLayout.Tab tab = tabLayout.newTab();
            tab.setText(roles[i]);
            tab.setIcon(roleIcons[i]);
            tabLayout.addTab(tab);
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                filterHero(tab.getText().toString());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private void filterHero(String role) {
        ArrayList<Hero> filteredList = new ArrayList<>();
        for (Hero hero : list) {
            if (hero.getRole().equalsIgnoreCase(role)) {
                filteredList.add(hero);
            }
        }
        adapter.setListHero(filteredList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_list) {
            rvHeroes.setLayoutManager(new LinearLayoutManager(this));
        } else if (item.getItemId() == R.id.action_grid) {
            rvHeroes.setLayoutManager(new GridLayoutManager(this, 2));
        }
        return super.onOptionsItemSelected(item);
    }

    public ArrayList<Hero> getListHeroes() {
        String[] dataName = getResources().getStringArray(R.array.data_name);
        String[] dataRole = getResources().getStringArray(R.array.data_role);
        String[] dataAge = getResources().getStringArray(R.array.data_age);
        String[] dataGender = getResources().getStringArray(R.array.data_gender);
        String[] dataWeapon = getResources().getStringArray(R.array.data_weapon);
        String[] dataLore = getResources().getStringArray(R.array.data_lore);
        TypedArray dataPhoto = getResources().obtainTypedArray(R.array.data_photo);

        ArrayList<Hero> listHero = new ArrayList<>();
        for (int i = 0; i < dataName.length; i++) {
            Hero hero = new Hero();
            hero.setName(dataName[i]);
            hero.setRole(dataRole[i]);
            hero.setAge(dataAge[i]);
            hero.setGender(dataGender[i]);
            hero.setWeapon(dataWeapon[i]);
            hero.setLore(dataLore[i]);
            hero.setPhoto(dataPhoto.getResourceId(i, -1));
            listHero.add(hero);
        }
        dataPhoto.recycle();
        return listHero;
    }

    private void showRecyclerList() {
        rvHeroes.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListHeroAdapter(list);
        rvHeroes.setAdapter(adapter);
    }
}
