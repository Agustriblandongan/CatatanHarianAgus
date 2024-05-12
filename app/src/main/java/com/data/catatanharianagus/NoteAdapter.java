package com.data.catatanharianagus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private List<Note> notes; // Daftar catatan
    private OnNoteListener onNoteListener; // Listener untuk aksi pada catatan

    public NoteAdapter(List<Note> notes, OnNoteListener onNoteListener) {
        this.notes = notes;
        this.onNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(view, onNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.textViewTitle.setText(note.getTitle());
        holder.textViewContent.setText(note.getContent());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    // Metode untuk memperbarui daftar catatan
    public void setNotes(List<Note> newNotes) {
        this.notes = newNotes;
        notifyDataSetChanged();
    }

    // Metode untuk mendapatkan daftar catatan saat ini
    public List<Note> getNotes() {
        return notes;
    }

    public interface OnNoteListener {
        void onNoteEdit(int position);
        void onNoteDelete(int position);
    }

    class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewTitle, textViewContent;
        OnNoteListener onNoteListener;

        public NoteViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewContent = itemView.findViewById(R.id.text_view_content);
            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                onNoteListener.onNoteEdit(position);
            }
        }
    }
}



