package Clases;

import java.util.LinkedList;

public class Cubilete {
    
    public LinkedList<Dado> dados;
    public int cantidadDeDados=5;
    
    public Cubilete(){
        this.dados=new LinkedList<>();
        for(int i=1;i<=cantidadDeDados;i++){
            dados.addLast(new Dado(1,false));
        }
    }
    
    public Dado getDado(int indice){
        return this.dados.get(indice);
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
}
