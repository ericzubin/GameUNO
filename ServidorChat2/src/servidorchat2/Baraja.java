/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorchat2;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Eric
 */
public class Baraja {
             private static ArrayList<Carta> cartas;
             private static String color[] = {"Azul","Roja","Verde","Amarrilla","COMODIN"};
             private static String valor[] = {"0","1","2","3","4","5","6","7","8","9","CAMBIO DE SENTIDO"," PIERDE EL TURNO","ROBA DOS","CAMBIO DE COLOR","CAMBIO DE COLOR ROBA CUATRO"};
       
    public Baraja(int op) {
        switch(op)
                {
            case 0:
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
                break;
            case 1:
                break;
                }
    }

    public Baraja( ArrayList<Carta> newCartas) {
        cartas.addAll(newCartas);
    }

    public static ArrayList<Carta> getCartas() {
        return cartas;
    }

    public static void setCartas(ArrayList<Carta> cartas) {
        Baraja.cartas = cartas;
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
       //Nos barajea las cartas
      public static void barajar(){
        Collections.shuffle(cartas);
    }
 private static void verUltimaCarta() {
     if(cartas.size() == 0)
     {
      System.out.println("Se esta empezando una partida");

     }
     else 
     {
      System.out.println("La carta de la mesa es " + cartas.get(cartas.size()-1).getColor() + " " +cartas.get(cartas.size()-1).getValor());

     }
    }

  
   public  Carta sacar(int posicion){
             Carta carta=cartas.get(posicion);
             cartas.remove(posicion);   
             return carta;
             

    }
     public  void meter(Carta carta){
              
              cartas.add(carta);

    }

 
}