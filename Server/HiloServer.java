
import java.io.*;
import java.net.*;
import java.util.*;

public class HiloServer implements Runnable{

	private Socket socket;

	public  HiloServer(Socket socket)
	{
		this.socket = socket;
	}
	
	public void run(){
		
		
			try{

			  String cadena = leeMensaje(socket);	
		      System.out.println(cadena);
   			  if (cadena != null){
				if(cadena.toUpperCase().startsWith("GET") 
					|| cadena.toUpperCase().startsWith("POST")
					|| cadena.toUpperCase().startsWith("HEAD")){
					String ruta = obtieneRuta(cadena);
					ruta = ruta.substring(1);
					System.out.println(cadena);
					int longitud = longitudArchivo(ruta);
					if(longitud > 0){
						byte[] datos = obtieneArchivo(ruta, longitud);
						enviaArchivo(datos, longitud, socket,cadena);
					}
					else{
						enviaError(socket, cadena);
					}
				}	else{
					enviaMalFormado(socket);
				}
			  }
			}catch(Exception e){
				e.printStackTrace();
			}
		

		
	}

	public String leeMensaje(Socket socket)throws Exception{
		BufferedReader entradaCliente;
		entradaCliente = new BufferedReader (
				new InputStreamReader(
					socket.getInputStream()));
		
		String cadena = entradaCliente.readLine();
		return cadena;
	}

	public String obtieneRuta(String cadena){
		StringTokenizer st = new StringTokenizer(cadena);
		if (st.countTokens() == 3){
			st.nextToken();
			return st.nextToken();
		}
		return null;
	}

	public int longitudArchivo(String ruta){
		File archivo = new File(ruta);
		if (archivo.exists()){
			return (int) archivo.length();
		}
		return 0;
	}

	public byte [] obtieneArchivo(String ruta, int longitud)
					throws Exception{
		
		byte [] buffer;
		buffer = new byte[longitud];

		 //File archivo = new File (rutaA);
         FileInputStream fr = new FileInputStream(ruta);
   //      BufferedReader br = new BufferedReader(fr);
 		 fr.read(buffer,0,longitud);
 		 return buffer;
    /*     String linea;
         while((linea=br.readLine())!=null){
            System.out.println(linea);
         }*/
	}

	private void enviaArchivo(
		byte[] datos, int longitud, Socket socket, String resource)
		throws Exception {

		DataOutputStream salidaCliente = new DataOutputStream(socket.getOutputStream());
		salidaCliente.writeBytes("HTTP/1.0 200 ok\n");
		salidaCliente.writeBytes("Server: SDSilvita Server/1.0\n");
		salidaCliente.writeBytes("Date: " + new Date() );
	//	salidaCliente.writeBytes("Content-Type: img/png\n");
		
		
		if (resource.endsWith(".jpg")) {
            salidaCliente.writeBytes("Content Type: image/jpeg\r\n");
        } else { 
        	if (resource.endsWith(".png")) {
            	salidaCliente.writeBytes("Content Type: image/png\r\n");
        	} else {
        		if (resource.endsWith(".gif")) {
            		salidaCliente.writeBytes("Content Type: image/gif\r\n");
        		} else {
        			salidaCliente.writeBytes("Content-Type: text/html\n");
        		}
        	}
        }
        salidaCliente.writeBytes("Content-Length: " + longitud + "\r\n\r\n");
		//socket.close();
		salidaCliente.write(datos,0,longitud);
		socket.close();

	}

	public static void enviaError(Socket socket, String resource)
			throws Exception{
		DataOutputStream salidaCliente = new DataOutputStream(socket.getOutputStream());
		salidaCliente.writeBytes("HTTP/1.0 404 Archivo no encontrado\n");
		salidaCliente.writeBytes("Server: SDSilvita Server/1.0\n");
		salidaCliente.writeBytes("Date: " + new Date() + "\n");
		if (resource.endsWith(".jpg")) {
            salidaCliente.writeBytes("Content Type: image/jpeg\r\n");
        } else { 
        	if (resource.endsWith(".png")) {
            	salidaCliente.writeBytes("Content Type: image/png\r\n");
        	} else {
        		if (resource.endsWith(".gif")) {
            		salidaCliente.writeBytes("Content Type: image/gif\r\n");
        		} else {
        			salidaCliente.writeBytes("Content-Type: text/html\n");
        		}
        	}
        }
        socket.close();

		

	}

	public static void enviaMalFormado(Socket socket)
			throws Exception{
		DataOutputStream salidaCliente = new DataOutputStream(socket.getOutputStream());
		salidaCliente.writeBytes("HTTP/1.0 300 Solicitud erronea\n");
		salidaCliente.writeBytes("Server: SDSilvita Server/1.0\n");
		salidaCliente.writeBytes("Date: " + new Date() + "\n");
		salidaCliente.writeBytes("Content-Type: text/html\n");
		socket.close();
	}


}