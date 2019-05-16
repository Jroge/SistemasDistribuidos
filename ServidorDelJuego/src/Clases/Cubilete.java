/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.LinkedList;

/**
 *
 * @Jroge
 */
public class Cubilete {
    
    public LinkedList<Dado> dados;
    public int cantidadDeDados=5;
    
    public Cubilete(){
        this.dados=new LinkedList<>();
        for(int i=1;i<=cantidadDeDados;i++){
            dados.addLast(new Dado(1,false));
        }
    }
    public Cubilete(LinkedList<Dado> nuevosDados ){
        this.dados=nuevosDados;
    }
    
    public void addDado(Dado nuevoDado){
        dados.addLast(nuevoDado);
    }
    public Dado getDado(int indice){
        return this.dados.get(indice);
    }

    public boolean estaVacio(){
        return dados.isEmpty();
    }
    public void todosSeleccionados(){
        for(int i=1;i<=cantidadDeDados;i++){
            dados.get(i-1).setFueElegido(true);
        }
    }
    public boolean hay(int num){
        for(int i=1;i<=cantidadDeDados;i++){
            if(dados.get(i-1).getValor()==num){
                return true;
            }
        }return false;
    }
    public int getCantidad(int num){
        int can=0;
        for(int i=1;i<=cantidadDeDados;i++){
            if(dados.get(i-1).getValor()==num){
                can++;
            }
        }
        return can;
    }
    public boolean hayEscalera(){
        return false;
    }
    
    @Override
    public String toString() {
        return dados.get(0).toString()+","+dados.get(1).toString()+","+
                dados.get(2).toString()+","+dados.get(3).toString()+","+
                dados.get(4).toString();
    }
    
    
}
