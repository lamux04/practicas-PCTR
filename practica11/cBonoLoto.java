package practica11;

import java.rmi.Naming;
import java.util.Random;

public class cBonoLoto {
    public static void main(String[] args) {
        try {
            String URLRegistro = "rmi://localhost:1234/bonoloto";
            Random random = new Random();
            iBonoLoto h = (iBonoLoto) Naming.lookup(URLRegistro);
            boolean acertado = false;
            int[] numeros = new int[6];
            for (int i = 0; i < 6; ++i)
                numeros[i] = random.nextInt(50);
            acertado = h.compApuesta(numeros);
        
            if (acertado)
                System.out.println("Has ganado la bonoloto");
            else
                System.out.println("No has ganado");
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
