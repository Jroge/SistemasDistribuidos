/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TSocket.TServer.Eventos;

import TSocket.TServer.Servidor.TSServerClienteSocket;
import java.util.EventObject;

/**
 *
 * @author Elito
 */
public class TSServerClienteEvento extends EventObject{
    private final TSServerClienteSocket cliente;
    private String mensaje;
    public TSServerClienteEvento(Object source, TSServerClienteSocket c) {
        super(source);
        cliente = c;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String msg) {
        this.mensaje = msg;
    }

    public TSServerClienteSocket getCliente() {
        return cliente;
    }
    public String getClientAddress(){
        return cliente.getSocket().getInetAddress().toString();
    }
    public String getClientHostName(){
        return cliente.getSocket().getInetAddress().getHostName();
    }
    public int getID(){
        return cliente.getID();
    }
    public String getHoraDeConexion(){
        return cliente.getHoraDeConexion();
    }
}
