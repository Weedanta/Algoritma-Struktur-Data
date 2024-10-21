import java.util.*;

public class AplikasiPlaylist {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Playlist> daftarPlaylist = new HashMap<>();

        while (scanner.hasNextLine()) {
            String baris = scanner.nextLine().trim();
            if (baris.equals("EXIT")) {
                break;
            }

            String[] perintah = baris.split(" ");
            String aksi = perintah[0];

            if (aksi.equals("INSERT")) {
                if (perintah.length == 2) {
                    // INSERT A : Membuat playlist baru A
                    String namaPlaylist = perintah[1];
                    if (!daftarPlaylist.containsKey(namaPlaylist)) {
                        daftarPlaylist.put(namaPlaylist, new Playlist(namaPlaylist));
                    }
                } else if (perintah.length == 3) {
                    // INSERT A B : Memasukkan musik di playlist A berjudul B pada urutan paling akhir
                    String namaPlaylist = perintah[1];
                    String judulLagu = perintah[2];
                    Playlist playlist = daftarPlaylist.get(namaPlaylist);
                    if (playlist != null) {
                        playlist.tambahLagu(judulLagu);
                    }
                }
            } else if (aksi.equals("MOVE")) {
                // MOVE A X Y : Memindahkan posisi musik dari index X ke index Y di playlist A
                String namaPlaylist = perintah[1];
                int indeksAsal = Integer.parseInt(perintah[2]);
                int indeksTujuan = Integer.parseInt(perintah[3]);
                Playlist playlist = daftarPlaylist.get(namaPlaylist);
                if (playlist != null && !playlist.kosong()) {
                    playlist.pindahLagu(indeksAsal, indeksTujuan);
                }
            } else if (aksi.equals("REMOVE")) {
                // REMOVE A N : Menghapus musik pada index ke N di playlist A
                String namaPlaylist = perintah[1];
                int indeks = Integer.parseInt(perintah[2]);
                Playlist playlist = daftarPlaylist.get(namaPlaylist);
                if (playlist != null && !playlist.kosong()) {
                    playlist.hapusLagu(indeks);
                }
            } else if (aksi.equals("PLAYAT")) {
                // PLAYAT A N : Memutar musik pada posisi ke N di playlist A
                String namaPlaylist = perintah[1];
                int indeks = Integer.parseInt(perintah[2]);
                Playlist playlist = daftarPlaylist.get(namaPlaylist);
                if (playlist != null && !playlist.kosong()) {
                    playlist.putarDi(indeks);
                }
            } else if (aksi.equals("NEXT")) {
                // NEXT A : Memutar musik selanjutnya dalam playlist A
                String namaPlaylist = perintah[1];
                Playlist playlist = daftarPlaylist.get(namaPlaylist);
                if (playlist != null && !playlist.kosong()) {
                    playlist.putarBerikutnya();
                }
            } else if (aksi.equals("PREV")) {
                // PREV A : Memutar musik sebelumnya dalam playlist A
                String namaPlaylist = perintah[1];
                Playlist playlist = daftarPlaylist.get(namaPlaylist);
                if (playlist != null && !playlist.kosong()) {
                    playlist.putarSebelumnya();
                }
            }
        }

        scanner.close();
    }
}

class Playlist {
    private String nama;
    private LinkedList<String> daftarLagu;
    private int indeksSekarang; // -1 jika belum ada lagu yang diputar

    public Playlist(String nama) {
        this.nama = nama;
        this.daftarLagu = new LinkedList<>();
        this.indeksSekarang = -1;
    }

    public void tambahLagu(String judulLagu) {
        daftarLagu.add(judulLagu);
    }

    public void pindahLagu(int indeksAsal, int indeksTujuan) {
        int ukuran = daftarLagu.size();
        int indeksAsalDisesuaikan = sesuaikanIndeks(indeksAsal, ukuran);
        int indeksTujuanDisesuaikan = sesuaikanIndeks(indeksTujuan, ukuran);

        if (indeksAsalDisesuaikan == indeksTujuanDisesuaikan) return;

        // Ambil dan hapus lagu dari indeks asal
        String lagu = daftarLagu.remove(indeksAsalDisesuaikan);
        // Tambahkan lagu pada indeks tujuan
        daftarLagu.add(indeksTujuanDisesuaikan, lagu);

        // Update indeks lagu yang sedang diputar
        if (indeksSekarang == indeksAsalDisesuaikan) {
            indeksSekarang = indeksTujuanDisesuaikan;
        } else if (indeksAsalDisesuaikan < indeksSekarang && indeksSekarang <= indeksTujuanDisesuaikan) {
            indeksSekarang--;
        } else if (indeksTujuanDisesuaikan <= indeksSekarang && indeksSekarang < indeksAsalDisesuaikan) {
            indeksSekarang++;
        }
    }

    public void hapusLagu(int indeks) {
        int ukuran = daftarLagu.size();
        if (ukuran == 0) return; // No song to delete

        int indeksDisesuaikan = sesuaikanIndeks(indeks, ukuran);

        // Simpan apakah lagu yang dihapus adalah lagu yang sedang diputar
        boolean sedangDiputar = (indeksDisesuaikan == indeksSekarang);

        // Hapus lagu pada indeks yang ditentukan
        daftarLagu.remove(indeksDisesuaikan);

        // Update ukuran setelah penghapusan
        ukuran--;

        // Jika playlist kosong setelah penghapusan, reset indeks
        if (ukuran == 0) {
            indeksSekarang = -1;
            return;
        }

        if (sedangDiputar) {
            if (ukuran == 1) {
                // Only one song left after deletion
                indeksSekarang = 0;
            } else if (indeksDisesuaikan < ukuran) {
                // There are songs after the deleted song
                indeksSekarang = indeksDisesuaikan % ukuran;
            } else {
                // Move to the previous song
                indeksSekarang = (indeksDisesuaikan - 1 + ukuran) % ukuran;
            }
        } else {
            // If the deleted song is before the current song, decrement indeksSekarang
            if (indeksDisesuaikan < indeksSekarang) {
                indeksSekarang--;
            }
            // No need to adjust if the deleted song is after the current song
        }
    }

    public void putarDi(int indeks) {
        int ukuran = daftarLagu.size();

        int indeksDisesuaikan = sesuaikanIndeks(indeks, ukuran);

        indeksSekarang = indeksDisesuaikan;
        System.out.println("Playlist " + nama + " sedang diputar: " + daftarLagu.get(indeksSekarang));
    }

    public void putarBerikutnya() {
        if (indeksSekarang == -1) {
            indeksSekarang = 0;
        } else {
            indeksSekarang = (indeksSekarang + 1) % daftarLagu.size();
        }
        System.out.println("Playlist " + nama + " sedang diputar: " + daftarLagu.get(indeksSekarang));
    }

    public void putarSebelumnya() {
        if (indeksSekarang == -1) {
            indeksSekarang = daftarLagu.size() - 1;
        } else {
            indeksSekarang = (indeksSekarang - 1 + daftarLagu.size()) % daftarLagu.size();
        }
        System.out.println("Playlist " + nama + " sedang diputar: " + daftarLagu.get(indeksSekarang));
    }

    private int sesuaikanIndeks(int indeks, int ukuran) {
        int indeksDisesuaikan = indeks;
        // Tangani indeks negatif untuk indeks circular
        indeksDisesuaikan = ((indeksDisesuaikan % ukuran) + ukuran) % ukuran;
        return indeksDisesuaikan;
    }

    public boolean kosong() {
        return daftarLagu.isEmpty();
    }
}
