package practica11.ejercicio2;

import java.rmi.Naming;

public class cPiMonteCarlo {
    public static void main(String[] args) {
        try {
            String URLRegistro = "rmi://localhost:3333/pi";
            iPiMonteCarlo objeto = (iPiMonteCarlo) Naming.lookup(URLRegistro);
            System.out.println(objeto.contribuir(100));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
