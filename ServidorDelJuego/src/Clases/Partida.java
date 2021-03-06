package Clases;

import TSocket.TServer.Servidor.TSServerServidorSocket;
import TSocket.TSocketInfo;
import com.google.gson.Gson;
import java.util.LinkedList;

public class Partida {
    
    private final TSServerServidorSocket server;
    public LinkedList<Jugador> listaJugadores;
    private LinkedList<Tablero> listaDeTableros;
    private Jugador jugadorEnTurno;
    private final Gson json;
    private final String nombreDeLaPartida;
    private final int cantidadMaxDeJugadores;
    private final String tipoDeJuego;
    private boolean partidaIniciada;
    
    public Partida(TSServerServidorSocket newServer,String nuevoNombre,
            int cantJugadores,String tipoP) {
        server = newServer;
        listaJugadores=new LinkedList<>();
        json=new Gson();
        nombreDeLaPartida=nuevoNombre;
        cantidadMaxDeJugadores=cantJugadores;
        tipoDeJuego=tipoP;
        partidaIniciada=false;
    }
    
    public String getNombreDeLaPartida(){return nombreDeLaPartida;}
    public String getTipoDeJuego(){return tipoDeJuego;}
    public int getCantidadMaxDeJugadores(){return cantidadMaxDeJugadores;}
    public int getCupos(){return cantidadMaxDeJugadores-listaJugadores.size();}
    
    public void administrarJuego(TSocketInfo socketInfo, String mensaje){
        String[] accion = mensaje.split("_");
        System.out.println("Partida: "+mensaje);
        switch (accion[2]){
            case Constantes.LISTO_PARA_JUGAR:juegoSetListoParaJugar(socketInfo);break;
            case Constantes.NO_LISTO_PARA_JUGAR:juegoSetNoListoParaJugar(socketInfo);break;
            case Constantes.INICAR_PARTIDA:juegoIniciarPartida(); break;
            case Constantes.NUEVO_NOMBRE:juegoSetNuevoNombre(socketInfo,accion[3]); break;
            case Constantes.NOMBRE_JUGADOR_EN_TURNO:juegoEnviarNombreJugadorEnTurno(accion[3]);break;
            case Constantes.GENERAR_DADOS:juegoGenerarDados(accion[3]); break;
            case Constantes.CAMBIAR_DADO:juegoCambiarDado(accion[3]);break;
            case Constantes.LISTA_DE_JUGADAS:juegoEnviarListaJugadas(socketInfo,accion[3]); break;
            case Constantes.JUGADA_ESCOGIDA:juegoJugadaEscogida(socketInfo, accion[3]); break;
            case Constantes.FIN_DE_TURNO:juegoPasarAlSiguienteTurno(socketInfo);break;
            case Constantes.ABANDONAR_PARTIDA:juegoSacarDeLaPartida(socketInfo);break;
        }
    }
    
