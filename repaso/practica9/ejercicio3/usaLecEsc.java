package practica9.ejercicio3;

public class usaLecEsc {
    static class Lector extends Thread {
        lecEscAN monitor;
        recursoParaLE recurso;

        public Lector(lecEscAN monitor, recursoParaLE recurso) {
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
        lecEscAN monitor;
        recursoParaLE recurso;

        public Escritor(lecEscAN monitor, recursoParaLE recurso) {
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
        lecEscAN monitor = new lecEscAN();
        recursoParaLE recurso = new recursoParaLE();
        Lector lector1 = new Lector(monitor, recurso);
        Lector lector2 = new Lector(monitor, recurso);
        Lector lector3 = new Lector(monitor, recurso);

        Escritor escritor1 = new Escritor(monitor, recurso);
        Escritor escritor2 = new Escritor(monitor, recurso);
        Escritor escritor3 = new Escritor(monitor, recurso);

        escritor1.start();
        escritor2.start();
        lector1.start();
        lector2.start();
        escritor3.start();
        lector3.start();
    }
}
