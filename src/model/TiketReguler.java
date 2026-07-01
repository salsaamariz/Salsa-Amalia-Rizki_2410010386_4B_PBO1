package model;

// [NILAI #1: Class] + [NILAI #8: Inheritance]
public class TiketReguler extends Tiket {
    // [NILAI #3: Atribut]
    private String kategoriUsia; // ANAK / DEWASA / LANSIA

    // [NILAI #4: Constructor]
    public TiketReguler(String kodeTiket, String namaPengunjung, double hargaDasar,
                        boolean weekend, String kategoriUsia) {
        super(kodeTiket, namaPengunjung, hargaDasar, weekend);
        this.kategoriUsia = kategoriUsia;
    }

    // [NILAI #6: Accessor] & [NILAI #5: Mutator]
    public String getKategoriUsia() { return kategoriUsia; }
    public void setKategoriUsia(String kategoriUsia) { this.kategoriUsia = kategoriUsia; }

    // [NILAI #9: Polymorphism] (override)
    @Override
    public double hitungHargaTiket() {
        double harga = getHargaDasar();
        if (isWeekend()) harga *= 1.25;
        // [NILAI #10: Seleksi]
        switch (kategoriUsia.toUpperCase()) {
            case "ANAK" -> harga *= 0.50;
            case "LANSIA" -> harga *= 0.70;
            default -> harga *= 1.0;
        }
        harga += feeAirLava();
        return harga;
    }

    @Override
    public String getJenis() { return "REGULER"; }

    @Override
    public String toString() {
        return super.toString() + " | Reguler | " + kategoriUsia;
    }
}