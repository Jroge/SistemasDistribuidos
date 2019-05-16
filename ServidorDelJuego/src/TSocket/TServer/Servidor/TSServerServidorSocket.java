/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TSocket.TServer.Servidor;

import TSocket.TServer.Eventos.TSServerClienteEventListener;
import TSocket.TServer.Eventos.TSServerClienteEvento;
import TSocket.TSocketInfo;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author Elito
 */
public class TSServerServidorSocket implements TSServerClienteEventListener {
    private ServerSocket server;
    private int puerto;
    private TSServerEscuchador escuchadorClientes;
    private LinkedList<TSServerClienteSocket> clientesSocket;
    private LinkedList<TSocketInfo> clientesInfo;
    private HashMap<String, TSServerClienteSocket> listaClientes;
    private int countID;
    private int maxConnections;
    private boolean duplicados;
    public static boolean listaocupada;
    
    public TSServerServidorSocket(int puerto){
        this.puerto = puerto;
        clientesInfo = new LinkedList<>();
        clientesSocket = new LinkedList<>();
        listaClientes = new HashMap<>();
        Map myhash = Collections.synchronizedMap(listaClientes);
        countID = 0;
        listaocupada = false;
        duplicados = false;
    }
    public TSServerServidorSocket(int puerto, int maxConnections){
       this(puerto);
       this.maxConnections = maxConnections;
    }
    
    public void startServer(){
        try {
            this.server = new ServerSocket(this.puerto,maxConnections);
            escuchadorClientes = new TSServerEscuchador(server);
            escuchadorClientes.addEventListener(this);
            escuchadorClientes.start();
            onServerStart(true);
        } catch (Exception ex) {
            onError(TSServerCode.ERROR_OPENING_SERVER);
            onServerStart(false);
        }
    }
    public void stopServer(){
        try {
            stopClientes();
            escuchadorClientes.stopEscuchador();
            server.close();
            onServerStop(true);
            clientesInfo.clear();
            listaClientes.clear();
            System.out.println("El Servidor se detuvo...");
            server = null;
        } catch (IOException ex) {
           onError(TSServerCode.ERROR_CLOSING_SERVER);
            onServerStop(false);
        }
    }
    public void sendMensaje(TSocketInfo socketInfo, String mensaje){
        sendMensajeCliente(clientesSocket.get(socketInfo.getIDSession()), mensaje);
        
    }
    public void sendMensajeTodos(String mensaje){
        sendMensajeATodos(mensaje);
    }
    public LinkedList<TSocketInfo> getClientesInfo(){
        return clientesInfo;
    }
    public void aceptarDuplicados(boolean b){
        duplicados = b;
    }
    
    
    private void stopClientes() {
        sendMensajeATodos("S_DISCONNECT");
        for (TSocketInfo socketInfo : clientesInfo) {
           if(socketInfo != null){ 
            TSServerClienteSocket cliente = clientesSocket.get(socketInfo.getIDSession());
            if(cliente != null)
                cliente.stopCliente();
           }
        }
        clientesInfo.clear();
    }
     private void stopCliente(TSServerClienteSocket cliente){
        clientesSocket.remove(cliente.getID());
        clientesInfo.remove(cliente.getID());
        cliente.stopCliente();
    }
    private void sendMensajeCliente(TSServerClienteSocket cliente, String mensaje){
        if(cliente != null){
            TSServerMensaje msj = new TSServerMensaje(cliente, mensaje);
            msj.addEventListener(this);
            msj.start();
        }
    }
    private void sendMensajeATodos(String mensaje){
        for (TSServerClienteSocket cliente : clientesSocket) {
            if (cliente != null)
                sendMensajeCliente(cliente, mensaje);
        }
    }
    private int existeCliente(TSServerClienteSocket clienteAddress){
           for (TSServerClienteSocket clienteSocket : clientesSocket) {
            if(clienteSocket != null && clienteAddress.getClientAddress().equals(clienteSocket.getClientAddress()))
                return clienteSocket.getID();
        }
        return -1;
    }
   
// PARA EL USUARIO - HACER OVERRIDE
    public void onServerStart(boolean success){
        
    }
    public void onServerStop(boolean success){
        
    }
    public void onConnect(TSocketInfo socketinfo){
        
    }
    public void onDisconnect(TSocketInfo socketInfo){
        
    }
    public void onWrite(TSocketInfo socketInfo, String mensaje, int code){
        
    }
    public void onRead(TSocketInfo socketInfo, String mensaje){
        
    }
    public void onError(int code){
        
    }
    public void onError(TSocketInfo socketInfo, int code){
        
    }
 // INTERFACE EVENTOS
     @Override
    public void onConnectedClient(TSServerClienteEvento ce) {
        TSServerClienteSocket cliente = ce.getCliente();
        if(!duplicados && existeCliente(cliente) > 0)
            stopCliente(cliente);
        
        TSocketInfo socketInfo = new TSocketInfo(cliente.getID(), ce.getClientHostName(), ce.getClientAddress(), ce.getHoraDeConexion());
        clientesInfo.add(socketInfo);
        clientesSocket.add(cliente);
        ce.getCliente().addEventListener(this);
        ce.getCliente().start();
        onConnect(socketInfo);
        
    }
    @Override
    public void onDesconnectedClient(TSServerClienteEvento ce) {
        clientesSocket.set(ce.getID(), null);
        clientesInfo.set(ce.getID(), null);
        onDisconnect(new TSocketInfo( ce.getID(), ce.getClientHostName(), ce.getClientAddress(), ce.getHoraDeConexion()));
    }

    @Override
    public void onReadMensaje(TSServerClienteEvento ce) {
        TSocketInfo socketInfo = new TSocketInfo(ce.getID(), ce.getClientHostName(), ce.getClientAddress(), ce.getHoraDeConexion());
        if(!ce.getMensaje().equals("pping"))
            onRead(socketInfo, ce.getMensaje());
        
    }
    @Override
    public void onWriteMensaje(TSServerClienteEvento ce, String mensaje, int code){
        TSocketInfo socketInfo = new TSocketInfo(ce.getID(), ce.getClientHostName(),ce.getClientAddress(),ce.getHoraDeConexion());
        onWrite(socketInfo, mensaje, code);
    }

    @Override
    public void onErrorServer(TSServerClienteEvento ce,int errorCode) {
        if (ce != null){
            TSocketInfo socketInfo = new TSocketInfo(ce.getID(), ce.getClientHostName(), ce.getClientAddress(), ce.getHoraDeConexion());
            onError(socketInfo, errorCode);
        }else
            onError(errorCode);
    }
}
