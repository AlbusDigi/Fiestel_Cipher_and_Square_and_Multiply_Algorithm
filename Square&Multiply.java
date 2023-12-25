import java.util.Scanner; 
public class SquareMultiplyAlgorithm { 
    public static void main(String[] args) { 
        Scanner input = new Scanner(System.in); 
        System.out.print("Entrez la valeur de x : "); 
        int x = input.nextInt(); 
        System.out.print("Entrez la valeur de b : "); 
        int b = input.nextInt(); 
        System.out.print("Entrez la valeur de n : "); 
        int n = input.nextInt(); 
        int result = squareMultiply(x, b, n); 
        System.out.println("Le résultat de " + x + "^" + b + " (mod " + n + ") est : " + result); 
        } 
        public static int squareMultiply(int x, int b, int n) { 
            int result = 1; while (b > 0) { 
                if (b % 2 == 1) { 
                    result = (result * x) % n; } 
                    x = (x * x) % n; 
                    b = b / 2; } 
                    return result;
                     }
                     }