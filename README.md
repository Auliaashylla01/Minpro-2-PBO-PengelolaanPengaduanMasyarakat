# Sistem Pengelolaan Pengaduan Masyarakat

**Oleh Aulia Ashylla Ananda Putri Hariawan (2509116076)**

## 1. Deskripsi Singkat Program

Sistem Pengelolaan Pengaduan Masyarakat merupakan program yang digunakan untuk mencatat dan mengelola data pengaduan yang disampaikan oleh masyarakat. Program ini dapat digunakan untuk menangani berbagai jenis laporan, seperti fasilitas umum, kebersihan, keamanan, jalan, dan pelayanan.

Setiap data pengaduan memiliki informasi berupa ID Pengaduan, Nama Pelapor, Jenis Pengaduan, Isi Pengaduan, Tanggal Pengaduan, Tingkat Urgensi, dan Status Pengaduan. ID pengaduan dibuat secara otomatis oleh sistem dengan format `P001`, `P002`, dan seterusnya. Jenis pengaduan dipilih melalui kategori yang telah disediakan sehingga data yang dimasukkan lebih terstruktur.

Program membedakan pengaduan menjadi dua jenis berdasarkan tingkat urgensinya, yaitu **pengaduan biasa** dan **pengaduan darurat**. Pengaduan biasa memiliki target penyelesaian standar 7 hari, sedangkan pengaduan darurat memiliki target respons awal standar 24 jam dan menyimpan kontak darurat pelapor.

Pengguna dapat menjalankan beberapa fitur utama melalui menu interaktif, yaitu Tambah Pengaduan, Lihat Pengaduan, Ubah Status Pengaduan, Hapus Pengaduan, dan Keluar. Data selama program berjalan disimpan menggunakan `ArrayList`.

---

## 2. Tujuan Program

Program ini dibuat sebagai penerapan konsep Pemrograman Berorientasi Objek melalui sebuah sistem pengelolaan pengaduan sederhana.

Tujuan program adalah:

* Mencatat data pengaduan masyarakat secara terstruktur.
* Mengelompokkan pengaduan berdasarkan jenis dan tingkat urgensi.
* Menampilkan data pengaduan yang tersimpan.
* Mengubah status pengaduan berdasarkan tahapan penanganan.
* Menghapus data pengaduan berdasarkan ID.

---

## 3. Struktur Program

Program terdiri dari beberapa class yang dikelompokkan berdasarkan fungsinya.

| Package      | Class                           | Peran                                                                                                                          |
| ------------ | ------------------------------- | ------------------------------------------------------------------------------------------------------------------------------ |
| `main`       | `PengelolaanPengaduanMasyarakt` | Menjadi *entry point* program dan mengatur alur utama menu serta proses input pengguna.                                        |
| `main`       | `ValidasiInput`                 | Menangani validasi berbagai input pengguna agar data sesuai aturan yang ditentukan.                                            |
| `controller` | `PengelolaDataPengaduan`        | Mengelola data pengaduan dalam `ArrayList`, menjalankan proses tambah, cari, ubah status, hapus, dan menghasilkan ID otomatis. |
| `model`      | `Pengaduan`                     | Menjadi superclass yang menyimpan atribut dan data umum dari sebuah pengaduan.                                             |
| `model`      | `pengaduanBiasa`                | Subclass dari `Pengaduan` untuk data pengaduan dengan tingkat urgensi biasa.                                                        |
| `model`      | `pengaduanDarurat`              | Subclass dari `Pengaduan` untuk data pengaduan dengan tingkat urgensi darurat.                                                      |
| `view`       | `PengaduanView`                 | Mengatur tampilan program pada terminal, seperti menu, judul, pilihan, informasi, pesan sukses, dan pesan error.               |

### Struktur package

```text
src/
└── main/
    └── java/
        ├── main/
        │   ├── PengelolaanPengaduanMasyarakt.java
        │   └── ValidasiInput.java
        │
        ├── controller/
        │   └── PengelolaDataPengaduan.java
        │
        ├── model/
        │   ├── Pengaduan.java
        │   ├── pengaduanBiasa.java
        │   └── pengaduanDarurat.java
        │
        └── view/
            └── PengaduanView.java
```

