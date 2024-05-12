package com.data.catatanharianagus;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    private EditText editTextTitle, editTextContent;
    private DatabaseHelper databaseHelper;
    private Note currentNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextContent = findViewById(R.id.editTextContent);
        databaseHelper = DatabaseHelper.getInstance(this);

        int noteId = getIntent().getIntExtra("NOTE_ID", -1);
        if (noteId == -1) {
            currentNote = new Note(-1, "", "", ""); // Default values for new note
        } else {
            currentNote = databaseHelper.getNoteById(noteId);
            if (currentNote != null) {
                editTextTitle.setText(currentNote.getTitle());
                editTextContent.setText(currentNote.getContent());
            } else {
                Toast.makeText(this, "Catatan tidak ditemukan", Toast.LENGTH_SHORT).show();
                finish(); // Tutup activity jika catatan tidak ditemukan
            }
        }

        findViewById(R.id.buttonSave).setOnClickListener(v -> saveNote());
        findViewById(R.id.fabDeleteNote).setOnClickListener(v -> deleteNote());
    }

    private void saveNote() {
        String title = editTextTitle.getText().toString();
        String content = editTextContent.getText().toString();
        if (currentNote.getId() == -1) {
            // Menyimpan catatan baru
            long id = databaseHelper.insertNote(new Note(0, title, content, "Tanggal hari ini"));
            if (id > 0) {
                Toast.makeText(this, "Catatan berhasil ditambahkan", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Gagal menambahkan catatan", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Memperbarui catatan yang ada
            currentNote.setTitle(title);
            currentNote.setContent(content);
            int result = databaseHelper.updateNote(currentNote);
            if (result > 0) {
                Toast.makeText(this, "Catatan berhasil diperbarui", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Gagal memperbarui catatan", Toast.LENGTH_SHORT).show();
            }
        }
        finish(); // Menutup activity setelah simpan atau update
    }

    private void deleteNote() {
        if (currentNote != null && currentNote.getId() != -1) {
            databaseHelper.deleteNote(currentNote.getId());
            Toast.makeText(this, "Catatan berhasil dihapus", Toast.LENGTH_SHORT).show();
            finish(); // Menutup activity setelah menghapus
        } else {
            Toast.makeText(this, "Tidak ada catatan untuk dihapus", Toast.LENGTH_SHORT).show();
        }
    }
}
