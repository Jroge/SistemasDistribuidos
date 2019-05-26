package Clases;

import TSocket.TServer.Servidor.TSServerServidorSocket;
import TSocket.TSocketInfo;
import java.util.LinkedList;

public class Sala {
    
    private LinkedList<Partida> listaDePartidas;
    private TSServerServidorSocket server;
    
    public Sala(TSServerServidorSocket nuevoServer){
        listaDePartidas=new LinkedList<>();
        server=nuevoServer;
    }
    public void administrarSala(TSocketInfo socket,String mensaje){
        String[]accion=mensaje.split("_");
        switch(accion[2]){
            case Constantes.NUEVA_PARTIDA:
                crearNuevaPartida(accion[3],accion[4],accion[5]);break;
        }
    }
    public void crearNuevaPartida(String nombre,String cantidadJugadores,String tipo){
        listaDePartidas.addLast(new Partida(
                server,nombre,Integer.parseInt(cantidadJugadores),tipo));
        /*if(!listaMisConectados.isEmpty()){
            listaMisConectados.forEach((socket)->{
                partida.addNuevoJugador(socket);});
            if(partida.listaJugadores.size()>1&&partida.listaJugadores.size()<=5)
                partida.administrarJuego(null,Constantes.JUEGO+Constantes.INICAR_PARTIDA);
            else
                System.out.println("NO HAY JUGADORES");
            modificarListaJugadores();
        }*/
    }
}
