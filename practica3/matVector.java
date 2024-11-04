import java.lang.Math;
import java.util.Random;
public class matVector {
    public static void main(String[] args) 
    {
        int[][] matriz1 = new int[(int) Math.pow(10, 3)][(int) Math.pow(10, 3)];
        int[][] matriz2 = new int[(int) Math.pow(10, 3)][(int) Math.pow(10, 3)];
        int[][] matriz3 = new int[(int) Math.pow(10, 3)][(int) Math.pow(10, 3)];
        Random random = new Random();

        for (int i = 0; i < Math.pow(10, 3); ++i)
        {
            for (int j = 0; j < Math.pow(10, 3); ++j) {
                matriz1[i][j] = random.nextInt();
                matriz2[i][j] = random.nextInt();
            }
        }

        double inicio = System.nanoTime();
        
        for (int k = 0; k < Math.pow(10, 3); ++k)
        {
            for (int i = 0; i < Math.pow(10, 3); ++i) {
                int valor = 0;
                for (int j = 0; j < Math.pow(10, 3); ++j) {
                    valor += matriz1[i][j] * matriz2[i][k];
                }
                matriz3[k][i] = valor;
            }
        }
        
        double fin = System.nanoTime();

        System.out.println((fin - inicio) / Math.pow(10, 9));
        
    }
}
