package com.example.crudbelajar.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudbelajar.R;
import com.example.crudbelajar.model.Note;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.RecyclerViewAdapter> {

    private List<Note> notes;
    private Context context;
   private ItemClickListener itemClickListener;


    public MainAdapter(List<Note> notes, Context context, ItemClickListener itemClickListener) {
        this.notes = notes;
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_note,
                parent,false);
        return new RecyclerViewAdapter(view , itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter holder, int position) {
        Note note = notes.get(position);
        holder.tv_title.setText(note.getTitle());
        holder.tv_note.setText(note.getNote());
        holder.tv_date.setText(note.getDate());
        holder.card_item.setCardBackgroundColor(note.getColor());


    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

  class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv_title, tv_note, tv_date;
        CardView card_item;
        ItemClickListener itemClickListener;

        public RecyclerViewAdapter (View itemView, ItemClickListener itemClickListener){
            super(itemView);
            this.itemClickListener = itemClickListener;

            tv_title = itemView.findViewById(R.id.title);
            tv_note = itemView.findViewById(R.id.note);
            tv_date = itemView.findViewById(R.id.date);
            card_item = itemView.findViewById(R.id.card_item);

            card_item.setOnClickListener(this);
        }
        @Override
      public void onClick(View v){
            itemClickListener.onIntemClick(v, getAdapterPosition());
        }
    }

public interface ItemClickListener {
        void onIntemClick(View view,int position);
}

}
