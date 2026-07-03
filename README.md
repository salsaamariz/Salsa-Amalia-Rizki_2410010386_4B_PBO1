# Proyek Akhir Pemrograman Berbasis Objek 1

Proyek ini adalah aplikasi Sistem Tiket Waterbom Banua Splash menggunakan Java sebagai tugas akhir dari mata kuliah pemrograman berbasis objek 1.

## Deskripsi

Aplikasi ini menerima input berupa data penjualan tiket taman air Waterbom Banua Splash, dan memberikan output berupa perhitungan harga tiket, laporan pendapatan, serta struk. Tersedia tiga jenis tiket yaitu Reguler, VIP, dan Terusan, ditambah wahana berbayar Air Lava, serta sistem kode unik anti-plagiat berbasis NPM pembuat.

Aplikasi ini mengimplementasikan beberapa konsep penting dalam pemrograman berorientasi objek (OOP) seperti Class, Object, Atribut, Method Constructor, Method Mutator, Method Accessor, Encapsulation, Inheritance, Overloading, Overriding, Seleksi, Perulangan, IO Sederhana, Array, dan Error Handling.

## Penjelasan Kode

Berikut adalah bagian kode yang relevan dengan konsep OOP yang dijelaskan:

1. **Class** adalah template atau blueprint dari object. Pada kode ini, `Tiket`, `TiketReguler`, `TiketVIP`, `TiketTerusan`, `WaterbomService`, dan `App` adalah contoh dari class.

```java
public class Tiket {
    ...
}

public class TiketReguler extends Tiket {
    ...
}

public class WaterbomService {
    ...
}
```

2. **Object** adalah instance dari class. Pada kode ini, `daftar[jumlah] = new TiketReguler(kode, nama, harga, weekend, usia);` adalah contoh pembuatan object.

```java
daftar[jumlah] = new TiketReguler(kode, nama, harga, weekend, usia);
```

3. **Atribut** adalah variabel yang ada dalam class. Pada kode ini, `kodeTiket`, `namaPengunjung`, dan `hargaDasar` adalah contoh atribut.

```java
String kodeTiket;
String namaPengunjung;
double hargaDasar;
```

4. **Constructor** adalah method yang pertama kali dijalankan pada saat pembuatan object. Pada kode ini, constructor ada di dalam class `Tiket` dan `TiketReguler`.

```java
public Tiket(String kodeTiket, String namaPengunjung, double hargaDasar, boolean weekend) {
    this.kodeTiket = kodeTiket;
    this.namaPengunjung = namaPengunjung;
    this.hargaDasar = hargaDasar;
    this.weekend = weekend;
}

public TiketReguler(String kodeTiket, String namaPengunjung, double hargaDasar, boolean weekend, String kategoriUsia) {
    super(kodeTiket, namaPengunjung, hargaDasar, weekend);
    this.kategoriUsia = kategoriUsia;
}
```

5. **Mutator** atau setter digunakan untuk mengubah nilai dari suatu atribut. Pada kode ini, `setAksesAirLava` dan `setHargaDasar` adalah contoh method mutator.

```java
public void setAksesAirLava(boolean aksesAirLava) {
    this.aksesAirLava = aksesAirLava;
}

public void setHargaDasar(double hargaDasar) {
    this.hargaDasar = hargaDasar;
}
```

6. **Accessor** atau getter digunakan untuk mengambil nilai dari suatu atribut. Pada kode ini, `getKodeTiket`, `getNamaPengunjung`, `getHargaDasar`, dan `isWeekend` adalah contoh method accessor.

```java
public String getKodeTiket() {
    return kodeTiket;
}

public boolean isWeekend() {
    return weekend;
}
```

7. **Encapsulation** adalah konsep menyembunyikan data dengan membuat atribut menjadi private dan hanya bisa diakses melalui method. Pada kode ini, atribut `kodeTiket` dan `hargaDasar` dienkapsulasi dan hanya bisa diakses melalui method getter dan setter.

