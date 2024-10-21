import java.util.Scanner;

public class TangkapKoruptor {
    public static void main(String[] args) {
        // Inisialisasi
        int Tertangkap = 0;
        int Kabur = 0;
        int SalahTangkap = 0;

        // Scanner
        Scanner sc = new Scanner(System.in);
        int baris = sc.nextInt();
        int kolom = sc.nextInt();
        int[][] posisi = new int[baris][kolom];

        // Memasukkan banyak nilai
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                posisi[i][j] = sc.nextInt();
            }
        }

        // Operasi tangkap tangan
        int banyakTangkapTangan = sc.nextInt();
        for (int i = 0; i < banyakTangkapTangan; i++) {
            // posisi penangkapan
            int barisPenangkapan = sc.nextInt();
            int kolomPenangkapan = sc.nextInt();

            if (barisPenangkapan >= baris || kolomPenangkapan >= kolom || barisPenangkapan < 0
                    || kolomPenangkapan < 0) {
                SalahTangkap++;
                continue;
            }

            // cek
            if (posisi[barisPenangkapan][kolomPenangkapan] == 1) {
                Tertangkap++;
                posisi[barisPenangkapan][kolomPenangkapan] = 0;
            } else {
                SalahTangkap++;
            }
        }

        // banyak salah tangkap
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                if (posisi[i][j] == 1) {
                    Kabur++;
                }
            }
        }

        System.out.println("Total Ketangkap : " + Tertangkap + " orang");

        System.out.println("Total Kabur     : " + Kabur + " orang");

        System.out.println("Salah Tangkap   : " + SalahTangkap + " orang");

        sc.close();
    }
}
