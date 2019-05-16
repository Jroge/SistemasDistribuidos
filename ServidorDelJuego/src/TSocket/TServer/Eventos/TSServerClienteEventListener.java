/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TSocket.TServer.Eventos;

import java.util.EventListener;

/**
 *
 * @author Elito
 */
public interface TSServerClienteEventListener extends EventListener {
    public abstract void onConnectedClient(TSServerClienteEvento ce);
    public abstract void onDesconnectedClient(TSServerClienteEvento ce); 
    public abstract void onReadMensaje(TSServerClienteEvento ce);
    public abstract void onWriteMensaje(TSServerClienteEvento ce, String mensaje, int code);
    public abstract void onErrorServer(TSServerClienteEvento ce,int ErrorCode); 
}
