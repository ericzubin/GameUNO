

import java.io.*;
import java.net.*;
import java.util.*;
import servidorchat2.Baraja;
import servidorchat2.Juego;

public class ChatHilo implements Runnable
{
	
	private static Vector<Socket> sockets;	
	private Socket socket;
	private DataInputStream entradaCliente;
        private Juego Juego;
        private Baraja tusCartas;

    ChatHilo(Socket socket, Vector<Socket> sockets, Juego Juego, Baraja tusCartas) {
               this.socket = socket;
               this.sockets = sockets;  
               this.Juego=Juego;
               this.tusCartas=tusCartas;
    }

  

	private void inicializaEntrada(){
		try {
			entradaCliente = new DataInputStream(
						socket.getInputStream());						
		} catch (IOException ioe){
			System.out.println("Error al inicializar entrada");
		}
	}

	public ChatHilo(Socket socket, Vector<Socket> sockets)
	{
		this.socket = socket;
		this.sockets = sockets;
	}

	//Llamadas CallBack
	public void run(){

		for(;true;)
		{
			inicializaEntrada();
			try{
				String cadena = entradaCliente.readUTF();
				if (cadena.toUpperCase().startsWith("M")){
					enviaMensaje(cadena);
				} else {
					if (cadena.toUpperCase().startsWith("J"))
					{
						mensajeEntrada(cadena);
					} else {
						if (cadena.toUpperCase().startsWith("P"))
						{
							mensajeSalida(cadena);
							socket.close();
							return;	
						}
					}
				}
			} catch(Exception e){
				e.printStackTrace();
			}
		}

	}


	public void enviaMensaje(String msg){
		for (Socket socket :  sockets){
			try{
				DataOutputStream salidaCliente;
				salidaCliente = new DataOutputStream(
							socket.getOutputStream());
				salidaCliente.writeUTF(msg);
			}catch (Exception e ){
				e.printStackTrace();
			}

		}
	} 

	public void mensajeEntrada(String msg)throws Exception{
		StringTokenizer st = 
					new StringTokenizer(msg, "^");
		st.nextToken();
		String userIP = st.nextToken();
		StringTokenizer stui = 
					new StringTokenizer(userIP,"@");
		String usuario =  stui.nextToken();
		String ip = stui.nextToken();
		String cadena = "m^Server@" + 
				InetAddress.getLocalHost().getHostAddress()	
				 + 	"^–^" + usuario + " entro desde " + ip;
		enviaMensaje(cadena);		 					
	}

	public void mensajeSalida(String msg)throws Exception
	{
		StringTokenizer st = 
					new StringTokenizer(msg, "^");
		st.nextToken();
		String userIP = st.nextToken();
		StringTokenizer stui = 
					new StringTokenizer(userIP,"@");
		String usuario =  stui.nextToken();
		String ip = stui.nextToken();
		String cadena = "m^Server@" + 
				InetAddress.getLocalHost().getHostAddress()	
				 + 	"^–^" + usuario + "salio desde " + ip;
		sockets.remove(socket);		 
		enviaMensaje(cadena);
	}

}