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
    
    public boolean todosSeleccionados(){
        for(int i=1;i<=cantidadDeDados;i++){
            if(!dados.get(i-1).fueElegido()){
                return false;
            }
        }return true;
    }
    public void seleccionarTodos(){
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
    public int cantidadDeDados(int num){
        int can=0;
        for(int i=1;i<=cantidadDeDados;i++){
            if(dados.get(i-1).getValor()==num){
                can++;
            }
        }
        return can;
    }
    public boolean hayEscalera(){
        LinkedList<Dado>ordenados=ordenar();
        boolean b=false;
        for(int i=1;i<=cantidadDeDados-1;i++){
            if(ordenados.get(i-1).getValor()+1!=ordenados.get(i).getValor()){
                if(ordenados.get(i-1).getValor()!=1||b){
                    return false;
                }
                b=true;
            }
        }
        return true;
    }
    public boolean hayFull(){
        for(int i=1;i<=cantidadDeDados;i++){
            int x=dados.get(i-1).getValor();
            if(cantidadDeDados(x)!=3&&cantidadDeDados(x)!=2){
                return false;
            }
        }return true;
    }
    public boolean hayPoquer(){
        int num=0;
        for(int i=1;i<=cantidadDeDados;i++){
            int x=dados.get(i-1).getValor();
            if(cantidadDeDados(x)==1&&num==0){
                num=x;
            }else if(cantidadDeDados(x)<4){
                return false;
            }
        }return true;
    }
    public boolean hayGrande(){
        return cantidadDeDados(dados.get(0).getValor())==5;
    }
    private LinkedList<Dado> ordenar(){
        LinkedList<Dado> nuevaListaOrdenada=dados;
        for(int i=1;i<cantidadDeDados;i++){
            for(int j=i+1;j<=cantidadDeDados;j++){
                if(nuevaListaOrdenada.get(j-1).getValor()<nuevaListaOrdenada.get(i-1).getValor()){
                    int aux=nuevaListaOrdenada.get(i-1).getValor();
                    nuevaListaOrdenada.get(i-1).setValor(nuevaListaOrdenada.get(j-1).getValor());
                    nuevaListaOrdenada.get(j-1).setValor(aux);
                }
            }
        }return nuevaListaOrdenada;
    }
    
    @Override
    public String toString() {
        return dados.get(0).toString()+","+dados.get(1).toString()+","+
                dados.get(2).toString()+","+dados.get(3).toString()+","+
                dados.get(4).toString();
    }
    
    
}
