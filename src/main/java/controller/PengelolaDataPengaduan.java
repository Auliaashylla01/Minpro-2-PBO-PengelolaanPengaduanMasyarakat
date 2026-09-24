/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.util.ArrayList;
//import model pengaduan untuk mengelola data dari superclass dan sub class
import model.Pengaduan;
import model.pengaduanBiasa;
import model.pengaduanDarurat;
/**
 *
 * @author ASUS
 */
//Class PengelolaDataPengaduan adalah sebagai Controller.
//Class PengelolaPengaduan berfungsi untuk mengelola daftar atau data objek Pengaduan.
public class PengelolaDataPengaduan {
    //Kumpulan data pengaduan disimpan dalam bentuk ArrayList
    private ArrayList<Pengaduan> daftarPengaduan;

    //Constructor untuk mengatur list pengaduan agar dapat digunakan (tidak null)
    public PengelolaDataPengaduan() {
        daftarPengaduan = new ArrayList<>();
        muatData();
    }
    //Metode privat untuk menambahkan data dummy ke dalam daftar.
    private void muatData(){
        Pengaduan p1 = new pengaduanBiasa(
                generateId(),
                "Budi Santoso",
                "Jalan",
                "Jalan berlubang cukup dalam di depan gang, mengganggu pengendara motor.",
                "20/09/2026"
        );
        daftarPengaduan.add(p1);
        Pengaduan p2 = new pengaduanDarurat (
                generateId(),
                "Siti Aminah",
                "Keamanan",
                "Terjadi percikan api di panel listrik dekat pasar, berisiko kebakaran.",
                "21/09/2026",
                "081234567890"
        );
        daftarPengaduan.add(p2);
    }
    
   //Mengecek apakah suatu ID Pengaduan sudah ada di dalam list.
    public boolean idSudahAda(String idPengaduan) {

        for (Pengaduan pengaduan : daftarPengaduan) {

            if (pengaduan.getIdPengaduan().equalsIgnoreCase(idPengaduan)) {
                return true; //ID ditemukan
            }
        }

        return false; // ID belum pernah dipakai
    }
    //Membuat ID Pengaduan otomatis dengan format P001, P002, dan seterusnya.
    public String generateId() {

    int nomor = 1;
    String idBaru;

    do {
        idBaru = String.format("P%03d", nomor); // Format 3 digit angka (P001)
        nomor++;
    } while (idSudahAda(idBaru));

    return idBaru;
}

    //Menambahkan data Pengaduan biasa baru ke dalam ArrayList.
    public void tambahPengaduanBiasa(String id, String nama, String jenis,
                String isi, String tanggal) {
        Pengaduan p = new pengaduanBiasa(id, nama, jenis, isi, tanggal);
        daftarPengaduan.add(p);
    }
    //Menambahkan data Pengaduan darurat baru ke dalam ArrayList.
    public void tambahPengaduanDarurat(String id, String nama, String jenis, String isi,
                String tanggal, String kontakDarurat) {
        Pengaduan p = new pengaduanDarurat(id, nama, jenis, isi, tanggal, kontakDarurat);
        daftarPengaduan.add(p);
    }
    //Untuk mengambil data pengaduan dan menjadikan nya akses public
    public ArrayList<Pengaduan> getDaftarPengaduan() {
        return daftarPengaduan;
    }
    //Mencari objek Pengaduan berdasarkan ID.
    public Pengaduan cariPengaduan(String idPengaduan) {

        for (Pengaduan pengaduan : daftarPengaduan) {

            if (pengaduan.getIdPengaduan().equalsIgnoreCase(idPengaduan)) {
                return pengaduan;
            }
        }

        return null; //Pengaduan tidak ditemukan
    }
    //Untuk memperbarui status sesuai dengan alur nya, sehingga status tidak bisa melompat dari menunggu ke selesai
    public String statusBerikutnya(String statusSaatIni) {

        if (statusSaatIni.equals("Menunggu Konfirmasi Petugas")) {
            return "Sedang Diproses";
        }

        if (statusSaatIni.equals("Sedang Diproses")) {
            return "Selesai Ditindaklanjuti";
        }

        return null;
    }
    //Memperbarui status dari data pengaduan berdasarkan ID.
    public boolean ubahStatus(String idPengaduan, String statusBaru) {

        Pengaduan pengaduan = cariPengaduan(idPengaduan);

        if (pengaduan != null) {
            pengaduan.setStatus(statusBaru);
            return true;
        }
        return false;
    }
    
    //Menghapus pengaduan dari ArrayList berdasarkan ID.
    public boolean hapusPengaduan(String idPengaduan) {

        Pengaduan pengaduan = cariPengaduan(idPengaduan);

        if (pengaduan != null) {
            daftarPengaduan.remove(pengaduan); //Hapus objek dari list
            return true;
        }

        return false;
    }
}
