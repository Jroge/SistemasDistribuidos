/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TSocket.TServer.Servidor;

import TSocket.TServer.Eventos.TSServerClienteEventListener;
import TSocket.TServer.Eventos.TSServerClienteEvento;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 *
 * @author Elito
 */
public class TSServerMensaje extends Thread{
    private static ArrayList listeners;
    private final TSServerClienteSocket cliente;
    private final String mensaje;
    

    public TSServerMensaje(TSServerClienteSocket cliente, String mensaje) {
        super();
        this.cliente = cliente;
        this.mensaje = mensaje;
        listeners = new ArrayList();
    }
    @Override
    public void run() {
       try {
             ObjectOutputStream oos = new ObjectOutputStream(cliente.getSocket().getOutputStream());
            oos.writeObject(mensaje);
            onWrite(cliente, mensaje, TSServerCode.SUCCESS);
        } catch (IOException ex) {
            onError(TSServerCode.ERROR_SENDING_MESSAGE);
        } 
      
    }
    
    public void addEventListener(TSServerClienteEventListener listener){
        listeners.add(listener);
    }
    public void onWrite(TSServerClienteSocket cliente, String mensaje, int code){
        ListIterator li = listeners.listIterator();
        while (li.hasNext()) {
            TSServerClienteEventListener listener = (TSServerClienteEventListener) li.next();
            TSServerClienteEvento clientEvent;
            clientEvent = new TSServerClienteEvento(this, cliente);
            (listener).onWriteMensaje(clientEvent, mensaje, code);
        }
    }
    public void onError(int errorCode){
        ListIterator li = listeners.listIterator();
        while (li.hasNext()) {
            TSServerClienteEventListener listener = (TSServerClienteEventListener) li.next();
            TSServerClienteEvento clientEvent = new TSServerClienteEvento(this, cliente);
            (listener).onErrorServer(clientEvent,errorCode);
        }
    }
}
