/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import TSocket.TClient.Cliente.TSocketInfo;


/**
 *
 * @author PC
 */
public class Jugador {
    
    public TSocketInfo socketJugador;
    public String nombre;
    
    public Jugador(){
        nombre="nombre";
    }
    public Jugador(TSocketInfo socket,String nuevoNombre){
        socketJugador=socket;
        nombre=nuevoNombre;
    }

    public TSocketInfo getSocketJugador() {
        return socketJugador;
    }
    public void setSocketJugador(TSocketInfo socketJugador) {
        this.socketJugador = socketJugador;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
