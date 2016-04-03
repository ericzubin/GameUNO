/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorchat2;

import java.util.ArrayList;

/**
 *
 * @author Eric
 */

public class Juego {
        private static Baraja Mesa=new Baraja(0);
        private static Baraja Cartas=new Baraja(1);
        public int primeraPartida;
        public ArrayList<Carta> newCartas;

        
 public  ArrayList<Carta> primeraPartida() {
            newCartas = new ArrayList<Carta>();

      for(int i=0;i<=6;i++)
           {
 	    newCartas.add(Mesa.sacar(0));
           } 
      return newCartas;
    }

    /**
     *
     */
   public String verMesa()
   {
       return Mesa.verBaraja();
   }
    public String verCartas()
   {
       return Cartas.verBaraja();
   }
}
