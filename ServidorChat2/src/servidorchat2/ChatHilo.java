package servidorchat2;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatHilo implements Runnable
{
	
	private static Vector<Socket> sockets;	
       private static ArrayList<String> usuarios;	
	private Socket socket;
	private DataInputStream entradaCliente;
  private static ArrayList<Carta> mesa;
      	      private static ArrayList<Carta> cartas;

             private static ArrayList<Mano> Mano;


    ChatHilo(Socket socket, Vector<Socket> sockets, ArrayList<Mano> Mano, ArrayList<Carta> mesa, ArrayList<Carta> cartas) {
   this.socket=socket;
            this.sockets=sockets;
             this.Mano=Mano;
             this.mesa=mesa;
            this.cartas=cartas;      }
      
  

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
                                       mensajeMesaString(cadena);

                                        
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
                             System.out.println(msg);

                                StringTokenizer st = 
					new StringTokenizer(msg, "^");
		                st.nextToken();
                                String userIP = st.nextToken();
                                StringTokenizer stui = 
        			new StringTokenizer(userIP,"@");
                                String usuario =  stui.nextToken();
                                String ip = stui.nextToken();
                                st.nextToken();
		                st.nextToken();
                                int Intusuario=usuarios.indexOf(usuario);
		               String contenido= st.nextToken();
				salidaCliente.writeUTF(msg);
			}catch (Exception e ){
				e.printStackTrace();
			}

		}
	} 
     public static void JugarCarta(int Mano,int Usuario){
       
             if(ComprobarColor(Mano)||ComprobarNumero(Mano))
             {
              mesa.add(tusCartas.get(Mano));
             tusCartas.remove(Mano);   
             return ;
             }else
             {
                 System.out.println("_____________Error_________");
                 return ;
             }
     }
           
             public void enviaMensaje(String msg,int Jugador){
			try{
				DataOutputStream salidaCliente;
				salidaCliente = new DataOutputStream(
							sockets.get(Jugador).getOutputStream());
				salidaCliente.writeUTF(msg);
			}catch (Exception e ){
				e.printStackTrace();
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
                usuarios.add(usuario);
		enviaMensaje(cadena);
                System.out.println("Tamaño de jugadores: "+sockets.size());
                System.out.println("Tamaño de la mano "+Mano.get(Mano.size()-1).sizeCartas());
                enviaMensaje(cadena + "TusCartas"+ verBaraja(Mano.get(Mano.size()-1).getTusCartas()),sockets.size()-1);
	}
                public void mensajeMesaString (String msg)throws Exception{
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
				 + 	"^–^";
                enviaMensaje(cadena + "La cartas de la Mesa"+ verBaraja(mesa));
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
          public static String verBaraja(ArrayList<Carta> tipoCartas)
               {
                   String Salida="";
                    for (int i = 0; i < tipoCartas.size(); i++) {
                       Salida+=i +"--.";
                       Salida+=tipoCartas.get(i).getColor();
                       Salida+="__";
                       Salida+=tipoCartas.get(i).getValor();
                       Salida+="               ";
                        

		}
                    return Salida;
               }

}