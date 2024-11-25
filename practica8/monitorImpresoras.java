package practica8;

public class monitorImpresoras {
    boolean[] impresoras = new boolean[3];
    int disponibles = 3;

    public monitorImpresoras() {
        disponibles = 3;
        for (int i = 0; i < 3; ++i)
            impresoras[i] = true;
    }
    
    synchronized int pedirImpresora() {
        while (disponibles == 0)
        {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
            
        int i = 0;
        while (!impresoras[i])
            ++i;
        impresoras[i] = false;
        disponibles--;
        return i;
    }

    synchronized void liberarImpresora(int n) {
        impresoras[n] = true;
        disponibles++;
        notifyAll();
    }
}
