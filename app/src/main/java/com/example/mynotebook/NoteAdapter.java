package com.example.mynotebook;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {

    ArrayList<NoteList> mDataList;

    LayoutInflater inflater;

    public NoteAdapter(Context context, ArrayList<NoteList> data) {

        inflater = LayoutInflater.from(context);
        //veya
        //inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.mDataList = data;

    }


    @NonNull
    @Override
    public NoteAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.notelist_item, parent, false);
        // homelist_item daki xmli javaya donusturup viewin içine attık. view ı de holdera
        NoteAdapter.MyViewHolder myViewHolder = new NoteAdapter.MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.MyViewHolder holder, int position) {

        NoteList selected = mDataList.get(position);
        holder.setData(selected, position);

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        TextView mNoteListTitle;
        ImageView mNoteListImage;
        int selectedposition = 0;
        Notes notes = new Notes();


        public MyViewHolder(final View itemView) {
            super(itemView);

            mNoteListTitle = itemView.findViewById(R.id.tvNoteTitle);
            mNoteListImage = itemView.findViewById(R.id.imgNoteImage);

/*
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (selectedposition == 0) {

                        Intent intent = new Intent(v.getContext(), Notes.class);
                        // intent.putExtra("Title","Notes");
                        v.getContext().startActivity(intent);

                    } else if (selectedposition == 1) {
                        Intent intent = new Intent(v.getContext(), Dates.class);
                        //intent.putExtra("Title","Dates");
                        v.getContext().startActivity(intent);
                    } else if (selectedposition == 2) {
                        Intent intent = new Intent(v.getContext(), Locations.class);
                        //intent.putExtra("Title","Locations");
                        v.getContext().startActivity(intent);
                    } else {
                        Intent intent = new Intent(v.getContext(), Movies.class);
                        //intent.putExtra("Title","Locations");
                        v.getContext().startActivity(intent);
                    }

                }
            });*/

        }

        public void setData(NoteList selected, int position) {

            this.mNoteListTitle.setText(selected.getTitle());
            this.mNoteListImage.setImageResource(selected.getImageId());
            this.selectedposition = position;


        }


    }

}
