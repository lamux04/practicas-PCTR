import java.io.*;
import java.net.*;

public class FileReceiverServer {
    public static void main(String[] args) {
        int port = 1234; // Puerto en el que el servidor escucha

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor esperando conexiones en el puerto " + port + "...");

            // Aceptar la conexión del cliente
            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado desde " + clientSocket.getInetAddress());

            // Flujo de entrada para recibir datos del cliente
            InputStream in = clientSocket.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(in);

            // Preparar el archivo donde se guardará el contenido recibido
            File receivedFile = new File("archivo_recibido.txt");
            FileOutputStream fos = new FileOutputStream(receivedFile);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            // Leer datos del flujo de entrada y escribirlos en el archivo
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }

            // Cerrar flujos y socket
            bos.close();
            clientSocket.close();

            System.out.println("Archivo recibido y guardado como: " + receivedFile.getName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
