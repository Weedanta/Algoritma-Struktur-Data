import java.util.*;
public class main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedList<String> MahasiswaLama = new LinkedList<String>();
        LinkedList<String> MahasiswaBaru = new LinkedList<>();
        LinkedList<String> SI = new LinkedList<>();
        LinkedList<String> TI = new LinkedList<>();
        LinkedList<String> PTI = new LinkedList<>();

        int banyakMahasiswa = sc.nextInt();
        sc.nextLine();
            for (int i = 0; i < banyakMahasiswa; i++) {
                String nama = sc.nextLine().trim();
                String NIM = nama.substring(nama.lastIndexOf(" ")+1);

                int angkatan = Integer.parseInt(NIM.substring(0,2));
                String namaSaja = nama.substring(0, nama.lastIndexOf(" "));
                String prodi = NIM.substring(5,7);

                if (angkatan>=22) {
                    MahasiswaBaru.add(namaSaja);
                } else {
                    MahasiswaLama.add(namaSaja);
                }
                if (prodi.equals("04")) {
                    SI.add(namaSaja);
                }
                if (prodi.equals("06")) {
                    PTI.add(namaSaja);
                }
                if (prodi.equals("07")) {
                    TI.add(namaSaja);
                }
            }

            if (!MahasiswaLama.isEmpty()) {
                System.out.println("Mahasiswa Lama:");
                for (String mahasiswa : MahasiswaLama) {
                    System.out.println(mahasiswa);
                }
                System.out.println();
            }
            if (!MahasiswaBaru.isEmpty()) {
                System.out.println("Mahasiswa Baru:");
                for (String mahasiswa : MahasiswaBaru) {
                    System.out.println(mahasiswa);
                }
                System.out.println();
            }
            if (!SI.isEmpty()) {
                System.out.println("SI:");
                for (String mahasiswa : SI) {
                    System.out.println(mahasiswa);
                }
                System.out.println();
            }
            if (!PTI.isEmpty()) {
                System.out.println("PTI:");
                for (String mahasiswa : PTI) {
                    System.out.println(mahasiswa);
                }
                System.out.println();
            }
            if (!TI.isEmpty()) {
                System.out.println("TI:");
                for (String mahasiswa : TI) {
                    System.out.println(mahasiswa);
                }
                System.out.println();
            }
        sc.close();
    }
}
