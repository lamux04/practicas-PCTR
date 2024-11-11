import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;

public class ServidorHiloconPool implements Runnable
{
    Socket enchufe;
    int id;

    public ServidorHiloconPool (Socket enchufe, int id)
    {
        this.enchufe = enchufe;
        this.id = id;
    }


    public void run()
    {
        try
        {
            BufferedReader entrada = new BufferedReader(
                                        new InputStreamReader(
                                            enchufe.getInputStream()));
            String datos = entrada.readLine();
            int j;
            int i = Integer.valueOf(datos).intValue();
            for (j = 1; j <= 20; j++)
            {
                System.out.println("El hilo "+id+" escribiendo el dato "+i);
                Thread.sleep(1000);
            }
            enchufe.close();
            System.out.println("El hilo "+id+"cierra su conexion...");
        } catch(Exception e) {System.out.println("Error...");}
    }

    public static void main(String[] args) 
    {
        int puerto = 2001;

        ExecutorService executor = Executors.newCachedThreadPool();

        try
        {
            ServerSocket chuff = new ServerSocket (puerto, 3000);
            int i = 0;
            while (true)
            {
                System.out.println("Esperando solicitud de conexion...");
                Socket cable = chuff.accept();
                System.out.println("Recibida solicitud de conexion...");
                executor.submit(new ServidorHiloconPool(cable, i));
                i++;
            } //while
        } catch (Exception e)
            {System.out.println("Error en sockets...");}
    }
}
