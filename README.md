# Sistem Pengelolaan Pengaduan Masyarakat

**Oleh Aulia Ashylla Ananda Putri Hariawan (2509116076)**

## 1. Deskripsi Singkat Program

**Sistem Pengelolaan Pengaduan Masyarakat** merupakan program yang digunakan untuk mencatat dan mengelola data pengaduan yang disampaikan oleh masyarakat. Program ini dapat digunakan untuk menangani berbagai jenis laporan, seperti fasilitas umum, kebersihan, keamanan, jalan, dan pelayanan.

Setiap data pengaduan memiliki informasi berupa **ID Pengaduan, Nama Pelapor, Jenis Pengaduan, Isi Pengaduan, Tanggal Pengaduan, Tingkat Urgensi, dan Status Pengaduan**. ID pengaduan dibuat secara otomatis oleh sistem dengan format `P001`, `P002`, dan seterusnya. Jenis pengaduan dipilih melalui kategori yang telah disediakan sehingga data yang dimasukkan lebih terstruktur.

Program membedakan pengaduan menjadi dua jenis berdasarkan tingkat urgensinya, yaitu **pengaduan biasa** dan **pengaduan darurat**. Pengaduan biasa memiliki target penyelesaian standar 7 hari, sedangkan pengaduan darurat memiliki target respons awal standar 24 jam dan menyimpan kontak darurat pelapor.

Pengguna dapat menjalankan beberapa fitur utama melalui menu interaktif, yaitu **Tambah Pengaduan, Lihat Pengaduan, Ubah Status Pengaduan, Hapus Pengaduan, dan Keluar**. Data selama program berjalan disimpan menggunakan `ArrayList`.

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

```text
==================================================
      SISTEM PENGELOLAAN PENGADUAN MASYARAKAT
==================================================
1. Tambah Pengaduan
2. Lihat Pengaduan
3. Ubah Status Pengaduan
4. Hapus Pengaduan
5. Keluar
==================================================
```

Menu tersebut digunakan sebagai pusat navigasi program. Setelah pengguna menyelesaikan suatu proses, program akan kembali ke menu utama selama pengguna belum memilih menu **Keluar**.

<!-- MASUKKAN SCREENSHOT OUTPUT MENU UTAMA DI SINI -->

*Gambar 2: Tampilan menu utama Sistem Pengelolaan Pengaduan Masyarakat sebagai pusat navigasi seluruh fitur program.*

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
          ↓
Generate ID Otomatis
          ↓
Input Nama Pelapor
          ↓
Pilih Jenis Pengaduan
          ↓
Input Isi Pengaduan
          ↓
Input Tanggal Pengaduan
          ↓
Pilih Tingkat Urgensi
       ↙         ↘
    Biasa       Darurat
      ↓             ↓
  Buat Object   Input Kontak
pengaduanBiasa    Darurat
      ↓             ↓
      └──────┬──────┘
             ↓
       Simpan ke ArrayList
             ↓
       Tampilkan Berhasil
             ↓
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

*Gambar 3: Proses penambahan pengaduan biasa, mulai dari ID otomatis, pengisian data pengaduan, pemilihan jenis dan urgensi, hingga data berhasil disimpan.*                 

### Bukti output proses tambah pengaduan darurat            

<img width="374" height="306" alt="image" src="https://github.com/user-attachments/assets/2dbff5be-98f0-43bc-88fe-7545cee8f4b1" />        

*Gambar 4: Proses penambahan pengaduan darurat yang menghasilkan object `pengaduanDarurat` dan meminta input kontak darurat pelapor.*          

---

## 5.3 Alur Lihat Pengaduan

Fitur **Lihat Pengaduan** digunakan untuk menampilkan seluruh data yang tersimpan pada `ArrayList`.

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

Data dummy telah dimasukkan sejak `PengelolaDataPengaduan` dibuat. Oleh karena itu, ketika program pertama kali menjalankan menu **Lihat Pengaduan**, data sudah langsung tersedia tanpa harus melakukan proses tambah terlebih dahulu.

<!-- MASUKKAN SCREENSHOT OUTPUT MENU LIHAT PENGADUAN DI SINI -->

*Gambar 5: Tampilan data pengaduan pada fitur **Lihat Pengaduan**, termasuk data dummy yang telah tersedia sejak program dijalankan.*

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

```text
Pilih Ubah Status
       ↓
Input ID Pengaduan
       ↓
Cari Data berdasarkan ID
       ↓
Data ditemukan?
   ┌────┴────┐
  Tidak      Ya
   │          │
   ▼          ▼
 Error    Cek Status Saat Ini
              ↓
       Tentukan Status Berikutnya
              ↓
       Konfirmasi perubahan y/n
          ┌───┴───┐
          │       │
          ▼       ▼
          y       n
          │       │
          ▼       ▼
     Ubah Status  Batal
          │       │
          └───┬───┘
              ▼
        Kembali ke Menu
```

