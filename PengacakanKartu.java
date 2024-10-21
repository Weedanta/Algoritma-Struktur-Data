import java.util.*;

public class PengacakanKartu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
         if (!scanner.hasNextLine()) {
            System.out.println("");
            return;
        }
        
        String input = scanner.nextLine().trim();
        
           

        
        // Jika input kosong, langsung keluar.
        if (input.isEmpty()) {
            System.out.println("");
            return;
        }

        // Memecah input menjadi array kartu.
        String[] kartu = input.split(" ");

        // Melakukan pengacakan riffle shuffle.
        kartu = riffleShuffle(kartu);

        // Melakukan pengacakan 3x+1.
        kartu = pengacakan3xPlus1(kartu);

        // Menggabungkan kembali array kartu menjadi string dan mencetaknya.
        System.out.println(String.join(" ", kartu));
    }

    // Metode untuk riffle shuffle.
    private static String[] riffleShuffle(String[] kartu) {
        int n = kartu.length; // Menggunakan int karena panjang array
        int mid = (n + 1) / 2; // Tumpukan kiri lebih besar jika jumlah kartu ganjil

        // Memisahkan kartu menjadi dua bagian.
        String[] bagian1 = Arrays.copyOfRange(kartu, 0, mid);
        String[] bagian2 = Arrays.copyOfRange(kartu, mid, n);
        
        String[] hasil = new String[n];
        int index = 0;
        
        // Menyusun kembali dengan selang-seling.
        for (int i = 0; i < bagian1.length; i++) {
            hasil[index++] = bagian1[i];
            if (i < bagian2.length) {
                hasil[index++] = bagian2[i];
            }
        }

        return hasil;
    }

    // Metode untuk pengacakan 3x+1.
    private static String[] pengacakan3xPlus1(String[] kartu) {
        int n = kartu.length; // Menggunakan int karena panjang array

        for (int i = 0; i < n; i++) {
            int newIndex;
            if (i % 2 != 0) {
                // Jika posisi ganjil, tukar dengan (3*i + 1) % n.
                newIndex = (int) ((3L * i + 1) % n); // Menggunakan long untuk perhitungan, tetapi casting ke int
            } else {
                // Jika posisi genap, tukar dengan i / 2.
                newIndex = i / 2;
            }
            // Melakukan penukaran dengan metode tukar.
            tukar(kartu, i, newIndex);
        }

        return kartu;
    }

    // Metode untuk menukar elemen pada indeks i dan j.
    private static void tukar(String[] kartu, int i, int j) {
        String temp = kartu[i];
        kartu[i] = kartu[j];
        kartu[j] = temp;
    }
}
