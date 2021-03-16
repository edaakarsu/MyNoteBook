package com.example.mynotebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.HashMap;
import android.widget.Toast;

import java.sql.Blob;
import java.util.concurrent.BlockingQueue;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "MyNoteBook_database";
    private static final String NOTE_TABLE_NAME = "Note_List";
    private static String NOTE_ID = "note_id";
    private static String NOTE_EXPLANATION= "Note_Explain";
    private static String NOTE_IMAGE= "Note_Image";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            String CREATE_TABLE="CREATE TABLE " + NOTE_TABLE_NAME + "("
                    + NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + NOTE_EXPLANATION + " TEXT" + ")";
            db.execSQL(CREATE_TABLE);
        }catch (Exception e){
            System.out.println("Something is wrong."+e.toString());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (newVersion > oldVersion) {
            db.execSQL("ALTER TABLE " +NOTE_TABLE_NAME+ " ADD COLUMN " +NOTE_IMAGE+ " VARCHAR(250)");
        }

    }

    public void addNote(String note_explanation){

        try {

            SQLiteDatabase db= this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(NOTE_EXPLANATION,note_explanation);

            db.insert(NOTE_TABLE_NAME,null,values);
            db.close();

        }catch (Exception e){

            System.out.println("Something is wrong in addnote"+e.toString());

        }


    }

    public void addNoteImage(String note_explanation,String pathToFile){

        try {

            SQLiteDatabase db= this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(NOTE_EXPLANATION,note_explanation);
            values.put(NOTE_IMAGE,pathToFile);

            db.insert(NOTE_TABLE_NAME,null,values);
            db.close();

        }catch (Exception e){

            System.out.println("Something is wrong in addnote"+e.toString());

        }


    }

    public HashMap<String, String> kitapDetay(int id){
        //Databeseden id si belli olan row u çekmek için.
        //Bu methodda sadece tek row değerleri alınır.
        //HashMap bir çift boyutlu arraydir.anahtar-değer ikililerini bir arada tutmak için tasarlanmıştır.
        //map.put("x","300"); mesala burda anahtar x değeri 300.

        HashMap<String,String> note = new HashMap<String,String>();
        try{
            String selectQuery = "SELECT " +NOTE_EXPLANATION+  " FROM " + NOTE_TABLE_NAME+ " WHERE id="+id;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            // Move to first row
            cursor.moveToFirst();
            if(cursor.getCount() > 0){
                note.put(NOTE_EXPLANATION, cursor.getString(1));
            }
            cursor.close();
            db.close();
        }catch (Exception e){
            System.out.println("Hashmapte hata var"+e.toString());
        }



        // return note
        return note;
    }


   public ArrayList<HashMap<String, String>> notes(){

        //Bu methodda ise tablodaki tüm değerleri alıyoruz
        //ArrayList adı üstünde Array lerin listelendiği bir Array.Burda hashmapleri listeleyeceğiz
        //Herbir satırı değer ve value ile hashmap a atıyoruz. Her bir satır 1 tane hashmap arrayı demek.
        //olusturdugumuz tüm hashmapleri ArrayList e atıp geri dönüyoruz(return).

       SQLiteDatabase db = this.getReadableDatabase();
       ArrayList<HashMap<String, String>> noteList = new ArrayList<HashMap<String, String>>();
        try {
            String selectQuery = "SELECT " +NOTE_EXPLANATION+ " " + NOTE_ID + " FROM " + NOTE_TABLE_NAME;
            Cursor cursor = db.rawQuery(selectQuery, null);

            // looping through all rows and adding to list

            if (cursor.moveToFirst()) {
                do {
                    HashMap<String, String> map = new HashMap<String, String>();
                    for(int i=0; i<cursor.getColumnCount();i++)
                    {
                        map.put(cursor.getColumnName(i), cursor.getString(i));
                    }

                    noteList.add(map);
                } while (cursor.moveToNext());
            }
            db.close();

        }catch (Exception e){
            System.out.println("Something is wrong in ArrayList"+e.toString());

        }

       return noteList;

    }



}
