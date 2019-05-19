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

    public int getUno() {
        return uno;
    }

    public int getDos() {
        return dos;
    }

    public int getTres() {
        return tres;
    }

    public int getCuatro() {
        return cuatro;
    }

    public int getCinco() {
        return cinco;
    }

    public int getSeis() {
        return seis;
    }

    public int getEscalera() {
        return escalera;
    }

    public int getFull() {
        return full;
    }

    public int getPoquer() {
        return poquer;
    }

    public int getGrande() {
        return grande;
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
            int deMano=0;
            if(jugada.contains(Constantes.JUGADA_DE_MANO)){
                deMano=5;
            }
            if(jugada.contains(Constantes.JUGADA_ESCALERA)){
                setEscalera(20+deMano);
            }
            if(jugada.contains(Constantes.JUGADA_FULL)){
                setFull(30+deMano);
            }
            if(jugada.contains(Constantes.JUGADA_POQUER)){
                setPoquer(40+deMano);
            }
            if(jugada.contains(Constantes.JUGADA_GRANDE)){
                setGrande(50);
            }
            if(jugada.contains(Constantes.JUGADA_DORMIDA)){
                llenarTodoAlMaximo();
            }
        }
    }
    
    public void llenarTodoAlMaximo(){
        setAlUno(5);
        setAlDos(10);
        setAlTres(15);
        setAlCuatro(20);
        setAlCinco(25);
        setAlSeis(30);
        setEscalera(25);
        setFull(35);
        setPoquer(45);
        setGrande(50);
    }
    
    public int getTotal(){
        return total;
    }
}
