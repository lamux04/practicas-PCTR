package pruebas.tema2;

public class thredLocal {
    


    public static void main(String[] args) throws InterruptedException {
        conDatos objetoMain = new conDatos();

        objetoMain.imprimir();

        Runnable r = new Runnable() {
            @Override
            public void run() {
                conDatos objetoThread = new conDatos();
                conDatos.enteroTLE.set(-1);
                objetoThread.enteroTL.set(-2);
                conDatos.enteroE = 10;
                objetoThread.entero = 25;
                objetoThread.imprimir();

                conDatos objetoThread2 = new conDatos();
                conDatos.enteroTLE.set(-15);
                objetoThread2.enteroTL.set(-25);
                conDatos.enteroE = 105;
                objetoThread2.entero = 255;
                objetoThread2.imprimir();
                objetoThread.imprimir();
            }
        };

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t1.join();
        t2.start();
        t2.join();
        objetoMain.imprimir();
    }
}

class conDatos {
    public static ThreadLocal<Integer> enteroTLE = new ThreadLocal<>();
    public ThreadLocal<Integer> enteroTL = new ThreadLocal<>();
    public int entero = 2;
    public static int enteroE = 3;

    public conDatos() {
        enteroTLE.set(0);
        enteroTL.set(1);
    }
    
    public void imprimir() {
        System.out.println("Hilo -> " + Thread.currentThread().getName());
        System.out.println("Thread Local Static: " + enteroTLE.get());
        System.out.println("Thread Local: " + enteroTL.get());
        System.out.println("Entero: " + entero);
        System.out.println("Entero estático: " + enteroE);
    }
}