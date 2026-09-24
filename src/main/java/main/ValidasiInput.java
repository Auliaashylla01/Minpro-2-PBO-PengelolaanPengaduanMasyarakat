/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import java.util.Scanner;
/**
 *
 * @author ASUS
 */
//Class ValidasiInput digunakan untuk method untuk memvalidasi input user agar mencegah error program.
public class ValidasiInput {
    // Jumlah hari pada tiap bulan untuk validasi tanggal
    private static final int[] jumlah_hari_perbulan =
            {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
     //Memastikan input teks dari user tidak kosong/spasi saja.
    public static String inputTidakKosong(Scanner scanner, String pesan) {

        String input;

        while (true) {

            System.out.print(pesan);
            input = scanner.nextLine().trim(); //trim()untuk menghapus spasi di awal/akhir

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("[Error] Input tidak boleh kosong! Silakan isi data dengan benar.");
        }
    }
    //Input Untuk pilihan menu Sistem
    public static int inputMenu(Scanner scanner) {

        while (true) {
            System.out.print("Pilih menu: ");

            //Cek apakah inputan user benar-benar berupa angka
            if (scanner.hasNextInt()) {
                int menu = scanner.nextInt();
                scanner.nextLine();

                if (menu >= 1 && menu <= 5) {
                    return menu; //Kembali ke menu jika angka 1 - 5
                } else {
                    System.out.println("[Error] Pilihan menu hanya dari 1 sampai 5!");
                }
            } else {
                System.out.println("[Error] Input harus berupa angka!");
                scanner.nextLine();
            }
        }
    }
        // Input Untuk konfirmasi Penghapusan 
        public static boolean inputKonfirmasi(Scanner scanner) {

        while (true) {

            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("y")) {
                return true;
            }

            if (input.equalsIgnoreCase("n")) {
                return false;
            }

            System.out.println("[Error] Input hanya boleh berupa huruf 'y' (ya) atau 'n' (tidak)!");
        }
    }
        //Digunakan agar nama pelapor tidak kosong, dengan panjang minimal 3 karakter hanya boleh terdiri dari huruf dan spasi 
        public static String inputNamaPelapor (Scanner scanner, String pesan){
            while (true){
                System.out.print(pesan);
                String input = scanner.nextLine().trim();
                
                if (input.isEmpty()){
                    System.out.println("[Error] Nama pelapor tidak boleh kosong!");
                    continue;
                }
                if (input.length() < 3){
                    System.out.println("[Error] Nama minimal 3 karakter!");
                        continue;
                }
                boolean formatBenar = true;
                
                for (int i = 0; i < input.length(); i++){
                char c = input.charAt(i);
                
                if (!Character.isLetter(c) && c != ' '){
                    formatBenar = false;
                    break;
                }
            }
                if (!formatBenar){
                    System.out.println("[Error] Nama hanya boleh berisi huruf dan spasi!");
                    continue;
                }
                return input;
            }
            
        }
        //Format Tanggal dd/mm/yyyy dan dicek manual pakai split() dan array jumlah hari per bulan
        public static String inputTanggal (Scanner scanner, String pesan){
            while(true){
                System.out.print(pesan);
                String input = scanner.nextLine().trim();
                
                String[] bagian = input.split("/");
                if (bagian.length != 3 || bagian[0].length() != 2
                        || bagian[1].length() != 2 || bagian[2].length() != 4){
                    System.out.println("[Error] Format tanggal harus dd/mm/yy, contoh 20/02/2026.");
                    continue;
                }
                try{
                    int tanggal = Integer.parseInt(bagian[0]);
                    int bulan = Integer.parseInt(bagian[1]);
                    Integer.parseInt(bagian[2]);
                    
                    if (bulan <1 || bulan > 12 ){
                        System.out.println("[Error] Bulan tidak valid! Harus di antara 01-12.");
                        continue;
                    }
                    if (tanggal < 1 || tanggal > jumlah_hari_perbulan[bulan]){
                        System.out.println("[Error] Tanggal tidak valid untuk bulan tersebut!");
                        continue;
                    }
                    return input;
                } catch (NumberFormatException e){
                    System.out.println("[Error] Tanggal, bulan, dan tahun harus berupa angka!");
                }
            }
        }
        //ID Pengaduan dengan format P001 dan seterusnya 
        public static String inputidPengaduan (Scanner scanner,String pesan){
            while(true){
                System.out.print(pesan);
                String input = scanner.nextLine().trim().toUpperCase();
                
                boolean formatBenar = true;
                
                if (input.length() != 4 || input.charAt(0) != 'P'){
                    formatBenar = false;
                }else {
                    //Digunakan untuk memastikan angka setelah P
                    for (int i = 1; i < input.length(); i++) {
                if (!Character.isDigit(input.charAt(i))) {
                    formatBenar = false;
                    break;
                    }
                }         
        }
                if (!formatBenar) {
                System.out.println("[Error] Format ID tidak valid! Contoh format yang benar: P001");
                continue;
                }
                return input;
            }
        }
        //Untuk memastikan format penulisan kontak darurat diawali 08 dan total panjangnya 10-13 digit angka
        public static String inputKontakDarurat (Scanner scanner, String pesan){
            while (true) {
                System.out.print(pesan);
                String input = scanner.nextLine().trim();

            boolean formatBenar = true;

            if (input.length() < 10 || input.length() > 13) {
                formatBenar = false;
            } else if (!input.startsWith("08")) {
                formatBenar = false;
            } else {
                //Cek semua karakter harus berupa angka
                for (int i = 0; i < input.length(); i++) {
                    if (!Character.isDigit(input.charAt(i))) {
                        formatBenar = false;
                        break;
                    }
            }
        }
            if (!formatBenar) {
                System.out.println("[Error] Kontak darurat harus diawali '08' dan berjumlah 10-13 digit angka!");
                continue;
            }
            return input;
        }
        }
}
        
            


