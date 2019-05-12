package servidordejuego;

import Clases.Cubilete;
import Clases.Dado;
import TSocket.TServer.Servidor.TSServerServidorSocket;
import TSocket.TSocketInfo;
import com.google.gson.Gson;
import java.util.LinkedList;
import java.util.Random;
/**
 *
 * @author Elito
 */
public class Partida {
    public TSServerServidorSocket server;
    public LinkedList<TSocketInfo> listaSocketInfoJugadores;
    private Cubilete cubilete;
    private int i;
    
    public Partida(){
        i=0;
        listaSocketInfoJugadores=new LinkedList<>();
    }
    public Partida(TSServerServidorSocket newServer) {
        this();
        server = newServer;
    }
    
    public void addNuevoJugador(TSocketInfo nuevoSocket){
        listaSocketInfoJugadores.addLast(nuevoSocket);
    }
    public void removeJugador(TSocketInfo socket){
        listaSocketInfoJugadores.remove(socket);
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
        Gson json=new Gson();
        cubilete=json.fromJson(cubile,Cubilete.class);
        agitarCubilete();
        String cad=json.toJson(cubilete);
        //esto deberia enviar, pero no da :( constNames.JUEGO+constNames.MOSTRAR_DADOS+"_"+cad
        server.sendMensajeTodos(" NO DAAA :( ");
    }
    
    private void juegoJugadaEscogida(TSocketInfo socketInfo, String jugada){
        actualizarTablero(jugada);
    }
    private void siguienteTiro(TSocketInfo socketInfo){
        server.sendMensaje(socketInfo,constNames.NO_ES_TU_TURNO);
        TSocketInfo siguiente=siguiente(socketInfo);
        server.sendMensaje(siguiente,constNames.ES_TU_TURNO);
    }
    
    //UTILS
    private TSocketInfo siguiente(TSocketInfo socket){
        int pos=0;
        for(int i=1;i<=listaSocketInfoJugadores.size();i++){
            if(socket.equals(listaSocketInfoJugadores.get(i-1))){
                pos=i;
            }
        }
        if(pos==listaSocketInfoJugadores.size()){
            pos=0;
        }
        return listaSocketInfoJugadores.get(pos);
    }
    private void agitarCubilete(){
        for (int i = 1; i <= 5; i++) {
            if (!cubilete.getDado(i - 1).fueElegido()) {
                cubilete.getDado(i - 1).setValor(random(1, 6));
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
}
