package util;

// [NILAI #1: Class]
public class KodeUnik {
    // [NILAI #3: Atribut] + [NILAI #7: Encapsulation] (konstanta identitas)
    public static final String NAMA = "Salsa Amalia Rizki";
    public static final String NPM = "2410010386";
    public static final String BRAND = "Waterbom Banua Splash";

    // Hitung digit checksum dari NPM (jumlah semua digit mod 9)
    private static int checksum() {
        int total = 0;
        // [NILAI #11: Perulangan]
        for (char c : NPM.toCharArray()) {
            if (Character.isDigit(c)) total += (c - '0');
        }
        return total % 9;
    }

    // Kode tiket unik: WB-<NPM>-<urut>-<checksum>
    public static String generateKodeTiket(int urutan) {
        return String.format("WB-%s-%03d-%d", NPM, urutan, checksum());
    }

    // Bongkar NPM jadi identitas (anti-plagiat)
    public static String infoPengembang() {
        StringBuilder sb = new StringBuilder();
        sb.append("===== INFO PENGEMBANG (KODE UNIK) =====\n");
        sb.append("Nama         : ").append(NAMA).append("\n");
        sb.append("Brand        : ").append(BRAND).append("\n");
        sb.append("NPM          : ").append(NPM).append("\n");
        sb.append("Tahun Masuk  : 20").append(NPM.substring(0, 2)).append("\n");

        // [NILAI #10: Seleksi] (if-else)
        String fakultas;
        if (NPM.substring(2, 4).equals("10")) {
            fakultas = "Teknologi Informasi";
        } else {
            fakultas = "Fakultas Lain";
        }
        sb.append("Fakultas     : ").append(fakultas).append("\n");

        // [NILAI #10: Seleksi] (switch)
        String prodi;
        switch (NPM.substring(4, 6)) {
            case "01" -> prodi = "Teknik Informatika";
            case "02" -> prodi = "Sistem Informasi";
            default -> prodi = "Prodi Lain";
        }
        sb.append("Program Studi: ").append(prodi).append("\n");
        sb.append("No. Registrasi: ").append(NPM.substring(6)).append("\n");
        sb.append("Checksum Kode : ").append(checksum()).append("\n");
        sb.append("=======================================");
        return sb.toString();
    }
}