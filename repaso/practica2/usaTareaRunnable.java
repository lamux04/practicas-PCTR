package practica2;

public class usaTareaRunnable {
    public static void main(String[] args) {
        tareaRunnable num = new tareaRunnable();
        Thread hilo1 = new Thread(new Runnable() {
            @Override
            public void run () {
                for (int i = 0; i < 100000; ++i)
                    num.incrementar();
            }
        });

        Thread hilo2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; ++i)
                    num.decrementar();
            }
        });

        hilo1.start();
        hilo2.start();

        try {
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
        }
        
        System.out.println(num.consultar());
    }
}
