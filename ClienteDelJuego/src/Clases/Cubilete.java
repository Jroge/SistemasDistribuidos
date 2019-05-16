/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @Jroge
 */
public class Cubilete {
    
    public LinkedList<Dado> dados;
    public int cantidadDeDados=5;
    
    public Cubilete(){
        dados=new LinkedList<>();
        for(int i=1;i<=cantidadDeDados;i++){
            dados.addLast(new Dado(1,false));
        }
    }
    public Cubilete(LinkedList<Dado> nuevosDados ){
        this.dados=nuevosDados;
    }
    
    public void addDado(int indice,Dado nuevoDado){
        dados.get(indice).setFueElegido(nuevoDado.fueElegido);
        dados.get(indice).setValor(nuevoDado.valor);
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
    
    @Override
    public String toString() {
        return  dados.get(0).toString()+","+
                dados.get(1).toString()+","+
                dados.get(2).toString()+","+
                dados.get(3).toString()+","+
                dados.get(4).toString();
    }
    
    
}
