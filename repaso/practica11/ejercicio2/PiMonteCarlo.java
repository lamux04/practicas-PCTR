package practica11.ejercicio2;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class PiMonteCarlo {
    public static void main(String[] args) {
        try {
            String URLRegistro = "rmi://localhost:3333/pi";
            ImplementacionPiMonteCarlo objExportado = new ImplementacionPiMonteCarlo();
            Naming.rebind(URLRegistro, objExportado);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

class ImplementacionPiMonteCarlo extends UnicastRemoteObject implements iPiMonteCarlo {
    double puntos_circulo = 0;
    double puntos = 0;
    Random random = new Random();

    public ImplementacionPiMonteCarlo() throws RemoteException {
        super();
    }

    synchronized public double contribuir(int n) {
        puntos += n;
        for (int i = 0; i < n; ++i) {
            double x = random.nextDouble(-1, 1);
            double y = random.nextDouble(-1, 1);

            if (x * x + y * y <= 1)
                puntos_circulo++;
        }
        return 4 * (double) puntos_circulo / puntos;
    }
    
    synchronized public void reset() {
        puntos = puntos_circulo = 0;
    }
}