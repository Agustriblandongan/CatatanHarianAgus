package com.data.catatanharianagus;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements NoteAdapter.OnNoteListener {
    private RecyclerView recyclerView;
    private NoteAdapter noteAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        FloatingActionButton fabAddNote = findViewById(R.id.fabAddNote);
        databaseHelper = DatabaseHelper.getInstance(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fabAddNote.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("NOTE_ID", -1); // Mengasumsikan -1 menunjukkan sebuah catatan baru
            startActivity(intent);
        });

        loadNotesAsync();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotesAsync(); // Pastikan untuk memuat ulang catatan untuk mencerminkan pembaruan apa pun
    }

    private void loadNotesAsync() {
        Executors.newSingleThreadExecutor().execute(() -> {
            List<Note> allNotes = databaseHelper.getAllNotes();
            runOnUiThread(() -> {
                if (noteAdapter == null) {
                    noteAdapter = new NoteAdapter(allNotes, this);
                    recyclerView.setAdapter(noteAdapter);
                } else {
                    noteAdapter.setNotes(allNotes);
                }
            });
        });
    }

    @Override
    public void onNoteEdit(int position) {
        Note note = noteAdapter.getNotes().get(position);
        if (note != null) {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("NOTE_ID", note.getId());
            startActivity(intent);
        }
    }

    @Override
    public void onNoteDelete(int position) {
        Note note = noteAdapter.getNotes().get(position);
        if (note != null) {
            databaseHelper.deleteNote(note.getId());
            loadNotesAsync(); // Memuat ulang catatan setelah penghapusan untuk memastikan tampilan diperbarui
        }
    }
}
