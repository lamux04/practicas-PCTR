public class Usa_hebra {
    public static void main(String[] args) {
        hebra hebra1 = new hebra(true, 10000);
        hebra hebra2 = new hebra(false, 10000);

        hebra1.start();
        hebra2.start();
        try {
            hebra1.join();
            hebra2.join();
        } catch (Exception e)
        {
        }
        System.out.println(hebra.get());
    }    
}