---

## 4. Menu Program

Menu utama yang tersedia pada sistem adalah:

<img width="280" height="119" alt="image" src="https://github.com/user-attachments/assets/f8c819d5-7c75-4229-99bd-28b323aa2432" />                    

*Gambar 1: Tampilan menu utama Sistem Pengelolaan Pengaduan Masyarakat sebagai pusat navigasi seluruh fitur program.*

Menu tersebut digunakan sebagai pusat navigasi program. Setelah pengguna menyelesaikan suatu proses, program akan kembali ke menu utama selama pengguna belum memilih menu Keluar.


---

# 5. Alur Program

## 5.1 Alur Sistem

Secara umum, sistem dimulai dengan menyediakan data awal pada `ArrayList`, kemudian pengguna dapat memilih fitur yang tersedia melalui menu utama.

```text
                          ┌──────────────┐
                          │    MULAI     │
                          └──────┬───────┘
                                 │
                                 ▼
                      Inisialisasi Controller
                                 │
                                 ▼
                       Muat Dummy Data Awal
                                 │
           ┌─────────────────────┴─────────────────────┐
           │                                           │
           ▼                                           │
┌────────────────────┐                                 │
│   Tampilkan Menu   │◄────────────────────────────┐   │
└─────────┬──────────┘                             │   │
          │                                        │   │
          ▼                                        │   │
┌────────────────────┐                             │   │
│   Input Pilihan    │                             │   │
│        Menu        │                             │   │
└─────────┬──────────┘                             │   │
          │                                        │   │
          ▼                                        │   │
  ┌───────────────┐                                │   │
  │ Pilihan Menu? │                                │   │
  └───────┬───────┘                                │   │
          │                                        │   │
  ┌───────┼─────────────┬──────────────┬───────────┤   │
  │       │             │              │           │   │
  ▼       ▼             ▼              ▼           ▼   │
 [1]     [2]           [3]            [4]         [5]  │
Tambah  Lihat          Ubah          Hapus       Keluar│
  │       │             │              │           │   │
  ▼       ▼             ▼              ▼           ▼   │
Proses  Proses        Proses        Proses      Selesai│
  │       │             │              │               │
  └───────┴──────┬──────┴──────────────┘               │
                 │                                     │
                 ▼                                     │
          Kembali ke Menu ─────────────────────────────┘
```

Alur sistem tersebut menunjukkan bahwa setiap fitur bekerja melalui menu utama dan setelah proses selesai pengguna kembali ke menu. Program akan berhenti ketika pilihan menu bernilai `5`.

---

## 5.2 Alur Tambah Pengaduan

Fitur **Tambah Pengaduan** digunakan untuk membuat data pengaduan baru.

Urutan prosesnya adalah:

```text
Pilih Menu Tambah Pengaduan
                         │
                         ▼
                Generate ID Otomatis
                         │
                         ▼
                Input Nama Pelapor
                         │
                         ▼
              Pilih Jenis Pengaduan
                         │
                         ▼
                 Input Isi Pengaduan
                         │
                         ▼
               Input Tanggal Pengaduan
                         │
                         ▼
               Pilih Tingkat Urgensi?
                  ┌──────┴──────┐
                  │             │
                  ▼             ▼
                Biasa        Darurat
                  │             │
                  ▼             ▼
             Buat Object   Input Kontak
           pengaduanBiasa    Darurat
                  │             │
                  │             ▼
                  │        Buat Object
                  │      pengaduanDarurat
                  │             │
                  └──────┬──────┘
                         │
                         ▼
                Simpan ke ArrayList
                         │
                         ▼
                Tampilkan Berhasil
                         │
                         ▼
                 Kembali ke Menu
```

ID tidak dimasukkan secara manual oleh pengguna. Sistem menghasilkan ID berdasarkan ID yang belum digunakan, misalnya `P001`, `P002`, dan seterusnya.

Jenis pengaduan juga tidak dimasukkan sebagai teks bebas. Pengguna memilih salah satu dari lima kategori yang disediakan, yaitu:

```text
1. Fasilitas Umum
2. Kebersihan
3. Keamanan
4. Jalan
5. Pelayanan
```

