package Forms;

import Clases.Constantes;
import TSocket.TClient.Cliente.TSClientClienteSocket;
import TSocket.TClient.Cliente.TSocketInfo;
import javax.swing.JOptionPane;

public class FormClienteSala extends javax.swing.JFrame {

    String nombreJugador;
    TSClientClienteSocket cliente;
    
    public FormClienteSala(String nombre) {
        initComponents();
        nombreJugador=nombre;
        nombreJugadorLabel.setText(nombreJugador);
        tresTirosCheck.setSelected(true);
        unirseAPartidaBtn.requestFocus();
        cliente = new TSClientClienteSocket("127.0.0.1",9090){//LOCAL
            @Override
            public void onRead(String mensaje){String[] accion=mensaje.split("_");
            System.out.println(mensaje);
            switch(accion[2]){
                case "a":break;
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nombreJugadorLabel = new javax.swing.JLabel();
        unirseAPartidaBtn = new javax.swing.JButton();
        buscarPartidaBtn = new javax.swing.JButton();
        crearPartidaBtn = new javax.swing.JButton();
        buscarPartidaTxt = new javax.swing.JTextField();
        tresTirosCheck = new javax.swing.JRadioButton();
        dosTirosCheck = new javax.swing.JRadioButton();
        cantidadDeJugadoresCombo = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nombreNuevaPartidaTxt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        nombreJugadorLabel.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        nombreJugadorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombreJugadorLabel.setAlignmentX(5.0F);
        nombreJugadorLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        unirseAPartidaBtn.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        unirseAPartidaBtn.setText("UNIRSE A PARTIDA ALEATORIA");

        buscarPartidaBtn.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        buscarPartidaBtn.setText("BUSCAR PARTIDA");
        buscarPartidaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarPartidaBtnActionPerformed(evt);
            }
        });

        crearPartidaBtn.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        crearPartidaBtn.setText("CREAR PARTIDA");
        crearPartidaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearPartidaBtnActionPerformed(evt);
            }
        });

        tresTirosCheck.setText("TRES TIROS");
        tresTirosCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tresTirosCheckActionPerformed(evt);
            }
        });

        dosTirosCheck.setText("DOS TIROS-UN VOLTEO");
        dosTirosCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dosTirosCheckActionPerformed(evt);
            }
        });

        cantidadDeJugadoresCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2", "3", "4", "5" }));

        jLabel1.setText("CANTIDAD JUGADORES");

        jLabel2.setText("NOMBRE: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nombreJugadorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(unirseAPartidaBtn)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buscarPartidaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(buscarPartidaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(crearPartidaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tresTirosCheck)
                                    .addComponent(dosTirosCheck))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(cantidadDeJugadoresCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nombreNuevaPartidaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(nombreJugadorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(unirseAPartidaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buscarPartidaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(buscarPartidaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(crearPartidaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(tresTirosCheck)
                                .addGap(1, 1, 1)
                                .addComponent(dosTirosCheck))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel1)
                                .addGap(7, 7, 7)
                                .addComponent(cantidadDeJugadoresCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(nombreNuevaPartidaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tresTirosCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tresTirosCheckActionPerformed
        tresTirosCheck.setSelected(true);
        dosTirosCheck.setSelected(false);
    }//GEN-LAST:event_tresTirosCheckActionPerformed

    private void dosTirosCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dosTirosCheckActionPerformed
        tresTirosCheck.setSelected(false);
        dosTirosCheck.setSelected(true);
    }//GEN-LAST:event_dosTirosCheckActionPerformed

    private void buscarPartidaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarPartidaBtnActionPerformed
        if(buscarPartidaTxt.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane,"DEBE INTRODUCIR UN NOMBRE DE PARTIDA A BUSCAR");
        }else{
           //BUSCAR PARTIDA
        }
    }//GEN-LAST:event_buscarPartidaBtnActionPerformed

    private void crearPartidaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearPartidaBtnActionPerformed
        if(!tresTirosCheck.isSelected()&&!dosTirosCheck.isSelected()){
            JOptionPane.showMessageDialog(rootPane,"DEBE SELECCIONAR UN TIPO DE JUEGO");
        }else if(nombreNuevaPartidaTxt.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane,"DEBE PONER UN NOMRE A LA PARTIDA");
        }else{
            String nombreNuevapartida=nombreNuevaPartidaTxt.getText();
            String cantidadMaximaJugadores=cantidadDeJugadoresCombo.getSelectedItem().toString();
            String tipoDeJuego=tresTirosCheck.isSelected()?
                    Constantes.PARTIDA_TRES_TIROS:Constantes.PARTIDA_DOS_TIROS;
            
            cliente.sendMensaje(Constantes.SALA+Constantes.NUEVA_PARTIDA+
            "_"+nombreNuevapartida+"_"+cantidadMaximaJugadores+"_"+tipoDeJuego);
        }
    }//GEN-LAST:event_crearPartidaBtnActionPerformed

    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(FormClienteSala.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormClienteSala.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormClienteSala.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormClienteSala.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormClienteSala("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscarPartidaBtn;
    private javax.swing.JTextField buscarPartidaTxt;
    private javax.swing.JComboBox<String> cantidadDeJugadoresCombo;
    private javax.swing.JButton crearPartidaBtn;
    private javax.swing.JRadioButton dosTirosCheck;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel nombreJugadorLabel;
    private javax.swing.JTextField nombreNuevaPartidaTxt;
    private javax.swing.JRadioButton tresTirosCheck;
    private javax.swing.JButton unirseAPartidaBtn;
    // End of variables declaration//GEN-END:variables
}
