package Clases;

import java.util.LinkedList;

public class Tablero {
    
    public int[][] tablero;
    public int total;
    public LinkedList<String> listaDeJugadas;
    
    public Tablero(){
        tablero=new int[][]{
            {0,0,0},
            {0,0,0},
            {0,0,0},
            {0,0,0}
        };
        listaDeJugadas=new LinkedList<>();
    }
    
    public int getUno() {return tablero[0][0];}
    public int getDos() {return tablero[1][0];}
    public int getTres() {return tablero[2][0];}
    public int getCuatro() {return tablero[0][2];}
    public int getCinco() {return tablero[1][2];}
    public int getSeis() {return tablero[2][2];}
    public int getEscalera(){return tablero[0][1];}
    public int getFull(){return tablero[1][1];}
    public int getPoquer(){return tablero[2][1];}
    public int getGrande() {return tablero[3][1];}
    public int get(int numero){
        switch(numero){
            case 1:return getUno();
            case 2:return getDos();
            case 3:return getTres();
            case 4:return getCuatro();
            case 5:return getCinco();
            case 6:return getSeis();
            default:return 0;
        }
    }
    public String getUltimaJugada(){return listaDeJugadas.getLast();}
    
    public void setAlUno(int valor){tablero[0][0]=valor;}
    public void setAlDos(int valor){tablero[1][0]=valor;}
    public void setAlTres(int valor){tablero[2][0]=valor;}
    public void setAlCuatro(int valor){tablero[0][2]=valor;}
    public void setAlCinco(int valor){tablero[1][2]=valor;}
    public void setAlSeis(int valor){tablero[2][2]=valor;}
    public void setEscalera(int valor){tablero[0][1]=valor;}
    public void setFull(int valor){tablero[1][1]=valor;}
    public void setPoquer(int valor){tablero[2][1]=valor;}
    public void setGrande(int valor){tablero[3][1]=valor;}
}
