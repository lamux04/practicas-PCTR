package practica11.ejercicio2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface iPiMonteCarlo extends Remote {
    double contribuir(int n) throws RemoteException;

    void reset() throws RemoteException;
}

