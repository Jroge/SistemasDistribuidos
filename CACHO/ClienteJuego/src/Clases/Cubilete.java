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
    int cantidadDeDados=5;
    
    public void Cubilete(){
        this.dados=new LinkedList<>();
        for(int i=1;i<=cantidadDeDados;i++){
            dados.addLast(new Dado(1,false));
        }
    }
    public void Cubilete(LinkedList<Dado> nuevosDados ){
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
    
    @Override
    public String toString() {
        return dados.get(0)+","+dados.get(1)+","+dados.get(2)+","+dados.get(3)+","+dados.get(4);
    }
    
    
}
