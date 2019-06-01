package Clases;

import TSocket.TSocketInfo;

public class Jugador {
    
    public TSocketInfo socketJugador;
    public String id;
    public String nombre;
    public boolean listo;
    
    public Jugador(TSocketInfo socket,String nuevoId){
        socketJugador=socket;
        id=nuevoId;
        nombre=" ";
        listo=false;
    }
    
    public void setListo(){listo=true;}
    public void setNoListo(){listo=false;}
    public void setSocketJugador(TSocketInfo nuevoSocketJugador){socketJugador=nuevoSocketJugador;}
    public void setId(String nombre) {this.id = nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    
    public boolean getListo(){return listo;}
    public TSocketInfo getSocketJugador() {return socketJugador;}
    public String getId() {return id;}
    public String getNombre() {return nombre;}
}
