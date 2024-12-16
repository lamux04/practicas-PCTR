package practica11;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class sBonoLoto {

    public static void main(String[] args) {
        int numPuertoRMI = 1234;
        String URLRegistro;
        try {
            impBonoLoto objetoExportado = new impBonoLoto();
            arrancarRegistro(numPuertoRMI);
            URLRegistro = "rmi://localhost:1234/bonoloto";
            Naming.rebind(URLRegistro, objetoExportado);
            System.out.println("Servidor de bonoloto preparado");
        } catch (Exception exc) {
            System.out.println("Excepción en Servidor bonoloto: " + exc);
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

class impBonoLoto extends UnicastRemoteObject implements iBonoLoto {
    int[] numeros;
    Random random = new Random();

    public impBonoLoto() throws RemoteException
    {
        resetServidor();
    }

    public void resetServidor() throws RemoteException {
        numeros = new int[6];
        for (int i = 0; i < 6; ++i)
        {
            numeros[i] = random.nextInt(50);
        }
    }

    public boolean compApuesta(int[] apuesta) throws RemoteException {
        boolean resultado = true;
        int i = 0;
        while (i < 6 && resultado)
        {
            boolean aciertaNumero = false;
            int j = 0;
            while (j < 6 && !aciertaNumero) {
                if (numeros[j] == apuesta[i])
                    aciertaNumero = true;
                ++j;
            }
            if (!aciertaNumero)
                resultado = false;
        }
        return resultado;
    }
}
