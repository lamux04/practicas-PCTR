package practica2;

public class hebra extends Thread {
    private int[] n;

    public hebra(int[] n) {
        this.n = n;
    }

    public void run() {
        for (int i = 0; i < 1000000; ++i)
            n[0]++;
    }
}
