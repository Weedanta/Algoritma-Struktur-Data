import java.util.*;

public class KursiAcara {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Set<String> vip = new HashSet<>(Arrays.asList("Rektor", "Wakil Rektor", "Dekan", "Wakil Dekan", "Beyonce"));
        Set<String> tamuKhusus = new HashSet<>(Arrays.asList("Staf Akademik", "Dosen", "Kaprodi"));
        Set<String> reguler = new HashSet<>(Arrays.asList("Ketua BEM", "Ketua EM", "Mahasiswa"));

        List<String> vipList = new ArrayList<>();
        List<String> tamuKhususList = new ArrayList<>();
        List<String> regulerList = new ArrayList<>();
        int jumlahTamuTidakDikenal = 0;

        int jumlahTamu = input.nextInt();
        input.nextLine();

        for (int i = 0; i < jumlahTamu; i++) {
            String jabatan = input.nextLine();

            if (vip.contains(jabatan)) {
                vipList.add(jabatan);
            } else if (tamuKhusus.contains(jabatan)) {
                tamuKhususList.add(jabatan);
            } else if (reguler.contains(jabatan)) {
                regulerList.add(jabatan);
            } else {
                System.out.println("Jabatan tidak dikenal, tidak dapat dimasukkan.");
                jumlahTamuTidakDikenal++;
            }
        }

        cetakHasil(vipList, tamuKhususList, regulerList, jumlahTamuTidakDikenal);
    }

    private static void cetakKategori(List<String> daftar, String kategori) {
        for (int i = 0; i < daftar.size(); i++) {
            if (i != 0) {
                System.out.print(", ");
            }
            System.out.print(kategori);
        }
    }

    private static void cetakUrutanDuduk(List<String> daftar) {
        for (int i = 0; i < daftar.size(); i++) {
            if (i != 0) {
                System.out.print(", ");
            }
            System.out.print(daftar.get(i));
        }
    }

    private static void cetakHasil(List<String> vipList, List<String> tamuKhususList, List<String> regulerList, int jumlahTamuTidakDikenal) {
        if (vipList.isEmpty() && tamuKhususList.isEmpty() && regulerList.isEmpty()) {
            if (jumlahTamuTidakDikenal > 0) {
                System.out.println();
            }
            System.out.println("Penyusup mencoba menjadi tamu!");
        } else {
            System.out.println("Jabatan Tamu:");
            cetakKategori(vipList, "VIP");
            if (!vipList.isEmpty() && (!tamuKhususList.isEmpty() || !regulerList.isEmpty())) {
                System.out.print(", ");
            }
            cetakKategori(tamuKhususList, "Tamu Khusus");
            if (!tamuKhususList.isEmpty() && !regulerList.isEmpty()) {
                System.out.print(", ");
            }
            cetakKategori(regulerList, "Reguler");
            System.out.println();
            System.out.println();

            System.out.println("Urutan Duduk:");
            List<String> urutanDuduk = new ArrayList<>();
            urutanDuduk.addAll(vipList);
            urutanDuduk.addAll(tamuKhususList);
            urutanDuduk.addAll(regulerList);
            cetakUrutanDuduk(urutanDuduk);
            System.out.println();
        }
    }
}
