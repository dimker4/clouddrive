import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Paths;

public class ClientApp {
    public final static int PORT = 8189;
    public final static String IP_ADDR = "127.0.0.1";

    public static void main(String[] args) {
        try (Socket socket = new Socket(IP_ADDR, PORT)) {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            FileWrap fw = new FileWrap(Paths.get("client/test.txt"));
            out.writeObject(fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
