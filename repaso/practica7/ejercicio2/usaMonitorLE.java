package practica7.ejercicio2;

public class usaMonitorLE {
    static class Lector extends Thread {
        monitorLEPE monitor;
        recursoParaLE recurso;

        public Lector(monitorLEPE monitor, recursoParaLE recurso) {
            this.monitor = monitor;
            this.recurso = recurso;
        }

        public void run() {
            for (int i = 0; i < 10; ++i)
            {
                monitor.abrir_lectura();
                System.out.println("Leemos -> " + recurso.leer());
                monitor.cerrar_lectura();
            }
        }
    }

    static class Escritor extends Thread {
        monitorLEPE monitor;
        recursoParaLE recurso;

        public Escritor(monitorLEPE monitor, recursoParaLE recurso) {
            this.monitor = monitor;
            this.recurso = recurso;
        }

        public void run() {
            for (int i = 0; i < 10; ++i)
            {
                monitor.abrir_escritura();
                recurso.escribir();
                System.out.println("Escribimos");
                monitor.cerrar_escritura();
            }
        }
    }

    public static void main(String[] args) {
        monitorLEPE monitor = new monitorLEPE();
        recursoParaLE recurso = new recursoParaLE();
        Lector lector1 = new Lector(monitor, recurso);
        Lector lector2 = new Lector(monitor, recurso);
        Lector lector3 = new Lector(monitor, recurso);

        Escritor escritor1 = new Escritor(monitor, recurso);
        Escritor escritor2 = new Escritor(monitor, recurso);
        Escritor escritor3 = new Escritor(monitor, recurso);

        lector1.start();
        escritor1.start();
        lector2.start();
        escritor2.start();
        escritor3.start();
        lector3.start();
    }
}
