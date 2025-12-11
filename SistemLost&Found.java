import java.util.ArrayList;
import java.util.Scanner;

public class SistemLostFound {

    // Struktur
    // Menggabungkan atribut Lost Information & Found Information
    static class ItemBarang {
        String kodeBarang;      // Nomor referensi
        String namaBarang;      // Jenis barang (ex: Laptop, Dompet)
        String deskripsi;       // Warna, Merek, Kondisi
        String lokasi;          // Perkiraan lokasi / Lokasi ditemukan
        String tanggal;         // Waktu kehilangan / penemuan
        String pelapor;         // Data Pelapor (utk Lost) atau Nama Staf (utk Found)
        String status;          // "KEHILANGAN" atau "DITEMUKAN"

        public ItemBarang(String kode, String nama, String desk, String lok, String tgl, String orang, String stat) {
            this.kodeBarang = kode;
            this.namaBarang = nama;
            this.deskripsi = desk;
            this.lokasi = lok;
            this.tanggal = tgl;
            this.pelapor = orang;
            this.status = stat;
        }

        @Override
        public String toString() {
            return String.format(
                "[%s] %s (%s)\n   - Deskripsi : %s\n   - Lokasi    : %s\n   - Tanggal   : %s\n   - Kontak    : %s", 
                status, namaBarang, kodeBarang, deskripsi, lokasi, tanggal, pelapor
            );
        }
    }

    // Variabel Global
    static ArrayList<ItemBarang> dataBarang = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // Data Contoh (Contoh Awal)
        dataBarang.add(new ItemBarang("REG001", "Laptop Asus", "Hitam, Lecet dikit", "Lab Dasar", "25-11-2024", "Akbar (Mhs)", "DITEMUKAN"));
        dataBarang.add(new ItemBarang("REG002", "Casan Type C", "Warna Putih", "TU Fasilkom", "24-11-2024", "Balqis (Mhs)", "KEHILANGAN"));
        dataBarang.add(new ItemBarang("REG003", "Kunci Motor", "Gantungan Doraemon", "Ruang 80", "26-11-2024", "Ray (Asisten Lab)", "DITEMUKAN"));

        boolean running = true;
        while (running) {
            System.out.println("\n==================================================");
            System.out.println("   SISTEM LOST & FOUND FASILKOM UNSIKA (INTEGRATED)");
            System.out.println("====================================================");
            System.out.println("1. Lapor Kehilangan Barang (Lost Information)");
            System.out.println("2. Catat Penemuan Barang (Found Information)");
            System.out.println("3. Lihat Semua Data (Quick Sort by Name)");
            System.out.println("4. Cari Barang (Binary Search by Name)");
            System.out.println("5. Keluar");
            System.out.print("Pilih Menu: ");
            String menu = input.nextLine();

            switch (menu) {
                case "1": inputData("KEHILANGAN"); break;
                case "2": inputData("DITEMUKAN"); break;
                case "3": tampilkanData(); break;
                case "4": cariData(); break;
                case "5": running = false; System.out.println("Sistem ditutup."); break;
                default: System.out.println("Menu tidak valid.");
            }
        }
    }

    // --- INPUT DATA (Mencakup field detail permintaan user) ---
    static void inputData(String tipe) {
        System.out.println("\n--- FORM " + tipe + " ---");
        System.out.print("Masukkan Kode Referensi (Unik) : ");
        String kode = input.nextLine();
        System.out.print("Nama/Jenis Barang              : ");
        String nama = input.nextLine();
        System.out.print("Deskripsi (Warna, Merek, Kondisi): ");
        String desk = input.nextLine();
        System.out.print("Lokasi (Perkiraan/Ditemukan)   : ");
        String lok = input.nextLine();
        System.out.print("Tanggal & Waktu (DD-MM-YYYY)   : ");
        String tgl = input.nextLine();
        
        String kontakLabel = (tipe.equals("KEHILANGAN")) ? "Data Pelapor (Nama)" : "Nama Penemu";
        System.out.print(kontakLabel + "        : ");
        String kontak = input.nextLine();

        dataBarang.add(new ItemBarang(kode, nama, desk, lok, tgl, kontak, tipe));
        System.out.println(">> Data berhasil disimpan ke ArrayList!");
    }

    // --- QUICK SORT (REKURSIF) ---
    static void tampilkanData() {
        if (dataBarang.isEmpty()) {
            System.out.println(">> Data kosong.");
            return;
        }
        // Sorting sebelum tampil
        quickSort(dataBarang, 0, dataBarang.size() - 1);

        System.out.println("\nDATA BARANG TERURUT (A-Z):");
        for (ItemBarang b : dataBarang) {
            System.out.println("------------------------------------------");
            System.out.println(b.toString());
        }
        System.out.println("---------------------------------------------");
    }

    static void quickSort(ArrayList<ItemBarang> arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    static int partition(ArrayList<ItemBarang> arr, int low, int high) {
        String pivot = arr.get(high).namaBarang.toLowerCase();
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr.get(j).namaBarang.toLowerCase().compareTo(pivot) < 0) {
                i++;
                ItemBarang temp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, temp);
            }
        }
        ItemBarang temp = arr.get(i + 1);
        arr.set(i + 1, arr.get(high));
        arr.set(high, temp);
        return i + 1;
    }

    // --- BINARY SEARCH (REKURSIF) ---
    static void cariData() {
        quickSort(dataBarang, 0, dataBarang.size() - 1); // Wajib sort dulu
        System.out.print("\nMasukkan Nama Barang yang dicari: ");
        String keyword = input.nextLine();

        int index = binarySearch(dataBarang, 0, dataBarang.size() - 1, keyword);
        if (index != -1) {
            System.out.println(">> BARANG DITEMUKAN!");
            System.out.println(dataBarang.get(index).toString());
        } else {
            System.out.println(">> Barang tidak ditemukan.");
        }
    }

    static int binarySearch(ArrayList<ItemBarang> arr, int left, int right, String x) {
        if (right >= left) {
            int mid = left + (right - left) / 2;
            String midVal = arr.get(mid).namaBarang.toLowerCase();
            String cari = x.toLowerCase();

            if (midVal.equals(cari)) return mid;
            if (midVal.compareTo(cari) > 0) return binarySearch(arr, left, mid - 1, x);
            return binarySearch(arr, mid + 1, right, x);
        }
        return -1;
    }
}
