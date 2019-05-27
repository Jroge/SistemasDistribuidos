package Clases;

import TSocket.TSocketInfo;

public class Jugador {
    
    public TSocketInfo socketJugador;
    public String id;
    public String nombre;
    public boolean listo;
    
    public Jugador(){
        id="nombre";
    }
    public Jugador(TSocketInfo socket,String nuevoId){
        socketJugador=socket;
        id=nuevoId;
        nombre=" ";
        listo=true;
    }

    public void setListo(){
        listo=true;
    }
    public void setNoListo(){
        listo=false;
    }
    public boolean getListo(){
        return listo;
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
