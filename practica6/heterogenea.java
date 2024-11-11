public class heterogenea 
{
    int n, m;

    public heterogenea()
    {
        this.n = 0;
        this.m = 0;
    }

    public synchronized void incrementar_n()
    {
        n++;
    }

    public void incrementar_m()
    {
        m++;
    }

    public synchronized int ver_n()
    {
        return n;
    }

    public int ver_m()
    {
        return m;
    }
}
