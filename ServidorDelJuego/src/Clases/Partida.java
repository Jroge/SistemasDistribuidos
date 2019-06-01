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
    
    public Partida(TSServerServidorSocket newServer,String nuevoNombre,
            int cantJugadores,String tipoP) {
        server = newServer;
        listaJugadores=new LinkedList<>();
        json=new Gson();
        nombreDeLaPartida=nuevoNombre;
        cantidadMaxDeJugadores=cantJugadores;
        tipoDeJuego=tipoP;
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
            case Constantes.JUGADOR_DESCONECTADO:juegoSetEnNoLinea(socketInfo);break;
            case Constantes.JUGADOR_RECONECTADO:juegoSetEnLinea(socketInfo);break;
        }
    }
    
    private void juegoSetListoParaJugar(TSocketInfo socket){
        boolean buscar=true;
        for(int i=1;i<=listaJugadores.size()&&buscar;i++){
            if(sonElMismoSocket(socket,listaJugadores.get(i-1).getSocketJugador())){
                listaJugadores.get(i-1).setListo();
                buscar=false;
            }
        }if(todosListos()&&cantidadJugadoresEnLinea()>1)juegoIniciarPartida();
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
        if(!hayJugadoresEnLinea())
            server.sendMensaje(socket, Constantes.SALA+Constantes.CERRAR_PARTIDA+"_"+nombreDeLaPartida);
    }
    private void juegoIniciarPartida(){
        boolean buscar=true;
        crearNuevosTableros();
        for(int i=1;i<=listaJugadores.size()&&buscar;i++){
            Jugador jugador=listaJugadores.get(i-1);
            if(jugador.getEnLinea()){
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
        if(!existeSocketEnListaJugadores(nuevoSocket)){
            listaJugadores.addLast(new Jugador(
                nuevoSocket,Constantes.JUGADOR+(listaJugadores.size()+1)));
            server.sendMensaje(nuevoSocket,Constantes.JUEGO+
                    Constantes.PARTIDA_INFO+"_"+nombreDeLaPartida+"_"+tipoDeJuego+"_"+
                    Integer.toString(cantidadMaxDeJugadores));
        }else{
            boolean buscar=true;
            for(int i=1;i<=listaJugadores.size()&&buscar;i++){
                if(sonElMismoSocket(nuevoSocket,
                        listaJugadores.get(i-1).getSocketJugador())){
                    listaJugadores.get(i-1).setListo();
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
                listaJugadores.get(i-1).setNoListo();
                buscar=false;
            }
        }
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
    private boolean esUltimoJugadorEnLinea(TSocketInfo socket){
        int posicionDeJugador=posicionDelJugador(socket);
        for(int i=listaJugadores.size();i>posicionDeJugador;i--){
            Jugador jugador=listaJugadores.get(i-1);
            if(jugador.getEnLinea()){
                return sonElMismoSocket(socket,jugador.getSocketJugador());
            }
        }return true;
    }
    private boolean sonElMismoSocket(TSocketInfo socketA,TSocketInfo socketB){
        return socketA.getIDSession()==socketB.getIDSession();
    }
    private boolean existeSocketEnListaJugadores(TSocketInfo nuevoSocket) {
        for(int i=1;i<=listaJugadores.size();i++){
            if(sonElMismoSocket(listaJugadores.get(i-1).getSocketJugador(),nuevoSocket))
                return true;
        }return false;
    }
    private Jugador primerJugadorEnLinea() {
        for(Jugador jugador:listaJugadores){
            if(jugador.getListo())return jugador;
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
        sendMensajeJugadoresPartida(Constantes.JUEGO+Constantes.TERMINAR_PARTIDA+
                "_"+listaDeResultado+"_"+Integer.toString(maximaPuntuacion));
    }
    public void juegoPasarAlSiguienteTurno(TSocketInfo socket){
        Tablero tablero=listaDeTableros.get(posicionDelJugador(socket));
        if((tablero.estaLleno()&&esUltimoJugadorEnLinea(socket))
                ||soloHayUnJugadorEnLinea())terminarPartida();
        else{
            boolean encontrado=false;
            while(!encontrado){
                jugadorEnTurno=siguienteJugador(socket);
                if(jugadorEnTurno.getEnLinea()){
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
    private boolean todosListos(){
        return listaJugadores.stream().noneMatch((jugador)
                ->(!jugador.getListo()));
    }
    private int cantidadJugadoresEnLinea(){
        int cantidad=0;
        cantidad = listaJugadores.stream().filter((jugador)
                ->(jugador.getEnLinea())).map((_item)->1).reduce(cantidad, Integer::sum);
        return cantidad;
    }
    private boolean soloHayUnJugadorEnLinea(){
        int cantidad=0;
        cantidad=listaJugadores.stream().filter((jugador)
                ->(jugador.getEnLinea())).map((_item) -> 1).reduce(cantidad, Integer::sum);
        return cantidad==1;
    }
    private boolean hayJugadoresEnLinea(){
        for(Jugador jugador:listaJugadores){
            if(jugador.getEnLinea())
                return true;
        }return false;
    }
    private void crearNuevosTableros(){
        listaDeTableros=new LinkedList<>();
        for(int i=1;i<=listaJugadores.size();i++){
            listaDeTableros.addLast(new Tablero());
            listaJugadores.get(i-1).setNoListo();
        }
    }
    private void juegoSetEnNoLinea(TSocketInfo socketDesconectado){
        boolean buscar=true;
        for(int i=1;i<=listaJugadores.size()&&buscar;i++){
            Jugador jugador=listaJugadores.get(i-1);
            if(sonElMismoSocket(jugador.getSocketJugador(),socketDesconectado)){
                listaJugadores.get(i-1).setEnNoLinea();
                server.sendMensajeTodos(Constantes.JUEGO+
                        Constantes.JUGADOR_DESCONECTADO+"_"+jugador.getId());
                buscar=false;
            }
        }
    }
    private void juegoSetEnLinea(TSocketInfo socketDesconectado){
        boolean buscar=true;
        for(int i=1;i<=listaJugadores.size()&&buscar;i++){
            Jugador jugador=listaJugadores.get(i-1);
            if(sonElMismoSocket(jugador.getSocketJugador(),socketDesconectado)){
                listaJugadores.get(i-1).setEnLinea();
                buscar=false;
                if(jugadorEnTurno.equals(jugador))
                    server.sendMensaje(jugador.getSocketJugador(),Constantes.JUEGO+
                            Constantes.REANUDAR_TURNO);
            }
        }
    }
    private void sendMensajeJugadoresPartida(String mensaje){
        listaJugadores.forEach((Jugador jugador)->
        {server.sendMensaje(jugador.getSocketJugador(), mensaje);});
    }
}