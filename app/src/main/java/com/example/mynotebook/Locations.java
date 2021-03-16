package com.example.mynotebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Locations extends AppCompatActivity {


    private Toolbar ltoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);
        this.setTitle("My Locations");

        ltoolbar = findViewById(R.id.toolbar);

       setSupportActionBar(ltoolbar);

        if(getSupportActionBar() != null){

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_item,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.menu_add) Toast.makeText(this,"ADD LOCATIONS",Toast.LENGTH_SHORT).show();
       // else if (item.getItemId()==R.id.menu_back) super.onBackPressed();
        if (item.getItemId()==android.R.id.home) super.onBackPressed();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu (Menu menu) {

        MenuItem item = menu.findItem(R.id.menu_new);
        item.setVisible(false);
        return true;
    }

}
