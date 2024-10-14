class HebraIncrementar implements Runnable 
{
    Variable a;
    int veces;

    public HebraIncrementar (Variable a, int veces)
    {
        this.a = a;
        this.veces = veces;
    }

    public void run() 
    {
        for (int i = 0; i < veces; ++i)
            a.incrementar();
    }
}

class HebraDecrementar implements Runnable 
{
    Variable a;
    int veces;

    public HebraDecrementar (Variable a, int veces)
    {
        this.a = a;
        this.veces = veces;
    }

    public void run()
    {
        for (int i = 0; i < veces; ++i)
            a.decrementar();
    }
}

class Variable
{
    int n;

    public Variable (int n)
    {
        this.n = n;
    }

    public int get ()
    {
        return n;
    }

    public void incrementar()
    {
        n++;
    }

    public void decrementar()
    {
        n--;
    }
}