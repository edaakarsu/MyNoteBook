package com.example.mynotebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    private Toolbar htoolbar;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        htoolbar = findViewById(R.id.toolbar);
        htoolbar.setTitle("My NoteBook");
/*
        setSupportActionBar(htoolbar);

        if(getSupportActionBar() != null){

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }*/



        recyclerView = findViewById(R.id.homerecyclerview);
        HomeAdapter homeAdapter = new HomeAdapter(this, HomeList.getData()); // HomeAdapterdaki contexti yolladık. bu class benim context ulasmak için kullanacağım class.

        recyclerView.setAdapter(homeAdapter);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);


    }

/*    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_item,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu (Menu menu) {

        MenuItem item = menu.findItem(R.id.menu_add);
        item.setEnabled(false);
        return true;
    }*/

}
