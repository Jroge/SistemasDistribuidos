/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import TSocket.TSocketInfo;

/**
 *
 * @Jroge
 */
public class Jugador {
    
    public TSocketInfo socketJugador;
    public String id;
    public String nombre;
    
    public Jugador(){
        id="nombre";
    }
    public Jugador(TSocketInfo socket,String nuevoId){
        socketJugador=socket;
        id=nuevoId;
        nombre=" ";
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
