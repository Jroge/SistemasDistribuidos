package Controladores;

import Clases.*;
import Forms.FormClienteJuego;
import Forms.FormClienteSala;
import TSocket.TClient.Cliente.TSClientClienteSocket;
import TSocket.TClient.Cliente.TSocketInfo;

public class ControladorCliente extends javax.swing.JFrame {
    
    private TSClientClienteSocket cliente;
    private FormClienteJuego juego;
    private FormClienteSala sala;
    public static String miNombre,miId;
    
    public ControladorCliente(){
        
        cliente = new TSClientClienteSocket("127.0.0.1",9090){//LOCAL
            @Override
            public void onRead(String mensaje){String[] accion=mensaje.split("_");
                System.out.println(mensaje);
                switch(accion[2]){
                    case Constantes.LISTA_DE_PARTIDAS:sala.mostrarListaDePartidas(accion[3]);break;
                    
                    case Constantes.NUEVO_ID:juego.setMiNuevoId(accion[3]);break;
                    case Constantes.NOMBRE_JUGADORES:juego.mostrarNombreDeJugadores(accion[3]);break;
                    case Constantes.ES_TU_TURNO:juego.iniciarTurno();break;
                    case Constantes.NOMBRE_JUGADOR_EN_TURNO:juego.cambiarJugadorEnTurno(accion[3]);break;
                    case Constantes.MOSTRAR_DADOS:juego.mostrarDados(accion[3]);break;
                    case Constantes.CAMBIAR_DADO:juego.cambiarDado(accion[3]);break;
                    case Constantes.LISTA_DE_JUGADAS:juego.mostrarListaDeJugadas(accion[3]);break;
                    case Constantes.CAMBIAR_TABLERO:juego.setTablero(accion[3],accion[4]);break;
                    case Constantes.TERMINAR_PARTIDA:juego.mostrarGanadores(accion[3],accion[4]);break;
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
    
    public void setNuevoNombre(String nuevoNombre){
        miNombre=nuevoNombre;
        iniciarFormClienteSala();
    }
    public void unirseAPartidaSeleccionada(String nuevaPartida){
        cliente.sendMensaje(Constantes.SALA+Constantes.UNIRSE_A_PARTIDA+
                "_"+nuevaPartida);
    }
    public void controladorUnirseAPartida(int cantMaxJugadores,String tipo) {
        cliente.sendMensaje(Constantes.SALA + Constantes.NUEVA_PARTIDA
                + "_" + cantMaxJugadores + "_" + tipo);
        terminarFormClienteSala();
    }
    
    
    //  METODOS AUXILIARES
    private void iniciarFormClienteSala(){
        sala=new FormClienteSala();
        sala.setVisible(true);
    }
    private void iniciarFormClienteJuego() {
        juego = new FormClienteJuego();
        juego.setVisible(true);
    }
    private void terminarFormClienteSala(){
        sala.setVisible(false);
    }
    private void terminarFormClienteJuego(){
        juego.setVisible(false);
    }
    
    
}