    private void juegoSetListoParaJugar(TSocketInfo socket){
        if(!partidaIniciada){
            boolean buscar=true;
            for(int i=1;i<=listaJugadores.size()&&buscar;i++){
                if(sonElMismoSocket(socket,listaJugadores.get(i-1).getSocketJugador())){
                    listaJugadores.get(i-1).setListo();
                    buscar=false;
                }
            }if(todosListos()&&listaJugadores.size()>1)juegoIniciarPartida();
        }else{
            server.sendMensaje(socket,Constantes.JUEGO+Constantes.JUEGO_INICIADO);
        }
    }
    private void juegoSetNoListoParaJugar(TSocketInfo socket){
        boolean buscar=true;
        for(int i=1;i<=listaJugadores.size()&&buscar;i++){
            if(sonElMismoSocket(socket,listaJugadores.get(i-1).getSocketJugador())){
                listaJugadores.get(i-1).setNoListo();
                buscar=false;
            }
        }
    }
    private void juegoSacarDeLaPartida(TSocketInfo socket){
        boolean buscar=true;
        for(int i=1;i<=listaJugadores.size()&&buscar;i++){
            if(sonElMismoSocket(listaJugadores.get(i-1).getSocketJugador(),socket)){
                listaJugadores.remove(i-1);
                if(listaDeTableros!=null)
                listaDeTableros.remove(i-1);
                buscar=false;
            }
        }
        if(listaJugadores.isEmpty())
            server.sendMensaje(socket, Constantes.SALA+Constantes.CERRAR_PARTIDA+"_"+nombreDeLaPartida);
    }
    private void juegoIniciarPartida(){
        partidaIniciada=true;
        crearNuevosTableros();
        boolean buscar=true;
        for(int i=1;i<=listaJugadores.size()&&buscar;i++){
            Jugador jugador=listaJugadores.get(i-1);
            if(jugador.getListo()){
                jugador.setId(Constantes.JUGADOR+posicionDelJugador(jugador.getSocketJugador()));
                server.sendMensaje(jugador.getSocketJugador(),Constantes.JUEGO+
                    Constantes.NUEVO_ID+"_"+jugador.getId());
                buscar=false;
            }
        }
    }
    private void juegoSetNuevoNombre(TSocketInfo socket,String nuevoNombre){
        boolean buscar=true;
        for(int i=1;i<=listaJugadores.size()&&buscar;i++){
            if(sonElMismoSocket(listaJugadores.get(i-1).getSocketJugador(), socket)){
                buscar=false;
                listaJugadores.get(i-1).setNombre(nuevoNombre);
                if(i<listaJugadores.size())
                    server.sendMensaje(listaJugadores.get(i).
                    getSocketJugador(),Constantes.JUEGO+
                    Constantes.NUEVO_ID+"_"+listaJugadores.get(i).getId());
            }
        }
        if(todosConNombre())
            sendMensajeJugadoresPartida(Constantes.JUEGO+
                    Constantes.NOMBRE_JUGADORES+"_"+listaJugadoresSinSocket());
    }
    private void juegoEnviarNombreJugadorEnTurno(String nombreDeJugadorEnTurno){
        sendMensajeJugadoresPartida(Constantes.JUEGO+
                Constantes.NOMBRE_JUGADOR_EN_TURNO+"_"+nombreDeJugadorEnTurno);
    }
    private void juegoGenerarDados(String cubileteJSON){
        Cubilete cubilete=json.fromJson(cubileteJSON,Cubilete.class);
        cubilete.agitar();
        String cad=json.toJson(cubilete);
        sendMensajeJugadoresPartida(Constantes.JUEGO+Constantes.MOSTRAR_DADOS+"_"+cad);
    }
    private void juegoCambiarDado(String cubileteJson){
        sendMensajeJugadoresPartida(Constantes.JUEGO+Constantes.CAMBIAR_DADO+"_"+cubileteJson);
    }
    private void juegoEnviarListaJugadas(TSocketInfo socket,String cubileteJSON){
        Cubilete cubilete=json.fromJson(cubileteJSON,Cubilete.class);
        Tablero tablero=listaDeTableros.get(posicionDelJugador(socket));
        String lista=tablero.listaDeJugadas(cubilete);
        sendMensajeJugadoresPartida(Constantes.JUEGO+Constantes.LISTA_DE_JUGADAS+"_"+lista);
    }
    private void juegoJugadaEscogida(TSocketInfo socketInfo, String jugada){
        Tablero tablero=listaDeTableros.get(posicionDelJugador(socketInfo));
        actualizarTablero(socketInfo,jugada,tablero);
    }
    public void addNuevoJugador(TSocketInfo nuevoSocket){
            listaJugadores.addLast(new Jugador(
                nuevoSocket,Constantes.JUGADOR+(listaJugadores.size()+1)));
            server.sendMensaje(nuevoSocket,Constantes.JUEGO+
                    Constantes.PARTIDA_INFO+"_"+nombreDeLaPartida+"_"+tipoDeJuego+"_"+
                    Integer.toString(cantidadMaxDeJugadores));
    }
    public boolean contieneAlJugador(TSocketInfo socketDelJugador){
        return listaJugadores.stream().anyMatch((jugador)
                ->(sonElMismoSocket(jugador.getSocketJugador(),socketDelJugador)));
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
        sendMensajeJugadoresPartida(Constantes.JUEGO+
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
    private boolean esUltimoJugadorListo(TSocketInfo socket){
        int posicionDeJugador=posicionDelJugador(socket);
        for(int i=listaJugadores.size();i>posicionDeJugador;i--){
            Jugador jugador=listaJugadores.get(i-1);
            if(jugador.getListo()){
                return sonElMismoSocket(socket,jugador.getSocketJugador());
            }
        }return true;
    }
    private boolean sonElMismoSocket(TSocketInfo socketA,TSocketInfo socketB){
        return socketA.getIDSession()==socketB.getIDSession();
    }
    private int posicionDelJugador(TSocketInfo socket){
        for(int i=1;i<=listaJugadores.size();i++){
            Jugador jugador=listaJugadores.get(i-1);
            if(sonElMismoSocket(jugador.getSocketJugador(), socket))return i-1;
        }return -1;
    }
    public void terminarPartida(){
        partidaIniciada=false;
        int maximaPuntuacion=0;
        String listaDeResultado="";
        for(int i=1;i<=listaDeTableros.size();i++){
            Tablero tablero=listaDeTableros.get(i-1);
            listaDeResultado=listaDeResultado+listaJugadores.get(i-1).getId()+"-"+
                    listaJugadores.get(i-1).getNombre()+"-"+tablero.getTotal()+",";
            if(tablero.getTotal()>maximaPuntuacion){
                maximaPuntuacion=tablero.getTotal();
            }
        }
        sendMensajeJugadoresPartida(Constantes.JUEGO+Constantes.TERMINAR_PARTIDA+
                "_"+listaDeResultado+"_"+Integer.toString(maximaPuntuacion));
        setTodosNoListos();
    }
    public void juegoPasarAlSiguienteTurno(TSocketInfo socket){
        Tablero tablero=listaDeTableros.get(posicionDelJugador(socket));
        if((tablero.estaLleno()&&esUltimoJugadorListo(socket))
                ||listaDeTableros.size()==1)terminarPartida();
        else{
            boolean encontrado=false;
            while(!encontrado){
                jugadorEnTurno=siguienteJugador(socket);
                if(jugadorEnTurno.getListo()){
                    server.sendMensaje(jugadorEnTurno.getSocketJugador(),Constantes.JUEGO+
                        Constantes.ES_TU_TURNO);
                    encontrado=true;
                }else{
                    socket=jugadorEnTurno.getSocketJugador();
                }
            }
        }
    }
    
    
    //OERACIONES AUXILIARES
    private boolean todosConNombre(){
        return listaJugadores.stream().noneMatch((Jugador jugador)
                ->jugador.getNombre().equals(" "));
    }
    private void crearNuevosTableros(){
        listaDeTableros=new LinkedList<>();
        for(int i=1;i<=listaJugadores.size();i++){
            listaDeTableros.addLast(new Tablero());
        }
    }
    private boolean todosListos(){
        for(Jugador jugador:listaJugadores){
            if(!jugador.getListo())
                return false;
        }return true;
    }
    private void setTodosNoListos(){
        for(Jugador jugador:listaJugadores){
            jugador.setNoListo();
        }
    }
    private void sendMensajeJugadoresPartida(String mensaje){
        for(Jugador jugador:listaJugadores){
            if(jugador.getListo())
                server.sendMensaje(jugador.getSocketJugador(),mensaje);
        }
    }
}
