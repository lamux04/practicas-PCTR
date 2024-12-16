package practica11;

import java.rmi.Naming;

public class cPiMonteCarlo {
    public static void main(String[] args) {
        try {
            String URLRegistro = "rmi://localhost:1234/pi";
            iPiMonteCarlo h = (iPiMonteCarlo) Naming.lookup(URLRegistro);
            // h.reset();
            double pi = h.contribuir(100000);

            System.out.println("Valor de PI: " + pi);

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
