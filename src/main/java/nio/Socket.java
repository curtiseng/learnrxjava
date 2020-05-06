package nio;

public class Socket {

    java.net.Socket socket = new java.net.Socket();
    io.netty.channel.unix.Socket nettySocket = new io.netty.channel.unix.Socket(1);
}
