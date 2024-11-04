import java.lang.Math;
import java.util.Random;
public class matVector {
    public static void main(String[] args) 
    {
        int[][] matriz = new int[(int) Math.pow(10, 3)][(int) Math.pow(10, 3)];
        int[] vector = new int[(int) Math.pow(10, 3)];
        int[] resultado = new int[(int) Math.pow(10, 3)];
        Random random = new Random();

        for (int i = 0; i < Math.pow(10, 3); ++i)
        {
            vector[i] = random.nextInt();
            for (int j = 0; j < Math.pow(10, 3); ++j) {
                matriz[i][j] = random.nextInt();
            }
        }
        
        for (int i = 0; i < Math.pow(10, 3); ++i)
        {
            int valor = 0;
            for (int j = 0; j < Math.pow(10, 3); ++j)
            {
                valor += matriz[i][j] * vector[i];
            }
            resultado[i] = valor;
        }
    }
}