Pada tahap berikutnya pengguna memilih tingkat urgensi:

```text
1. Biasa
2. Darurat
```

Pilihan tersebut menentukan object subclass yang dibuat. Pengaduan biasa akan menghasilkan object `pengaduanBiasa`, sedangkan pengaduan darurat akan menghasilkan object `pengaduanDarurat`.

### Bukti output proses tambah pengaduan biasa

<img width="334" height="298" alt="image" src="https://github.com/user-attachments/assets/dfbd73e3-e3bb-42e5-a5b6-16b4828c6dbb" />                      

*Gambar 2: Proses penambahan pengaduan biasa, mulai dari ID otomatis, pengisian data pengaduan, pemilihan jenis dan urgensi, hingga data berhasil disimpan.*                 

### Bukti output proses tambah pengaduan darurat            

<img width="374" height="306" alt="image" src="https://github.com/user-attachments/assets/2dbff5be-98f0-43bc-88fe-7545cee8f4b1" />        

*Gambar 3: Proses penambahan pengaduan darurat yang menghasilkan object `pengaduanDarurat` dan meminta input kontak darurat pelapor.*          

---

## 5.3 Alur Lihat Pengaduan

Fitur **Lihat Pengaduan** digunakan untuk menampilkan seluruh data yang tersimpan pada `ArrayList`.


<img width="464" height="283" alt="image" src="https://github.com/user-attachments/assets/05dbb5a0-5465-4e98-bd92-143d5d8dfaa0" />                        

*Gambar 4: Tampilan data pengaduan pada fitur Lihat Pengaduan, termasuk data dummy yang telah tersedia sejak program dijalankan.*   


Data dummy telah dimasukkan sejak `PengelolaDataPengaduan` dibuat. Oleh karena itu, ketika program pertama kali menjalankan menu Lihat Pengaduan, data sudah langsung tersedia tanpa harus melakukan proses tambah terlebih dahulu.                    

```text
Pilih Menu Lihat Pengaduan
          ↓
Controller mengambil ArrayList
          ↓
View menerima daftar pengaduan
          ↓
Perulangan menampilkan setiap object
          ↓
Method getDetailPengaduan()
          ↓
Data tampil pada terminal
```
---

## 5.4 Alur Ubah Status Pengaduan

Fitur **Ubah Status Pengaduan** menggunakan alur status bertahap agar perubahan status tidak dapat dilakukan secara acak.

Urutan status adalah:

```text
Menunggu Konfirmasi Petugas
             ↓
       Sedang Diproses
             ↓
   Selesai Ditindaklanjuti
```

Pengguna memasukkan ID pengaduan yang akan diubah. Sistem kemudian mencari data berdasarkan ID tersebut. Setelah data ditemukan, sistem menentukan status berikutnya berdasarkan status saat ini.

Apabila pengaduan sudah berada pada status `Selesai Ditindaklanjuti`, status tidak dapat diubah lagi.


<img width="377" height="242" alt="image" src="https://github.com/user-attachments/assets/bac2cd7f-d218-4dde-9450-e6c6bf2493e8" />                

*Gambar 5: Proses perubahan status pengaduan berdasarkan ID dengan konfirmasi pengguna sebelum status diperbarui.*        

```text
Pilih Ubah Status
                      │
                      ▼
           ┌► Input ID Pengaduan ◄────────────────┐
           │          │                           │
           │          ▼                           │
           │ Cari Data berdasarkan ID             │
           │          │                           │
           │          ▼                           │
           │   Data ditemukan?                    │
           │     ┌────┴────┐                      │
           │   Tidak       Ya                     │
           │     │          │                     │
           │     ▼          ▼                     │
           └── Error  Cek Status Saat Ini         │
                            │                     │
                            ▼                     │
                 Tentukan Status Berikutnya       │
                            │                     │
                            ▼                     │
                 Konfirmasi perubahan?            │
                     ┌──────┴──────┐              │
                     │             │              │
                     ▼             ▼              │
                    'y'           'n'             │
                     │             │              │
                     ▼             ▼              │
                Ubah Status      Batal            │
                     │             │              │
                     └──────┬──────┘              │
                            │                     │
                            ▼                     │
                     Kembali ke Menu ─────────────┘
```

