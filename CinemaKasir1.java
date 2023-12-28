/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package a;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Film {
    private String judul;
    private String genre;
    private LocalTime[] jamTayang;
    private int teater;

    public Film(String judul, String genre, int teater) {
        this.judul = judul;
        this.genre = genre;
        this.jamTayang = new LocalTime[]{LocalTime.of(10, 0), LocalTime.of(12, 0), LocalTime.of(14, 0),
                LocalTime.of(16, 0), LocalTime.of(18, 0), LocalTime.of(20, 0), LocalTime.of(22, 0)};
        this.teater = teater;
    }

    public String getJudul() {
        return judul;
    }

    public String getGenre() {
        return genre;
    }

    public LocalTime[] getJamTayang() {
        return jamTayang;
    }

    public int getTeater() {
        return teater;
    }
}

class Tiket {
    private Film film;
    private String hari;
    private int harga;

    public Tiket(Film film, String hari, int harga) {
        this.film = film;
        this.hari = hari;
        this.harga = harga;
    }

    public Film getFilm() {
        return film;
    }

    public String getHari() {
        return hari;
    }

    public int getHarga() {
        return harga;
    }
}

class Bioskop {
    private List<Film> filmList;
    private Map<String, Integer> hargaTiket;

    public Bioskop() {
        filmList = new ArrayList<>();
        hargaTiket = new HashMap<>();
    }

    public void tambahFilm(Film film) {
        filmList.add(film);
    }

    public void aturHargaTiket(String hari, int harga) {
        hargaTiket.put(hari, harga);
    }

    public List<Film> tampilkanDaftarFilm() {
        return filmList;
    }

    public int hitungHargaTiket(String hari, int hargaDasar) {
        if (hargaTiket.containsKey(hari)) {
            return hargaTiket.get(hari);
        } else {
            return hargaDasar;
        }
    }
}

