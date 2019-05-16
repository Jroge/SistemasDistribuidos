/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TSocket.TServer.Servidor;

import java.net.ServerSocket;
import java.util.ArrayList;
import TSocket.TServer.Eventos.TSServerClienteEventListener;
import TSocket.TServer.Eventos.TSServerClienteEvento;
import java.io.IOException;
import java.net.Socket;
import java.util.Calendar;
import java.util.ListIterator;
/**
 *
 * @author Elito
 */
public class TSServerEscuchador extends Thread{
    private static ArrayList listeners;
    private boolean         escuchando;
    private final ServerSocket    server;
    private int countID;
    public TSServerEscuchador(ServerSocket server) {
        this.server = server;
        escuchando = true;
        listeners = new ArrayList();
        countID = 0;
    }
    
    
    public void stopEscuchador(){
        stop();
    }
    @Override
    public void run() {
        while (escuchando) {                
            try {
                Socket client;
                client = server.accept();
                TSServerClienteSocket nuevoCliente = new TSServerClienteSocket(client, true, "generic", countID, getTiempo());
                countID++;
                onConnected(nuevoCliente);
            } catch (IOException ex) {
                onError(TSServerCode.ERROR_ON_CONNECTION);
                break;
            }
        }
    }
    
    private String getTiempo(){
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1; // Note: zero based!
        int day = now.get(Calendar.DAY_OF_MONTH);
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        int second = now.get(Calendar.SECOND);
        int millis = now.get(Calendar.MILLISECOND);
        return ""+day+"-"+month+"-"+year+" "+hour+":"+minute+":"+second;
    }
    public void addEventListener(TSServerClienteEventListener listener){
        listeners.add(listener);
    }
    
    public void onConnected(TSServerClienteSocket client){
        ListIterator li = listeners.listIterator();
        while (li.hasNext()) {
            TSServerClienteEventListener listener = (TSServerClienteEventListener) li.next();
            TSServerClienteEvento clienteEvento;
            clienteEvento = new TSServerClienteEvento(this, client);
            (listener).onConnectedClient(clienteEvento);
        }
    }
    public void onError(int errorCode){
        ListIterator li = listeners.listIterator();
        while (li.hasNext()) {
            TSServerClienteEventListener listener = (TSServerClienteEventListener) li.next();
            (listener).onErrorServer(null,errorCode);
        }
    }
}