---

## 5.5 Alur Hapus Pengaduan

Fitur **Hapus Pengaduan** digunakan untuk menghapus data berdasarkan ID.

<img width="328" height="239" alt="image" src="https://github.com/user-attachments/assets/11717486-9d2c-4af5-b98a-645641ac31d8" />            

*Gambar 6: Proses penghapusan data pengaduan melalui pencarian ID dan konfirmasi pengguna sebelum data dihapus.*


Sebelum data benar-benar dihapus, sistem menampilkan ringkasan data dan meminta konfirmasi. Hal tersebut mencegah penghapusan dilakukan secara langsung tanpa persetujuan pengguna.


```text
Pilih Hapus Pengaduan
                         │
                         ▼
           ┌► Input ID Pengaduan
           │             │
           │             ▼
           │       Cari Pengaduan
           │             │
           │             ▼
           │      Data ditemukan?
           │        ┌────┴────┐
           │      Tidak      Ya
           │        │         │
           │        ▼         ▼
           └─── Error    Tampilkan Ringkasan Data
                              │
                              ▼
                     Konfirmasi (y/n)?
                        ┌─────┴─────┐
                        │           │
                        ▼           ▼
                       'y'         'n'
                        │           │
                        ▼           ▼
                      Hapus       Batal
                        │           │
                        └─────┬─────┘
                              │
                              ▼
                       Kembali ke Menu
```

---

## 5.6 Alur Keluar Program

Ketika pengguna memilih menu `5`, program menampilkan pesan penutup dan mengakhiri perulangan menu.

<img width="380" height="170" alt="image" src="https://github.com/user-attachments/assets/e3a15630-7d23-4331-92f8-e794194d1c5a" />                    

*Gambar 7: Tampilan ketika pengguna memilih menu Keluar dan program mengakhiri proses.*

---

# 6. Penerapan Ketentuan OOP

## A. Access Modifier

Access modifier digunakan untuk mengatur hak akses terhadap atribut dan method di dalam class.

Pada class `Pengaduan`, seluruh atribut utama menggunakan `private`:

<img width="503" height="120" alt="image" src="https://github.com/user-attachments/assets/b669957c-23b5-4e6c-960b-8462c1d6283d" />                

*Gambar 9: Penerapan access modifier `private` pada atribut class `Pengaduan` untuk membatasi akses langsung terhadap data pengaduan.*            

Atribut tersebut tidak dapat diakses secara langsung dari class lain. Akses terhadap data dilakukan melalui method yang telah disediakan.            

---

## B. Encapsulation

**Encapsulation** diterapkan dengan menyembunyikan data internal object melalui atribut `private` dan menyediakan method `getter` serta `setter` sesuai kebutuhan.

Contoh penerapannya pada class `Pengaduan`:

<img width="526" height="143" alt="image" src="https://github.com/user-attachments/assets/c8f08e32-85c0-4df3-a6bc-b0eb24199b00" />                            

*Gambar 10: Penerapan encapsulation melalui method `getter` dan `setter` pada class `Pengaduan`.* 

Atribut `idPengaduan` tidak memiliki `setter`. Hal tersebut dilakukan karena ID dibuat secara otomatis oleh sistem dan digunakan sebagai identitas pengaduan sehingga tidak diubah melalui setter. Dengan demikian, data tidak diberikan akses langsung dari luar class, tetapi melalui method yang disediakan oleh class tersebut.                

---

## C. Inheritance

**Inheritance** digunakan dengan membuat class `Pengaduan` sebagai superclass yang memiliki atribut dan method umum untuk seluruh jenis pengaduan.

Dua subclass mewarisi class tersebut:                        

<img width="420" height="50" alt="image" src="https://github.com/user-attachments/assets/7867d53a-aab1-4c7e-b240-38ac9667ed98" />                    

*Gambar 11: Penerapan inheritance pada class `pengaduanBiasa` melalui keyword `extends` dan penggunaan `super()` untuk memanggil constructor superclass `Pengaduan`.*

dan

