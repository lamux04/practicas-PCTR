
public class prodParcial extends Thread 
{
    static final int nhebras = 2;
    public static void main(String[] args) {
        int[] productoParcial = new int[nhebras];
        prodEscalarParalelo[] vHebras = new prodEscalarParalelo[nhebras];
        int[] v1 = new int[1000000];
        int[] v2 = new int[1000000];
        int prod = 0;
        
        for (int i = 0; i < 1000000; ++i)
        {
            v1[i] = i;
            v2[i] = 2 * i;
        }
        
        long startTime = System.nanoTime();

        int inicio = 0;
        int tamanno = 1000000 / nhebras;
        
        for (int i = 0; i < nhebras; ++i)
        {
            vHebras[i] = new prodEscalarParalelo(i, inicio, inicio + tamanno, productoParcial, v1, v2);
            vHebras[i].start();
        }

        for (int i = 0; i < nhebras; ++i)
        {
            try {
                vHebras[i].join();
                prod += productoParcial[i];
            } catch (Exception e) {
            }
        }

        long endTime = System.nanoTime();

        System.out.println(endTime - startTime);

    }
}

class prodEscalarParalelo extends Thread 
{
    int idHebra, inicio, fin;
    int[] productoParcial;
    int[] v1, v2;
    public prodEscalarParalelo (int idHebra, int inicio, int fin, int[] productoParcial, int[] v1, int[] v2)
    {
        this.idHebra = idHebra;
        this.inicio = inicio;
        this.fin = fin;
        this.productoParcial = productoParcial;
        this.v1 = v1;
        this.v2 = v2;
    }

    public void run ()
    {
        productoParcial[idHebra] = 0;

        for (int i = inicio; i < fin; ++i)
        {
            productoParcial[idHebra] += v1[i] * v2[i];
        }
    }
}


