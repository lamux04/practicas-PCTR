package practica11;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class PiMonteCarlo {

    public static void main(String[] args) {
        int numPuertoRMI = 1234;
        String URLRegistro;
        try {
            impMonteCarlo objetoExportado = new impMonteCarlo();
            arrancarRegistro(numPuertoRMI);
            URLRegistro = "rmi://localhost:1234/pi";
            Naming.rebind(URLRegistro, objetoExportado);
            System.out.println("Servidor de pi preparado");
        } catch (Exception exc) {
            System.out.println("Excepción en Servidor pi: " + exc);
        }
    }

    public static void arrancarRegistro(int numPuertoRMI) throws RemoteException {
        try {
            Registry registro = LocateRegistry.getRegistry(numPuertoRMI);
            registro.list();
        } catch (RemoteException exc) {
            System.out.println("El registro RMI no se puede localizar en el puerto: " + numPuertoRMI);
            LocateRegistry.createRegistry(numPuertoRMI);
            System.out.println("Registro RMI creado en el puerto " + numPuertoRMI);
        }
    }
}

class impMonteCarlo extends UnicastRemoteObject implements iPiMonteCarlo {
    Random random = new Random();
    int totalPuntos = 0;
    int puntosCirculo = 0;

    public impMonteCarlo() throws RemoteException {
        totalPuntos = 0;
        puntosCirculo = 0;
    }

    public void reset() throws RemoteException {
        totalPuntos = 0;
        puntosCirculo = 0;
    }

    public double contribuir(int c) throws RemoteException {
        for (int i = 0; i < c; ++i)
        {
            double nuevoPuntoX = random.nextDouble() * 2 - 1;
            double nuevoPuntoY = random.nextDouble() * 2 - 1;
            double distancia = Math.sqrt(nuevoPuntoX * nuevoPuntoX + nuevoPuntoY * nuevoPuntoY);
            if (distancia <= 1)
                puntosCirculo++;
        }
        totalPuntos += c;

        return (double) puntosCirculo * 4 / totalPuntos;
    }
}

