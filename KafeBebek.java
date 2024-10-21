import java.util.*;

class Pelanggan {
    String nama;
    int jumlahItem;

    Pelanggan(String nama, int jumlahItem) {
        this.nama = nama;
        this.jumlahItem = jumlahItem;
    }
}

public class KafeBebek {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();  
        int K = scanner.nextInt(); 
        scanner.nextLine(); 

        Queue<Pelanggan> antrean = new LinkedList<>();
        List<String> urutanSelesai = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            String[] data = scanner.nextLine().split(" ");
            String nama = data[0];
            int jumlahItem = Integer.parseInt(data[1]);
            antrean.add(new Pelanggan(nama, jumlahItem));
        }

        while (!antrean.isEmpty()) {
            Pelanggan pelanggan = antrean.poll(); 

            if (pelanggan.jumlahItem > K) {
                pelanggan.jumlahItem -= K;
                antrean.add(pelanggan);
            } else {
                urutanSelesai.add(pelanggan.nama);
            }
        }

        for (String nama : urutanSelesai) {
            System.out.print(nama + " ");
        }
    }
}
