package model;

//[NILAI 1: CLASS] + [NILAI 8: INHERITANCE]
public class TiketVIP extends Tiket {
    //[NILAI 3: ATRIBUT]
    private boolean pakaiLocker;
    private boolean fastTrack;
    
    //[NILAI 4: CONSTRUCTOR]
    public TiketVIP(String kodeTiket, String namaPengunjung, double hargaDasar,
            boolean weekend, boolean pakaiLocker, boolean fastTrack) {
        super(kodeTiket, namaPengunjung, hargaDasar, weekend);
        this.pakaiLocker = pakaiLocker;
        this.fastTrack = fastTrack;
    }
    
    //[NILAI 6: ACCESSOR] & [NILAI 5 MUTATOR]
    public boolean isPakaiLocker() { return pakaiLocker; }
    public void setPakaiLocker(boolean pakaiLocker) { this.pakaiLocker = pakaiLocker; }
    public boolean isFastTrack() { return fastTrack; }
    public void setFastTrack(boolean fastTrack) {this.fastTrack = fastTrack; }
    
    //[NILAI 9: POLYMORPHISM] (override)
    @Override
    public double hitungHargaTiket() {
        double harga = getHargaDasar() * 1.50; //[NILAI 10: SELEKSI]
        if (isWeekend()) harga *= 1.25;
        if (pakaiLocker) harga += 25000;
        if (fastTrack) harga += 50000;
        harga += feeAirLava();
        return harga;
    }
    
    @Override
    public String getJenis() { return "VIP"; }
    
    @Override
    public String toString() {
        return super.toString() + " | VIP"
                + (pakaiLocker ? " | +Locker" : "")
                + (fastTrack ? " | + FastTrack" : "");
    }
}