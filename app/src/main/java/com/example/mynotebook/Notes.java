package com.example.mynotebook;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynotebook.Helper.MyButtonClickListener;
import com.example.mynotebook.Helper.MySwipeHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.*;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class Notes extends AppCompatActivity {

    // FragmentManager manager;
    ArrayList<HashMap<String, String>> note_list;


    int note_id[];

    private Toolbar ntoolbar;

    RecyclerView recyclerView;

    Intent intent;
    public String note_explanation[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        this.setTitle("My Notes");


        //  View vNotes = new View(this);

        ntoolbar = findViewById(R.id.toolbar);

        setSupportActionBar(ntoolbar);

        if (getSupportActionBar() != null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        onResume();

        //   manager = getSupportFragmentManager();

     /* FragmentNotes fragmentNotes = new FragmentNotes();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.add(R.id.activity_notes, fragmentNotes, "FRAGMENT NOTES");

        transaction.commit(); */

/*
        recyclerView = findViewById(R.id.noterecyclerview);
        NoteAdapter noteAdapter = new NoteAdapter(this, NoteList.getData()); // HomeAdapterdaki contexti yollad??k. bu class benim context ulasmak i??in kullanaca????m class.

        recyclerView.setAdapter(noteAdapter);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);
*/

    }

    @Override
    public void onResume() {
        super.onResume();

        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
        note_list = dbHelper.notes();

        if (note_list.size() == 0) {//note listesi bo??sa
            Toast.makeText(getApplicationContext(), "Hen??z Note Eklenmemi??.\nYukar??daki + Butonundan Ekleyiniz", Toast.LENGTH_LONG).show();
        } else {
            note_explanation = new String[note_list.size()]; // kitap adlar??n?? tutucam??z string arrayi olusturduk.
            note_id = new int[note_list.size()]; // kitap id lerini tutucam??z string arrayi olusturduk.
            for (int i = 0; i < note_list.size(); i++) {
                String x = note_list.get(i).get("Note_Explain");
                int id = Integer.parseInt(note_list.get(i).get("note_id"));

/*                final String TAG = "MyActivity";
                System.out.println(x);
                Log.d(TAG, "onResume: "+ x );
                System.out.println(note_list.get(i).toString());*/

                note_explanation[i] = x;
                System.out.println(note_explanation[i]);

                //kitap_liste.get(0) bize arraylist i??indeki ilk hashmap arrayini d??ner. Yani tablomuzdaki ilk sat??r de??erlerini
                //kitap_liste.get(0).get("kitap_adi") //bize arraylist i??indeki ilk hashmap arrayin anahtar?? kitap_adi olan value d??ner

                note_id[i] = id;
                //Yukar??daki ile ayn?? tek fark?? de??erleri integer a ??evirdik.

            }

            recyclerView = findViewById(R.id.noterecyclerview);
            NoteAdapter noteAdapter = new NoteAdapter(this, NoteList.getData(note_explanation)); // HomeAdapterdaki contexti yollad??k. bu class benim context ulasmak i??in kullanaca????m class.

            recyclerView.setAdapter(noteAdapter);


            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

            recyclerView.setLayoutManager(linearLayoutManager);

            MySwipeHelper mySwipeHelper = new MySwipeHelper(this,recyclerView,30) {
                @Override
                public void instantiateMyButton(RecyclerView.ViewHolder viewHolder, List<MySwipeHelper.MyButton> buffer) {


                    buffer.add(new MyButton(Notes.this,
                            "Delete",
                            R.drawable.ic_delete_white_24dp,
                            0,
                            Color.parseColor("#ff0000"),
                            new MyButtonClickListener() {

                                @Override
                                public void onClick(int pos) {

                                    System.out.println(pos);

                                        deleteAll();
                                   // Toast.makeText(Notes.this, "Delete Click", Toast.LENGTH_SHORT).show();
                                }
                            }));
                    buffer.add(new MyButton(Notes.this,
                            "Update",
                            R.drawable.ic_edit_white_24dp,
                            0,
                            Color.parseColor("#26a585"),
                            new MyButtonClickListener() {
                                @Override
                                public void onClick(int pos) {

                                    Toast.makeText(Notes.this, "Update Click", Toast.LENGTH_SHORT).show();
                                }
                            }));

                }
            };

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_item, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_add) {

            intent = new Intent(this, AddNote.class);

            this.startActivity(intent);

        }
        if (item.getItemId() == android.R.id.home) super.onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem item = menu.findItem(R.id.menu_new);
        item.setVisible(false);
        return true;
    }

    public void deleteAll(){

        intent = new Intent(this, AddNote.class);

        this.startActivity(intent);

    }




    /*  private void getIncomingIntent(){

        TextView tvTitle = findViewById(R.id.tvTitleList);

        String title;

        if(getIntent().hasExtra("Title")){

            title = getIntent().getStringExtra("Title");
            tvTitle.setText(title);
        }

    } */

  /*  public void addFragmentNotes (View v){

        FragmentNotes fragmentNotes = new FragmentNotes();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.activity_notes, fragmentNotes, "FRAGMENT NOTES");

        transaction.commit();

    }
    public void addFragmentDates (View v){

        FragmentDates fragmentDates= new FragmentDates();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.activity_notes, fragmentDates, "FRAGMENT DATES");

        transaction.commit();

    }

    public void removeFragmentNotes (View v){

    }
    public void removeFragmentDates (View v){

    } */

/*    public ArrayList<HashMap<String, String>> noteList(){

        ArrayList<HashMap<String, String>> noteList = new ArrayList<HashMap<String, String>>();
        DatabaseHelper  dbHelper= new DatabaseHelper(this);

        noteList = dbHelper.notes();

        return noteList;

    }*/


}
