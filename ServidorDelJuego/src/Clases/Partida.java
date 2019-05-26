package Clases;

import TSocket.TServer.Servidor.TSServerServidorSocket;
import TSocket.TSocketInfo;
import com.google.gson.Gson;
import java.util.LinkedList;

public class Partida {
    
    private TSServerServidorSocket server;
    public LinkedList<Jugador> listaJugadores;
    private LinkedList<Tablero> listaDeTableros;
    private Jugador jugadorEnTurno;
    private Gson json;
    private String nombre;
    private int cantidadJugadores;
    private String tipo;
    
    public Partida(TSServerServidorSocket newServer,String nuevoNombre,
            int cantJugadores,String tipoP) {
        server = newServer;
        listaJugadores=new LinkedList<>();
        listaDeTableros=new LinkedList<>();
        json=new Gson();
        nombre=nuevoNombre;
        cantidadJugadores=cantJugadores;
        tipo=tipoP;
    }
    
    public void addNuevoJugador(TSocketInfo nuevoSocket){
        if(!existeSocketEnListaJugadores(nuevoSocket)){
            listaJugadores.addLast(new Jugador(
                nuevoSocket,Constantes.JUGADOR+(listaJugadores.size()+1)));
            listaDeTableros.addLast(new Tablero());
        }else{
            boolean buscar=true;
            for(int i=1;i<=listaJugadores.size()&&buscar;i++){
                if(sonElMismoSocket(nuevoSocket,
                        listaJugadores.get(i-1).getSocketJugador())){
                    listaJugadores.get(i-1).s
                    buscar=false;
                }
            }
        }
    }
    public void deshabilitarJugador(TSocketInfo socket){
        boolean buscar=true;
        for(int i=1;i<=listaJugadores.size()&&buscar;i++){
            if(sonElMismoSocket(socket,
                    listaJugadores.get(i-1).getSocketJugador())){
                listaJugadores.get(i-1).setNoEnLinea();
                buscar=false;
            }
        }
    }
    public void administrarJuego(TSocketInfo socketInfo, String mensaje){
        String[] accion = mensaje.split("_");
        System.out.println(mensaje);
        switch (accion[2]){
            case Constantes.INICAR_PARTIDA: juegoIniciarPartida(); break;
            case Constantes.NUEVO_NOMBRE: juegoSetNuevoNombre(socketInfo,accion[3]); break;
            case Constantes.NOMBRE_JUGADOR_EN_TURNO:juegoEnviarNombreJugadorEnTurno(accion[3]);break;
            case Constantes.GENERAR_DADOS: juegoGenerarDados(accion[3]); break;
            case Constantes.CAMBIAR_DADO: juegoCambiarDado(accion[3]);break;
            case Constantes.LISTA_DE_JUGADAS: juegoEnviarListaJugadas(socketInfo,accion[3]); break;
            case Constantes.JUGADA_ESCOGIDA: juegoJugadaEscogida(socketInfo, accion[3]); break;
            case Constantes.FIN_DE_TURNO:juegoPasarAlSiguienteTurno(socketInfo);break;
        }
    }
    
