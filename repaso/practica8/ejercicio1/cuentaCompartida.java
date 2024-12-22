package practica8.ejercicio1;

public class cuentaCompartida {
    private double saldo = 0;

    synchronized void ingreso(double cuantia) {
        saldo += cuantia;
        notifyAll();
    }

    synchronized void reintegro(double cuantia) {
        while (cuantia > saldo)
            try {
                wait();
            } catch (InterruptedException e) {
            }
        saldo -= cuantia;
    }
    
    synchronized double consultar() {
        return saldo;
    }
}
