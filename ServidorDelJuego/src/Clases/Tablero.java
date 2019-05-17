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
public class Tablero {
    
    public int uno,dos,tres,cuatro,cinco,seis,escalera,full,poquer,grande,total;
    
    public Tablero(){
        uno=dos=tres=cuatro=cinco=seis=escalera=full=poquer=grande=total=0;
    }
    
    public void setAlUno(int valor){
        uno=valor;
        total=total+valor;
    }
    public void setAlDos(int valor){
        dos=valor;
        total=total+valor;
    }
    public void setAlTres(int valor){
        tres=valor;
        total=total+valor;
    }
    public void setAlCuatro(int valor){
        cuatro=valor;
        total=total+valor;
    }
    public void setAlCinco(int valor){
        cinco=valor;
        total=total+valor;
    }
    public void setAlSeis(int valor){
        seis=valor;
        total=total+valor;
    }
    public void setEscalera(int valor){
        escalera=valor;
        total=total+valor;
    }
    public void setFull(int valor){
        full=valor;
        total=total+valor;
    }
    public void setPoquer(int valor){
        poquer=valor;
        total=total+valor;
    }
    public void setGrande(int valor){
        grande=valor;
        total=total+valor;
    }
    public void setJugada(String jugada){
        if(jugada.contains(" al ")){
            String[] valores=jugada.split(" ");
            int casilla=Integer.parseInt(valores[2]);
            int valor=Integer.parseInt(valores[0]);
            switch(casilla){
                case 1:setAlUno(valor);break;
                case 2:setAlDos(valor);break;
                case 3:setAlTres(valor);break;
                case 4:setAlCuatro(valor);break;
                case 5:setAlCinco(valor);break;
                case 6:setAlSeis(valor);break;
            }
        }else{
            
        }
    }
    public void llenarTodoAlMaximo(){
        uno=5;dos=10;tres=15;cuatro=20;cinco=25;seis=30;escalera=25;full=35;poquer=45;grande=50;
    }
    public int getTotal(){
        return total;
    }
}
