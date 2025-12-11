# ProjectASD

Sistem Lost & Found Fasilkom UNSIKA merupakan aplikasi sederhana berbasis Java Console yang dirancang untuk membantu proses pencatatan laporan kehilangan dan penemuan barang di lingkungan Fakultas Ilmu Komputer. Sistem ini berjalan melalui Command Line Interface (CLI), sehingga pengguna berinteraksi langsung melalui menu teks, input keyboard, dan tampilan hasil dalam bentuk teks yang terstruktur.

Meskipun sederhana, sistem ini mengimplementasikan konsep dasar Struktur Data dan Algoritma seperti ArrayList, Quick Sort, dan Binary Search, sehingga tidak hanya berguna untuk kebutuhan pendataan, tetapi juga sebagai media pembelajaran pemrograman bagi mahasiswa.


# Nama Anggota Kelompok
- Annisa Balqis Kusuma Putri  
- Kintan Dyah Astuti          
- Akbar Tri Putranto          
- Raynaldi Dwi Arnanto       

# Fitur Utama Sistem
1. Lapor Kehilangan Barang (Lost Information)
- Pengguna dapat memasukkan data kehilangan barang dengan mengisi beberapa informasi penting seperti:
- Kode referensi barang
- Nama atau jenis barang
- Deskripsi barang (warna, merek, kondisi, ciri-ciri)
- Lokasi perkiraan hilang
- Tanggal kehilangan
- Identitas pelapor
- Data yang dimasukkan langsung ditambahkan ke dalam ArrayList sebagai objek baru.

2. Catat Penemuan Barang (Found Information)
- Sistem juga dapat digunakan oleh staf atau mahasiswa untuk mencatat barang yang ditemukan. Informasi yang dimasukkan meliputi:
- Kode barang
- Nama/jenis barang
- Deskripsi
- Lokasi ditemukan
- Tanggal penemuan
- Nama penemu
- Data yang diklasifikasikan sebagai “DITEMUKAN” ini akan tersimpan bersama data lainnya.

3. Menampilkan Semua Data (Quick Sort)
- Sebelum ditampilkan, seluruh daftar barang akan diurutkan berdasarkan nama barang menggunakan algoritma Quick Sort.
Hal ini membuat tampilan data menjadi lebih rapi, sistematis, dan mudah dibaca.
- Hasil yang ditampilkan mencakup:
- Status barang (KEHILANGAN / DITEMUKAN)
- Nama barang
- Kode referensi
- Deskripsi barang
- Lokasi
- Tanggal
- Kontak pelapor atau penemu
- Semua informasi ditata dalam format teks yang konsisten.

4. Pencarian Barang (Binary Search)
- Untuk mempercepat proses pencarian, sistem menggunakan Binary Search pada data yang sebelumnya sudah diurutkan.
- Pengguna cukup memasukkan nama barang yang ingin dicari, dan jika ditemukan akan langsung ditampilkan detailnya.
- Jika tidak ditemukan, sistem akan memberikan notifikasi bahwa barang tidak ada dalam data