<img width="449" height="62" alt="image" src="https://github.com/user-attachments/assets/8d4f92c8-39de-4783-9c28-5402edc97443" />            

*Gambar 12: Penerapan inheritance pada class `pengaduanDarurat` sebagai subclass kedua dari superclass `Pengaduan`.*

Pada constructor subclass digunakan `super()` untuk menginisialisasi atribut yang berasal dari superclass.

Contoh:

<img width="556" height="115" alt="image" src="https://github.com/user-attachments/assets/cbf53c77-af77-4ed7-bdb3-b739e0842dae" />            

*Gambar 12: Inisialisasi `super()` pada subclass*                

Dengan inheritance, atribut dan perilaku umum tidak perlu ditulis kembali pada masing-masing subclass.

---

# 7. Penerapan Validasi Input

Validasi input diterapkan agar data yang dimasukkan pengguna sesuai dengan aturan yang telah ditentukan dan mengurangi kemungkinan kesalahan saat program berjalan.

Sebagian besar validasi dipusatkan pada class `ValidasiInput`, sedangkan pilihan jenis pengaduan dan tingkat urgensi divalidasi melalui perulangan pada proses tambah pengaduan.

### Jenis validasi yang diterapkan

| Method / Proses           | Fungsi Validasi                                                                             |
| ------------------------- | ------------------------------------------------------------------------------------------- |
| `inputMenu()`             | Memastikan pilihan menu berupa angka 1–5.                                                   |
| `inputTidakKosong()`      | Mencegah input teks kosong atau hanya berisi spasi.                                         |
| `inputNamaPelapor()`      | Memastikan nama tidak kosong, minimal 3 karakter, serta hanya terdiri dari huruf dan spasi. |
| `inputTanggal()`          | Memeriksa struktur tanggal `dd/mm/yyyy`, bulan, dan jumlah hari pada bulan.                 |
| `inputidPengaduan()`      | Memastikan ID mengikuti format seperti `P001`.                                              |
| `inputKontakDarurat()`    | Memastikan nomor kontak diawali `08`, terdiri dari angka, dan berjumlah 10–13 digit.        |
| `inputKonfirmasi()`       | Membatasi jawaban konfirmasi hanya `y` atau `n`.                                            |
| Pemilihan jenis pengaduan | Membatasi pilihan pada kategori 1–5.                                                        |
| Pemilihan tingkat urgensi | Membatasi pilihan hanya 1 untuk biasa atau 2 untuk darurat.                                 |

Contoh validasi menu:

<img width="569" height="295" alt="image" src="https://github.com/user-attachments/assets/3beece82-cb04-4866-b829-6199935f2ac0" />

*Gambar 15: Penerapan validasi input pada class `ValidasiInput` untuk membatasi format dan nilai input pengguna.*

Jika input bukan angka atau berada di luar rentang tersebut, sistem akan menampilkan pesan error dan meminta input kembali.

### Bukti validasi melalui output program

Screenshot output juga digunakan untuk menunjukkan bahwa validasi benar-benar berjalan ketika pengguna memberikan input yang tidak sesuai.

Contoh yang dapat ditampilkan:

<img width="332" height="172" alt="image" src="https://github.com/user-attachments/assets/bd6f26c7-e4ba-4b4f-acd2-4f2ab71cc28c" />

*Gambar 16: Pengujian validasi input melalui terminal yang menunjukkan sistem menolak input tidak sesuai dan meminta pengguna memasukkan data kembali.*

---

# 8. Dummy Data Awal

Program memiliki dummy data awal yang dimasukkan ke dalam `ArrayList` pada constructor `PengelolaDataPengaduan`.

Data awal terdiri dari:

1. Pengaduan biasa atas nama Budi Santoso dengan jenis pengaduan Jalan.
2. Pengaduan darurat atas nama Siti Aminah dengan jenis pengaduan Keamanan.

Contoh penerapannya:

<img width="471" height="68" alt="image" src="https://github.com/user-attachments/assets/c69ef63d-3e6c-4051-9867-c98bc58a35d7" />

*Gambar 17: Penerapan dummy data awal pada `PengelolaDataPengaduan` melalui method `muatData()` yang dipanggil ketika controller dibuat.*

