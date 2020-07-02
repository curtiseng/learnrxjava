package nio.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author yangzifeng
 */
public class BIOClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhsot", 8888);
            PrintWriter os = new PrintWriter(socket.getOutputStream());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
