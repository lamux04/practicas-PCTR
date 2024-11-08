import java.math.BigInteger;

public class bigFactorial {
    public static void main(String[] args) {
        int a = 342532;
        BigInteger resultado = new BigInteger("1");
    
        double inicio = System.nanoTime();

        for(int i = a; i > 1; i--)
        {
            resultado = resultado.multiply(BigInteger.valueOf(i));
        }

        System.out.println(resultado);
        
        double fin = System.nanoTime();
        
        System.out.println((fin - inicio) / Math.pow(10, 9));
    }
}
