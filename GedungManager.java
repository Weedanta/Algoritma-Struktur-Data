import java.util.*;

public class GedungManager {
    private LinkedList<String> gedung; 
    private int posisiAgen; 

    public GedungManager() {
        gedung = new LinkedList<>();
        posisiAgen = -1; 
    }

    public void prosesPerintah(String[] perintah) {
        if (perintah.length < 2) {
            System.out.println("Perintah tidak valid.");
            return;
        }

        String aksi = perintah[0];
        String namaLantai = perintah.length > 2 ? perintah[2] : null;

        switch (aksi) {
            case "FONDASI":
                fondasi();
                break;
            case "BANGUN":
                if (namaLantai == null) {
                    System.out.println("Perintah BANGUN membutuhkan nama lantai.");
                    return;
                }
                bangunLantai(namaLantai);
                break;
            case "HANCURKAN":
                hancurkanLantai();
                break;
            case "LIFT":
                if (perintah.length < 3) {
                    System.out.println("Perintah LIFT membutuhkan arah.");
                    return;
                }
                lift(perintah[2]);
                break;
            case "SKETSA":
                sketsa();
                break;
            default:
                System.out.println("Perintah tidak dikenal.");
        }
    }

    private void fondasi() {
        gedung.clear(); 
        posisiAgen = -1; 
    }

    private void bangunLantai(String namaLantai) {
        gedung.addLast(namaLantai); 
        posisiAgen = gedung.size() - 1; 
    }

    private void hancurkanLantai() {
        if (!gedung.isEmpty() && posisiAgen >= 0) {
            String lantaiHancur = gedung.remove(posisiAgen);
            System.out.println(lantaiHancur);

            posisiAgen = Math.max(0, gedung.size() - 1);
        }
    }

    private void lift(String arah) {
        if (arah.equals("ATAS") && posisiAgen < gedung.size() - 1) {
            posisiAgen++;
        } else if (arah.equals("BAWAH") && posisiAgen > 0) {
            posisiAgen--;
        }

        System.out.println(gedung.get(posisiAgen));
    }

    private void sketsa() {
        StringBuilder sb = new StringBuilder();
        for (String lantai : gedung) {
            sb.append(lantai);
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GedungManager manager = new GedungManager();
        int N = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < N; i++) {
            String[] perintah = scanner.nextLine().split(" ");
            manager.prosesPerintah(perintah);
        }
    }
}