<!-- MASUKKAN SCREENSHOT OUTPUT UBAH STATUS DI SINI -->

*Gambar 6: Proses perubahan status pengaduan berdasarkan ID dengan konfirmasi pengguna sebelum status diperbarui.*

---

## 5.5 Alur Hapus Pengaduan

Fitur **Hapus Pengaduan** digunakan untuk menghapus data berdasarkan ID.

```text
Pilih Hapus Pengaduan
         ↓
Input ID Pengaduan
         ↓
Cari Pengaduan
         ↓
Data ditemukan?
    ┌────┴────┐
   Tidak      Ya
    │          │
    ▼          ▼
  Error    Tampilkan Ringkasan Data
               ↓
        Konfirmasi y / n
            ┌──┴──┐
            │     │
            ▼     ▼
            y     n
            │     │
            ▼     ▼
          Hapus   Batal
            │     │
            └──┬──┘
               ▼
         Kembali ke Menu
```

Sebelum data benar-benar dihapus, sistem menampilkan ringkasan data dan meminta konfirmasi. Hal tersebut mencegah penghapusan dilakukan secara langsung tanpa persetujuan pengguna.

<!-- MASUKKAN SCREENSHOT OUTPUT HAPUS PENGADUAN DI SINI -->

*Gambar 7: Proses penghapusan data pengaduan melalui pencarian ID dan konfirmasi pengguna sebelum data dihapus.*

---

## 5.6 Alur Keluar Program

Ketika pengguna memilih menu `5`, program menampilkan pesan penutup dan mengakhiri perulangan menu.

<!-- MASUKKAN SCREENSHOT OUTPUT KELUAR DI SINI -->

*Gambar 8: Tampilan ketika pengguna memilih menu **Keluar** dan program mengakhiri proses.*

---

# 6. Penerapan Ketentuan OOP

## A. Access Modifier

Access modifier digunakan untuk mengatur hak akses terhadap atribut dan method di dalam class.

Pada class `Pengaduan`, seluruh atribut utama menggunakan `private`:

```java
private String idPengaduan;
private String namaPelapor;
private String jenisPengaduan;
private String isiPengaduan;
private String tanggalPengaduan;
private String status;
```

Atribut tersebut tidak dapat diakses secara langsung dari class lain. Akses terhadap data dilakukan melalui method yang telah disediakan.

<!-- MASUKKAN SCREENSHOT KODE ATRIBUT PRIVATE DI Pengaduan.java -->

*Gambar 9: Penerapan access modifier `private` pada atribut class `Pengaduan` untuk membatasi akses langsung terhadap data pengaduan.*

---

## B. Encapsulation

**Encapsulation** diterapkan dengan menyembunyikan data internal object melalui atribut `private` dan menyediakan method `getter` serta `setter` sesuai kebutuhan.

Contoh penerapannya pada class `Pengaduan`:

```java
public String getNamaPelapor() {
    return namaPelapor;
}

public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}
```

Atribut `idPengaduan` tidak memiliki `setter`. Hal tersebut dilakukan karena ID dibuat secara otomatis oleh sistem dan digunakan sebagai identitas pengaduan sehingga tidak diubah melalui setter.

Dengan demikian, data tidak diberikan akses langsung dari luar class, tetapi melalui method yang disediakan oleh class tersebut.

<!-- MASUKKAN SCREENSHOT GETTER DAN SETTER DI Pengaduan.java -->

*Gambar 10: Penerapan encapsulation melalui method `getter` dan `setter` pada class `Pengaduan`. Atribut `idPengaduan` hanya memiliki getter karena nilainya dibuat otomatis oleh sistem.*

---

## C. Inheritance

**Inheritance** digunakan dengan membuat class `Pengaduan` sebagai superclass yang memiliki atribut dan method umum untuk seluruh jenis pengaduan.

Dua subclass mewarisi class tersebut:

```java
public class pengaduanBiasa extends Pengaduan
```

dan

```java
public class pengaduanDarurat extends Pengaduan
```

Pada constructor subclass digunakan `super()` untuk menginisialisasi atribut yang berasal dari superclass.

Contoh:

```java
super(idPengaduan, namaPelapor, jenisPengaduan,
      isiPengaduan, tanggalPengaduan);
```

Dengan inheritance, atribut dan perilaku umum tidak perlu ditulis kembali pada masing-masing subclass.

<!-- MASUKKAN SCREENSHOT pengaduanBiasa.java -->