public class CinemaKasir {
    public static void main(String[] args) {
        // REAL TIME FORMAT
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH.mm.ss");
        LocalDateTime getNow = LocalDateTime.now();
        Bioskop bioskop = new Bioskop();

        // HEADER
        System.out.println("\n--------------  Cinema Rungkut  -------------");
        System.out.println("               Jl. Gunung Anyar                ");
        System.out.println("             Kec. Rungkut, Surabaya ");
        System.out.println("==============================================");
        System.out.println("Tanggal & Waktu  " + dateTimeFormatter.format(getNow));
        System.out.println("==============================================");
        System.out.println("====    Daftar Film yang Sedang Tayang    ====");

        Film film1 = new Film("|Aquaman and The Lost Kingdom", "Action|", 1);
        Film film2 = new Film("|        Siksa Neraka", "Horor         |", 2);
        Film film3 = new Film("|         Wonka", "Adventure           |", 3);
        Film film4 = new Film("|        Layangan Putus", "Drama       |", 4);
        Film film5 = new Film("| The Boy And The Heron", "Animation   |", 5);
        Film film6 = new Film("|          Dunki", "Comedy             |", 6);

        bioskop.tambahFilm(film1);
        bioskop.tambahFilm(film2);
        bioskop.tambahFilm(film3);
        bioskop.tambahFilm(film4);
        bioskop.tambahFilm(film5);
        bioskop.tambahFilm(film6);

        bioskop.aturHargaTiket("weekday", 20000);
        bioskop.aturHargaTiket("weekend", 30000);

        Scanner scanner = new Scanner(System.in);

        // Menyimpan daftar film dalam variabel terpisah
        List<Film> daftarFilm = bioskop.tampilkanDaftarFilm();

        int indeksFilm;
        do {
            // Menampilkan daftar film dari variabel terpisah
            for (int i = 0; i < daftarFilm.size(); i++) {
                Film film = daftarFilm.get(i);
                System.out.println((i + 1) + ". " + film.getJudul() + " - " + film.getGenre() + " | Teater: " + film.getTeater());
                System.out.println("   Jam Tayang:");
                LocalTime[] jamTayang = film.getJamTayang();
                for (int j = 0; j < jamTayang.length; j++) {
                    System.out.println("      " + (j + 1) + ". " + jamTayang[j]);
                }
            }

            System.out.println("Masukkan indeks Film (1-" + daftarFilm.size() + "): ");
            indeksFilm = scanner.nextInt();
        } while (indeksFilm < 1 || indeksFilm > daftarFilm.size());

        // Meminta input tanggal dan hari untuk menonton film
        System.out.println("Masukkan tanggal dan hari untuk menonton film (Contoh: 01/01/2023 Monday): ");
        String tanggalDanHari = scanner.next() + " " + scanner.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy EEEE");
        LocalDate tanggalNonton = LocalDate.parse(tanggalDanHari, formatter);

        // Menentukan apakah hari ini weekday atau weekend
        String hariPemesanan = tanggalNonton.getDayOfWeek().equals(DayOfWeek.SATURDAY) ||
                tanggalNonton.getDayOfWeek().equals(DayOfWeek.SUNDAY) ? "weekend" : "weekday";

        // Menampilkan jam tayang
        Film filmPilihan = daftarFilm.get(indeksFilm - 1);
        LocalTime[] jamTayangFilm = filmPilihan.getJamTayang();
        System.out.println("Pilih jam tayang (1-" + jamTayangFilm.length + "): ");
        int indeksJamTayang = scanner.nextInt();
        LocalTime jamTayangPilihan = jamTayangFilm[indeksJamTayang - 1];

        System.out.println("Masukkan jumlah tiket yang dipesan: ");
        int jumlahTiket = scanner.nextInt();

        // Menampilkan layout kursi duduk
        System.out.println("====    Layout Kursi Bioskop    ====");
        for (int i = 0; i < 10; i++) {
            char row = (char) ('A' + i);
            for (int j = 1; j <= 10; j++) {
                System.out.print(row + "" + j + " ");
            }
            System.out.println();
        }
        System.out.println("===================================");

        // Meminta input kursi duduk
        List<String> kursiDipilih = new ArrayList<>();
        for (int k = 0; k < jumlahTiket; k++) {
            System.out.println("Pilih Kursi untuk Tiket ke-" + (k + 1) + " (Contoh: A1): ");
            String kursiPilihan = scanner.next();
            kursiDipilih.add(kursiPilihan);
        }

        int hargaTiket = bioskop.hitungHargaTiket(hariPemesanan, 0) * jumlahTiket;
        System.out.println("Total Pembayaran: Rp" + hargaTiket);

        System.out.println("Masukkan jumlah uang: ");
        int uangPelanggan = scanner.nextInt();

        while (uangPelanggan < hargaTiket) {
            System.out.println("Maaf, uang yang dimasukkan tidak mencukupi. Masukkan jumlah uang yang cukup: ");
            uangPelanggan = scanner.nextInt();
        }

        int kembalian = uangPelanggan - hargaTiket;
        System.out.println("Sisa Uang Kembalian: Rp" + kembalian);

        System.out.println("=======    Informasi Tiket    ========");
        System.out.println("Film: " + filmPilihan.getJudul());
        System.out.println("Hari Pemesanan: " + hariPemesanan);
        System.out.println("Tanggal dan Hari Menonton: " + formatter.format(tanggalNonton));
        System.out.println("Jam Tayang: " + jamTayangPilihan);
        System.out.println("Jumlah Tiket:" + jumlahTiket);
        System.out.println("Kursi: " + kursiDipilih.toString());
        System.out.println("Harga Tiket per Tiket: Rp" + bioskop.hitungHargaTiket(hariPemesanan, 0));
        System.out.println("Total Pembayaran: Rp" + hargaTiket);
        System.out.println("--- Terimakasih dan Selamat Menonton! ----");
    }
}