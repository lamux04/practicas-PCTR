package practica8.ejercicio2;

public class monitorImpresoras {
    private final int N = 3;
    private int n = 3;
    private boolean[] libre = new boolean[N];


    public monitorImpresoras() {
        for (int i = 0; i < N; ++i)
            libre[i] = true;
    }

    synchronized int pedirImpresora() {
        while (n == 0)
            try {
                wait();
            } catch (InterruptedException e) {
            }

        int i = 0;
        while (!libre[i])
            i++;
        libre[i] = false;
        return i;
    }
    
    synchronized void liberarImpresora(int i) {
        libre[i] = true;
        n++;
        notifyAll();
    }
}
