/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @Jroge
 */
public class Dado {
    
    public int valor;
    public boolean fueElegido;
    
    public Dado(){
        valor=0;
        fueElegido=false;
    }
    public Dado(int valor, boolean fueElegido) {
        this.valor = valor;
        this.fueElegido = fueElegido;
    }
    public Dado(Dado nuevoDado){
        this.valor=nuevoDado.getValor();
        this.fueElegido=nuevoDado.fueElegido();
    }
    
    public void setValor(int nuevoValor){
        this.valor=nuevoValor;
    }
    public void setFueElegido(boolean valor){
        this.fueElegido=valor;
    }
    public int getValor(){
        return this.valor;
    }
    public boolean fueElegido(){
        return this.fueElegido;
    }

    @Override
    public String toString() {
        String bol=fueElegido?"t":"f";
        return Integer.toString(valor)+bol;
    }
    
}
