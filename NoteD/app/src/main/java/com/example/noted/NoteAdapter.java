package com.example.noted;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ListItemHolder> {

    private MainActivity mMainActivity;
    private List<Note> mNoteList;

    // this constructor implies that when using this class, we will send  in a reference to main
    // activity of this app (MainActivity) and List/ArrayList
    public NoteAdapter (MainActivity mainActivity, List<Note> noteList) {
        this.mMainActivity = mainActivity;
        this.mNoteList = noteList;
    }

    @NonNull
    @Override
    public ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return null;
        // this code works by init itemView using LayoutInflater and our newly designed list_item layout
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,
                parent,
                false);
        return new ListItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemHolder holder, int position) {
        Note note = mNoteList.get(position);
        holder.mTitle.setText(note.getmTitle());

        //show the first 15 characters of actual note
        //unless, a short note then show half
        if(note.getmDescription().length() > 15) {
            holder.mDescription.setText(note.getmDescription().substring(0,15));
        } else {
            holder.mDescription.setText(note.getmDescription().substring(0,
                    note.getmDescription().length()/2));
        }
    }

    @Override
    public int getItemCount() {
        return mNoteList.size();
    }

    public void removeItem(int position) {
        mNoteList.remove(position);
        notifyItemRemoved(position);
    }

    public class ListItemHolder extends RecyclerView.ViewHolder{

        TextView mTitle;
        TextView mDescription;
        TextView mStatus;

        public ListItemHolder(View view) {
            super(view);
            mTitle = view.findViewById(R.id.textViewTitle);
            mDescription = view.findViewById(R.id.textViewDescription);
            mStatus = view.findViewById(R.id.textViewStatus);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mMainActivity.showNote(getAdapterPosition());
                    Toast.makeText(v.getContext(), "showinggggg my current note", Toast.LENGTH_LONG).show();
                }
            });
        }

//        @Override
//        public void onClick(View v) {
//            mMainActivity.showNote(getAdapterPosition());
//            Toast.makeText(v.getContext(), "showinggggg my current not", Toast.LENGTH_LONG).show();
//        }
    }
}
