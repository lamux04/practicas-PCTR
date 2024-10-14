public class escalaVPar {
    public static void main(String[] args) {
        int[] vector = new int[1000000];
        for (int i = 0; i < 1000000; i++)
            vector[i] = i;

        escalaParcial e1 = new escalaParcial(0, vector);
        escalaParcial e2 = new escalaParcial(1, vector);
        escalaParcial e3 = new escalaParcial(2, vector);
        escalaParcial e4 = new escalaParcial(3, vector);

        e1.start();
        e2.start();
        e3.start();
        e4.start();
    }
}

class escalaParcial extends Thread {
    int inicial;
    int[] vector;

    escalaParcial(int inicial, int[] vector)
    {
        this.inicial = inicial;
        this.vector = vector;
    }

    public void run ()
    {
        for (int i = inicial; i < 1000000; i += 4)
            vector[i] *= 2;
    }
}