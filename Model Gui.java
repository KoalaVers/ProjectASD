import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SistemLostFoundSwing extends JFrame {

    // ====== DATA MODEL (Tetap sama) ======
    static class ItemBarang {
        String kodeBarang, namaBarang, deskripsi, lokasi, tanggal, pelapor, status;

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
            return "<html>"
                    + "<b style='font-size:14px;'>🔎 " + namaBarang + " (" + kodeBarang + ")</b><br>"
                    + "• Status: " + status + "<br>"
                    + "• Deskripsi: " + deskripsi + "<br>"
                    + "• Lokasi: " + lokasi + "<br>"
                    + "• Tanggal: " + tanggal + "<br>"
                    + "• Kontak: " + pelapor
                    + "<hr>"
                    + "</html>";
        }
    }

    static ArrayList<ItemBarang> dataBarang = new ArrayList<>();
    JTextPane outputArea;

    public SistemLostFoundSwing() {

        // ====== DATA AWAL ======
        dataBarang.add(new ItemBarang("REG001", "Laptop Asus", "Hitam, Lecet dikit", "Lab Dasar", "25-11-2024", "Akbar", "DITEMUKAN"));
        dataBarang.add(new ItemBarang("REG002", "Casan Type C", "Putih", "TU Fasilkom", "24-11-2024", "Balqis", "KEHILANGAN"));
        dataBarang.add(new ItemBarang("REG003", "Kunci Motor", "Gantungan Doraemon", "Ruang 80", "26-11-2024", "Ray", "DITEMUKAN"));

        // ====== JUDUL UTAMA ======
        JLabel title = new JLabel("SISTEM LOST & FOUND  •  FASILKOM UNSIKA", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        title.setOpaque(true);
        title.setBackground(new Color(78, 84, 200));
        title.setBorder(new EmptyBorder(25, 10, 25, 10));

        // ====== SIDEBAR ======
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(6, 1, 10, 10));
        sidebar.setBackground(new Color(240, 240, 255));
        sidebar.setBorder(new EmptyBorder(20, 20, 20, 20));

        JButton btnLost = createMenuButton("➕ Lapor Kehilangan");
        JButton btnFound = createMenuButton("📥 Catat Penemuan");
        JButton btnView = createMenuButton("📋 Lihat Semua Data");
        JButton btnSearch = createMenuButton("🔍 Cari Barang");
        JButton btnExit = createMenuButton("❌ Keluar");

        sidebar.add(btnLost);
        sidebar.add(btnFound);
        sidebar.add(btnView);
        sidebar.add(btnSearch);
        sidebar.add(btnExit);

        // ====== OUTPUT AREA ======
        outputArea = new JTextPane();
        outputArea.setContentType("text/html");
        outputArea.setEditable(false);
        outputArea.setFont(new Font("SansSerif", Font.PLAIN, 15));

        JScrollPane outputScroll = new JScrollPane(outputArea);
        outputScroll.setBorder(new LineBorder(new Color(200, 200, 200), 2, true));

        JPanel outputCard = new JPanel(new BorderLayout());
        outputCard.setBorder(new EmptyBorder(20, 20, 20, 20));
        outputCard.add(outputScroll);
        outputCard.setBackground(Color.WHITE);

        // ====== FRAME LAYOUT ======
        setLayout(new BorderLayout());
        add(title, BorderLayout.NORTH);
        add(sidebar, BorderLayout.WEST);
        add(outputCard, BorderLayout.CENTER);

        // ====== ACTIONS ======
        btnLost.addActionListener(e -> formInput("KEHILANGAN"));
        btnFound.addActionListener(e -> formInput("DITEMUKAN"));
        btnView.addActionListener(e -> tampilkanData());
        btnSearch.addActionListener(e -> cariData());
        btnExit.addActionListener(e -> System.exit(0));

        // ===== FRAME ======
        setTitle("Sistem Lost & Found - Swing Modern UI");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    // ===== BUTTON MODERN =====
    JButton createMenuButton(String text) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(78, 84, 200));
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setBorder(new EmptyBorder(10, 10, 10, 10));

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(100, 110, 230));
            }
            public void mouseExited(MouseEvent e) {
                btn.setBackground(new Color(78, 84, 200));
            }
        });
        return btn;
    }

    // ====== FORM INPUT ======
    void formInput(String tipe) {

        JTextField fKode = new JTextField();
        JTextField fNama = new JTextField();
        JTextField fDesk = new JTextField();
        JTextField fLok = new JTextField();
        JTextField fTgl = new JTextField();
        JTextField fKontak = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Kode Barang:")); panel.add(fKode);
        panel.add(new JLabel("Nama Barang:")); panel.add(fNama);
        panel.add(new JLabel("Deskripsi:")); panel.add(fDesk);
        panel.add(new JLabel("Lokasi:")); panel.add(fLok);
        panel.add(new JLabel("Tanggal:")); panel.add(fTgl);
        panel.add(new JLabel(tipe.equals("KEHILANGAN") ? "Pelapor:" : "Penemu:")); 
        panel.add(fKontak);

        int result = JOptionPane.showConfirmDialog(
                this, panel, "Input " + tipe,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            ItemBarang ib = new ItemBarang(
                    fKode.getText(),
                    fNama.getText(),
                    fDesk.getText(),
                    fLok.getText(),
                    fTgl.getText(),
                    fKontak.getText(),
                    tipe
            );
            dataBarang.add(ib);
            outputArea.setText("<html><h2 style='color:#4e54c8;'>✔ Data Berhasil Ditambahkan!</h2>" + ib + "</html>");
        }
    }

    // ===== QUICK SORT (Tetap) =====
    static void quickSort(ArrayList<ItemBarang> arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    static int partition(ArrayList<ItemBarang> arr, int low, int high) {
        String pivot = arr.get(high).namaBarang.toLowerCase();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr.get(j).namaBarang.toLowerCase().compareTo(pivot) < 0) {
                i++;
                var temp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, temp);
            }
        }
        var temp = arr.get(i+1);
        arr.set(i+1, arr.get(high));
        arr.set(high, temp);
        return i + 1;
    }

    // ===== TAMPILKAN DATA =====
    void tampilkanData() {
        quickSort(dataBarang, 0, dataBarang.size() - 1);

        StringBuilder sb = new StringBuilder("<html><h2>📋 Data Barang Terurut (A-Z)</h2>");

        for (ItemBarang b : dataBarang) {
            sb.append(b.toString());
        }
        sb.append("</html>");

        outputArea.setText(sb.toString());
    }

    // ===== BINARY SEARCH =====
    void cariData() {
        String keyword = JOptionPane.showInputDialog(this, "Masukkan nama barang:");

        if (keyword == null || keyword.isEmpty()) return;

        quickSort(dataBarang, 0, dataBarang.size() - 1);

        int index = binarySearch(dataBarang, 0, dataBarang.size() - 1, keyword.toLowerCase());

        if (index != -1) {
            outputArea.setText("<html><h2 style='color:green;'>✔ Barang Ditemukan!</h2>" + dataBarang.get(index) + "</html>");
        } else {
            outputArea.setText("<html><h2 style='color:red;'>❌ Barang Tidak Ditemukan</h2></html>");
        }
    }

    static int binarySearch(ArrayList<ItemBarang> arr, int left, int right, String x) {
        if (right >= left) {
            int mid = left + (right - left) / 2;
            String val = arr.get(mid).namaBarang.toLowerCase();

            if (val.equals(x)) return mid;
            if (val.compareTo(x) > 0) return binarySearch(arr, left, mid - 1, x);
            return binarySearch(arr, mid + 1, right, x);
        }
        return -1;
    }

    // MAIN
    public static void main(String[] args) {
        SwingUtilities.invokeLater(SistemLostFoundSwing::new);
    }
}
