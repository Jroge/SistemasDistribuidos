/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TSocket.TServer.Servidor;

import TSocket.TServer.Eventos.TSServerClienteEventListener;
import TSocket.TServer.Eventos.TSServerClienteEvento;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 *
 * @author Elito
 */
public class TSServerClienteSocket extends Thread{
    private static ArrayList listeners;
    private final Socket cliente;
    private final String nombreCliente;
    private boolean puedeRecibir;
    private final int ID;
    private final String horaDeConexion;
    private Thread hiloCliente;

    public TSServerClienteSocket(Socket cliente, boolean puedeRecibir, String nombreCliente, int ID, String horaDeConexion) {
        this.cliente = cliente;
        this.puedeRecibir = puedeRecibir;
        this.nombreCliente = nombreCliente;
        listeners = new ArrayList();
        this.ID = ID;
        this.horaDeConexion = horaDeConexion;
    }
    
    public Socket getSocket(){
        return this.cliente;
    }
    
    public String getnombreCliente(){
        return this.nombreCliente;
    }

    public int getID() {
        return ID;
    }

    public String getHoraDeConexion() {
        return horaDeConexion;
    }
    

    public String getClientAddress(){
        return cliente.getInetAddress().toString();
    }

    public void stopCliente(){
        try {
            cliente.getInputStream().close();
            cliente.close();
            puedeRecibir = false;
            stop();
        } catch (IOException ex) {}
        
    }
    @Override
    public void run() {
        try {
            while (this.puedeRecibir) {
                ObjectInputStream flujoEntrada = new ObjectInputStream(cliente.getInputStream());
                String mensaje = (String)flujoEntrada.readObject();
                onRead(mensaje);
            }
        } catch (IOException ex) {
            onDesconnected(this);
        } catch (ClassNotFoundException ex) {
            onError(this, TSServerCode.ERROR_CASTING_OBJECT);
        }
    }
    
    //EVENTOS
    
    public void addEventListener(TSServerClienteEventListener listener){
        listeners.add(listener);
    }
    
    public void onRead(String msg){
        ListIterator li = listeners.listIterator();
        while (li.hasNext()) {
            TSServerClienteEventListener listener = (TSServerClienteEventListener) li.next();
            TSServerClienteEvento clienteEvento = new TSServerClienteEvento(this,this);
            clienteEvento.setMensaje(msg);
            (listener).onReadMensaje(clienteEvento);
        }
    }
    
    public void onDesconnected(TSServerClienteSocket client){
        ListIterator li = listeners.listIterator();
        while(li.hasNext()){
            TSServerClienteEventListener listener = (TSServerClienteEventListener) li.next();
            TSServerClienteEvento clientEvent = new TSServerClienteEvento(this, client);
            (listener).onDesconnectedClient(clientEvent);
        }
    }
    public void onError(TSServerClienteSocket client, int code){
        ListIterator li = listeners.listIterator();
        while(li.hasNext()){
            TSServerClienteEventListener listener = (TSServerClienteEventListener) li.next();
            TSServerClienteEvento clientEvent = new TSServerClienteEvento(this, client);
            (listener).onErrorServer(clientEvent, code);
        }
    }
}
