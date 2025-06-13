# Tourism App - Final Lab Project

Tourism App adalah aplikasi desktop interaktif untuk mengeksplorasi destinasi wisata populer di Indonesia. Dibangun menggunakan Java dan JavaFX sebagai proyek akhir Lab Pemrograman Berorientasi Objek.

## Cara Menjalankan Aplikasi

1. **Persyaratan:**
   - Java JDK 11 atau lebih tinggi
   - JavaFX SDK (dikonfigurasi di IDE)

2. **Langkah-langkah:**
   - Clone repositori ini. https://github.com/Ghanyy25/-28--Tourism_App.git (Ghanyy25/-28--Tourism_App)
   - Buka project di IDE (disarankan: IntelliJ IDEA atau Eclipse).
   - Pastikan library JavaFX sudah dikonfigurasi (VM options: `--module-path {path to JavaFX lib} --add-modules javafx.controls,javafx.fxml`).
   - Jalankan file `App.java` dengan mengetik `./gradlew run` di terminal.

## Struktur Proyek

```
main/
│
├── java\tourismapp/
│ ├── App.java # Kelas utama yang mengatur navigasi antar scene
│ ├── Wisata.java # UI untuk memilih dan melihat detail destinasi wisata
│ ├── Planning.java # Perencanaan itinerary (jadwal kunjungan)
│ ├── Hotel.java # Model data hotel (nama, lokasi, harga, rating, dll)
│ └── UIHelp.java # Utilitas GUI seperti alert

```

## Penerapan Prinsip OOP

### 1. **Encapsulation (Enkapsulasi)**  
Menyembunyikan detail implementasi internal dan hanya mengekspos method-method yang dibutuhkan:
- Setiap class memiliki atribut `private` atau `protected` (misalnya `selectedDestination` di `App`).
- Method `getSelectedDestination()` dan `setSelectedDestination()` menyediakan akses terbatas.

Ini membantu menjaga integritas data dan mencegah manipulasi langsung dari luar kelas.

### 2. **Abstraction (Abstraksi)**  
Menghilangkan detail teknis dan hanya menampilkan fitur yang penting bagi pengguna:
- Kelas `UIHelp` menyembunyikan kompleksitas pembuatan tombol dan popup. Cukup memanggil `createMenuButton(...)` atau `showAlert(...)`.
- Dalam `Wisata.java`, cukup memilih destinasi tanpa perlu tahu bagaimana gambar dimuat dan ditampilkan (abstraksi galeri image dan layout).

Abstraksi membuat kode lebih mudah digunakan, dibaca, dan dipelihara.

### 3. **Inheritance (Pewarisan)**  
Memungkinkan kelas untuk mewarisi atribut dan method dari kelas lain:
- Kelas `App` mewarisi (`extends`) dari `Application`, kelas dasar JavaFX.
- Ini memungkinkan `App` untuk override method `start(Stage primaryStage)` — titik awal eksekusi JavaFX.

Pewarisan memungkinkan penggunaan kembali kode dan memperluas fungsionalitas.

### 4. **Polymorphism (Polimorfisme)**  
Kemampuan objek untuk mengambil banyak bentuk, terutama saat method yang sama menghasilkan perilaku berbeda:
- Penggunaan lambda expression seperti `button.setOnAction(e -> {...})` adalah bentuk polymorphism berbasis interface (`EventHandler`).
- Jika dikembangkan lebih lanjut, bisa menggunakan `interface` atau `abstract class` untuk berbagai tipe perencanaan perjalanan atau hotel, yang diimplementasikan berbeda-beda.

Polymorphism meningkatkan fleksibilitas dan memungkinkan arsitektur aplikasi yang lebih scalable dan modular.


## Fitur Aplikasi

- Menampilkan daftar destinasi wisata lengkap dengan detail dan foto
- Galeri foto interaktif untuk setiap destinasi
- Simpan pilihan destinasi untuk fitur selanjutnya
- Perencanaan wisata dan informasi hotel

## Tim Pengembang

**Kelompok 28**
- BISMILLAH GHANIYYU PUTRA DARMIN - H071241057
- SUCI SRI AULIA - H071241067
- MAHESA PUTRI LUKMAN - H071241009

---

