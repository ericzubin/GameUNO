/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barajauno;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Eric
 */
public class BarajaUNO {

    /**
     * @param args the command line arguments
     */
         private static ArrayList<Carta> cartas;
         private static String color[] = {"Azul","Roja","Verde","Amarrilla","COMODIN"};
         private static String valor[] = {"0","1","2","3","4","5","6","7","8","9","CAMBIO DE SENTIDO"," PIERDE EL TURNO","ROBA DOS","CAMBIO DE COLOR","CAMBIO DE COLOR ROBA CUATRO"};
         private static ArrayList<Carta> tusCartas;
    public static void main(String[] args) {
               cartas = new ArrayList<Carta>();
             tusCartas = new ArrayList<Carta>();

               //Las cartas con numeros 0
               cartas.add(new Carta(color[0], valor[0]));
               cartas.add(new Carta(color[1], valor[0]));
               cartas.add(new Carta(color[2], valor[0]));
               cartas.add(new Carta(color[3], valor[0]));
               //Crear las cartas comodines y que tenga el valor de CAMBIO DE COLOR
               cartas.add(new Carta(color[4], valor[13]));
               cartas.add(new Carta(color[4], valor[13]));
               cartas.add(new Carta(color[4], valor[13]));
               cartas.add(new Carta(color[4], valor[13]));
               //Crear las cartas comodines y que tenga el valor de CAMBIO DE COLOR ROBA CUATRO
               cartas.add(new Carta(color[4], valor[14]));
               cartas.add(new Carta(color[4], valor[14]));
               cartas.add(new Carta(color[4], valor[14]));
               cartas.add(new Carta(color[4], valor[14]));
               //Crear las cartas con valor 1 al 9 con color, Roja,Azul,Verde,Amarrilla
               for(int i=1;i<=12;i++)
               {
                   for(int p=0;p<=3;p++)
                   {
                   cartas.add(new Carta(color[p], valor[i]));
                   cartas.add(new Carta(color[p], valor[i]));
                   }
               }
               barajar();
               tusCartas.addAll(primeraPartida());
               verBaraja(tusCartas);


//verBaraja(cartas);
          // TODO code application logic here
    }
   
    //Nos barajea las cartas
      public static void barajar(){
        Collections.shuffle(cartas);
    }

    private static ArrayList<Carta> primeraPartida() {
            ArrayList<Carta> tusCartas;
            tusCartas = new ArrayList<Carta>();
      for(int i=0;i<=6;i++)
           {
 	    
              tusCartas.add(cartas.get(0));
              cartas.remove(0);
           } 
      return tusCartas;
    }
     public Carta sacar(){
        return cartas.remove(0);
    }
     
       public static void verBaraja(ArrayList<Carta> tipoCartas)
               {
                    for (int i = 0; i < tipoCartas.size(); i++) {
                   	System.out.print(i +"--.");
			System.out.print(tipoCartas.get(i).getColor());
                        System.out.print("__");
                        System.out.println(tipoCartas.get(i).getValor());

		}
               }
     
}
