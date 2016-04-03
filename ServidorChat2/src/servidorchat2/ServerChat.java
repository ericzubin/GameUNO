
import java.net.*;
import java.util.*;
import servidorchat2.Baraja;
import servidorchat2.Juego;

public class ServerChat
{
	
	private static Vector<Socket> sockets;
        private Juego Juego;

	public ServerChat(){
		sockets = new Vector<Socket>();
                Juego=new Juego();

	}

	public void principal(){
		ServerSocket sSocket;
		try {
			sSocket = new ServerSocket(1090,4);
			while(true)
			{
				Socket socket = sSocket.accept();
                                Baraja tusCartas;
                                tusCartas = new Baraja(Juego.primeraPartida());
				System.out.println(socket);
				sockets.add(socket);
				ChatHilo chatHilo = new ChatHilo(socket, sockets,Juego,tusCartas);
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


