public class cajero1 implements Runnable {
    cuentaCorriente cuenta;

    public cajero1 (cuentaCorriente cuenta)
    {
        this.cuenta = cuenta;
    }

    public void run ()
    {
        for (int i = 0; i < 1000; i++)
            cuenta.depositar(1);
    }
}

class cajero2 implements Runnable {
    cuentaCorriente cuenta;

    public cajero2 (cuentaCorriente cuenta)
    {
        this.cuenta = cuenta;
    }

    public void run ()
    {
        for (int i = 0; i < 1000; i++)
            cuenta.retirar(1);
    }
}
