package practica11.ejercicio1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface iBonoLoto extends Remote {
    void reiniciar() throws RemoteException;
    boolean comprobar(int[] v) throws RemoteException;
}
