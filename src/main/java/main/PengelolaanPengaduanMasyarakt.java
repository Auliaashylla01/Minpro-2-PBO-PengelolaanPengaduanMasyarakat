/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import controller.PengelolaDataPengaduan;
import model.Pengaduan;
import view.PengaduanView;
import java.util.Scanner;
/**
 *
 * @author ASUS
 */
//Class Main (Utama)untuk mengatur jalannya alur menu
public class PengelolaanPengaduanMasyarakt {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        PengelolaDataPengaduan controller = new PengelolaDataPengaduan(); // 
        PengaduanView view = new PengaduanView(); //


        int menu;

        do {
            view.tampilkanHeader();
            view.tampilkanMenu();

            menu = ValidasiInput.inputMenu(scanner);
            //Menjalankan fitur sesuai menu yang dipilih
            switch (menu) {
                case 1 -> tambahPengaduan(scanner, controller, view);
                case 2 -> view.tampilkanDaftarPengaduan(controller.getDaftarPengaduan());
                case 3 -> ubahStatus(scanner, controller, view);
                case 4 -> hapusPengaduan(scanner, controller, view);
                case 5 -> view.tampilkanSalam();
                default -> view.tampilkanError("Menu tidak tersedia. Silakan pilih angka 1-5!");
            }
            //Jeda di menu agar output tidak langsung hilang
            if (menu != 5) {
               view.tekanEnterUntukLanjut(scanner);
            }

        } while (menu != 5);

        scanner.close();
    }
    private static void tambahPengaduan(Scanner scanner, PengelolaDataPengaduan controller,
                PengaduanView view) {

        view.tampilkanJudul("TAMBAH PENGADUAN");
        String id = controller.generateId();
        view.tampilkanIdOtomatis(id);
        String nama = ValidasiInput.inputNamaPelapor(scanner, "Masukkan Nama Pelapor: ");
        view.tampilkanPilihanJenisPengaduan();
        String jenis;
        while (true) {
            System.out.print("Pilih Jenis Pengaduan: ");
            String pilihanJenis = scanner.nextLine();
            switch (pilihanJenis) {
                case "1":
                    jenis = "Fasilitas Umum";
                    break;
                case "2":
                    jenis = "Kebersihan";
                    break;
                case "3":
                    jenis = "Keamanan";
                    break;
                case "4":
                    jenis = "Jalan";
                    break;
                case "5":
                    jenis = "Pelayanan";
                    break;
                default:
                    System.out.println("Pilihan jenis pengaduan tidak tersedia.");
                    continue;
            }
            break;
        }
        String isi = ValidasiInput.inputTidakKosong(scanner, "Masukkan Isi Pengaduan: ");
        String tanggal = ValidasiInput.inputTanggal(scanner, "Masukkan Tanggal (dd/mm/yyyy): ");

        // Pilih tingkat urgensi -> menentukan objek subclass apa yang dibuat
        view.tampilkanPilihanTingkatUrgensi();
        String pilihanUrgensi;
        while (true) {

            System.out.print("Pilihan (1/2): ");
            pilihanUrgensi = scanner.nextLine();

            if (pilihanUrgensi.equals("1") || pilihanUrgensi.equals("2")) {
                break;
            }
            System.out.println("Pilihan tingkat urgensi tidak tersedia.");
        }

        if (pilihanUrgensi.equals("1")) {
            controller.tambahPengaduanBiasa(id, nama, jenis, isi, tanggal);
            view.tampilkanSukses("Pengaduan (BIASA) berhasil ditambahkan ke dalam sistem!");
        } else {
            String kontak = ValidasiInput.inputKontakDarurat(scanner, "Masukkan Kontak Darurat (contoh: 081234567890) : ");
            controller.tambahPengaduanDarurat(id, nama, jenis, isi, tanggal, kontak);
            view.tampilkanSukses("Pengaduan (DARURAT) berhasil ditambahkan ke dalam sistem!");
        }
        view.tampilkanPesan("Status awal   : Menunggu Konfirmasi Petugas");
    }
    private static void ubahStatus(Scanner scanner, PengelolaDataPengaduan controller,
                 PengaduanView view) {

        view.tampilkanJudul("UBAH STATUS PENGADUAN");
        String id = ValidasiInput.inputidPengaduan(scanner, "Masukkan ID Pengaduan(contoh: P001): ");
        Pengaduan p = controller.cariPengaduan(id);

        if (p == null) {
            view.tampilkanError("ID Pengaduan tidak ditemukan! Silakan periksa kembali ID yang dimasukkan.");
            return;
        }
        String statusBerikutnya = controller.statusBerikutnya(p.getStatus());
        view.tampilkanStatusUbah(p, statusBerikutnya);
        if (statusBerikutnya == null) {
            return;
        }
        boolean lanjut = ValidasiInput.inputKonfirmasi(scanner);
        if (lanjut) {
            controller.ubahStatus(id, statusBerikutnya);
            view.tampilkanSukses("Status pengaduan berhasil diperbarui menjadi \"" + statusBerikutnya + "\".");
        } else {
            view.tampilkanInfo("Perubahan status dibatalkan.");
        }
    }
    
    private static void hapusPengaduan(Scanner scanner, PengelolaDataPengaduan controller,
                PengaduanView view) {

        view.tampilkanJudul("HAPUS PENGADUAN");

        String id = ValidasiInput.inputidPengaduan(scanner, "Masukkan ID Pengaduan(contoh: P001): ");
        Pengaduan p = controller.cariPengaduan(id);
        if (p == null) {
            view.tampilkanError("ID Pengaduan tidak ditemukan! Silakan periksa kembali ID Anda.");
            return;
        }
        view.tampilkanKonfirmasiHapus(p);
        boolean konfirmasi = ValidasiInput.inputKonfirmasi(scanner);

        if (konfirmasi) {
            controller.hapusPengaduan(id);
            view.tampilkanSukses("Data pengaduan berhasil dihapus dari sistem.");
        } else {
            view.tampilkanInfo("Proses penghapusan data pengaduan dibatalkan.");
        }
    }
    // Menu utama
    public static void tampilkanMenu() {
        //Method yang digunakan untuk mencetak daftar menu utama
        System.out.println("1. Tambah Pengaduan");
        System.out.println("2. Lihat Pengaduan");
        System.out.println("3. Ubah Status Pengaduan");
        System.out.println("4. Hapus Pengaduan");
        System.out.println("5. Keluar");
        System.out.println("==================================================");
   }   
}

