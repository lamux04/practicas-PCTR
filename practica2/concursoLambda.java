public class concursoLambda 
{
    static private int x = 0;

    public static void main(String[] args) {
        Runnable tarea1 = () -> {
            for (int i = 0; i < 10000; ++i)
                x++;
        };

        Runnable tarea2 = new Runnable () {
            @Override
            public void run() 
            {
                for (int i = 0; i < 10000; ++i)
                    x--;
            }
        };

        Thread hilo1 = new Thread(tarea1);
        Thread hilo2 = new Thread(tarea2);

        hilo1.start();
        hilo2.start();

        try
        {
            hilo1.join();
            hilo2.join();
        } catch (Exception e)
        {}

        System.out.println(x);
    }
}
