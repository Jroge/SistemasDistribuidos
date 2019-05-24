package Clases;

import TSocket.TSocketInfo;

public class Jugador {
    
    public TSocketInfo socketJugador;
    public String id;
    public String nombre;
    public boolean enLinea;
    
    public Jugador(){
        id="nombre";
    }
    public Jugador(TSocketInfo socket,String nuevoId){
        socketJugador=socket;
        id=nuevoId;
        nombre=" ";
        enLinea=true;
    }

    public void setEnLinea(){
        enLinea=true;
    }
    public void setNoEnLinea(){
        enLinea=false;
    }
    public boolean getEnLinea(){
        return enLinea;
    }
    public TSocketInfo getSocketJugador() {
        return socketJugador;
    }
    public void setSocketJugador(TSocketInfo socketJugador) {
        this.socketJugador = socketJugador;
    }
    public String getId() {
        return id;
    }
    public void setId(String nombre) {
        this.id = nombre;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
