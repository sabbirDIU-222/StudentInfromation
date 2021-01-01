package com.example.studentinfromation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.HardwareRenderer;
import android.view.ContextMenu;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {
    public static Context context;
    public static ArrayList<ModelDataClass> arrayList;
    OnpositionItemClick onpositionItemClick;

    public CardAdapter(OnpositionItemClick onpositionItemClick) {
        this.onpositionItemClick = onpositionItemClick;
    }

    // constructor
    public CardAdapter(Context context, ArrayList<ModelDataClass> arrayList,OnpositionItemClick onpositionItemClick) {
        this.context = context;
        this.arrayList = arrayList;
        this.onpositionItemClick = onpositionItemClick;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.sample_card_ui,parent,false);


        return new MyViewHolder(v,onpositionItemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.id.setText(String.valueOf(arrayList.get(position).studentid));
        holder.name.setText(arrayList.get(position).name);
        holder.coursecode.setText(arrayList.get(position).courses);
        holder.section.setText(arrayList.get(position).section);
        holder.status.setText(arrayList.get(position).regstatus);

      if(holder.status.getText().equals("Complete")){
            holder.status.setTextColor(Color.GREEN);
        }else if(holder.status.getText().equals("Pending")){
            holder.status.setTextColor(Color.RED);
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener , View.OnCreateContextMenuListener {

        TextView id,name,coursecode,section,status;
        OnpositionItemClick onpositionItemClick;
        CardView cardView;


        public MyViewHolder(@NonNull final View itemView,OnpositionItemClick onpositionItemClick) {
            super(itemView);

            id = itemView.findViewById(R.id.cardstudentId);
            name = itemView.findViewById(R.id.cardstudentName);
            coursecode = itemView.findViewById(R.id.cardstudentCourses);
            section = itemView.findViewById(R.id.cardstudentSection);
            status = itemView.findViewById(R.id.cardstudentStatus);
            this.onpositionItemClick = onpositionItemClick;
            cardView = itemView.findViewById(R.id.card);
            cardView.setOnCreateContextMenuListener(this);


            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            onpositionItemClick.onpositionclick(getAdapterPosition());
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("select any one");
            menu.add(getAbsoluteAdapterPosition(),101,0,"important");
            menu.add(getAbsoluteAdapterPosition(),102,1,"Delete");

        }
    }

    public void removeItem(int position){
        arrayList.remove(position);
        notifyDataSetChanged();
    }

    public interface OnpositionItemClick{
         void onpositionclick(int position);
    }
}
