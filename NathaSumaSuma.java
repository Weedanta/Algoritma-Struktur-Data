import java.util.Scanner;

public class NathaSumaSuma {

    public static int kedalamanMaksimal(String s) {
        int kedalamanSaatIni = 0;
        int kedalamanMaksimal = 0;
        int keseimbangan = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                kedalamanSaatIni++;
                keseimbangan++;
                kedalamanMaksimal = Math.max(kedalamanMaksimal, kedalamanSaatIni);
            } else if (c == ')') {
                kedalamanSaatIni--;
                keseimbangan--;
            }
            
            if (keseimbangan < 0) {
                return -1;
            }
        }

        if (keseimbangan != 0) {
            return -1;
        }
        
        return kedalamanMaksimal;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        
        System.out.println(kedalamanMaksimal(input));
    }
}
