package practica11.ejercicio1;

import java.rmi.Naming;

public class cBonoLoto {
    public static void main(String[] args) {
        try {
            String URLRegistro = "rmi://localhost:3333/Bonoloto";
            iBonoLoto h = (iBonoLoto) Naming.lookup(URLRegistro);
            int[] v = { 1, 2, 3, 4, 5, 6 };
            if (h.comprobar(v)) {
                System.out.println("Acertado");
            } else {
                System.out.println("No acertado");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
