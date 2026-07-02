package service;

import model.Tiket;
import model.TiketReguler;
import model.TiketVIP;
import model.TiketTerusan;
import util.KodeUnik;

import java.io.*;

// [NILAI #1: Class]
public class WaterboomService {
    // [NILAI #13: Array] (penyimpanan tiket)
    private Tiket[] daftar = new Tiket[200];
    private int jumlah = 0;

    // [NILAI #13: Array] (nama wahana, termasuk Air Lava)
    private final String[] wahana = {
            "Seluncuran Bekantan", "Kolam Arus Barito",
            "Ember Tumpah Sasirangan", "Kolam Ombak Banua",
            "Seluncuran Air Lava"
    };

    public int getJumlah() { return jumlah; }

    public boolean tambahTiket(Tiket t) {
        if (jumlah >= daftar.length) return false;
        if (cariIndex(t.getKodeTiket()) != -1) return false;
        daftar[jumlah++] = t;
        return true;
    }

    private int cariIndex(String kode) {
        // [NILAI #11: Perulangan]
        for (int i = 0; i < jumlah; i++) {
            if (daftar[i].getKodeTiket().equalsIgnoreCase(kode)) return i;
        }
        return -1;
    }

    public Tiket cariByKode(String kode) {
        int idx = cariIndex(kode);
        return (idx == -1) ? null : daftar[idx];
    }

    public String generateKode() {
        return KodeUnik.generateKodeTiket(jumlah + 1);
    }

    public void tampilkanSemua() {
        if (jumlah == 0) { System.out.println("Belum ada tiket terjual."); return; }
        // [NILAI #11: Perulangan]
        for (int i = 0; i < jumlah; i++) {
            System.out.println((i + 1) + ". " + daftar[i]); // [NILAI #9: Polymorphism] (toString)
        }
    }

    public void tampilkanWahana() {
        System.out.println("Wahana di " + KodeUnik.BRAND + ":");
        for (int i = 0; i < wahana.length; i++) {
            System.out.println(" - " + wahana[i]);
        }
    }

    public double totalPendapatan() {
        double total = 0;
        for (int i = 0; i < jumlah; i++) total += daftar[i].hitungHargaTiket();
        return total;
    }

    public void laporanJenis() {
        int reg = 0, vip = 0, ter = 0;
        for (int i = 0; i < jumlah; i++) {
            // [NILAI #10: Seleksi]
            if (daftar[i] instanceof TiketVIP) vip++;
            else if (daftar[i] instanceof TiketTerusan) ter++;
            else if (daftar[i] instanceof TiketReguler) reg++;
        }
        System.out.println("Reguler: " + reg + " | VIP: " + vip
                + " | Terusan: " + ter + " | Total: " + jumlah);
    }

    // [NILAI #12: IO Sederhana] (tulis struk ke file)
    public void cetakStruk(String kode) throws Exception {
        Tiket t = cariByKode(kode);
        if (t == null) throw new Exception("Tiket tidak ditemukan.");

        StringBuilder sb = new StringBuilder();
        sb.append("===== TIKET ").append(KodeUnik.BRAND).append(" =====\n");
        sb.append("Kode        : ").append(t.getKodeTiket()).append("\n");
        sb.append("Pengunjung  : ").append(t.getNamaPengunjung()).append("\n");
        sb.append("Jenis       : ").append(t.getJenis()).append("\n");
        sb.append("Air Lava    : ").append(t.isAksesAirLava() ? "Ya (+Rp15.000)" : "Tidak").append("\n");
        sb.append("HARGA       : Rp").append((long) t.hitungHargaTiket()).append("\n");
        sb.append("Petugas     : ").append(KodeUnik.NAMA).append(" (").append(KodeUnik.NPM).append(")\n");
        sb.append("=========================================");

        System.out.println(sb);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("struk_tiket.txt", true))) {
            bw.write(sb.toString());
            bw.newLine();
            bw.newLine();
        }
    }

    // [NILAI #12: IO Sederhana] (simpan data ke file)
    public void simpanKeFile(String namaFile) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(namaFile))) {
            for (int i = 0; i < jumlah; i++) {
                Tiket t = daftar[i];
                if (t instanceof TiketReguler r) {
                    bw.write("REGULER|" + r.getKodeTiket() + "|" + r.getNamaPengunjung()
                            + "|" + r.getHargaDasar() + "|" + r.isWeekend()
                            + "|" + r.getKategoriUsia() + "|" + r.isAksesAirLava());
                } else if (t instanceof TiketVIP v) {
                    bw.write("VIP|" + v.getKodeTiket() + "|" + v.getNamaPengunjung()
                            + "|" + v.getHargaDasar() + "|" + v.isWeekend()
                            + "|" + v.isPakaiLocker() + "|" + v.isFastTrack() + "|" + v.isAksesAirLava());
                } else if (t instanceof TiketTerusan tr) {
                    bw.write("TERUSAN|" + tr.getKodeTiket() + "|" + tr.getNamaPengunjung()
                            + "|" + tr.getHargaDasar() + "|" + tr.isWeekend()
                            + "|" + tr.getJumlahHari() + "|" + tr.isAksesAirLava());
                } else {
                    bw.write("TIKET|" + t.getKodeTiket() + "|" + t.getNamaPengunjung()
                            + "|" + t.getHargaDasar() + "|" + t.isWeekend() + "|-|" + t.isAksesAirLava());
                }
                bw.newLine();
            }
        }
    }

    // [NILAI #12: IO Sederhana] (muat data) + [NILAI #14: Error Handling]
    public void muatDariFile(String namaFile) throws IOException {
        File f = new File(namaFile);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(namaFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                try { // [NILAI #14: Error Handling] (skip baris rusak)
                    String[] p = line.split("\\|");
                    String jenis = p[0];
                    String kode = p[1];
                    String nama = p[2];
                    double harga = Double.parseDouble(p[3]);
                    boolean weekend = Boolean.parseBoolean(p[4]);

                    switch (jenis) {
                        case "REGULER" -> {
                            TiketReguler r = new TiketReguler(kode, nama, harga, weekend, p[5]);
                            if (p.length > 6) r.setAksesAirLava(Boolean.parseBoolean(p[6]));
                            tambahTiket(r);
                        }
                        case "VIP" -> {
                            TiketVIP v = new TiketVIP(kode, nama, harga, weekend,
                                    Boolean.parseBoolean(p[5]), Boolean.parseBoolean(p[6]));
                            if (p.length > 7) v.setAksesAirLava(Boolean.parseBoolean(p[7]));
                            tambahTiket(v);
                        }
                        case "TERUSAN" -> {
                            TiketTerusan tr = new TiketTerusan(kode, nama, harga, weekend,
                                    Integer.parseInt(p[5]));
                            if (p.length > 6) tr.setAksesAirLava(Boolean.parseBoolean(p[6]));
                            tambahTiket(tr);
                        }
                        default -> tambahTiket(new Tiket(kode, nama, harga, weekend));
                    }
                } catch (Exception e) {
                    // baris rusak diabaikan
                }
            }
        }
    }
}