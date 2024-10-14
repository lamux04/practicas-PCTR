public class hebra extends Thread
{
    static int n = 0;
    boolean incrementar;
    int veces;

    public hebra (boolean incrementar, int veces)
    {
        this.incrementar = incrementar;
        this.veces = veces;
    }
    public void run ()
    {
        if (incrementar) {
            for (int i = 0; i < veces; ++i)
                n++;
        } else {
            for (int i = 0; i < veces; ++i)
                n--;
        }
    }

    static int get() 
    {
        return n;
    }
}