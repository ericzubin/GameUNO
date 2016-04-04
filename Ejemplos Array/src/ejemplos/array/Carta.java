/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplos.array;

/**
 *
 * @author Eric
 */
public class Carta {
    public String Color;
    public String Valor;

    Carta(String color, String valor) {
        Color=color;
        Valor=valor;
    }

    Carta(Carta carta) {
        
        this.Color=carta.getColor();
        this.Valor=carta.getValor();
    }

   


    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public String getValor() {
        return Valor;
    }

    public void setValor(String Valor) {
        this.Valor = Valor;
    }
    
    
    
}
