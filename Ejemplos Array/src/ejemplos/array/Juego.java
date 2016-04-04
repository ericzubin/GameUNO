/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplos.array;

import java.util.ArrayList;

/**
 *
 * @author Eric
 */

public class Juego {
        public  Baraja Mesa;
        public  Baraja Cartas;
        public int primeraPartida;
        public ArrayList<Carta> newCartas;

    public Juego() {
        Mesa=new Baraja(0);
        System.out.println(Mesa.verBaraja());
        Cartas=new Baraja(1);
    }

        
 public  ArrayList<Carta> primeraPartida() {
     newCartas = new ArrayList<>();

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
