public class Usa_tareaRunnable 
{
    public static void main(String[] args) 
    {
        Variable x = new Variable(0);
        Thread Hilo1 = new Thread(new HebraDecrementar(x, 10000));
        Thread Hilo2 = new Thread(new HebraIncrementar(x, 10000));
        Hilo1.start();
        Hilo2.start();
        try
        {
            Hilo1.join();
            Hilo2.join();
        } catch (Exception e)
        {
        }
        System.out.println(x.get());
    }
}
