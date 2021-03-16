package com.example.mynotebook;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.mynotebook.ui.main.SectionsPagerAdapter;

public class Movies extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mviewPager;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
      //  this.setTitle("My Movies");
        mSectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        mviewPager = findViewById(R.id.view_pager);
        setupViewPager(mviewPager);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(mviewPager);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

      /*  FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

    }

    private void setupViewPager(ViewPager viewPager) {

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        if (sectionsPagerAdapter.getCount() == 0) {
            sectionsPagerAdapter.addFragment(new FragmentAll(), "All");
            sectionsPagerAdapter.addFragment(new FragmentWatched(), "Watched");
            sectionsPagerAdapter.addFragment(new FragmentWatchable(), "Watchable");
            viewPager.setAdapter(sectionsPagerAdapter);
        } else viewPager.setAdapter(sectionsPagerAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.menu_add) Toast.makeText(this,"ADD MOVIES",Toast.LENGTH_SHORT).show();
       if (item.getItemId()==R.id.menu_back) super.onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu (Menu menu) {

        MenuItem item = menu.findItem(R.id.menu_add);
        item.setEnabled(false);
        return true;
    }*/

}