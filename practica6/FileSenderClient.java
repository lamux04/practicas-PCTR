import java.io.*;
import java.net.*;

public class FileSenderClient {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // Dirección del servidor
        int port = 1234; // Puerto del servidor
        String filePath = "/home/lamux/Documentos/Repositorios/practicas-PCTR/practica6/archivo_a_enviar.txt"; // Ruta del archivo que se va a enviar

        try (Socket socket = new Socket(serverAddress, port)) {
            System.out.println("Conectado al servidor en " + serverAddress + ":" + port);

            // Flujo de salida para enviar datos al servidor
            OutputStream out = socket.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(out);

            // Flujo para leer el archivo que se enviará
            FileInputStream fis = new FileInputStream(filePath);
            BufferedInputStream bis = new BufferedInputStream(fis);

            // Leer el archivo y enviarlo al servidor
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }

            // Cerrar flujos y socket
            bos.flush();
            bis.close();
            socket.close();

            System.out.println("Archivo enviado correctamente.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
