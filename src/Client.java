import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Client {
    private DatagramSocket datagramSocket;
    private InetAddress inetAddress;
    private byte[] buffer;

    public Client(DatagramSocket datagramSocket, InetAddress inetAddress) {
        this.datagramSocket = datagramSocket;
        this.inetAddress = inetAddress;
    }

    public void sendThenReceive() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String message = sc.nextLine();
                buffer = message.getBytes();
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, 1234);
                datagramSocket.send(datagramPacket);
                datagramSocket.receive(datagramPacket);
                String message1 = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                System.out.println("The server says you said " + message1);
            } catch (IOException i) {
                i.printStackTrace();
                break;
            }
        }
    }


    

    public static void main(String[] args) throws SocketException,UnknownHostException {
        DatagramSocket datagramSocket=new DatagramSocket();
        InetAddress inetAddress=InetAddress.getByName("LocalHost");
        Client client=new Client(datagramSocket,inetAddress);
        System.out.println("Send datagram packets to a server ");
        client.sendThenReceive();
    }
}

//ffvpvuzastmtlqbs