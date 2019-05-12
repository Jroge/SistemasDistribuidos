package servidordejuego;

import TSocket.TServer.Servidor.TSServerCode;
import TSocket.TServer.Servidor.TSServerServidorSocket;
import TSocket.TSocketInfo;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.DefaultListModel;
/**
 *
 * @author Elito
 */
public class Server extends javax.swing.JFrame {

    TSServerServidorSocket server;
    LinkedList<TSocketInfo> lista;
    Partida P;
    int turno=0;
    
    public Server() {
        initComponents();
        lista = new LinkedList<>();
        btnStop.setEnabled(false);
        P=new Partida();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnStart = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listConectados = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnStart.setText("Iniciar");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        btnStop.setText("Detener");
        btnStop.setEnabled(false);
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(listConectados);

        jLabel1.setText("Conectados");

        jButton1.setText("Iniciar juego");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnStart)
                        .addGap(47, 47, 47)
                        .addComponent(btnStop))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStart)
                    .addComponent(btnStop))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
       
        server = new TSServerServidorSocket(9090){
           @Override
           public void onConnect(TSocketInfo socketInfo){
               System.out.println("CONECTADO :" + socketInfo.getHostName() + "-"+socketInfo.getHoraDeConexion());
               lista.add(socketInfo);
               P.addNuevoJugador(socketInfo);
           }
           @Override
           public void onDisconnect(TSocketInfo socketInfo){
                System.out.println("DESCONECTADO :" + socketInfo.getHostName() + "-"+socketInfo.getHoraDeConexion());
                P.removeJugador(socketInfo);
           }
           @Override
            public void onWrite(TSocketInfo socketInfo, String mensaje, int code){
                System.out.println(mensaje);    
                switch (code){
                        case TSServerCode.SUCCESS: System.out.println("mensaje enviado con exito"); break;
                        case TSServerCode.ERROR_SENDING_MESSAGE: System.out.println("ERROR ENVIANDO");break;
                    }
            }
           @Override
            public void onRead(TSocketInfo socketInfo, String mensaje){
                System.out.println("Desde: "+socketInfo.getHostName() +" ID: " +socketInfo.getIDSession()+" msj: "+mensaje);
            }
           @Override
            public void onError(int code){

            }
           @Override
            public void onError(TSocketInfo socketInfo, int code){

            }
       };
       server.startServer();
       btnStop.setEnabled(true);
       btnStart.setEnabled(false);
    }//GEN-LAST:event_btnStartActionPerformed
   
 
    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
       server.stopServer();
       btnStart.setEnabled(true);
       btnStop.setEnabled(false);
    }//GEN-LAST:event_btnStopActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(!lista.isEmpty()){
            P.administrarJuego((TSocketInfo)lista.getLast(), constNames.JUEGO+constNames.TERMINE);
        }
        System.out.println("NINGUN JUGADOR");
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Server().setVisible(true);
            }
        });
    }
    public void actualizarLista(){
        DefaultListModel listModel = new DefaultListModel();
        for (int i = 0; i < lista.size(); i++)
        {
            if(lista.get(i) != null)
                listModel.addElement(lista.get(i).getClientAddress());
        }
        listConectados.setModel(listModel);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStart;
    private javax.swing.JButton btnStop;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList listConectados;
    // End of variables declaration//GEN-END:variables
    
}
