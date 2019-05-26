/*package Clases;

import java.util.LinkedList;
import java.util.Random;

public class Partida {
    
    public LinkedList<Jugador> listaJugadores;
    private Cubilete cubilete;
    private int i;
    
    public Partida(){
        i=0;
        listaJugadores=new LinkedList<>();
    }
    public Partida(TSServerServidorSocket newServer) {
        this();
        //server = newServer;
    }
    
    public void addNuevoJugador(TSocketInfo nuevoSocket){
        listaJugadores.addLast(new Jugador(
                nuevoSocket,"Jugador"+(listaJugadores.size()+1)));
    }
    public void removeJugador(TSocketInfo socket){
        listaJugadores.remove(socket);
    }
    public void administrarJuego(TSocketInfo socketInfo, String mensaje){
        String[] accion = mensaje.split("_");
        switch (accion[2]){
            case constNames.GENERAR_DADOS: juegoGenerarDados(accion[3]); break;
            case constNames.JUGADA_ESCOGIDA: juegoJugadaEscogida(socketInfo, accion[3]); break;
            case constNames.TERMINE: siguienteTiro(socketInfo); break;
        }
    }
    private void juegoGenerarDados(String cubile){
        /*Gson json=new Gson();
        cubilete=json.fromJson(cubile,Cubilete.class);
        agitarCubilete();
        String cad=json.toJson(cubilete);
        //esto deberia enviar, pero no da :( constNames.JUEGO+constNames.MOSTRAR_DADOS+"_"+cad
        //server.sendMensajeTodos(" NO DAAA :( ");
    }
    
    private void juegoJugadaEscogida(TSocketInfo socketInfo, String jugada){
        actualizarTablero(jugada);
    }
    private void siguienteTiro(TSocketInfo socketInfo){
        //server.sendMensaje(socketInfo,constNames.NO_ES_TU_TURNO);
        Jugador siguiente=siguiente(socketInfo);
        //server.sendMensaje(siguiente,constNames.ES_TU_TURNO);
    }
    
    //UTILS
    private Jugador siguiente(TSocketInfo socket){
        int pos=0;
        for(int in=1;in<=listaJugadores.size();in++){
            if(socket.equals(listaJugadores.get(in-1))){
                pos=in;
            }
        }
        if(pos==listaJugadores.size()){
            pos=0;
        }
        return listaJugadores.get(pos);
    }
    private void agitarCubilete(){
        for (int in = 1; in <= 5; in++) {
            if (!cubilete.getDado(in - 1).fueElegido()) {
                cubilete.getDado(in - 1).setValor(random(1, 6));
            }
        }
    }
    private String getListaPosibleJugadas(String dados){
        return "j1,j2,j3,j4,j5";
    }
    private void actualizarTablero(String jugada){
        
    }
    private int random(int a, int b){
        Random r = new Random();
        int result = r.nextInt(b-a) + a;
        return result;
    }
}*/