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
                nuevoSocket,Constantes.JUGADOR+(listaJugadores.size()+1)));
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
            case Constantes.INICAR_PARTIDA: juegoIniciarPartida(); break;
            case Constantes.GENERAR_DADOS: juegoGenerarDados(accion[3]); break;
            case Constantes.CAMBIAR_DADO: juegoCambiarDado(accion[3]);break;
            case Constantes.JUGADA_ESCOGIDA: juegoJugadaEscogida(socketInfo, accion[3],accion[4]); break;
            case Constantes.LISTA_DE_JUGADAS: juegoEnviarListaJugadas(socketInfo,accion[3],accion[4]); break;
            case Constantes.NOMBRE_JUGADOR_EN_TURNO:juegoEnviarNombreJugadorEnTurno(accion[3]);break;
            case Constantes.NUEVO_NOMBRE: juegoSetNuevoNombre(socketInfo,accion[3]); break;
        }
    }
    private void juegoIniciarPartida() {
        server.sendMensaje(listaJugadores.getFirst().getSocketJugador(),
                Constantes.JUEGO + Constantes.NUEVO_ID + "_"
                + listaJugadores.getFirst().getId());
    }
    
    private void juegoSetNuevoNombre(TSocketInfo socket,String nuevoNombre){
        boolean todosConNombre=false,b=true;
        for(int i=1;i<=listaJugadores.size()&&b;i++){
            if(listaJugadores.get(i-1).getSocketJugador().getHoraDeConexion().
                    equals(socket.getHoraDeConexion())){
                listaJugadores.get(i-1).setNombre(nuevoNombre);
                if(i<listaJugadores.size()){
                    server.sendMensaje(listaJugadores.get(i).
                    getSocketJugador(),Constantes.JUEGO+
                    Constantes.NUEVO_ID+"_"+listaJugadores.get(i).getId());
                }else{
                    todosConNombre=true;
                }
                b=false;
            }
        }
        if(todosConNombre){
            Gson json = new Gson();
            LinkedList<Jugador> lista = listaJugadoresSinSocket();
            String listaJ = json.toJson(lista);
            server.sendMensajeTodos(Constantes.JUEGO + Constantes.NOMBRE_JUGADORES
                   +"_"+listaJ);
        }
    }
    private void juegoCambiarDado(String cubileteJson){
        server.sendMensajeTodos(Constantes.JUEGO+Constantes.CAMBIAR_DADO+"_"+cubileteJson);
    }
    
    private void juegoGenerarDados(String cubile){
        Gson json=new Gson();
        cubilete=json.fromJson(cubile,Cubilete.class);
        agitarCubilete();
        String cad=json.toJson(cubilete);
        server.sendMensajeTodos(Constantes.JUEGO+Constantes.MOSTRAR_DADOS+"_"+cad);
    }
    
    private void juegoJugadaEscogida(TSocketInfo socketInfo, String jugada,String tableroJSON){
        Gson json=new Gson();
        actualizarTablero(socketInfo,jugada,tablero);
        tablero=json.fromJson(tableroJSON,Tablero.class);
        if(tablero.estaLleno()&&esUltimoJugador(socketInfo)){
            server.sendMensaje(listaJugadores.getFirst().getSocketJugador(),
                    Constantes.JUEGO+Constantes.TERMINAR_PARTIDA);
        }else{
            server.sendMensaje((TSocketInfo)siguiente(socketInfo),Constantes.JUEGO+Constantes.ES_TU_TURNO);
        }
    }
    
    private void juegoEnviarListaJugadas(TSocketInfo socket,String cubile,String tabler){
        Gson json=new Gson();
        cubilete=json.fromJson(cubile,Cubilete.class);
        tablero=json.fromJson(tabler, Tablero.class);
        String lista=getListaPosibleJugadas();
        server.sendMensajeTodos(Constantes.JUEGO+Constantes.LISTA_DE_JUGADAS+"_"+lista);
    }
    
    private void juegoEnviarNombreJugadorEnTurno(String nombre){
        server.sendMensajeTodos(Constantes.JUEGO+Constantes.NOMBRE_JUGADOR_EN_TURNO+"_"+nombre);
    }

    //UTILS
    private TSocketInfo siguiente(TSocketInfo socket){
        int pos=0;
        for(int in=1;in<=listaJugadores.size();in++){
            if(socket.getHoraDeConexion().equals(listaJugadores.get(in-1).getSocketJugador().getHoraDeConexion())){
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
                cubilete.getDado(in - 1).setValor(random(1, 7));
            }
        }
    }
    
    private String getListaPosibleJugadas(){
        String jugadas="";
        for(int i=1;i<=6;i++){
            if(cubilete.hay(i)&&tablero.get(i)==0){
                jugadas=jugadas+Integer.toString(cubilete.cantidadDeDados(i)*i)+" al "+i+",";
            }
        }
        if(cubilete.hayEscalera()&&tablero.getEscalera()==0){
            jugadas=jugadas+Constantes.JUGADA_ESCALERA+",";
        }
        if(cubilete.hayFull()&&tablero.getFull()==0){
            jugadas=jugadas+Constantes.JUGADA_FULL+",";
        }
        if(cubilete.hayPoquer()&&tablero.getPoquer()==0){
            jugadas=jugadas+Constantes.JUGADA_POQUER+",";
        }
        if(cubilete.hayGrande()&&tablero.getGrande()==0){
            jugadas=jugadas+Constantes.JUGADA_GRANDE+",";
        }
        if (jugadas.equals("")) {
            for (int i = 1; i <= 6; i++) {
                if (tablero.get(i) == 0) {
                    jugadas = jugadas+"Borrar los " + i + ",";
                }
            }
            if (tablero.getEscalera() == 0) {
                jugadas = jugadas + "Borrar "+Constantes.JUGADA_ESCALERA + ",";
            }
            if (tablero.getFull() == 0) {
                jugadas = jugadas + "Borrar "+ Constantes.JUGADA_FULL + ",";
            }
            if (tablero.getPoquer() == 0) {
                jugadas = jugadas + "Borrar "+ Constantes.JUGADA_POQUER + ",";
            }
            if (tablero.getGrande() == 0) {
                jugadas = jugadas + "Borrar "+ Constantes.JUGADA_GRANDE + ",";
            }
        }
        return jugadas;
    }
    
    private void actualizarTablero(TSocketInfo socket,String jugada,Tablero tablero){
        Gson json=new Gson();
        tablero.setJugada(jugada);
        String tableroJson=json.toJson(tablero);
        String idJugador="null";
        for(int i=1;i<=listaJugadores.size();i++){
            if(socket.getHoraDeConexion().equals(listaJugadores.get(i-1).
                    getSocketJugador().getHoraDeConexion())){
                idJugador=listaJugadores.get(i-1).getId();
            }
        }
        server.sendMensajeTodos(Constantes.JUEGO+Constantes.CAMBIAR_TABLERO_ULTIMA_JUGADA+"_"+
                tableroJson+"_"+idJugador+"_"+jugada);
        server.sendMensajeTodos(Constantes.JUEGO+Constantes.LISTA_DE_JUGADAS+"_Sin,Jugadas");
    }
    
    private int random(int a, int b){
        Random r = new Random();
        int result = r.nextInt(b-a) + a;
        if(result>6){
            result=6;
        }
        return result;
    }
    
    private LinkedList<Jugador> listaJugadoresSinSocket(){
        LinkedList<Jugador> nuevaLista=new LinkedList<>();
        for(int i=1;i<=listaJugadores.size();i++){
            Jugador j=new Jugador();
            j.setSocketJugador(null);
            j.setId(listaJugadores.get(i-1).getId());
            j.setNombre(listaJugadores.get(i-1).getNombre());
            nuevaLista.addLast(j);
        }return nuevaLista;
    }
    private boolean esUltimoJugador(TSocketInfo socket){
        return (listaJugadores.getLast().getSocketJugador().getHoraDeConexion().
                equals(socket.getHoraDeConexion()));
    }
}