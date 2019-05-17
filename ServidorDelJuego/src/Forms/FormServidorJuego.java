package Forms;

import Clases.Constantes;
import Clases.Partida;
import TSocket.TServer.Servidor.TSServerCode;
import TSocket.TServer.Servidor.TSServerServidorSocket;
import TSocket.TSocketInfo;
import java.util.LinkedList;
import javax.swing.DefaultListModel;

public class FormServidorJuego extends javax.swing.JFrame {

    private boolean b;
    private TSServerServidorSocket servidor;
    private LinkedList<TSocketInfo> listaMisConectados;
    private Partida partida;
    
    public FormServidorJuego() {
        initComponents();
        b=true;
        modificarEstadosBotones();
    }    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DetenerBtn = new javax.swing.JButton();
        IniciarBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaConectados = new javax.swing.JList<>();
        empezarNuevaPartida = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaJugadores = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        DetenerBtn.setText("DETENER");
        DetenerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DetenerBtnActionPerformed(evt);
            }
        });

        IniciarBtn.setText("INICIAR");
        IniciarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IniciarBtnActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(listaConectados);

        empezarNuevaPartida.setText("EMPEZAR NUEVA PARTIDA");
        empezarNuevaPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empezarNuevaPartidaActionPerformed(evt);
            }
        });

        jLabel1.setText("LISTA DE CONECTADOS");

        jLabel2.setText("LISTA DE JUGADORES");

        jScrollPane2.setViewportView(listaJugadores);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(IniciarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(DetenerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(empezarNuevaPartida))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(IniciarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DetenerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(empezarNuevaPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void IniciarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IniciarBtnActionPerformed
        listaMisConectados=new LinkedList<>();
        modificarEstadosBotones();
        servidor = new TSServerServidorSocket(9090){
           @Override
           public void onConnect(TSocketInfo socketInfo){
               System.out.println("Nueva Conexion Desde: " + socketInfo.getHostName() + " HRS: "+socketInfo.getHoraDeConexion());
               listaMisConectados.remove(socketInfo);
               listaMisConectados.addLast(socketInfo);
               modificarListaConectados();
           }
           @Override
           public void onDisconnect(TSocketInfo socketInfo){
                System.out.println("Se Desconectó: " + socketInfo.getHostName() + " HRS: "+socketInfo.getHoraDeConexion());
                listaMisConectados.remove(socketInfo);
                modificarListaConectados();
           }
           @Override
            public void onWrite(TSocketInfo socketInfo, String mensaje, int code){
                switch (code){
                        case TSServerCode.SUCCESS: System.out.println("mensaje enviado con exito"); break;
                        case TSServerCode.ERROR_SENDING_MESSAGE: System.out.println("ERROR ENVIANDO");break;
                    }
            }
           @Override
            public void onRead(TSocketInfo socketInfo, String mensaje){
                System.out.println("Desde: "+socketInfo.getHostName() +" ID: " +socketInfo.getIDSession()+" Mensaje: "+mensaje);
                if(mensaje.contains(Constantes.JUEGO)){
                    partida.administrarJuego(socketInfo, mensaje);
                }
            }
           @Override
            public void onError(int code){

            }
           @Override
            public void onError(TSocketInfo socketInfo, int code){

            }
       };
       servidor.startServer();
    }//GEN-LAST:event_IniciarBtnActionPerformed

    private void DetenerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DetenerBtnActionPerformed
        servidor.stopServer();
        modificarEstadosBotones();
    }//GEN-LAST:event_DetenerBtnActionPerformed

    private void empezarNuevaPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empezarNuevaPartidaActionPerformed
        partida = new Partida(this.servidor);
        if(!listaMisConectados.isEmpty()){
            listaMisConectados.forEach((socket)->{
                partida.addNuevoJugador(socket);});
            partida.administrarJuego(partida.listaJugadores.getLast().socketJugador,
                    Constantes.JUEGO+Constantes.JUGADA_ESCOGIDA+"_j_j");
        }else{
            System.out.println("NO HAY JUGADORES");
        }
        modificarListaJugadores();
    }//GEN-LAST:event_empezarNuevaPartidaActionPerformed
    
    private void modificarEstadosBotones(){
        IniciarBtn.setEnabled(b);
        DetenerBtn.setEnabled(!b);
        empezarNuevaPartida.setEnabled(!b);
        b=!b;
    }
    public void modificarListaConectados(){
        DefaultListModel listModel = new DefaultListModel();
        listaMisConectados.forEach((socket)->{
            listModel.addElement(socket.getClientAddress());});
        listaConectados.setModel(listModel);
    }
    public void modificarListaJugadores(){
        DefaultListModel listModelJugadores = new DefaultListModel();
        partida.listaJugadores.forEach((jugador)->{
            listModelJugadores.addElement(jugador.getNombre());});
        listaJugadores.setModel(listModelJugadores);
    }
    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormServidorJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(() -> {
            new FormServidorJuego().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DetenerBtn;
    private javax.swing.JButton IniciarBtn;
    private javax.swing.JButton empezarNuevaPartida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> listaConectados;
    private javax.swing.JList<String> listaJugadores;
    // End of variables declaration//GEN-END:variables
}
