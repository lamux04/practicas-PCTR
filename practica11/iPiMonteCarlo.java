package practica11;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface iPiMonteCarlo extends Remote {
    public double contribuir(int c) throws RemoteException;
    public void reset() throws RemoteException;
}
