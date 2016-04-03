
import java.net.*;
import java.util.*;

public class ServerChat
{
	
	private static Vector<Socket> sockets;

	public ServerChat(){
		sockets = new Vector<Socket>();

	}

	public void principal(){
		ServerSocket sSocket;
		try {
			sSocket = new ServerSocket(1090);
			while(true)
			{
				Socket socket = sSocket.accept();
				System.out.println(socket);
				sockets.add(socket);
				ChatHilo chatHilo = new ChatHilo(socket, sockets);
				Thread hilo = new Thread(chatHilo);
				hilo.start();
			}
		} catch (Exception e){
			e.printStackTrace();
		}

	}

	public static void main(String [] args){
		ServerChat sc = new ServerChat();
		sc.principal();
	}
}


