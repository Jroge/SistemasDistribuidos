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
    
    public int getUno() {
        return tablero[0][0];
    }
    public int getDos() {
        return tablero[1][0];
    }
    public int getTres() {
        return tablero[2][0];
    }
    public int getCuatro() {
        return tablero[0][2];
    }
    public int getCinco() {
        return tablero[1][2];
    }
    public int getSeis() {
        return tablero[2][2];
    }
    public int getEscalera(){
        return tablero[0][1];
    }
    public int getFull(){
        return tablero[1][1];
    }
    public int getPoquer(){
        return tablero[2][1];
    }
    public int getGrande() {
        return tablero[3][1];
    }
    public int get(int numero){
        switch(numero){
            case 1:
                return getUno();
            case 2:
                return getDos();
            case 3:
                return getTres();
            case 4:
                return getCuatro();
            case 5:
                return getCinco();
            case 6:
                return getSeis();
            default:
                return 0;
        }
    }
    
    public void setAlUno(int valor){
        tablero[0][0]=valor;
        if(valor>0)total=total+valor;
    }
    public void setAlDos(int valor){
        tablero[1][0]=valor;
        if(valor>0)total=total+valor;
    }
    public void setAlTres(int valor){
        tablero[2][0]=valor;
        if(valor>0)total=total+valor;
    }
    public void setAlCuatro(int valor){
        tablero[0][2]=valor;
        if(valor>0)total=total+valor;
    }
    public void setAlCinco(int valor){
        tablero[1][2]=valor;
        if(valor>0)total=total+valor;
    }
    public void setAlSeis(int valor){
        tablero[2][2]=valor;
        if(valor>0)total=total+valor;
    }
    public void setEscalera(int valor){
        tablero[0][1]=valor;
        if(valor>0)total=total+valor;
    }
    public void setFull(int valor){
        tablero[1][1]=valor;
        if(valor>0)total=total+valor;
    }
    public void setPoquer(int valor){
        tablero[2][1]=valor;
        if(valor>0)total=total+valor;
    }
    public void setGrande(int valor){
        tablero[3][1]=valor;
        if(valor>0)total=total+valor;
    }
    
    public String getUltimaJugada(){
        return listaDeJugadas.getLast();
    }
    public void setJugada(String jugada){
        listaDeJugadas.addLast(jugada);
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
        }else if(!jugada.contains("Borrar")){
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
        }else{
            if(jugada.contains(" los ")){
                String[] valores=jugada.split(" los ");
                int casilla=Integer.parseInt(valores[1]);
                switch(casilla){
                    case 1:setAlUno(-1);break;
                    case 2:setAlDos(-1);break;
                    case 3:setAlTres(-1);break;
                    case 4:setAlCuatro(-1);break;
                    case 5:setAlCinco(-1);break;
                    case 6:setAlSeis(-1);break;
                }
            }else{
                if(jugada.contains(Constantes.JUGADA_ESCALERA)){
                    setEscalera(-1);
                }
                if(jugada.contains(Constantes.JUGADA_FULL)){
                    setFull(-1);
                }
                if(jugada.contains(Constantes.JUGADA_POQUER)){
                    setPoquer(-1);
                }
                if(jugada.contains(Constantes.JUGADA_GRANDE)){
                    setGrande(-1);
                }
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
    public boolean estaLleno(){
        for(int i=1;i<=6;i++){
            if(get(i)==0){
                return false;
            }
        }
        return getEscalera()!=0&&getFull()!=0&&getPoquer()!=0&&getGrande()!=0;
    }
    public int getTotal(){
        return total;
    }

    public String listaDeJugadas(Cubilete cubilete) {
        String jugadas="";
        for(int i=1;i<=6;i++){
            if(cubilete.hay(i)&&get(i)==0){
                jugadas=jugadas+Integer.toString(cubilete.cantidadDeDados(i)*i)+" al "+i+",";
            }
        }
        if(cubilete.hayEscalera()&&getEscalera()==0){
            jugadas=jugadas+Constantes.JUGADA_ESCALERA+",";
        }
        if(cubilete.hayFull()&&getFull()==0){
            jugadas=jugadas+Constantes.JUGADA_FULL+",";
        }
        if(cubilete.hayPoquer()&&getPoquer()==0){
            jugadas=jugadas+Constantes.JUGADA_POQUER+",";
        }
        if(cubilete.hayGrande()&&getGrande()==0){
            jugadas=jugadas+Constantes.JUGADA_GRANDE+",";
        }
        if (jugadas.equals("")) {
            for (int i = 1; i <= 6; i++) {
                if (get(i) == 0) {
                    jugadas = jugadas+"Borrar los " + i + ",";
                }
            }
            if (getEscalera() == 0) {
                jugadas = jugadas + "Borrar "+Constantes.JUGADA_ESCALERA + ",";
            }
            if (getFull() == 0) {
                jugadas = jugadas + "Borrar "+ Constantes.JUGADA_FULL + ",";
            }
            if (getPoquer() == 0) {
                jugadas = jugadas + "Borrar "+ Constantes.JUGADA_POQUER + ",";
            }
            if (getGrande() == 0) {
                jugadas = jugadas + "Borrar "+ Constantes.JUGADA_GRANDE + ",";
            }
        }
        return jugadas;
    }
}
