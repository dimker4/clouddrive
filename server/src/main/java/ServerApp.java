import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
    // Порт сервера
    public final static int PORT = 8189;

    public static void main(String[] args) {
        try (ServerSocket ss = new ServerSocket(PORT)) {
            System.out.println("Server is listening");
            // Ожидаем подключения
            try (Socket socket = ss.accept(); ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
                // Получаем объект с сервера и кастуем его в обертку файла
                FileWrap file = (FileWrap) ois.readObject();
                // Создаем физический файл на диске
                creteFile(file);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void creteFile (FileWrap file) {
        // Создаю файл на диске из массива байтов
        try (FileOutputStream fs = new FileOutputStream("server/"+file.getFileName())) {
            fs.write(file.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
