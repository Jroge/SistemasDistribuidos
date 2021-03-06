package Clases;

import java.util.LinkedList;
import java.util.Random;

public class Cubilete {
    
    public LinkedList<Dado> dados;
    public int cantidadDeDados=5;
    
    public Cubilete(){
        this.dados=new LinkedList<>();
        for(int i=1;i<=cantidadDeDados;i++){
            dados.addLast(new Dado(1,false));
        }
    }
    public void agitar(){
        for(int in=1;in<=5;in++){
            if(!getDado(in-1).fueElegido()){
                //GRANDE
                //cubilete.getDado(in-1).setValor(5);
                //FULL
                //cubilete.getDado(in-1).setValor((in/3)+2);
                //POQUER
                //cubilete.getDado(in-1).setValor((in/5)+2);
                //ESCALERA
                //cubilete.getDado(in-1).setValor(in);
                //RANDOMICO
                getDado(in-1).setValor(random(1,7));
            }
        }
    }
    private int random(int a, int b){
        Random r = new Random();
        int result = r.nextInt(b-a) + a;
        if(result>6){
            result=6;
        }
        return result;
    }
    
    public void addDado(Dado nuevoDado){
        dados.addLast(nuevoDado);
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
            if(cantidadDeDados(ordenados.get(i-1).getValor())!=1){
                return false;
            }
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
