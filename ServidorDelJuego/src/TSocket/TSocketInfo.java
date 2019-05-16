/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TSocket;

/**
 *
 * @author Elito
 */
public class TSocketInfo {
    private final int IDSession;
    private final String hostName;
    private final String address;
    private final String horaDeConexion;

    public TSocketInfo(int IDSession, String hostName, String address, String horaDeConexion) {
        this.IDSession = IDSession;
        this.hostName = hostName;
        this.address = address;
        this.horaDeConexion = horaDeConexion;
    }

    public int getIDSession() {
        return IDSession;
    }

    public String getHostName() {
        return hostName;
    }

    public String getClientAddress() {
        return address;
    }

    public String getHoraDeConexion() {
        return horaDeConexion;
    }
    @Override
    public String toString(){
        return "ID :" + IDSession + " IP:"+ address;
                 
    }
}
