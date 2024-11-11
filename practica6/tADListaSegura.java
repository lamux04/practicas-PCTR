public class tADListaSegura extends Thread
{
    int[] v;
    int n;
    int nelem;

    public tADListaSegura(int n)
    {
        this.n = n;
        this.nelem = 0;
        v = new int[n];
    }
    
    synchronized void insertar(int elem, int indice)
    {
        if (nelem < n) {
            for (int i = n - 1; i > indice; --i) {
                v[i] = v[i - 1];
            }
            v[indice] = elem;
            nelem++;
        }
    }
    
    synchronized void eliminar(int indice)
    {
        for (int i = indice; i < nelem - 1; ++i) {
            v[i] = v[i + 1];
        }
        nelem--;
    }
    
    synchronized int elemento(int indice)
    {
        return v[indice];
    }

    synchronized void imprimir()
    {
        for (int i = 0; i < nelem; ++i)
        {
            System.out.print(v[i]);
        }
        System.out.println();
    }
}
