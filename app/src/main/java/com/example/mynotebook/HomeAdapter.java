package com.example.mynotebook;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;



public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    ArrayList<HomeList> mDataList;

    LayoutInflater inflater; // layout inflate Xml'i javaya cevirir. inflate() methodu geriye view dondurur.
    // inflater'ı new Inflater() methoduyla olusturamayız bu yuzden constructer olusturmalıyız ve içine tanımalanan context ile inflate olusturmalıyız:
    // bu adapter için bir nesne yaratılırken benim her zaman bir contexte ihityacım var.
    //Bunun için :

    public HomeAdapter(Context context, ArrayList<HomeList> data) {

        inflater = LayoutInflater.from(context);
        //veya
        //inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.mDataList = data;

    }


    @Override
    //ekranda gosterilecek olanları yaratır bir kez yaratması yeterli.
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.homelist_item, parent, false);
        // homelist_item daki xmli javaya donusturup viewin içine attık. view ı de holdera
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;


    }

    @Override
    //tıklanan nesnenin bilgilerini viewholdera bağlıyor
    public void onBindViewHolder(MyViewHolder holder, int position) {

        HomeList selected = mDataList.get(position);
        holder.setData(selected, position);

    }

    @Override
    //listenin eleman sayısı
    public int getItemCount() {
        return mDataList.size();
    }


    //data ekleme işlemleri için :
    class MyViewHolder extends RecyclerView.ViewHolder {


        TextView mHomeListTitle;
        ImageView mHomeListImage;
        int selectedposition = 0;
        Notes notes = new Notes();


        public MyViewHolder(final View itemView) {
            super(itemView);

            mHomeListTitle = itemView.findViewById(R.id.tvHomeTitle);
            mHomeListImage = itemView.findViewById(R.id.imgHomeImage);


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
            });

        }

        public void setData(HomeList selected, int position) {

            this.mHomeListTitle.setText(selected.getTitle());
            this.mHomeListImage.setImageResource(selected.getImageId());
            this.selectedposition = position;


        }


    }


}
