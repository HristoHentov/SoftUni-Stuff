import java.util.Scanner;

public class _01Simplified {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String pattern = sc.nextLine();
        
        while (true){
            
            int firstOccurance = input.indexOf(pattern);
            int lastOccurance = input.lastIndexOf(pattern);
            
            if (firstOccurance > -1 && lastOccurance > -1 && pattern.length() > 0){
                StringBuilder sb = new StringBuilder(input);
                
                sb.replace(firstOccurance, pattern.length() + firstOccurance, "");
                sb.replace(lastOccurance - pattern.length(), pattern.length() + (lastOccurance - pattern.length()), "");
                
                input = sb.toString();
                System.out.println("Shaked it.");
               
                sb = new StringBuilder(pattern);
                
                if (pattern.length() > 0){
                    sb.deleteCharAt(pattern.length() / 2);
                    pattern = sb.toString();
                }
                
            } 
            else {
                System.out.println("No shake.");
                System.out.println(input);
                break;
            }
        }
    }
}
