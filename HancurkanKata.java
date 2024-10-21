import java.util.*;

public class HancurkanKata {
    public static int hancurkanKata(String kalimat) {
        String[] kata = kalimat.split(" ");
        Stack<String> stack = new Stack<>();
        
        for (String k : kata) {
            if (!stack.isEmpty() && stack.peek().equalsIgnoreCase(k)) {
                stack.pop();
            } else {
                stack.push(k);
            }
        }
        
        return stack.size();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        
        System.out.println(hancurkanKata(input));
    }
}
