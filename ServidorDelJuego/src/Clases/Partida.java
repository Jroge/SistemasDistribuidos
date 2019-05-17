package Clases;

import TSocket.TServer.Servidor.TSServerServidorSocket;
import TSocket.TSocketInfo;
import com.google.gson.Gson;
import java.util.LinkedList;
import java.util.Random;

public class Partida {
    
    public TSServerServidorSocket server;
    public LinkedList<Jugador> listaJugadores;
    private Cubilete cubilete;
    private Tablero tablero;
    
    public Partida(TSServerServidorSocket newServer) {
        listaJugadores=new LinkedList<>();
        server = newServer;
    }
    
    public void addNuevoJugador(TSocketInfo nuevoSocket){
        listaJugadores.addLast(new Jugador(
                nuevoSocket,"Jugador"+(listaJugadores.size()+1)));
    }
    public void eliminarJugador(TSocketInfo socket){
        for(int i=1;i<=listaJugadores.size();i++){
            if(listaJugadores.get(i-1).socketJugador.equals(socket)){
                listaJugadores.remove(i-1);
            }
        }
    }
    public void administrarJuego(TSocketInfo socketInfo, String mensaje){
        String[] accion = mensaje.split("_");
        switch (accion[2]){
            case Constantes.GENERAR_DADOS: juegoGenerarDados(accion[3]); break;
            case Constantes.JUGADA_ESCOGIDA: juegoJugadaEscogida(socketInfo, accion[3],accion[4]); break;
            case Constantes.LISTA_DE_JUGADAS: juegoEnviarListaJugadas(socketInfo,accion[3]); break;
            case Constantes.NOMBRE_JUGADOR_EN_TURNO:juegoEnviarNombreJugadorEnTurno(accion[3]);break;
        }
    }
    private void juegoGenerarDados(String cubile){
        Gson json=new Gson();
        cubilete=json.fromJson(cubile,Cubilete.class);
        agitarCubilete();
        String cad=json.toJson(cubilete);
        server.sendMensajeTodos(Constantes.JUEGO+Constantes.MOSTRAR_DADOS+"_"+cad);
    }
    
    private void juegoJugadaEscogida(TSocketInfo socketInfo, String jugada,String tableroJSON){
        actualizarTablero(socketInfo,jugada,tableroJSON);
        server.sendMensaje((TSocketInfo)siguiente(socketInfo),Constantes.JUEGO+Constantes.ES_TU_TURNO);
    }
    
    private void juegoEnviarListaJugadas(TSocketInfo socket,String cubile){
        Gson json=new Gson();
        cubilete=json.fromJson(cubile,Cubilete.class);
        String lista=getListaPosibleJugadas();
        server.sendMensaje(socket, Constantes.JUEGO+Constantes.LISTA_DE_JUGADAS+"_"+lista);
    }
    
    private void juegoEnviarNombreJugadorEnTurno(String nombre){
        server.sendMensajeTodos(Constantes.JUEGO+Constantes.NOMBRE_JUGADOR_EN_TURNO+"_"+nombre);
    }

    //UTILS
    private TSocketInfo siguiente(TSocketInfo socket){
        int pos=0;
        for(int in=1;in<=listaJugadores.size();in++){
            if(socket.equals(listaJugadores.get(in-1).getSocketJugador())){
                pos=in;
            }
        }
        if(pos==listaJugadores.size()){
            pos=0;
        }
        return listaJugadores.get(pos).getSocketJugador();
    }
    
    private void agitarCubilete(){
        for (int in = 1; in <= 5; in++) {
            if (!cubilete.getDado(in - 1).fueElegido()) {
                //GRANDE
                //cubilete.getDado(in-1).setValor(5);
                
                //FULL
                //cubilete.getDado(in-1).setValor((in/3)+2);
                
                //POQUER
                //cubilete.getDado(in-1).setValor((in/5)+2);
                
                //ESCALERA
                //cubilete.getDado(in-1).setValor(in);
                
                //RANDOMICO
                cubilete.getDado(in - 1).setValor(random(1, 6));
            }
        }
    }
    
    private String getListaPosibleJugadas(){
        String jugadas="";
        for(int i=1;i<=6;i++){
            if(cubilete.hay(i)){
                jugadas=jugadas+Integer.toString(cubilete.cantidadDeDados(i)*i)+" al "+i+",";
            }
        }
        if(cubilete.hayEscalera()){
            jugadas=jugadas+Constantes.JUGADA_ESCALERA+",";
        }
        if(cubilete.hayFull()){
            jugadas=jugadas+Constantes.JUGADA_FULL+",";
        }
        if(cubilete.hayPoquer()){
            jugadas=jugadas+Constantes.JUGADA_POQUER+",";
        }
        if(cubilete.hayGrande()){
            jugadas=jugadas+Constantes.JUGADA_GRANDE+",";
        }
        return jugadas;
    }
    
    private void actualizarTablero(TSocketInfo socket,String jugada,String tableroJson){
        Gson json=new Gson();
        tablero=json.fromJson(tableroJson, Tablero.class);
        tablero.setJugada(jugada);
        tableroJson=json.toJson(tablero);
        server.sendMensajeTodos(Constantes.JUEGO+Constantes.CAMBIAR_TABLERO+"_"+tableroJson);
    }
    
    private int random(int a, int b){
        Random r = new Random();
        int result = r.nextInt(b-a) + a;
        return result;
    }
}