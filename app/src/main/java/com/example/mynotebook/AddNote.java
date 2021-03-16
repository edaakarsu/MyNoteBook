package com.example.mynotebook;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static android.os.Environment.MEDIA_BAD_REMOVAL;
import static android.os.Environment.getExternalStoragePublicDirectory;

public class AddNote extends AppCompatActivity {

    private Toolbar toolbar;
    ImageButton btnImage;
    ImageView ivImage;
    EditText etNote;
    String pathToFile;
    Bitmap bitmap;
    Button btnDelete;
    Drawable myDrawable;
    String url ;
    Uri photoUrI;
    DatabaseHelper dbHelper ;
    ArrayList<HashMap<String, String>> note_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        etNote = findViewById(R.id.etNote);
        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setVisibility(View.INVISIBLE);

        btnImage = findViewById(R.id.btnImage);

        if (Build.VERSION.SDK_INT >= 23)
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);

        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchPictureTakerAction();
            }
        });

        ivImage = findViewById(R.id.ivImage);
        url = "https://www.androidpolice.com/wp-content/uploads/2015/02/nexus2cee_MotorolaCameraNew.png";
        //For picasso ;
        //Picasso.with(this).load(url).into(ivImage);
        // For glide :
        Glide.with(this).load(url).into(ivImage);


       // myDrawable = ivImage.getDrawable();

        ivImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                longClick();

                return false;
            }
        });

        etNote = findViewById(R.id.etNote);

        toolbar = findViewById(R.id.antoolbar);

        toolbar.setTitle("New Note");

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_item, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //if (item.getItemId()==R.id.menu_add) Toast.makeText(this,"ADD DATES",Toast.LENGTH_SHORT).show();
        //else if (item.getItemId()==R.id.menu_back) super.onBackPressed();

        if (item.getItemId() == R.id.menu_new){
            String note = etNote.getText().toString();

            if(note.matches("")){
                Toast.makeText(this,"Please Enter Note", Toast.LENGTH_SHORT).show();
            }else {
                SaveNote();
                super.onBackPressed();
            }

        }

        if (item.getItemId() == android.R.id.home) super.onBackPressed();

        return super.onOptionsItemSelected(item);
    }



    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem item = menu.findItem(R.id.menu_add);
        item.setVisible(false);
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == 1) {
                //ivImage.setImageBitmap(null);
                ivImage.destroyDrawingCache();

                //bitmap = BitmapFactory.decodeFile(pathToFile);
                ivImage.setRotation(0);

                Glide.with(this).load(pathToFile).into(ivImage);

                url="";

                //ivImage.setImageBitmap(bitmap);

            }
        }
    }

    private void dispatchPictureTakerAction() {

        Intent takePic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePic.resolveActivity(getPackageManager()) != null) {

            File photoFile = null;
            photoFile = createPhotoFile();

            if (photoFile != null) {

                pathToFile = photoFile.getAbsolutePath();
                photoUrI = FileProvider.getUriForFile(AddNote.this, "com.mynotebook.cameraandroid.fileprovider", photoFile);
                takePic.putExtra(MediaStore.EXTRA_OUTPUT, photoUrI);
                startActivityForResult(takePic, 1);

            }

        }

    }

    private File createPhotoFile() {

        String name = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File storageDir = getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = null;

        try {
            image = File.createTempFile(name, ".jpg", storageDir);

        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }

        return image;

    }

    public void longClick() {
        if (url=="") btnDelete.setVisibility(View.VISIBLE);
    }

    public void deleteImage(View v) {

        url = "https://www.androidpolice.com/wp-content/uploads/2015/02/nexus2cee_MotorolaCameraNew.png";

        Glide.with(this).load(url).into(ivImage);
        btnDelete.setVisibility(View.INVISIBLE);
    }

/*    public ArrayList<HashMap<String, String>> noteList(){

        ArrayList<HashMap<String, String>> noteList = new ArrayList<HashMap<String, String>>();
        dbHelper = new DatabaseHelper(this);

        noteList = dbHelper.notes();

        return noteList;

    }*/


    private void SaveNote() {
        String note_explanation = etNote.getText().toString();

        if (pathToFile==null){
            if (note_explanation!=null) {
                dbHelper = new DatabaseHelper(getApplicationContext());
                dbHelper.addNote(note_explanation);
                Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
            }
        }else {
            if (note_explanation!=null) {
                dbHelper = new DatabaseHelper(getApplicationContext());
                dbHelper.addNoteImage(note_explanation,pathToFile);
                Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
