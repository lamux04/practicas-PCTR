public class redCajeros {
    public static void main(String[] args) 
    {
        cuentaCorriente cuenta = new cuentaCorriente("1234", 0);
        Thread caj1 = new Thread(new cajero1(cuenta));
        Thread caj2 = new Thread(new cajero2(cuenta));
        Thread caj3 = new Thread(new cajero2(cuenta));
        Thread caj4 = new Thread(new cajero1(cuenta));

        caj1.start();
        caj2.start();
        caj3.start();
        caj4.start();

        try 
        {
            caj1.join();
            caj2.join();
            caj3.join();
            caj4.join();    
        } catch (Exception e)
        {
        }
        
        System.out.println(cuenta.getSaldo());
    }
}
