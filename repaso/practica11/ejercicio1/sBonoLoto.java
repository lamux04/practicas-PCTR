package practica11.ejercicio1;

import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class sBonoLoto {
    public static void main(String[] args) {
        try {
            BonoLoto objExportado = new BonoLoto();
            String URLRegistro = "rmi://localhost:3333/Bonoloto";
            Naming.rebind(URLRegistro, objExportado);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}

class BonoLoto extends UnicastRemoteObject implements iBonoLoto {
    private int[] numeros;
    private Random random = new Random();

    public BonoLoto() throws RemoteException {
        numeros = new int[6];
        reiniciar();
    }

    public void reiniciar() throws RemoteException {
        for (int i = 0; i < 6; ++i)
            numeros[i] = random.nextInt();
    }

    public boolean comprobar(int[] v) throws RemoteException {
        boolean acierto = true;
        int i = 0;
        while (acierto && i < 6) {
            int j = 0;
            boolean encontrado = false;
            while (!encontrado && j < 6) {
                if (v[i] == numeros[j])
                    encontrado = true;
                ++j;
            }
            if (!encontrado)
                acierto = false;
            ++i;
        }

        return acierto;
    }
}
