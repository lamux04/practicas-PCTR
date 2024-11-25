package practica8;

public class cuentaCompartida {
    public double dinero = 0;

    synchronized void ingreso (double cuantia)
    {
        dinero += cuantia;
        notifyAll();
    }

    synchronized double reintegro (double cuantia) throws InterruptedException
    {
        while (dinero < cuantia)
        {
            wait();
        }   
        dinero -= cuantia;
        return cuantia;
    }
}
