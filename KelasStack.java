import java.util.*;

public class KelasStack{
    public static void main(String [] args){
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> max = new Stack<>();
        
        Scanner sc = new Scanner (System.in);
        int loop = sc.nextInt();
        sc.nextLine();
        
        for(int i= 0; i < loop; i++){
            String[] input = sc.nextLine().split(" ");
            
            switch (input[0]) {
                case "1":
                    stack.push(input[1]);
                    if(input[1]=stack.peek()){
                        
                    }
                    break;
                case "2":
                    stack.pop();
                    break;
                case "3":
                    System.out.println(stack.peek());
                    
                    break;
                
            }
            
        }
    }
}