*Gambar 11: Penerapan inheritance pada class `pengaduanBiasa` melalui keyword `extends` dan penggunaan `super()` untuk memanggil constructor superclass `Pengaduan`.*

<!-- MASUKKAN SCREENSHOT pengaduanDarurat.java -->

*Gambar 12: Penerapan inheritance pada class `pengaduanDarurat` sebagai subclass kedua dari superclass `Pengaduan`.*

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

```java
if (menu >= 1 && menu <= 5) {
    return menu;
}
```

Jika input bukan angka atau berada di luar rentang tersebut, sistem akan menampilkan pesan error dan meminta input kembali.

<!-- MASUKKAN SCREENSHOT ValidasiInput.java BAGIAN inputMenu/inputNamaPelapor/inputTanggal -->

*Gambar 15: Penerapan validasi input pada class `ValidasiInput` untuk membatasi format dan nilai input pengguna.*

### Bukti validasi melalui output program

Screenshot output juga digunakan untuk menunjukkan bahwa validasi benar-benar berjalan ketika pengguna memberikan input yang tidak sesuai.

Contoh yang dapat ditampilkan:

```text
Pilih menu: abc
[Error] Input harus berupa angka!

Pilih menu: 9
[Error] Pilihan menu hanya dari 1 sampai 5!

Pilih menu: 1
```

Contoh lainnya dapat berupa input nama yang mengandung angka, tanggal dengan format yang salah, atau kontak darurat yang tidak sesuai aturan.

<!-- MASUKKAN SCREENSHOT OUTPUT VALIDASI DI SINI -->

*Gambar 16: Pengujian validasi input melalui terminal yang menunjukkan sistem menolak input tidak sesuai dan meminta pengguna memasukkan data kembali.*

---

# 8. Dummy Data Awal

Program memiliki **dummy data awal** yang dimasukkan ke dalam `ArrayList` pada constructor `PengelolaDataPengaduan`.

Data awal terdiri dari:

1. Pengaduan biasa atas nama **Budi Santoso** dengan jenis pengaduan **Jalan**.
2. Pengaduan darurat atas nama **Siti Aminah** dengan jenis pengaduan **Keamanan**.

Contoh penerapannya:

```java
public PengelolaDataPengaduan() {
    daftarPengaduan = new ArrayList<>();
    muatData();
}
```

Kemudian method `muatData()` membuat object pengaduan dan memasukkannya ke dalam `ArrayList`.

Dummy data dibuat agar fitur **Read/Lihat Pengaduan** dapat langsung diuji ketika program pertama kali dijalankan.

<!-- MASUKKAN SCREENSHOT controller BAGIAN constructor + muatData -->

*Gambar 17: Penerapan dummy data awal pada `PengelolaDataPengaduan` melalui method `muatData()` yang dipanggil ketika controller dibuat.*

<!-- MASUKKAN SCREENSHOT OUTPUT MENU 2 YANG MENAMPILKAN DUMMY DATA -->

*Gambar 18: Dummy data langsung tampil pada fitur **Lihat Pengaduan** tanpa harus melakukan input data terlebih dahulu.*

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

<img width="907" height="152" alt="image" src="https://github.com/user-attachments/assets/523c51d7-0ee0-42c8-9dc0-313216b0567e" />          

pada `pengaduanBiasa`, dan:             

*Gambar 13: Penerapan polymorphism melalui method overriding `getTingkatUrgensi()` dan `getDetailPengaduan()` pada subclass.*              
                           

<img width="791" height="179" alt="image" src="https://github.com/user-attachments/assets/4d29e4d0-896e-46fa-b724-6fd791a056f0" />           

pada `pengaduanDarurat`.                  


*Gambar 13: Penerapan polymorphism melalui method overriding `getTingkatUrgensi()` dan `getDetailPengaduan()` pada subclass.*                    
        

Method `getDetailPengaduan()` juga dioverride pada kedua subclass untuk menambahkan informasi khusus sesuai jenis pengaduan.

Polymorphism juga terlihat pada penggunaan:                  

<img width="408" height="56" alt="image" src="https://github.com/user-attachments/assets/46895aa6-fe00-4edb-9be3-71bd97aabedc" />                      


Walaupun tipe referensinya adalah `Pengaduan`, object yang disimpan dapat berasal dari subclass `pengaduanBiasa` maupun `pengaduanDarurat`. Ketika method yang dioverride dipanggil, Java akan menjalankan implementasi sesuai object sebenarnya.                            

*Gambar 14: Penggunaan `ArrayList<Pengaduan>` pada controller yang memungkinkan object `pengaduanBiasa` dan `pengaduanDarurat` disimpan dalam satu koleksi.*                 
