package nio.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author yangzifeng
 */
public class BIOServer {

    public static void main(String[] args) throws IOException {
        int port = 8888;
        String info = null;
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket  = serverSocket.accept();
        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        while ((info = bufferedReader.readLine()) != null ) {
            System.out.println("收到了信息，内容是：" + info);
        }
        socket.shutdownInput();
        PrintWriter os = new PrintWriter(socket.getOutputStream());
        os.write("我同意你的请求");
        os.flush();
        socket.shutdownOutput();
        inputStreamReader.close();
        os.close();
        bufferedReader.close();
        socket.close();
        serverSocket.close();
    }
}
