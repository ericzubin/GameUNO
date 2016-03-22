/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barajauno;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
         private static ArrayList<Carta> mesa;

    public static void main(String[] args) throws IOException {
               cartas = new ArrayList<Carta>();
             tusCartas = new ArrayList<Carta>();
             mesa = new ArrayList<Carta>();
InputStreamReader isr = new InputStreamReader(System.in);
BufferedReader br = new BufferedReader (isr);
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
               primeraPartida();
               while(tusCartas.size()>0 || cartas.size()>0)
               {
                   if(cartas.size()>0)
                   {
                    sacar();
                    verUltimaCarta();
                    verBaraja(tusCartas);
                    System.out.println("Escoger una carta detu mano");
                    int numero = Integer.parseInt (br.readLine());
                    JugarCarta(numero);

                   }
                   else if(tusCartas.size()>0)
                   {
                    verUltimaCarta();
                    System.out.println("-1  Tomar carta");
                    verBaraja(tusCartas);
                    System.out.println("Escoger una carta detu mano");
                    int numero = Integer.parseInt (br.readLine());
                    JugarCarta(numero);
                   }
                  
                   
               }


          // TODO code application logic here
    }
   
    //Nos barajea las cartas
      public static void barajar(){
        Collections.shuffle(cartas);
    }
 private static void verUltimaCarta() {
     if(mesa.size() == 0)
     {
      System.out.println("Se esta empezando una partida");

     }
     else 
     {
      System.out.println("La carta de la mesa es " + mesa.get(mesa.size()-1).getColor() + " " +mesa.get(mesa.size()-1).getValor());

     }
    }
    private static void primeraPartida() {
     
      for(int i=0;i<=6;i++)
           {
 	    
              tusCartas.add(cartas.get(0));
              cartas.remove(0);
           } 
    }
     public static void sacar(){
              
              tusCartas.add(cartas.get(0));
             cartas.remove(0);   

    }
      public static void JugarCarta(int Mano){
       
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
      
     public static boolean ComprobarColor(int Mano)
      { 
          if(mesa.size()==0)
          {
              return true;
          }
          
          if(mesa.get(mesa.size()-1).getColor().equals(tusCartas.get(Mano).getColor()))
               {
                 return true;
               }
               else
                {
                 return false;
                }
      }
     
         public static boolean ComprobarNumero(int Mano)
      { 
           if(mesa.size()==0)
          {
              return true;
          }
          if(mesa.get(mesa.size()-1).getValor().equals(tusCartas.get(Mano).getValor()))
               {
                 return true;
               }
               else
                {
                 return false;
                }
      }
     public static boolean ComprobarColorComodines(int Mano) throws IOException
     {
            if(mesa.size()==0)
          {
              return true;
          }
          
          if("COMODIN".equals(tusCartas.get(Mano).getColor()))
               {
                   System.out.println("Escoger el nuevo color \n1.- Azul  \n2.- Roja  \n3.-Verde \n4.-Amarrilla");
                   InputStreamReader isr = new InputStreamReader(System.in);
                   BufferedReader br = new BufferedReader (isr);
                   int numero = Integer.parseInt (br.readLine());
                   switch(numero)
                   {
                       case 1:
                           tusCartas.get(Mano).setColor(color[0]);
                           break;
                           case 2:
                           tusCartas.get(Mano).setColor(color[1]);
                           break;
                           case 3:
                           tusCartas.get(Mano).setColor(color[2]);
                           break;
                           case 4:
                           tusCartas.get(Mano).setColor(color[3]);
                           break;
                   }
                  return true;
               }
               else
                {
                 return false;
                }   
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
