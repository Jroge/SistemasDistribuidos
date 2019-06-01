package Controladores;

import Clases.*;
import Forms.FormClienteJuego;
import Forms.FormClienteSala;
import TSocket.TClient.Cliente.TSClientClienteSocket;
import TSocket.TClient.Cliente.TSocketInfo;

public class ControladorCliente{
    
    private TSClientClienteSocket cliente;
    private FormClienteJuego juego;
    private FormClienteSala sala;
    public static String miNombre,miId,miPartida;
    
    public ControladorCliente(){
        
    }
    public ControladorCliente(String nuevoNombre){
        miNombre=nuevoNombre;
        iniciarFormClienteSala();
        cliente = new TSClientClienteSocket("127.0.0.1",9090){//LOCAL
            @Override
            public void onRead(String mensaje){String[] accion=mensaje.split("_");
                System.out.println(mensaje);
                switch(accion[2]){
                    case Constantes.LISTA_DE_PARTIDAS:sala.mostrarListaDePartidas(accion[3]);break;
                    case Constantes.CERRAR_PARTIDA:cerrarPartida(accion[3]);break;
                    
                    case Constantes.PARTIDA_INFO:iniciarFormClienteJuego(accion[3],accion[4],accion[5]);break;
                    case Constantes.JUEGO_INICIADO:juego.setMensajeJuegoIniciado();break;
                    case Constantes.NUEVO_ID:setNuevoId(accion[3]);break;
                    case Constantes.JUGADOR_NUEVO:juego.actualizarNombresJugadores(accion[3]);break;
                    case Constantes.NOMBRE_JUGADORES:juego.mostrarNombreDeJugadores(accion[3]);break;
                    case Constantes.ES_TU_TURNO:juego.iniciarTurno();break;
                    case Constantes.NOMBRE_JUGADOR_EN_TURNO:juego.cambiarJugadorEnTurno(accion[3]);break;
                    case Constantes.MOSTRAR_DADOS:juego.mostrarDados(accion[3]);break;
                    case Constantes.CAMBIAR_DADO:juego.cambiarDado(accion[3]);break;
                    case Constantes.LISTA_DE_JUGADAS:juego.mostrarListaDeJugadas(accion[3]);break;
                    case Constantes.CAMBIAR_TABLERO:juego.setTablero(accion[3],accion[4]);break;
                    case Constantes.TERMINAR_PARTIDA:juego.mostrarGanadores(accion[3],accion[4]);break;
                    case Constantes.JUGADOR_DESCONECTADO:juego.mostrarJugadorDesconectado(accion[3]);break;
                }
            }
            @Override
            public void onWrite(String mensaje){}
            @Override
            public void onConnected(TSocketInfo socketInfo){
                    System.out.println("CONECTADO");}
            @Override
            public void onDisconnect(TSocketInfo socketInfo){
                    System.out.println("DESCONECTADO");}
            @Override
            public void onReconnect(TSocketInfo socketInfo){
                    System.out.println("RECONECTADO");}
            @Override
            public void onError(int errorCode){}
        };cliente.connect();
    }
    
    public void setNuevoId(String nuevoId){
        miId = nuevoId;
        System.out.println("Mi nuevo Id es: "+miId);
        cliente.sendMensaje(Constantes.JUEGO+Constantes.NUEVO_NOMBRE+"_"+miNombre);
    }
    public void controladorCrearPartida(int cantMaxJugadores,String tipo) {
        cliente.sendMensaje(Constantes.SALA + Constantes.NUEVA_PARTIDA
                + "_" + cantMaxJugadores + "_" + tipo);
        terminarFormClienteSala();
    }
    public void controladorUnirseAPartidaSeleccionada(String nombrePartida){
        cliente.sendMensaje(Constantes.SALA+Constantes.UNIRSE_A_PARTIDA+
                "_"+nombrePartida);
        terminarFormClienteSala();
    }
    public void controladorBuscarPartida(String nombreDePartidaABuscar){
        cliente.sendMensaje(Constantes.SALA+Constantes.BUSCAR_PARTIDA+
                    "_"+nombreDePartidaABuscar);
    }
    public void controladorUnirseAPartidaAleatoria(){
        cliente.sendMensaje(Constantes.SALA+Constantes.UNIRSE_A_PARTIDA_ALEATORIA);
        terminarFormClienteSala();
    }
    
    public void controladorSolicitarListaDeJugadas(String cubileteJSON){
        cliente.sendMensaje(Constantes.JUEGO+
                    Constantes.LISTA_DE_JUGADAS+"_"+cubileteJSON);
    }
    public void controladorGenerarDados(String cubileteJSON){
        cliente.sendMensaje(Constantes.JUEGO+
                    Constantes.GENERAR_DADOS+"_"+cubileteJSON);
    }
    public void controladorEnviarJugadaEscogida(String jugada){
        cliente.sendMensaje(Constantes.JUEGO+
                    Constantes.JUGADA_ESCOGIDA+"_"+jugada);
    }
    public void controladorEnviarNombreJugadorEnTurno(){
        cliente.sendMensaje(Constantes.JUEGO+
                Constantes.NOMBRE_JUGADOR_EN_TURNO+"_"+miNombre);
    }
    public void controladorEnviarCambioDeDado(String cubileteJSON){
        cliente.sendMensaje(Constantes.JUEGO+Constantes.CAMBIAR_DADO+"_"+cubileteJSON);
    }
    public void controladorFinalizarTurno(){
        cliente.sendMensaje(Constantes.JUEGO+Constantes.FIN_DE_TURNO);
    }
    public void controladorAbandonarPartida(){
        cliente.sendMensaje(Constantes.JUEGO+Constantes.ABANDONAR_PARTIDA);
        terminarFormClienteJuego();
        iniciarFormClienteSala();
    }
    public void controladorListoParaJugar(){
        cliente.sendMensaje(Constantes.JUEGO+Constantes.LISTO_PARA_JUGAR);
    }
    public void controladorNoListoParaJugar(){
        cliente.sendMensaje(Constantes.JUEGO+Constantes.NO_LISTO_PARA_JUGAR);
    }
    public void cerrarPartida(String nombreDeLaPartida){
        cliente.sendMensaje(Constantes.SALA+Constantes.CERRAR_PARTIDA+"_"+nombreDeLaPartida);
    }
    
    
    //  METODOS AUXILIARES
    public String getNombre(){
        return miNombre;
    }
    public String getId(){
        return miId;
    }
    private void iniciarFormClienteSala(){
        sala=new FormClienteSala(this);
        sala.setVisibleC(true);
    }
    private void iniciarFormClienteJuego(String nombreNuevaPartida,String tipoDeJuego,String cantidadJugadores) {
        juego = new FormClienteJuego(
                this,
                nombreNuevaPartida,
                tipoDeJuego,
                Integer.parseInt(cantidadJugadores)
        );
        juego.setVisibleC(true);
    }
    private void terminarFormClienteSala(){
        sala.setVisibleC(false);
    }
    private void terminarFormClienteJuego(){
        juego.setVisibleC(false);
    }
    
    
}
