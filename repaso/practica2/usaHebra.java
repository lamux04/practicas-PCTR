package practica2;

public class usaHebra {
    public static void main(String[] args) {
        int[] n = new int[1];
        n[0] = 0;
        Thread hebra1 = new hebra(n);
        Thread hebra2 = new hebra(n);

        hebra1.start();
        hebra2.start();

        try {
            hebra1.join();
            hebra2.join();
        } catch (InterruptedException e) {
        }
        
        System.out.println(n[0]);
    }
}
