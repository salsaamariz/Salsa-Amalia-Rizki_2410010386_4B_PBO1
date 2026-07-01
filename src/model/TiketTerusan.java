package model;

// [NILAI #1: Class] + [NILAI #8: Inheritance]
public class TiketTerusan extends Tiket {
    // [NILAI #3: Atribut]
    private int jumlahHari;

    // [NILAI #4: Constructor]
    public TiketTerusan(String kodeTiket, String namaPengunjung, double hargaDasar,
                        boolean weekend, int jumlahHari) {
        super(kodeTiket, namaPengunjung, hargaDasar, weekend);
        this.jumlahHari = jumlahHari;
    }

    // [NILAI #6: Accessor] & [NILAI #5: Mutator]
    public int getJumlahHari() { return jumlahHari; }
    public void setJumlahHari(int jumlahHari) { this.jumlahHari = jumlahHari; }

    // [NILAI #9: Polymorphism] (override)
    @Override
    public double hitungHargaTiket() {
        double harga = getHargaDasar() * jumlahHari;
        // [NILAI #10: Seleksi]
        if (jumlahHari >= 7) harga *= 0.70;
        else if (jumlahHari >= 3) harga *= 0.85;
        harga += feeAirLava();
        return harga;
    }

    @Override
    public String getJenis() { return "TERUSAN"; }

    @Override
    public String toString() {
        return super.toString() + " | Terusan | " + jumlahHari + " hari";
    }
}