    private void juegoIniciarPartida(){
        boolean buscar=true;int i=1;
        while(i<=listaJugadores.size()){
            Jugador jugador=listaJugadores.get(i-1);
            if(jugador.getEnLinea()){
                if(buscar){
                    server.sendMensaje(jugador.getSocketJugador(),Constantes.JUEGO+
                        Constantes.NUEVO_ID+"_"+jugador.getId());
                    buscar=false;
                }
            }else{
                listaJugadores.remove(jugador);
                listaDeTableros.remove(i-1);
                i--;
            }
            i++;
        }
    }
    private void juegoSetNuevoNombre(TSocketInfo socket,String nuevoNombre){
        boolean buscar=true,todosConNombre=false;
        for(int i=1;i<=listaJugadores.size()&&buscar;i++){
            if(sonElMismoSocket(listaJugadores.get(i-1).getSocketJugador(), socket)){
                buscar=false;
                listaJugadores.get(i-1).setNombre(nuevoNombre);
                if(i<listaJugadores.size())
                    server.sendMensaje(listaJugadores.get(i).
                    getSocketJugador(),Constantes.JUEGO+
                    Constantes.NUEVO_ID+"_"+listaJugadores.get(i).getId());
                else todosConNombre=true;
            }
        }
        if(todosConNombre){
            String lista = listaJugadoresSinSocket();
            server.sendMensajeTodos(Constantes.JUEGO+
                    Constantes.NOMBRE_JUGADORES+"_"+lista);
        }
    }
    private void juegoEnviarNombreJugadorEnTurno(String nombre){
        server.sendMensajeTodos(Constantes.JUEGO+
                Constantes.NOMBRE_JUGADOR_EN_TURNO+"_"+nombre);
    }
    private void juegoGenerarDados(String cubile){
        Cubilete cubilete=json.fromJson(cubile,Cubilete.class);
        cubilete.agitar();
        String cad=json.toJson(cubilete);
        server.sendMensajeTodos(Constantes.JUEGO+Constantes.MOSTRAR_DADOS+"_"+cad);
    }
    private void juegoCambiarDado(String cubileteJson){
        server.sendMensajeTodos(Constantes.JUEGO+Constantes.CAMBIAR_DADO+"_"+cubileteJson);
    }
    private void juegoEnviarListaJugadas(TSocketInfo socket,String cubile){
        Cubilete cubilete=json.fromJson(cubile,Cubilete.class);
        Tablero tablero=listaDeTableros.get(posicionDelJugador(socket));
        String lista=tablero.listaDeJugadas(cubilete);
        server.sendMensajeTodos(Constantes.JUEGO+Constantes.LISTA_DE_JUGADAS+"_"+lista);
    }
    private void juegoJugadaEscogida(TSocketInfo socketInfo, String jugada){
        Tablero tablero=listaDeTableros.get(posicionDelJugador(socketInfo));
        actualizarTablero(socketInfo,jugada,tablero);
    }

    
    private Jugador siguienteJugador(TSocketInfo socket){
        int pos=0;boolean buscar=true;
        for(int in=1;in<=listaJugadores.size()&&buscar;in++){
            if(sonElMismoSocket(socket,listaJugadores.get(in-1).getSocketJugador())){
                pos=in;buscar=false;
                if(pos==listaJugadores.size())pos=0;
            }
        }return listaJugadores.get(pos);
    }
    private void actualizarTablero(TSocketInfo socket,String jugada,Tablero tablero){
        tablero.setJugada(jugada);
        String tableroJson=json.toJson(tablero);
        Jugador jugador=listaJugadores.get(posicionDelJugador(socket));
        server.sendMensajeTodos(Constantes.JUEGO+
                Constantes.CAMBIAR_TABLERO+"_"+tableroJson+"_"+jugador.getId());
    }
    private String listaJugadoresSinSocket(){
        String nuevaLista="";
        for(int i=1;i<=listaJugadores.size();i++){
            nuevaLista=nuevaLista+
                    listaJugadores.get(i-1).getId()+"|"+
                    listaJugadores.get(i-1).getNombre()+",";
        }return nuevaLista;
    }
    private boolean esUltimoJugador(TSocketInfo socket){
        return sonElMismoSocket(socket,listaJugadores.getLast().getSocketJugador());
    }
    private boolean sonElMismoSocket(TSocketInfo socketA,TSocketInfo socketB){
        return socketA.getHoraDeConexion().equals(socketB.getHoraDeConexion());
    }
    private boolean existeSocketEnListaJugadores(TSocketInfo nuevoSocket) {
        for(int i=1;i<=listaJugadores.size();i++){
            if(sonElMismoSocket(listaJugadores.get(i-1).getSocketJugador(),nuevoSocket))
                return true;
        }return false;
    }
    private Jugador primerJugadorEnLinea() {
        for(Jugador jugador:listaJugadores){
            if(jugador.getEnLinea())return jugador;
        }return null;
    }
    private int posicionDelJugador(TSocketInfo socket){
        for(int i=1;i<=listaJugadores.size();i++){
            Jugador jugador=listaJugadores.get(i-1);
            if(sonElMismoSocket(jugador.getSocketJugador(), socket))return i-1;
        }return -1;
    }
    public void terminarPartida(){
        int maximaPuntuacion=0;
        LinkedList<String> listaDeGanadores=new LinkedList<>();
        String listaDeResultado="";
        for(int i=1;i<=listaDeTableros.size();i++){
            Tablero tablero=listaDeTableros.get(i-1);
            listaDeResultado=listaDeResultado+listaJugadores.get(i-1).getId()+"-"+
                    listaJugadores.get(i-1).getNombre()+"-"+tablero.getTotal()+",";
            if(tablero.getTotal()>maximaPuntuacion){
                maximaPuntuacion=tablero.getTotal();
            }
        }
        server.sendMensajeTodos(Constantes.JUEGO+Constantes.TERMINAR_PARTIDA+
                "_"+listaDeResultado+"_"+Integer.toString(maximaPuntuacion));
    }
    public void juegoPasarAlSiguienteTurno(TSocketInfo socket){
        Tablero tablero=listaDeTableros.get(posicionDelJugador(socket));
        if(tablero.estaLleno()&&esUltimoJugador(socket))terminarPartida();
        else{
            jugadorEnTurno=siguienteJugador(socket);
            server.sendMensaje(jugadorEnTurno.getSocketJugador(),Constantes.JUEGO+
                Constantes.ES_TU_TURNO);
        }
    }
}