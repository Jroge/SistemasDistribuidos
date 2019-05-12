/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientejuego;

/**
 *
 * @author Elito
 */
public class ClienteJuego {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new ClienteJuego();
    }
    public ClienteJuego(){
        Juego j = new Juego();
        j.setVisible(true);
    }
    
}
