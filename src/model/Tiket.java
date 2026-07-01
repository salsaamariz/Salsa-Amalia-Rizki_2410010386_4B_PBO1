package model;

// [NILAI 1: CLASS] (induk/parent)
public class Tiket {
    // [NILAI 3: ATRIBUT] + [NILAI 7: ENCAPSULATION] (private)
    private String kodeTiket;
    private String namaPengunjung;
    private double hargaDasar;
    private boolean weekend;
    private boolean aksesAirLava = false;
    protected static final double BIAYA_AIR_LAVA = 15000;
    
    // [NILAI 4: CONSTRUCTOR]
    public Tiket (String kodeTiket, String namaPengunjung, double hargaDasar, boolean weekend) {
        this.kodeTiket = kodeTiket;
        this.namaPengunjung = namaPengunjung;
        this.hargaDasar = hargaDasar;
        this.weekend = weekend;
    }
    
    // [NILAI 6: ACCESSOR]
    public String getKodeTiket() { return kodeTiket; }
    public String getNamaPengunjung() { return namaPengunjung; }
    public double getHargaDasar() { return hargaDasar; }
    public boolean isWeekend () { return weekend; }
    public boolean isAksesAirLava() { return aksesAirLava; }
    
    // [NILAI 5: MUTATOR]
    public void setNamaPengunjung(String namaPengunjung) { this.namaPengunjung = namaPengunjung; }
    public void setHargaDasar(double hargaDasar) { this.hargaDasar = hargaDasar; }
    public void setWeekend(boolean weekend) {this.weekend = weekend; }
    public void setAksesAirLava(boolean aksesAirLava) {this.aksesAirLava = aksesAirLava; }
    
    //biaya tambahan wahana air lava
    protected double feeAirLava() {
        return aksesAirLava ? BIAYA_AIR_LAVA : 0;
    }
    
    // [NILAI 9: POLYMORPHISM] (methode override)
    public double hitungHargaTiket() {
        double harga = hargaDasar;
        if (weekend) harga *=1.25;
        harga += feeAirLava();
        return harga;
    }
    
    public String getJenis() { return "TIKET"; }
    
    @Override
    
    public String toString() {
        return String.format("[%s] %s | Rp%.0f | %s%s", 
                kodeTiket, namaPengunjung, hitungHargaTiket(),
                weekend ? "Weekend" : "Weekday",
                aksesAirLava ? " | +AirLava" : "");
    }
}