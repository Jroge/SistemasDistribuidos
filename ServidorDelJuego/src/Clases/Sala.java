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
        System.out.println(mensaje);
        switch(accion[2]){
            case Constantes.NUEVA_PARTIDA:salaCrearNuevaPartida(socket,accion[3],accion[4]);break;
            case Constantes.BUSCAR_PARTIDA:salaEnviarListaDePartidas(socket,accion[3]);break;
            case Constantes.UNIRSE_A_PARTIDA:salaUnirAPartidaSeleccionada(socket,accion[3]);break;
            case Constantes.UNIRSE_A_PARTIDA_ALEATORIA:salaUnirAPartidaAleatoria(socket);break;
            default:salaMandarAPartida(socket,mensaje);break;
        }
    }
    private void salaCrearNuevaPartida(TSocketInfo socket,String cantidadJugadores,String tipo){
        listaDePartidas.addLast(new Partida(
                server,"Partida"+Integer.toString(listaDePartidas.size()+1),
                Integer.parseInt(cantidadJugadores),tipo));
        Partida partida=listaDePartidas.getLast();
        partida.addNuevoJugador(socket);
    }
    private void salaEnviarListaDePartidas(TSocketInfo socket,String nombreABuscar){
        String lista="";
        for(Partida partida:listaDePartidas){
            if(partida.getNombre().contains(nombreABuscar))
                lista=lista+partida.getNombre()+" - "+
                        partida.getTipo()+" - Espacios: "+
                        Integer.toString(partida.getCupos())+",";
        }
        if(lista.equals(""))lista=" ";
        server.sendMensaje(socket,Constantes.SALA+
                Constantes.LISTA_DE_PARTIDAS+"_"+lista);
    }
    private void salaUnirAPartidaSeleccionada(TSocketInfo socket,String nombrePartida){
        boolean buscar=true;
        for(int i=1;i<=listaDePartidas.size()&&buscar;i++){
            if(listaDePartidas.get(i-1).getNombre().equals(nombrePartida))
                listaDePartidas.get(i-1).addNuevoJugador(socket);
        }
    }
    private void salaUnirAPartidaAleatoria(TSocketInfo socket){
        boolean unido=false;
        for(int i=1;i<=listaDePartidas.size()&&!unido;i++){
            Partida partida=listaDePartidas.get(i-1);
            if(partida.getCupos()>0){
                partida.addNuevoJugador(socket);
                unido=true;
            }
        }
        if(!unido)salaCrearNuevaPartida(socket,"2",Constantes.PARTIDA_TRES_TIROS);
    }
    private void salaMandarAPartida(TSocketInfo socket,String mensaje){
        boolean buscar=true;
        for(int i=1;i<=listaDePartidas.size()&&buscar;i++){
            if(listaDePartidas.get(i-1).contieneAlJugador(socket)){
                listaDePartidas.get(i-1).administrarJuego(socket, mensaje);
                buscar=false;
            }
        }
    }
}
