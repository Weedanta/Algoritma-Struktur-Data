import java.util.*;

class Mhs {
    String nama;
    String nim;
    double ipk;
    String prodi;

    public Mhs(String nama, String nim, double ipk) {
        this.nama = nama;
        this.nim = nim;
        this.ipk = ipk;

        switch (nim.substring(5, 7)) {
            case "04":
                this.prodi = "SI";
                break;
            case "06":
                this.prodi = "PTI";
                break;
            case "07":
                this.prodi = "TI";
                break;
        }
    }

    @Override
    public String toString() {
        return nama + " " + ipk;
    }
}

class Leaderboard {
    LinkedList<Mhs> daftarMahasiswa = new LinkedList<>();
    LinkedList<Mhs> daftarSI = new LinkedList<>();
    LinkedList<Mhs> daftarPTI = new LinkedList<>();
    LinkedList<Mhs> daftarTI = new LinkedList<>();

    void tambahMahasiswa(String nama, String nim, double ipk) {
        Mhs mhs = new Mhs(nama, nim, ipk);

        // Untuk List Global
        daftarMahasiswa.add(mhs);

        Collections.sort(daftarMahasiswa, new Comparator<Mhs>() {
            @Override
            public int compare(Mhs orang1, Mhs orang2) {
                // Mengurutkan berdasarkan IPK
                if (orang1.ipk != orang2.ipk) {
                    return Double.compare(orang2.ipk, orang1.ipk);
                } else {
                    // Mengurutkan berdasarkan NIM
                    return orang1.nim.compareTo(orang2.nim);
                }
            }
        });

        // Untuk Linked List setiap Prodi
        switch (mhs.prodi) {
            case "SI":
                daftarSI.add(mhs);
                Collections.sort(daftarSI, new Comparator<Mhs>() {
                    @Override
                    public int compare(Mhs orang1, Mhs orang2) {
                        if (orang1.ipk != orang2.ipk) {
                            return Double.compare(orang2.ipk, orang1.ipk);
                        } else {
                            return orang1.nim.compareTo(orang2.nim);
                        }
                    }
                });
                break;
            case "PTI":
                daftarPTI.add(mhs);
                Collections.sort(daftarPTI, new Comparator<Mhs>() {
                    @Override
                    public int compare(Mhs orang1, Mhs orang2) {
                        if (orang1.ipk != orang2.ipk) {
                            return Double.compare(orang2.ipk, orang1.ipk);
                        } else {
                            return orang1.nim.compareTo(orang2.nim);
                        }
                    }
                });
                break;
            case "TI":
                daftarTI.add(mhs);
                Collections.sort(daftarTI, new Comparator<Mhs>() {
                    @Override
                    public int compare(Mhs orang1, Mhs orang2) {
                        if (orang1.ipk != orang2.ipk) {
                            return Double.compare(orang2.ipk, orang1.ipk);
                        } else {
                            return orang1.nim.compareTo(orang2.nim);
                        }
                    }
                });
                break;
        }

    }

    void printAll() {
        if (daftarMahasiswa.isEmpty()) {
            System.out.println("EMPTY!");
            return;
        } else {
            int ranking = 1;
            int currentRank = 1;
            double previousIpk = -1;

            for (Mhs print : daftarMahasiswa) {
                if (print.ipk != previousIpk) {
                    ranking = currentRank;
                } else {
                    currentRank--;
                }
                System.out.println(ranking + ". " + print.nama + " " + print.ipk);
                previousIpk = print.ipk;
                currentRank++;
            }
        }
    }

    void printList(LinkedList<Mhs> daftar) {
        if (daftar.isEmpty()) {
            System.out.println("EMPTY!");
            return;
        }
        int ranking = 1;
        int currentRank = 1;
        double previousIpk = -1;

        for (Mhs print : daftar) {
            if (print.ipk != previousIpk) {
                ranking = currentRank;
            } else {    
                currentRank--;
            }
            System.out.println(ranking + ". " + print.nama + " " + print.ipk);
            previousIpk = print.ipk;
            currentRank++;
        }
    }

    void printListSI() {
        printList(daftarSI);
    }

    void printListPTI() {
        printList(daftarPTI);
    }

    void printListTI() {
        printList(daftarTI);
    }
}

public class TesAdvancedLeaderboard {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Leaderboard leaderboard = new Leaderboard();

        while (true) {
            String req = sc.nextLine();

            if (req.equalsIgnoreCase("END")) {
                break;
            }

            String[] bagian = req.split("_");

            switch (bagian[0].toUpperCase()) {
                case "ADD":
                    String nama = bagian[1];
                    String nim = bagian[2];
                    double ipk = Double.parseDouble(bagian[3]);
                    leaderboard.tambahMahasiswa(nama, nim, ipk);
                    break;

                case "PRINT":
                    if (bagian.length < 2) {
                        System.out.println("PRINT MAHASISWA\n");
                        leaderboard.printAll();
                        System.out.println();
                        continue;
                    }

                    if (bagian[1].equalsIgnoreCase("SI")) {
                        System.out.println("PRINT MAHASISWA SISTEM INFORMASI\n");
                        leaderboard.printListSI();
                        System.out.println();
                    } else if (bagian[1].equalsIgnoreCase("TI")) {
                        System.out.println("PRINT MAHASISWA TEKNOLOGI INFORMASI\n");
                        leaderboard.printListTI();
                        System.out.println();
                    } else if (bagian[1].equalsIgnoreCase("PTI")) {
                        System.out.println("PRINT MAHASISWA PENDIDIKAN TEKNOLOGI INFORMASI\n");
                        leaderboard.printListPTI();
                        System.out.println();
                    }
                    break;
            }
        }
        sc.close();
    }
}
