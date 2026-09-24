/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
//Class ini merupakan subclass dari class pengaduan sebagai superclass nya.
//Atribut tambahan yang digunakan untuk class ini adalah batasResponJam sebagai target waktu respon awal
// dan kontakDarurat sebagai nomor yang bisa dihubungi petugas terkait informasi lebih lanjut terkait situasi 

public class pengaduanDarurat extends Pengaduan {
    //kontak darurat dari pelapor untuk kemudian dihubungi oleh petugas
    private String kontakDarurat;
    private int batasResponJam;
    
    //constructor yang digunakan untuk mengisi data umum dari class pengaduan 
    public pengaduanDarurat (String idPengaduan, String namaPelapor,String jenisPengaduan, 
            String isiPengaduan,String tanggalPengaduan, String kontakDarurat){
            super(idPengaduan, namaPelapor, jenisPengaduan, 
                    isiPengaduan, tanggalPengaduan);
            //batas waktu respon jam sebagai default dengan standar 24 jam untuk respons awal
            this.batasResponJam = 24;
            this.kontakDarurat = kontakDarurat;
    }
    //Penerapan Getter Pada Sistem digunakan untuk mengambil atau membaca nilai atribut
    public String getkontakDarurat(){
        return kontakDarurat;
    }
    public int getbatasResponJam (){
        return batasResponJam;
    }
    public void setkontakDarurat(String kontakDarurat){
        this.kontakDarurat = kontakDarurat;
    }
    //setter dan validasi nilai jam bersifat positif
    public void setbatasResponJam(int batasResponJam){
        if (batasResponJam > 0){
            this.batasResponJam = batasResponJam;
        }  
    }
     //override method dari superclass untuk nilai tingkat urgensi darurat
    @Override
    public String getTingkatUrgensi (){
        return "DARURAT";
    }
    //override method dari superclass untuk detail pengaduan umum, tingkat urgensi, kontak darurat dan batas respon jam nya.
    @Override
    public String getDetailPengaduan(){
        return super.getDetailPengaduan() + "\n" +
                "Kontak Darurat  : " + kontakDarurat + "\n" +
                "Batas Respon Jam : " + batasResponJam + " Jam";
    }
}
