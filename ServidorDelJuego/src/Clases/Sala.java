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
                salaCrearNuevaPartida(socket,accion[3],accion[4]);break;
            case Constantes.BUSCAR_PARTIDA:salaEnviarListaDePartidas(socket,accion[3]);break;
        }
    }
    public void salaCrearNuevaPartida(TSocketInfo socket,String cantidadJugadores,String tipo){
        listaDePartidas.addLast(new Partida(
                server,"Partida"+Integer.toString(listaDePartidas.size()+1),
                Integer.parseInt(cantidadJugadores),tipo));
        Partida partida=listaDePartidas.getLast();
        partida.addNuevoJugador(socket);
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
    private void salaEnviarListaDePartidas(TSocketInfo socket,String nombreABuscar){
        String lista="";
        for(int i=1;i<=listaDePartidas.size();i++){
            if(listaDePartidas.get(i-1).getNombre().contains(nombreABuscar)){
                lista=lista+listaDePartidas.get(i-1).getNombre()+",";
            }
        }
        if(lista.equals("")){
            lista=" ";
        }
        server.sendMensaje(socket,Constantes.SALA+
                Constantes.LISTA_DE_PARTIDAS+"_"+lista);
    }
}
