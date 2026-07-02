package app;

import model.TiketReguler;
import model.TiketVIP;
import model.TiketTerusan;
import service.WaterboomService;
import util.KodeUnik;

import java.util.Scanner;

// [NILAI #1: Class]
public class App {
    public static void main(String[] args) {
        // [NILAI #12: IO Sederhana] (input dari user)
        Scanner sc = new Scanner(System.in);
        // [NILAI #2: Object] (membuat object service)
        WaterboomService service = new WaterboomService();
        String fileData = "data_tiket.txt";

        try { // [NILAI #14: Error Handling]
            service.muatDariFile(fileData);
        } catch (Exception e) {
            System.out.println("Gagal load data: " + e.getMessage());
        }

        int pilihan = -1;
        // [NILAI #11: Perulangan] (menu berulang)
        while (pilihan != 0) {
            System.out.println("\n===== " + KodeUnik.BRAND + " =====");
            System.out.println("1. Jual Tiket Reguler");
            System.out.println("2. Jual Tiket VIP");
            System.out.println("3. Jual Tiket Terusan (Season Pass)");
            System.out.println("4. Tampilkan Semua Tiket");
            System.out.println("5. Cari Tiket (by kode)");
            System.out.println("6. Cetak Struk Tiket");
            System.out.println("7. Laporan Pendapatan");
            System.out.println("8. Laporan per Jenis");
            System.out.println("9. Lihat Wahana");
            System.out.println("10. Info Pengembang (Kode Unik)");
            System.out.println("11. Simpan Data");
            System.out.println("0. Keluar");
            System.out.print("Pilih: ");

            try { // [NILAI #14: Error Handling]
                pilihan = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Input harus angka!");
                pilihan = -1;
                continue;
            }

            // [NILAI #10: Seleksi] (switch menu)
            switch (pilihan) {
                case 1 -> {
                    try {
                        System.out.print("Nama: "); String nama = sc.nextLine();
                        System.out.print("Harga dasar: "); double h = Double.parseDouble(sc.nextLine());
                        System.out.print("Weekend? (true/false): "); boolean wk = Boolean.parseBoolean(sc.nextLine());
                        System.out.print("Usia (ANAK/DEWASA/LANSIA): "); String usia = sc.nextLine();
                        System.out.print("Akses Air Lava (+Rp15.000)? (true/false): "); boolean lava = Boolean.parseBoolean(sc.nextLine());
                        String kode = service.generateKode();
                        // [NILAI #2: Object]
                        TiketReguler t = new TiketReguler(kode, nama, h, wk, usia);
                        t.setAksesAirLava(lava); // [NILAI #5: Mutator]
                        boolean ok = service.tambahTiket(t);
                        System.out.println(ok ? "Terjual. Kode: " + kode : "Gagal (penuh).");
                    } catch (Exception e) { System.out.println("Gagal input: " + e.getMessage()); }
                }
                case 2 -> {
                    try {
                        System.out.print("Nama: "); String nama = sc.nextLine();
                        System.out.print("Harga dasar: "); double h = Double.parseDouble(sc.nextLine());
                        System.out.print("Weekend? (true/false): "); boolean wk = Boolean.parseBoolean(sc.nextLine());
                        System.out.print("Sewa loker? (true/false): "); boolean lk = Boolean.parseBoolean(sc.nextLine());
                        System.out.print("Fast track? (true/false): "); boolean ft = Boolean.parseBoolean(sc.nextLine());
                        System.out.print("Akses Air Lava (+Rp15.000)? (true/false): "); boolean lava = Boolean.parseBoolean(sc.nextLine());
                        String kode = service.generateKode();
                        // [NILAI #2: Object]
                        TiketVIP t = new TiketVIP(kode, nama, h, wk, lk, ft);
                        t.setAksesAirLava(lava); // [NILAI #5: Mutator]
                        boolean ok = service.tambahTiket(t);
                        System.out.println(ok ? "Terjual. Kode: " + kode : "Gagal (penuh).");
                    } catch (Exception e) { System.out.println("Gagal input: " + e.getMessage()); }
                }
                case 3 -> {
                    try {
                        System.out.print("Nama: "); String nama = sc.nextLine();
                        System.out.print("Harga dasar/hari: "); double h = Double.parseDouble(sc.nextLine());
                        System.out.print("Jumlah hari: "); int hari = Integer.parseInt(sc.nextLine());
                        System.out.print("Akses Air Lava (+Rp15.000)? (true/false): "); boolean lava = Boolean.parseBoolean(sc.nextLine());
                        String kode = service.generateKode();
                        // [NILAI #2: Object]
                        TiketTerusan t = new TiketTerusan(kode, nama, h, false, hari);
                        t.setAksesAirLava(lava); // [NILAI #5: Mutator]
                        boolean ok = service.tambahTiket(t);
                        System.out.println(ok ? "Terjual. Kode: " + kode : "Gagal (penuh).");
                    } catch (Exception e) { System.out.println("Gagal input: " + e.getMessage()); }
                }
                case 4 -> service.tampilkanSemua();
                case 5 -> {
                    System.out.print("Kode: "); String kode = sc.nextLine();
                    var t = service.cariByKode(kode);
                    System.out.println(t == null ? "Tidak ditemukan." : t.toString());
                }
                case 6 -> {
                    try {
                        System.out.print("Kode tiket: "); String kode = sc.nextLine();
                        service.cetakStruk(kode);
                    } catch (Exception e) { System.out.println("Gagal: " + e.getMessage()); }
                }
                case 7 -> System.out.println("Total pendapatan: Rp" + (long) service.totalPendapatan());
                case 8 -> service.laporanJenis();
                case 9 -> service.tampilkanWahana();
                case 10 -> System.out.println(KodeUnik.infoPengembang());
                case 11 -> {
                    try {
                        service.simpanKeFile(fileData);
                        System.out.println("Data tersimpan.");
                    } catch (Exception e) { System.out.println("Gagal simpan: " + e.getMessage()); }
                }
                case 0 -> {
                    try { service.simpanKeFile(fileData); } catch (Exception ignored) {}
                    System.out.println("Terima kasih, sampai jumpa di " + KodeUnik.BRAND + "!");
                }
                default -> System.out.println("Menu tidak ada.");
            }
        }
        sc.close();
    }
}