```java
private String kodeTiket;
private double hargaDasar;
```

8. **Inheritance** adalah konsep di mana sebuah class bisa mewarisi property dan method dari class lain. Pada kode ini, `TiketReguler`, `TiketVIP`, dan `TiketTerusan` mewarisi `Tiket` dengan sintaks `extends`.

```java
public class TiketVIP extends Tiket {
    ...
}
```

9. **Polymorphism** adalah konsep di mana sebuah nama dapat digunakan untuk merujuk ke beberapa tipe atau bentuk objek berbeda. Ini memungkinkan metode-metode dengan nama yang sama untuk berperilaku berbeda tergantung pada tipe objek yang mereka manipulasi, polymorphism bisa berbentuk Overloading ataupun Overriding. Pada kode ini, method `hitungHargaTiket(int diskonPersen)` di `Tiket` merupakan overloading dari method `hitungHargaTiket`, dan `hitungHargaTiket` di `TiketVIP` merupakan override dari method `hitungHargaTiket` di `Tiket`.

```java
public double hitungHargaTiket(int diskonPersen) {
    return hitungHargaTiket() * (1 - diskonPersen / 100.0);
}

@Override
public double hitungHargaTiket() {
    ...
}
```

10. **Seleksi** adalah statement kontrol yang digunakan untuk membuat keputusan berdasarkan kondisi. Pada kode ini, digunakan seleksi `switch` dalam method `hitungHargaTiket` untuk kategori usia dan seleksi `if` untuk biaya weekend.

```java
switch (kategoriUsia.toUpperCase()) {
    case "ANAK":
        harga *= 0.50;
        break;
    case "LANSIA":
        harga *= 0.70;
        break;
    default:
        harga *= 1.0;
}

if (isWeekend()) {
    harga *= 1.25;
}
```

11. **Perulangan** adalah statement kontrol yang digunakan untuk menjalankan blok kode berulang kali. Pada kode ini, digunakan loop `for` untuk menampilkan data dan `while` untuk menu.

```java
for (int i = 0; i < jumlah; i++) {
    System.out.println((i + 1) + ". " + daftar[i]);
}
```

12. **Input Output Sederhana** digunakan untuk menerima input dari user dan menampilkan output ke user. Pada kode ini, digunakan class `Scanner` untuk menerima input dan method `System.out.println` untuk menampilkan output.

```java
Scanner scanner = new Scanner(System.in);
System.out.print("Masukkan nama pengunjung: ");
String nama = scanner.nextLine();

System.out.println("\nData Tiket:");
System.out.println(tiket);
```

13. **Array** adalah struktur data yang digunakan untuk menyimpan beberapa nilai dalam satu variabel. Pada kode ini, `Tiket[] daftar = new Tiket[200];` adalah contoh penggunaan array.

```java
Tiket[] daftar = new Tiket[200];
```

14. **Error Handling** digunakan untuk menangani error yang mungkin terjadi saat runtime. Pada kode ini, digunakan `try catch` untuk menangani error input.

```java
try {
    pilihan = Integer.parseInt(scanner.nextLine());
} catch (Exception e) {
    System.out.println("Error: Input harus angka!");
}
```

## Usulan nilai

| No  | Materi         |  Nilai  |
| :-: | -------------- | :-----: |
|  1  | Class          |    5    |
|  2  | Object         |    5    |
|  3  | Atribut        |    5    |
|  4  | Constructor    |    5    |
|  5  | Mutator        |    5    |
|  6  | Accessor       |    5    |
|  7  | Encapsulation  |    5    |
|  8  | Inheritance    |    5    |
|  9  | Polymorphism   |   10    |
| 10  | Seleksi        |    5    |
| 11  | Perulangan     |    5    |
| 12  | IO Sederhana   |   10    |
| 13  | Array          |   15    |
| 14  | Error Handling |   15    |
|     | **TOTAL**      | **100** |\

## Pembuat

Nama: Salsa Amalia Rizki 
NPM: 2410010386
