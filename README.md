Penjelasan Tentang Aplikasi
1.	Deskripsi Aplikasi 
Aplikasi CatatanHarianAgus ini digunakan untuk membuat, melihat, mengedit, dan menghapus catatan harian berupa momen penting, ide, dan tugas.

2.	Fitur Aplikasi
•	Menyimpan Catatan: Pengguna dapat menambah catatan baru dengan judul dan isi.
•	Melihat Catatan: Catatan ditampilkan dalam daftar menggunakan RecyclerView, memudahkan pengguna melihat semua catatan yang tersimpan.
•	Mengedit dan Menghapus Catatan: Dari DetailActivity, pengguna dapat memperbarui atau menghapus catatan yang telah ada.
•	Login dan Registrasi: Memfasilitasi pengguna untuk masuk atau mendaftar dalam aplikasi, yang menyimpan preferensi login dalam SharedPreferences.

3.	Komponen yang Digunakan
•	ConstraintLayout: sebagai kontainer fleksibel untuk posisi dan ukuran widget.
•	Activity (MainActivity, LoginActivity, RegisterActivity, DetailActivity): Mengatur tampilan dan interaksi utama dalam aplikasi.
•	Listview/RecyclerView: Menampilkan daftar catatan yang efisien.
•	EditText: Memasukkan dan mengedit judul serta isi catatan.
•	Toast: Untuk menampilkan pemberitahuan setelah aksi dijalankan dengan waktu yang singkat dan menghilang secara otomatis.
•	Floating Action Button: Tombol untuk menambahkan catatan baru, menyimpan perubahan dan menghapus catatan yang sudah ada sebelumnya.
•	SQLite: Menyimpan dan mengelola data catatan secara lokal.
•	SharedPreferences: Menyimpan informasi login pengguna.

4.	Implementasi Kode Detail
•	DatabaseHelper: Mengelola database SQLite untuk operasi seperti menambah, mengedit, dan menghapus catatan termasuk metode insertNote, updateNote, dan deleteNote.
•	DetailActivity: Mengelola tampilan untuk menambah atau mengedit catatan. Pengguna dapat menyimpan perubahan atau menghapus catatan melalui tombol di FloatingActionButton.
•	MainActivity: Digunakan sebagai layar utama, untuk menampilkan semua catatan yang ada dan memberikan opsi untuk menambah catatan baru. Catatan ditampilkan menggunakan RecyclerView yang diisi oleh adapter kustom.
•	NoteAdapter: Adapter yang menghubungkan data catatan dengan RecyclerView. Adapter ini menangani pemuatan data catatan ke dalam tampilan dan menyediakan callback untuk interaksi seperti edit dan hapus.
•	LoginActivity dan RegisterActivity: Menangani proses autentikasi pengguna. 
•	LoginActivity mengelola proses login dengan memverifikasi kredensial dari SharedPreferences, sedangkan RegisterActivity untuk mendaftar dan menyimpan informasi pengguna baru.




