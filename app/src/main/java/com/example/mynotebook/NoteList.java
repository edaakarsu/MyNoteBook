package com.example.mynotebook;

import android.provider.ContactsContract;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;

public class NoteList  {

    private int imageId;
    private String title;


    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static ArrayList<NoteList> getData(String nList[]) {

        ArrayList<NoteList> dataList = new ArrayList<NoteList>();

/*
        Notes ad = new Notes();

        if (ad.note_explanation.length!=0) {
            int length = ad.note_explanation.length-1;
            String nList[] = new String[length];

            for (int i=0; i<length+1;i++){
                nList[i] = ad.note_explanation[i];
            }*/

            int images = R.drawable.note_icon;
            //boolean check = nList.isEmpty();

            if (nList.length==0){
                NoteList n = new NoteList();
                n.setImageId(images);
                n.setTitle("My Note");
                dataList.add(n);
            }else {
                for (int i = 0; i < nList.length; i++) {

                    NoteList n = new NoteList();
                    n.setImageId(images);
                    n.setTitle(nList[i]);

                    dataList.add(n);
                }
            }

    //    }

        return dataList;
    }

}