Kemudian method `muatData()` membuat object pengaduan dan memasukkannya ke dalam `ArrayList`.

---

# 9. Penerapan CRUD

Program menerapkan operasi pengelolaan data menggunakan `ArrayList`.

| Operasi    | Implementasi                                                                      |
| ---------- | --------------------------------------------------------------------------------- |
| **Create** | Menambahkan object `pengaduanBiasa` atau `pengaduanDarurat` ke dalam `ArrayList`. |
| **Read**   | Menampilkan seluruh data dari `ArrayList` melalui `PengaduanView`.                |
| **Update** | Mengubah status pengaduan berdasarkan ID.                                         |
| **Delete** | Menghapus object pengaduan berdasarkan ID.                                        |

Controller menjadi bagian yang menangani proses CRUD, sedangkan `PengaduanView` digunakan untuk menampilkan hasilnya kepada pengguna.

---

# 10. Ringkasan Penerapan Nilai Tambah

Nilai tambah yang diterapkan dalam program adalah:

### 1. Struktur MVC

### Penerapan struktur MVC

Program menggunakan konsep **Model-View-Controller (MVC)** untuk memisahkan tanggung jawab setiap bagian program.

* **Model** berisi representasi data dan perilaku objek pengaduan.
* **View** menangani tampilan yang ditampilkan kepada pengguna pada terminal.
* **Controller** menangani pengelolaan data dan proses CRUD.
* **Main** mengatur jalannya program serta menghubungkan input pengguna dengan controller dan view.
* **ValidasiInput** dipisahkan sebagai class khusus agar proses validasi input tidak bercampur dengan pengelolaan data.

Pemisahan ini digunakan untuk membedakan pengelolaan data, tampilan, dan proses pengelolaan sistem.

<img width="251" height="188" alt="image" src="https://github.com/user-attachments/assets/d8ec4acc-8f86-4ef4-ba74-4b5c0c2d97f8" />                        

*Gambar : Struktur package program yang menunjukkan pemisahan class menjadi package `main`, `model`, `controller`, dan `view` sebagai penerapan arsitektur MVC.*

### 2. Polymorphism

**Polymorphism** diterapkan menggunakan **method overriding** pada subclass.

Superclass `Pengaduan` memiliki method:            

<img width="650" height="205" alt="image" src="https://github.com/user-attachments/assets/b3ca9064-78c2-4da2-b692-f543c9115f32" />          

*Gambar : Method asal Override di Kedua Subclass.*

Method tersebut kemudian dioverride oleh masing-masing subclass:                  

<img width="712" height="176" alt="image" src="https://github.com/user-attachments/assets/8215712a-7919-430a-b6c1-ef21433246e7" />                    


*Gambar 13: Penerapan polymorphism melalui method overriding `getTingkatUrgensi()` dan `getDetailPengaduan()` pada subclass.*          

pada `pengaduanBiasa`, dan:                        

<img width="523" height="175" alt="image" src="https://github.com/user-attachments/assets/81a0c49c-0c07-47c8-ac51-4fb1021f8f9f" />                                    

*Gambar 13: Penerapan polymorphism melalui method overriding `getTingkatUrgensi()` dan `getDetailPengaduan()` pada subclass.*                

pada `pengaduanDarurat`.                  

Method `getDetailPengaduan()` juga dioverride pada kedua subclass untuk menambahkan informasi khusus sesuai jenis pengaduan.

Polymorphism juga terlihat pada penggunaan:                  

<img width="408" height="56" alt="image" src="https://github.com/user-attachments/assets/46895aa6-fe00-4edb-9be3-71bd97aabedc" />                      


Walaupun tipe referensinya adalah `Pengaduan`, object yang disimpan dapat berasal dari subclass `pengaduanBiasa` maupun `pengaduanDarurat`. Ketika method yang dioverride dipanggil, Java akan menjalankan implementasi sesuai object sebenarnya.                            

*Gambar 14: Penggunaan `ArrayList<Pengaduan>` pada controller yang memungkinkan object `pengaduanBiasa` dan `pengaduanDarurat` disimpan dalam satu koleksi.*                 
