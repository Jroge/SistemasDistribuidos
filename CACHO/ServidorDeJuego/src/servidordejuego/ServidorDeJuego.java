/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidordejuego;

/**
 *
 * @author Elito
 */
public class ServidorDeJuego {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new ServidorDeJuego();
    }
    public ServidorDeJuego(){
        Server s = new Server();
        s.setVisible(true);
    }
}
